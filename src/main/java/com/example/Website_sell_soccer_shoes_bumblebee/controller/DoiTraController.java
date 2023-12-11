package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.dto.ChiTietSanPhamCustom;
import com.example.Website_sell_soccer_shoes_bumblebee.dto.ChiTietSanPhamDto;
import com.example.Website_sell_soccer_shoes_bumblebee.dto.DoiTraChiTietCustom;
import com.example.Website_sell_soccer_shoes_bumblebee.dto.HoaDonChiTietCustom;
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
    private String maHoaDon = null;
    private UUID idDoiTra = null;
    private NhanVien nhanVien = null;
    private String nameNhanVien = "";
    private List<ChiTietSanPham> listCTSP = null;
    private UUID idHoaDon = null;
    private List<Integer> listSoLuong = new ArrayList<>();

    @GetMapping("/bumblebee/doi-hang/list-san-pham-loi")
    public String danhSachSanPhamLoi(Model model) {
        model.addAttribute("view", "../doi-tra/danh-sach-san-pham-loi.jsp");
        model.addAttribute("listDoiTra", doiTraChiTietService.findSanPhamLoi());
        return "/admin/index";
    }

    @GetMapping("/bumblebee/doi-hang/list-doi-hang")
    public String danhSachHuy(Model model, @RequestParam(defaultValue = "0", name = "page") Integer page) {
        model.addAttribute("view", "../doi-tra/danh-sach-doi-tra.jsp");
//        model.addAttribute("listDuDK", hoaDonService.danhSachHDDuDK());
        model.addAttribute("listDoiTra", doiTraService.listHuyDoiTra(page));
        return "/admin/index";
    }

    @GetMapping("/bumblebee/doi-hang/list-tra-hang")
    public String danhSachThanhCong(Model model, @RequestParam(defaultValue = "0", name = "page") Integer page) {
        model.addAttribute("view", "../doi-tra/danh-sach-doi-tra.jsp");
        model.addAttribute("listDoiTra", doiTraService.listDoiTraThanhCong(page));
        return "/admin/index";
    }

