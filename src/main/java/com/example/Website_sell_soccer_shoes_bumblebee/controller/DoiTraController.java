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
import java.util.*;
import java.util.stream.Collectors;

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

    private UUID idCTSP = null;
    private String  maHoaDon = null;
    private UUID idDoiTra = null;
    private NhanVien nhanVien = null;
    private String nameNhanVien = "";
    private List<ChiTietSanPham> listCTSP = null;
    private UUID idHoaDon = null;

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
        model.addAttribute("listDuDK",hoaDonService.danhSachHDDuDK());
        model.addAttribute("listDoiTra",doiTraService.listHuyDoiTra());
        return "/admin/index";
    }

    @GetMapping("/bumblebee/doi-hang/list-thanh-cong")
    public String danhSachThanhCong(Model model){
        model.addAttribute("view","../doi-tra/danh-sach-doi-tra.jsp");
        model.addAttribute("listDuDK",hoaDonService.danhSachHDDuDK());
        model.addAttribute("listDoiTra",doiTraService.listDoiTraThanhCong());
        return "/admin/index";
    }

    @GetMapping("/bumblebee/don-hang/{idDoiTra}")
    public String chiTietDonHang(Model model,@PathVariable("idDoiTra")UUID idDoiTra){
        model.addAttribute("view","../doi-tra/doi-hang.jsp");
        List<HoaDonChiTiet> listSPMuonDoi =hoaDonChiTietService.listHDCTByMaHD(this.maHoaDon);
        model.addAttribute("listHDCT", listSPMuonDoi);
        List<DoiTraChiTiet> listDoiTraCT = doiTraChiTietService.listDoiTraCTById(idDoiTra);
        model.addAttribute("listDoiTraCT",listDoiTraCT);
        getTaiKhoan(model);
        return "/admin/index";
    }

    @GetMapping("/bumblebee/don-hang/searchByQr/{maHD}")
    public String searchHoaDon(Model model, @PathVariable("maHD")String maHD){
        model.addAttribute("view","../doi-tra/doi-hang.jsp");
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
    public String taoDoiTra(Model model,@PathVariable("maHD")String maHD) throws ParseException {
        model.addAttribute("view","../doi-tra/doi-hang.jsp");
        DoiTra doiTra = new DoiTra();
        HoaDon hoaDon = hoaDonService.searchHoaDon(maHD);
        createMaDoiTraAuto(doiTra,hoaDon);
        this.maHoaDon = maHD;
        this.idDoiTra = doiTra.getId();
        return "redirect:/bumblebee/don-hang/"+ this.idDoiTra;
    }

    private void createDoiTraCT(DoiTra doiTra,String lydo){
        for (ChiTietSanPham ctsp : listCTSP){
            DoiTraChiTiet doiTraChiTiet = new DoiTraChiTiet();
            HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getHDCTDoiTra(this.idHoaDon,ctsp.getId());
            doiTraChiTiet.setChiTietSanPham(ctsp);
            doiTraChiTiet.setHoaDonChiTiet(hoaDonChiTiet);
            doiTraChiTiet.setSoLuong(1);
            doiTraChiTiet.setDoiTra(doiTra);
            doiTraChiTiet.setLyDoDoiTra(lydo);
            doiTraChiTiet.setDonGia(ctsp.getGiaBan());
            doiTraChiTiet.setTrangThai(2);
            doiTraChiTietService.saveDoiTraCT(doiTraChiTiet);
        }
        this.idDoiTra = doiTra.getId();
    }

    private static int maDoiTra = 1;
    private void createMaDoiTraAuto(DoiTra doiTra,HoaDon hoaDon) throws ParseException {
        String formatDoiTra = "DT" + String.format("%07d",maDoiTra);
        DoiTra checkMaDoiTra = doiTraService.getDoiTraByMa(formatDoiTra);
        if (checkMaDoiTra != null){
            String maDoiTraMax = doiTraService.maxMaDoiTra();
            maDoiTra = Integer.valueOf(maDoiTraMax.substring(2));
            maDoiTra++;
            String formatSoMa = "DT" + String.format("%07d",maDoiTra);
            doiTra.setMaDoiTra(formatSoMa);
        }else {
            doiTra.setMaDoiTra(formatDoiTra);
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String format = sdf.format(date);
        doiTra.setNgayDoiTra(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(format));
        doiTra.setHoaDon(hoaDon);
        doiTra.setNhanVien(nhanVien);
        doiTraService.saveDoiTra(doiTra);
        this.idHoaDon = hoaDon.getId();
    }
    @RequestMapping("/bumblebee/don-hang/create-doi-tra/{idSanPham}")
    public String createDoiTra(Model model,@PathVariable("idSanPham")UUID idSanPham){
        model.addAttribute("view","../doi-tra/doi-hang.jsp");
//        DoiTra checkDoiTra = doiTraService.getOneDoiTra(this.maHoaDon);
//        if (checkDoiTra != null){
//            createDoiTraCT(idSanPham,checkDoiTra);
//        }else {
//            DoiTra doiTra = new DoiTra();
//            HoaDon hoaDon = hoaDonService.searchHoaDon(this.maHoaDon);
//            createDoiTraCT(idSanPham,doiTra);
//        }
        return "redirect:/bumblebee/don-hang/"+ this.maHoaDon;
    }

    @RequestMapping("/bumblebee/don-hang/create-tra-hang")
    public String createTraHang(Model model,
                                @RequestParam(name = "idListCartDetail", required = false) String idListCartDetail,
                                @RequestParam(name = "lyDoDoiTra")String lyDoDoiTra){
        model.addAttribute("view","../doi-tra/doi-hang.jsp");
        List<UUID> idCartUUIDList = Arrays.asList(idListCartDetail.split(","))
                .stream()
                .map(UUID::fromString)
                .collect(Collectors.toList());
        listCTSP = new ArrayList<>();
        for (UUID id : idCartUUIDList) {
            ChiTietSanPham ctkm = chiTietSanPhamService.getOne(id);
            listCTSP.add(ctkm);
        }
        DoiTra checkDoiTra = doiTraService.getOneDoiTra(this.maHoaDon);
        if (checkDoiTra != null){
            checkDoiTra.setTrangThai(2);
            doiTraService.saveDoiTra(checkDoiTra);
            createDoiTraCT(checkDoiTra,lyDoDoiTra);
            HoaDon hoaDon = hoaDonService.searchHoaDon(this.maHoaDon);
            hoaDon.setTrangThai(7);
            hoaDonService.saveHoaDon(hoaDon);
            for (ChiTietSanPham ctsp : listCTSP){
                HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getHDCTDoiTra(this.idHoaDon,ctsp.getId());
                hoaDonChiTiet.setTrangThai(0);
            }
        }else {
            DoiTra doiTra = new DoiTra();
            HoaDon hoaDon = hoaDonService.searchHoaDon(this.maHoaDon);
            checkDoiTra.setTrangThai(2);
            doiTraService.saveDoiTra(checkDoiTra);
            createDoiTraCT(doiTra,lyDoDoiTra);
            hoaDon.setTrangThai(7);
            hoaDonService.saveHoaDon(hoaDon);
            for (ChiTietSanPham ctsp : listCTSP){
                HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getHDCTDoiTra(this.idHoaDon,ctsp.getId());
                hoaDonChiTiet.setTrangThai(0);
            }
        }
        return "redirect:/bumblebee/doi-hang/list";
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

    @RequestMapping("/bumblebee/don-hang/huy-doi-tra")
    public String huyDoiTra(){
        doiTraService.huyDoiTra(this.idDoiTra);
        return "redirect:/bumblebee/doi-hang/list";
    }
}
