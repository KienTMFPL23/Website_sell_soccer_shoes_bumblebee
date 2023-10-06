package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.*;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.KhachHangRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/bumblebee/ban-hang-tai-quay")
public class BanHangTaiQuayController {

    @Autowired
    HoaDonService hoaDonService;

    @Autowired
    ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    HttpSession session;

    @Autowired
    NhanVienService nhanVienService;


    @Getter
    @Setter
    public static class SearchForm {
        String keyword = "";
    }

    private NhanVien nhanVien = null;
    private UUID idHoaDon = null;
    private UUID idHoaDonCT = null;
    private Double sumMoney = 0.0;
    //    private UUID idHoaDonCT = null;
//    private List<HoaDonChiTiet> dsHoaDonCT = null;
//    private Double sumMoney = 0.0;
//    private Integer soLuongTon = 0;
//    private ChiTietSanPham ctsp = null;
//    private List<ChiTietSanPham> danhSachSPSearch = null;

    //khach hang
    @Autowired
    KhachHangRepository khachHangRepository;

    @ModelAttribute("listKhachHang")
    List<KhachHang> listSP() {
        return khachHangRepository.findAll();
    }

    //ban hang
    @GetMapping("/sell")
    public String banHang(Model model) {
        model.addAttribute("view", "../ban_hang_tai_quay/index.jsp");
        model.addAttribute("listHoaDonCho", hoaDonService.listHoaDonCho());
        model.addAttribute("listSanPham", chiTietSanPhamService.getList());
        model.addAttribute("searchForm", new SearchForm());
//        model.addAttribute("sumMoney", sumMoney);
        model.addAttribute("hoaDon", new HoaDon());
        return "admin/index";
    }

    private void getTaiKhoan(Model model) {
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        nhanVien = nhanVienService.getOne(taiKhoan.getId());
        model.addAttribute("username", taiKhoan.getUsername());
        String fullname = nhanVien.getHo() + " " + nhanVien.getTenDem() + " " + nhanVien.getTen();
        model.addAttribute("fullNameStaff", fullname);
    }

    @RequestMapping("/create-hoadon")
    public String createHoaDon(Model model) throws ParseException {
        model.addAttribute("view", "../ban_hang_tai_quay/index.jsp");
        Integer soLuongHDCho = hoaDonService.listHoaDonCho().size();
        if (soLuongHDCho >= 5) {
            model.addAttribute("soLuongHD", soLuongHDCho);
            return "redirect:/bumblebee/ban-hang-tai-quay/sell";
        }
        hoaDonService.createHoaDon();
        return "redirect:/bumblebee/ban-hang-tai-quay/sell";
    }

    @RequestMapping("/delete-hoadon/{id}")
    public String deleteHoaDon(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("view", "../ban_hang_tai_quay/index.jsp");
        hoaDonService.deleteHoaDon(id);
        return "redirect:/bumblebee/ban-hang-tai-quay/sell";
    }

    @RequestMapping("/search-san-pham")
    public String timKiemSanPham() {

        return "admin/index";
    }

    @GetMapping("/searchSanPham")
    public String searchSanPham(Model model, @ModelAttribute("searchForm") SearchForm searchForm) {
        model.addAttribute("view", "../ban_hang_tai_quay/ban-hang.jsp");
        List<SanPham> danhSachSPSearch = sanPhamService.searchSanPham("" + searchForm.keyword + "");
        model.addAttribute("listSearch", danhSachSPSearch);
        return "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDon;
    }

    @GetMapping("/hoa-don-chi-tiet/{id}")
    public String hoaDonChiTiet(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("view", "../ban_hang_tai_quay/index.jsp");
        idHoaDon = id;
        model.addAttribute("searchForm", new SearchForm());
        model.addAttribute("listHoaDonCho", hoaDonService.listHoaDonCho());
        model.addAttribute("listSanPham", chiTietSanPhamService.getList());
        model.addAttribute("listHDCT", hoaDonChiTietService.getListHoaDonCTByIdHoaDon(id));
        model.addAttribute("idHoaDon", this.idHoaDon);
        getTaiKhoan(model);
        model.addAttribute("hoaDon", hoaDonService.getOne(id));
        List<HoaDonChiTiet> list = hoaDonChiTietService.getListHoaDonCTByIdHoaDon(id);
        sumMoney = hoaDonChiTietService.getTotalMoney(list);
        model.addAttribute("sumMoney", sumMoney);
        idHoaDonCT = id;
        return "/admin/index";
    }

