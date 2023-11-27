package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.dto.ChiTietSanPhamCustom;
import com.example.Website_sell_soccer_shoes_bumblebee.dto.ChiTietSanPhamDto;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.*;
import com.example.Website_sell_soccer_shoes_bumblebee.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class DoiTraController {

    @Autowired
    HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    HoaDonService hoaDonService;

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    DoiTraService doiTraService;

    @Autowired
    DoiTraChiTietService doiTraChiTietService;

    @Autowired
    NhanVienService nhanVienService;

    @Autowired
    HttpSession session;


    @Data
    public static class SearchForm{
        String key = "";
    }

    private UUID idCTSP = null;
    private String  maHoaDon = null;
    private UUID idDoiTra = null;
    private NhanVien nhanVien = null;
    private String nameNhanVien = "";

    @GetMapping("/bumblebee/doi-hang/list")
    public String danhSachCho(Model model){
        model.addAttribute("view","../doi-tra/danh-sach-doi-tra.jsp");
        model.addAttribute("listDuDK",hoaDonService.danhSachHDDuDK());
        model.addAttribute("listDoiTra",doiTraService.listChoXacNhan());
        getTaiKhoan(model);
        return "/admin/index";
    }

    @GetMapping("/bumblebee/doi-hang/list-huy")
    public String danhSachHuy(Model model){
        model.addAttribute("view","../doi-tra/danh-sach-doi-tra.jsp");
        model.addAttribute("listDoiTra",doiTraService.listHuyDoiTra());
        return "/admin/index";
    }

    @GetMapping("/bumblebee/doi-hang/list-thanh-cong")
    public String danhSachThanhCong(Model model){
        model.addAttribute("view","../doi-tra/danh-sach-doi-tra.jsp");
        model.addAttribute("listDoiTra",doiTraService.listDoiTraThanhCong());
        return "/admin/index";
    }

    @GetMapping("/bumblebee/doi-hang")
    public String doiHang(Model model){
        model.addAttribute("view","../doi-tra/doi-hang.jsp");
        model.addAttribute("SearchForm",new SearchForm());
        return "/admin/index";
    }

    @GetMapping("/bumblebee/don-hang/search")
    public String getDonHang(Model model, @ModelAttribute("SearchForm")SearchForm searchForm){
        model.addAttribute("view","../doi-tra/doi-hang.jsp");
        List<HoaDonChiTiet> listSPMuonDoi = hoaDonChiTietService.hoaDonMuonDoi(searchForm.key);
        model.addAttribute("listHDCT", listSPMuonDoi);
        return "/admin/index";
    }

    @GetMapping("/bumblebee/don-hang/{maHoaDon}")
    public String chiTietDonHang(Model model,@PathVariable("maHoaDon")String maHoaDon){
        model.addAttribute("view","../doi-tra/doi-hang.jsp");
        List<HoaDonChiTiet> listSPMuonDoi =hoaDonChiTietService.listHDCTByMaHD(maHoaDon);
        model.addAttribute("listHDCT", listSPMuonDoi);
        this.maHoaDon = maHoaDon;
        List<DoiTraChiTiet> listDoiTraCT = doiTraChiTietService.listDoiTraCTById(this.idDoiTra);
        model.addAttribute("listDoiTraCT",listDoiTraCT);
        getTaiKhoan(model);
        return "/admin/index";
    }

    @GetMapping("/bumblebee/don-hang/searchByQr/{maHD}")
    public String searchHoaDon(Model model, @PathVariable("maHD")String maHD){
        model.addAttribute("view","../doi-tra/doi-hang.jsp");
        model.addAttribute("SearchForm",new SearchForm());
        List<HoaDonChiTiet> lstHoaDonCT = hoaDonChiTietService.listHDCTByMaHD(maHD);
        model.addAttribute("listHDCT",lstHoaDonCT);
        return "/admin/index";
    }

    @GetMapping("/bumblebee/don-hang/list-sp/{id}")
    public ResponseEntity<?> getListByMa(Model model, @PathVariable("id") UUID id){
        ChiTietSanPham ctsp = chiTietSanPhamService.getOne(id);
        List<ChiTietSanPhamCustom> lstSanPham = chiTietSanPhamService.listSPCungLoai(ctsp.getGiaBan());
        return ResponseEntity.ok(lstSanPham);
    }

    @RequestMapping("/bumblebee/don-hang/tao-doi-tra/{maHD}")
    public String taoDoiTra(Model model,@PathVariable("maHD")String maHD){
        model.addAttribute("view","../doi-tra/doi-hang.jsp");
        DoiTra doiTra = new DoiTra();
        HoaDon hoaDon = hoaDonService.searchHoaDon(maHD);
        doiTra.setHoaDon(hoaDon);
        doiTra.setNhanVien(nhanVien);
        doiTraService.saveDoiTra(doiTra);
        this.maHoaDon = maHD;
        return "redirect:/bumblebee/don-hang/"+ maHD;
    }

    private void createDoiTraCT(UUID idSanPham,DoiTra doiTra){
        DoiTraChiTiet doiTraChiTiet = new DoiTraChiTiet();

        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getHDCTDoiTra(this.maHoaDon,idCTSP);
        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getOne(idSanPham);
        doiTraChiTiet.setChiTietSanPham(chiTietSanPham);
        doiTraChiTiet.setHoaDonChiTiet(hoaDonChiTiet);
        doiTraChiTiet.setSoLuong(1);
        doiTraChiTiet.setDoiTra(doiTra);
        doiTraChiTiet.setDonGia(chiTietSanPham.getGiaBan());
        doiTraChiTietService.saveDoiTraCT(doiTraChiTiet);
        this.idDoiTra = doiTra.getId();
    }

//    private static int maDoiTra = 1;
//    private void createMaDoiTraAuto(DoiTra doiTra,HoaDon hoaDon){
//        String formatDoiTra = "DT" + String.format("%07d",maDoiTra);
//        DoiTra checkMaDoiTra = doiTraService.getDoiTraByMa(formatDoiTra);
//        if (checkMaDoiTra != null){
//            String maDoiTraMax = doiTraService.maxMaDoiTra();
//            maDoiTra = Integer.valueOf(maDoiTraMax.substring(2));
//            maDoiTra++;
//            String formatSoMa = "DT" + String.format("%07d",maDoiTra);
//            doiTra.setMaDoiTra(formatSoMa);
//        }else {
//            doiTra.setMaDoiTra(formatDoiTra);
//        }
//        doiTra.setHoaDon(hoaDon);
//        doiTra.setNhanVien(nhanVien);
//        doiTraService.saveDoiTra(doiTra);
//    }
    @RequestMapping("/bumblebee/don-hang/create-doi-tra/{idSanPham}")
    public String createDoiTra(Model model,@PathVariable("idSanPham")UUID idSanPham){
        model.addAttribute("view","../doi-tra/doi-hang.jsp");
        DoiTra checkDoiTra = doiTraService.getOneDoiTra(this.maHoaDon);
        if (checkDoiTra != null){
            createDoiTraCT(idSanPham,checkDoiTra);
        }else {
            DoiTra doiTra = new DoiTra();
            HoaDon hoaDon = hoaDonService.searchHoaDon(this.maHoaDon);
            createDoiTraCT(idSanPham,doiTra);
        }
        return "redirect:/bumblebee/don-hang/"+ this.maHoaDon;
    }

    @RequestMapping("/bumblebee/don-hang/create-tra-hang/{idSanPham}")
    public String createTraHang(Model model,@PathVariable("idSanPham")UUID idSanPham){
        model.addAttribute("view","../doi-tra/doi-hang.jsp");
        DoiTra checkDoiTra = doiTraService.getOneDoiTra(this.maHoaDon);
        if (checkDoiTra != null){
            createDoiTraCT(idSanPham,checkDoiTra);
        }else {
            DoiTra doiTra = new DoiTra();
            HoaDon hoaDon = hoaDonService.searchHoaDon(this.maHoaDon);
            createDoiTraCT(idSanPham,doiTra);
        }
        return "redirect:/bumblebee/don-hang/"+ this.maHoaDon;
    }
    private void getTaiKhoan(Model model) {
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        nhanVien = nhanVienService.getOne(taiKhoan.getId());
        model.addAttribute("username", taiKhoan.getUsername());
        nameNhanVien = nhanVien.getHo() + " " + nhanVien.getTenDem() + " " + nhanVien.getTen();
        model.addAttribute("fullNameStaff", nameNhanVien);
    }
    @RequestMapping("/bumblebee/don-hang/xac-nhan-doi")
    public String doiHang() throws ParseException {
        DoiTra doiTra = doiTraService.getOneDoiTra(this.maHoaDon);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String format = sdf.format(date);
        doiTra.setNgayDoiTra(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(format));
        doiTra.setTrangThai(2);
        doiTraService.saveDoiTra(doiTra);
        return "redirect:/bumblebee/doi-hang/list";
    }

    @RequestMapping("/bumblebee/don-hang/remove-doi-tra/{id}")
    public String removeDoiTraCT(@PathVariable("id")UUID id){
        doiTraChiTietService.removeDoiTraCT(id);
        return "redirect:/bumblebee/don-hang/"+ this.maHoaDon;
    }
}