//    @GetMapping("/bumblebee/don-hang/{maHD}")
//    public String chiTietDonHang(Model model, @PathVariable("maHD") String maHoaDon) {
//        model.addAttribute("view", "../doi-tra/doi-hang.jsp");
//        List<HoaDonChiTiet> listSPMuonDoi = hoaDonChiTietService.listHDCTByMaHD(maHoaDon);
//        model.addAttribute("listHDCT", listSPMuonDoi);
//        model.addAttribute("listDoiTraCT",doiTraChiTietService.listDoiTraCTByHoaDon(this.maHoaDon));
//
//        getTaiKhoan(model);
//        return "/admin/index";
//    }

    @GetMapping("/bumblebee/don-hang/tao-doi-tra/{maHD}")
    public String hienThiDoiTra(Model model, @ModelAttribute("doiTraCT") DoiTraChiTiet doiTraCT, @PathVariable("maHD") String maHD) {
        model.addAttribute("view", "../doi-tra/tra-hang.jsp");
        HoaDon hoaDon = hoaDonService.searchHoaDon(maHD);
        model.addAttribute("hoaDonMua", hoaDon);
        List<HoaDonChiTiet> lstHoaDonCT = hoaDonChiTietService.listHDCTByMaHD(maHD);
        model.addAttribute("listHDCT", lstHoaDonCT);
        model.addAttribute("listDoiTraCT", doiTraChiTietService.listDoiTraCTByHoaDon(this.maHoaDon));
        Double sumMoney = hoaDonChiTietService.getTotalMoney(lstHoaDonCT);
        model.addAttribute("sumMoney", sumMoney);
        this.maHoaDon = maHD;
        return "/admin/index";
    }

    @GetMapping("/bumblebee/don-hang/{maHD}")
    public String searchHoaDon(Model model, @PathVariable("maHD") String maHD) {
        getTaiKhoan(model);
        List<HoaDon> listHoaDonDuDK = hoaDonService.danhSachHDDuDK();
        if (listHoaDonDuDK != null) {
            for (HoaDon hd : listHoaDonDuDK) {
                if (hd.getMaHoaDon().equals(maHD)) {
                    return "redirect:/bumblebee/don-hang/tao-doi-tra/" + maHD;
                }
            }
            model.addAttribute("notFound", "Khong tim thay");
            return "redirect:/bumblebee/doi-hang/list-tra-hang";
        } else {
            model.addAttribute("notFound", "Khong tim thay");
            return "redirect:/bumblebee/doi-hang/list-tra-hang";
        }
    }

    @GetMapping("/bumblebee/don-hang/list-sp/{id}")
    public ResponseEntity<?> getListByMa(Model model, @PathVariable("id") UUID id) {
        ChiTietSanPham ctsp = chiTietSanPhamService.getOne(id);
        List<ChiTietSanPhamCustom> lstSanPham = chiTietSanPhamService.listSPCungLoai(ctsp.getGiaBan());
        return ResponseEntity.ok(lstSanPham);
    }

    @GetMapping("/bumblebee/don-hang/list-hoa-don-ct/{id}")
    public ResponseEntity<?> listHoaDonCTCustom(Model model, @PathVariable("id") UUID id) {
        List<HoaDonChiTietCustom> lstHoaDonCT = hoaDonChiTietService.listHoaDonCTCustom(id);
        return ResponseEntity.ok(lstHoaDonCT);
    }

    @GetMapping("/bumblebee/don-hang/list-doi-tra-ct/{id}")
    public ResponseEntity<?> listDoiTraCTCustom(Model model, @PathVariable("id") UUID id) {
        List<DoiTraChiTietCustom> lstDoiTraCT = doiTraChiTietService.listDoiTraCTCustom(id);
        return ResponseEntity.ok(lstDoiTraCT);
    }

    private List<String> listlyDo = new ArrayList<>();
    private void createDoiTraCT(DoiTra doiTra, List<String> lydo) {
        for (int h = 0; h < listCTSP.size(); h++) {
            DoiTraChiTiet doiTraChiTiet = new DoiTraChiTiet();
            HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getHDCTDoiTra(this.idHoaDon, listCTSP.get(h).getId());
            doiTraChiTiet.setChiTietSanPham(listCTSP.get(h));
            doiTraChiTiet.setHoaDonChiTiet(hoaDonChiTiet);
            doiTraChiTiet.setSoLuong(listSoLuong.get(h));
            doiTraChiTiet.setDoiTra(doiTra);
            doiTraChiTiet.setLyDoDoiTra(lydo.get(h));
            doiTraChiTiet.setDonGia(listCTSP.get(h).getGiaBan());
            doiTraChiTiet.setTrangThai(2);
            doiTraChiTietService.saveDoiTraCT(doiTraChiTiet);
        }
//        for (ChiTietSanPham ctsp : listCTSP) {
//            DoiTraChiTiet doiTraChiTiet = new DoiTraChiTiet();
//            HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getHDCTDoiTra(this.idHoaDon, ctsp.getId());
//            doiTraChiTiet.setChiTietSanPham(ctsp);
//            doiTraChiTiet.setHoaDonChiTiet(hoaDonChiTiet);
//            doiTraChiTiet.setSoLuong(ctsp.getSoLuong());
//            doiTraChiTiet.setDoiTra(doiTra);
//            doiTraChiTiet.setLyDoDoiTra(lydo.get());
//            doiTraChiTiet.setDonGia(ctsp.getGiaBan());
//            doiTraChiTiet.setTrangThai(2);
//            doiTraChiTietService.saveDoiTraCT(doiTraChiTiet);
//        }
        this.idDoiTra = doiTra.getId();
    }

    private static int maDoiTra = 1;

    private void createMaDoiTraAuto(DoiTra doiTra, HoaDon hoaDon) throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String format = sdf.format(date);
        doiTra.setNgayDoiTra(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(format));
        doiTra.setHoaDon(hoaDon);
        doiTra.setNhanVien(nhanVien);
        doiTraService.saveDoiTra(doiTra);
        this.idHoaDon = hoaDon.getId();
    }

    private void createDoiSanPham(UUID idSanPham, DoiTra doiTra, HoaDon hoaDon) {
        DoiTraChiTiet doiTraChiTiet = new DoiTraChiTiet();
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getHDCTDoiTra(hoaDon.getId(), idCTSP);
        ChiTietSanPham ctsp = chiTietSanPhamService.getOne(idSanPham);
        doiTraChiTiet.setChiTietSanPham(ctsp);
        doiTraChiTiet.setHoaDonChiTiet(hoaDonChiTiet);
        doiTraChiTiet.setSoLuong(1);
        doiTraChiTiet.setDoiTra(doiTra);
        doiTraChiTiet.setDonGia(ctsp.getGiaBan());
        doiTraChiTiet.setTrangThai(1);
        doiTraChiTietService.saveDoiTraCT(doiTraChiTiet);
        this.idDoiTra = doiTra.getId();
    }


    @RequestMapping("/bumblebee/don-hang/update-so-Luong")
    public String updateSoLuong(@RequestParam("soLuong") Integer soLuong) {
        DoiTraChiTiet doiTraChiTiet = doiTraChiTietService.getDoiTraCT(this.idDoiTra, idCTSP);
        doiTraChiTiet.setSoLuong(soLuong);
        return "redirect:/bumblebee/don-hang/" + this.maHoaDon;
    }

    @RequestMapping("/bumblebee/don-hang/create-doi-tra/{idSanPham}")
    public String createDoiTra(Model model, @PathVariable("idSanPham") UUID idSanPham
    ) {
        model.addAttribute("view", "../doi-tra/tra-hang.jsp");
        DoiTra checkDoiTra = doiTraService.getOneDoiTra(this.maHoaDon);
        HoaDon hoaDon = hoaDonService.searchHoaDon(this.maHoaDon);

        if (checkDoiTra != null) {
            createDoiSanPham(idSanPham, checkDoiTra, hoaDon);
        } else {
            DoiTra doiTra = new DoiTra();
            doiTra.setHoaDon(hoaDon);
            doiTra.setNhanVien(nhanVien);
            doiTraService.saveDoiTra(doiTra);
            this.idHoaDon = hoaDon.getId();
            createDoiSanPham(idSanPham, doiTra, hoaDon);
            idCTSP = idSanPham;
        }
        return "redirect:/bumblebee/don-hang/" + this.maHoaDon;
    }

    @RequestMapping("/bumblebee/don-hang/xac-nhan-doi")
    public String doiHang(@RequestParam("lyDoDoiTra") String lyDoDoiTra,
                          @RequestParam("soLuong") Integer soLuong) throws ParseException {

        DoiTra doiTra = doiTraService.getOneDoiTra(this.maHoaDon);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String format = sdf.format(date);
        doiTra.setNgayDoiTra(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(format));
        doiTra.setTrangThai(1);
        doiTraService.saveDoiTra(doiTra);
        HoaDon hoaDon = hoaDonService.searchHoaDon(this.maHoaDon);
        hoaDon.setTrangThai(7);
        hoaDonService.saveHoaDon(hoaDon);
        List<DoiTraChiTiet> lstDoiTraCT = doiTraChiTietService.listDoiTraCTById(this.idDoiTra);
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getHDCTDoiTra(hoaDon.getId(), idCTSP);
        hoaDonChiTiet.setTrangThai(0);
        DoiTraChiTiet doiTraChiTiet = doiTraChiTietService.getDoiTraCT(this.idDoiTra, idCTSP);
        doiTraChiTiet.setHoaDonChiTiet(hoaDonChiTiet);
        doiTraChiTiet.setLyDoDoiTra(lyDoDoiTra);
        doiTraChiTiet.setSoLuong(soLuong);
        doiTraChiTietService.saveDoiTraCT(doiTraChiTiet);
        hoaDonChiTietService.saveHoaDonCT(hoaDonChiTiet);
        if (lyDoDoiTra.equals("Sản phẩm lỗi")) {
            return "redirect:/bumblebee/doi-hang/list-doi-hang";
        } else {
            ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getOne(idCTSP);
            chiTietSanPhamService.updateDelete(chiTietSanPham.getId(), soLuong);
        }
        return "redirect:/bumblebee/doi-hang/list-doi-hang";
    }

    @RequestMapping("/bumblebee/don-hang/create-tra-hang")
    public String createTraHang(Model model,
                                @RequestParam(name = "idListCartDetail", required = false) String idListCartDetail,
                                @RequestParam("lyDoDoiTra") String [] lyDoDoiTra,
                                @RequestParam("soLuong") Integer[] soLuong) throws ParseException {

        model.addAttribute("view", "../doi-tra/doi-hang.jsp");
        DoiTra doiTra = new DoiTra();
        HoaDon hoaDon = hoaDonService.searchHoaDon(this.maHoaDon);
        createMaDoiTraAuto(doiTra, hoaDon);
        this.idDoiTra = doiTra.getId();
        List<UUID> idCartUUIDList = Arrays.asList(idListCartDetail.split(","))
                .stream()
                .map(UUID::fromString)
                .collect(Collectors.toList());
        listCTSP = new ArrayList<>();
        listSoLuong = Arrays.asList(soLuong)
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
       listlyDo = Arrays.asList(lyDoDoiTra)
                .stream()
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
        for (int i = 0; i < idCartUUIDList.size(); i++) {
            ChiTietSanPham ctkm = chiTietSanPhamService.getOne(idCartUUIDList.get(i));
            listCTSP.add(ctkm);
        }
//        for (UUID id : idCartUUIDList) {
//            for (Integer sl : listSoLuong) {
//                ChiTietSanPham ctkm = chiTietSanPhamService.getOne(id);
//                ctkm.setSoLuong(sl.intValue());
//                listCTSP.add(ctkm);
//            }
//        }
        DoiTra checkDoiTra = doiTraService.getOneDoiTra(this.maHoaDon);
        checkDoiTra.setTrangThai(2);
        doiTraService.saveDoiTra(checkDoiTra);
        createDoiTraCT(checkDoiTra, listlyDo);
        hoaDon.setTrangThai(7);
        hoaDonService.saveHoaDon(hoaDon);
        for (int j = 0; j < listCTSP.size(); j++) {
            HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getHDCTDoiTra(this.idHoaDon, listCTSP.get(j).getId());
            hoaDonChiTiet.setTrangThai(0);
            hoaDonChiTietService.saveHoaDonCT(hoaDonChiTiet);
            if (listlyDo.get(j).equals("Sản phẩm lỗi")) {
                return "redirect:/bumblebee/doi-hang/list-tra-hang";
            } else {
                ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getOne(listCTSP.get(j).getId());
                Integer soLuongTra = listSoLuong.get(j);
                chiTietSanPhamService.updateDelete(chiTietSanPham.getId(), soLuongTra);
            }
        }
//        for (ChiTietSanPham ctsp : listCTSP) {
//            HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getHDCTDoiTra(this.idHoaDon, ctsp.getId());
//            hoaDonChiTiet.setTrangThai(0);
//            hoaDonChiTietService.saveHoaDonCT(hoaDonChiTiet);
//            if (!(lyDoDoiTra.equals("Sản phẩm lỗi"))) {
//                for (Integer sl : listSoLuong){
//                    ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getOne(ctsp.getId());
//                    chiTietSanPhamService.updateDelete(chiTietSanPham.getId(), );
//                }
//            }
//        }
        return "redirect:/bumblebee/doi-hang/list-tra-hang";
    }

    private void getTaiKhoan(Model model) {
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        nhanVien = nhanVienService.getOne(taiKhoan.getId());
        model.addAttribute("username", taiKhoan.getUsername());
        nameNhanVien = nhanVien.getHo() + " " + nhanVien.getTenDem() + " " + nhanVien.getTen();
        model.addAttribute("fullNameStaff", nameNhanVien);
    }


    @RequestMapping("/bumblebee/don-hang/remove-doi-tra/{id}")
    public String removeDoiTraCT(@PathVariable("id") UUID id) {
        doiTraChiTietService.removeDoiTraCT(id);
        return "redirect:/bumblebee/don-hang/" + this.maHoaDon;
    }

    @RequestMapping("/bumblebee/doi-hang/list-tra-hang")
    public String huyDoiTra() {
        doiTraService.huyDoiTra(this.idDoiTra);
        return "redirect:/bumblebee/doi-hang/list";
    }

    @RequestMapping("/bumblebee/doi-tra/chi-tiet/{id}")
    public String chiTietDoitra(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("view", "../doi-tra/detail-doi-tra.jsp");
        List<HoaDonChiTiet> lstHoaDonCT = hoaDonChiTietService.getListHoaDonCTByIdHoaDon(id);
        List<DoiTraChiTiet> lstSanPhamTra= doiTraChiTietService.listDoiTraCTByIdHoaDon(id);
        List<DoiTraChiTiet> lstSanPhamDoi = doiTraChiTietService.listSanPhamDoi(id);
        HoaDon hoaDon = hoaDonService.getOne(id);
        model.addAttribute("hoaDon", hoaDon);
        model.addAttribute("listHoaDonCT", lstHoaDonCT);
        model.addAttribute("listDoiTraCT", lstSanPhamTra);
        model.addAttribute("listSanPhamDoi", lstSanPhamDoi);
        return "admin/index";
    }
}