    @RequestMapping("/add-gio-hang/{id}")
    public String themSanPham(Model model, @PathVariable("id") UUID id) {
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        HoaDon hoaDon = hoaDonService.getOne(idHoaDon);
//        if (idHoaDon == null) {
//            return "redirect:/bumblebee/ban-hang-tai-quay/sell";
//        }
        ChiTietSanPham sp = chiTietSanPhamService.getOne(id);
        HoaDonChiTiet sanPhamInHDCT = hoaDonChiTietService.getAndUpdateSanPhamInHDCT(this.idHoaDon, id);
        if (sanPhamInHDCT != null) {
            Integer soLuong = sp.getSoLuong() - 1;
            chiTietSanPhamService.updateSoLuongTon(id, soLuong);
            hoaDonChiTietService.saveHoaDonCT(hoaDonChiTiet);
            return "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDonCT;
        } else {
            hoaDonChiTiet.setHoaDon(hoaDon);
            hoaDonChiTiet.setChiTietSanPham(sp);
            hoaDonChiTiet.setSoLuong(1);
            hoaDonChiTiet.setDonGia(sp.getGiaBan());
            hoaDonChiTiet.setTrangThai(1);
            Integer soLuong = sp.getSoLuong() - 1;
            chiTietSanPhamService.updateSoLuongTon(id, soLuong);
            hoaDonChiTietService.saveHoaDonCT(hoaDonChiTiet);
        }
        return "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDonCT;
    }

    @RequestMapping("/delete-hdct/{id}")
    public String deleteHoaDonCT(Model model, @PathVariable("id") UUID id) {
//        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getOneHoaDon(id);
//        Integer soLuong = hoaDonChiTiet.getSoLuong();
//        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getOne(hoaDonChiTiet.getChiTietSanPham().getId());
//        Integer soLuongTon = chiTietSanPham.getSoLuong();
//        chiTietSanPhamService.updateSoLuongTon(chiTietSanPham.getId(), soLuongTon + soLuong);
//        hoaDonChiTietService.deleteHoaDonCT(id);
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getOneHoaDon(id);
//        Integer soLuong = hoaDonChiTiet.getSoLuong();
        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getOne(hoaDonChiTiet.getChiTietSanPham().getId());
//        Integer soLuongTon = chiTietSanPham.getSoLuong();
        chiTietSanPhamService.updateDelete(chiTietSanPham.getId(), hoaDonChiTiet.getSoLuong());
        hoaDonChiTietService.deleteHoaDonCT(id);
        return "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDon;
    }

    @RequestMapping("/update-cart/{id}")
    public String updateSLGioHang(@PathVariable("id") UUID id, int soLuong) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getOneHoaDon(id);
        if (hoaDonChiTiet != null) {
            hoaDonChiTiet.setSoLuong(soLuong);
            Integer soLuongMua = hoaDonChiTiet.getSoLuong();
            ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getOne(hoaDonChiTiet.getChiTietSanPham().getId());
            Integer soLuongTon = chiTietSanPham.getSoLuong();
//            chiTietSanPhamService.updateSoLuongTon(chiTietSanPham.getId(),soLuongTon+soLuongMua);
            hoaDonChiTietService.saveHoaDonCT(hoaDonChiTiet);
        }
        return "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDon;
    }

    @RequestMapping("/thanhtoan/{idHoaDon}")
    public String thanhToan(Model model, @PathVariable("idHoaDon") UUID id, @ModelAttribute("hoaDon") HoaDon hoaDon) throws ParseException {
        HoaDon hoaDonThanhToan = hoaDonService.getOne(idHoaDon);
        if (hoaDonThanhToan != null) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String format = sdf.format(date);
            hoaDonThanhToan.setNgayThanhToan(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(format));
            hoaDonThanhToan.setTenNguoiNhan(hoaDon.getTenNguoiNhan());
            hoaDonThanhToan.setNhanVien(nhanVien);
            hoaDonThanhToan.setTrangThai(1);
            hoaDonThanhToan.setGhiChu(hoaDon.getGhiChu());
            hoaDonService.saveHoaDon(hoaDonThanhToan);
            this.sumMoney = 0.0;
            this.idHoaDon = null;
        }
        return "redirect:/bumblebee/ban-hang-tai-quay/sell";
    }
}
