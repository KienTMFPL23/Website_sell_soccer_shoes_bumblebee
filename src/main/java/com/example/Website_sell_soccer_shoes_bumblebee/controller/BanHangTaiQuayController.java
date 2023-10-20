package com.example.Website_sell_soccer_shoes_bumblebee.controller;


import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChiTietSanPham;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDon;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDonChiTiet;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.ChiTietSanPhamRepo;
import com.example.Website_sell_soccer_shoes_bumblebee.service.ChiTietSanPhamService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.HoaDonChiTietService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.HoaDonService;
import com.itextpdf.forms.xfdf.Mode;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.*;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.KhachHangRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
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

    ChiTietSanPhamRepo chiTietSanPhamRepo;

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    HttpSession session;

    @Autowired
    NhanVienService nhanVienService;

    @Autowired
    KhachHangService khachHangService;


    @Getter
    @Setter
    public static class SearchForm {
        String keyword = "";
    }

    private NhanVien nhanVien = null;

    private UUID idHoaDon = null;
    private UUID idHoaDonCT = null;
    private Double sumMoney = 0.0;
    private static int maKhachHang = 1;
    //    private UUID idHoaDonCT = null;

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
        this.idHoaDon = null;
        this.sumMoney = 0.0;
        getTaiKhoan(model);
        model.addAttribute("idHoaDon", idHoaDon);
        model.addAttribute("sumMoney", sumMoney);
        model.addAttribute("khachHang", new KhachHang());


        model.addAttribute("searchForm", new SearchForm());
//        model.addAttribute("sumMoney", sumMoney);

        model.addAttribute("hoaDon", new HoaDon());

        return "ban_hang_tai_quay/ban-hang";
    }

    private void getTaiKhoan(Model model) {
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        nhanVien = nhanVienService.getOne(taiKhoan.getId());
        model.addAttribute("username", taiKhoan.getUsername());
        String fullname = nhanVien.getHo() + " " + nhanVien.getTenDem() + " " + nhanVien.getTen();
        model.addAttribute("fullNameStaff", fullname);
    }

    @GetMapping("/so-luong-hoa-don-cho")
    public ResponseEntity<?> soLuongHoaDon() {
        Integer soLuongHDC = hoaDonService.listHoaDonCho().size();
        return ResponseEntity.ok(soLuongHDC);
    }

    @RequestMapping("/create-hoadon")
    public String createHoaDon(Model model) throws ParseException {
        model.addAttribute("view", "../ban_hang_tai_quay/index.jsp");
        Integer soLuongHDCho = hoaDonService.listHoaDonCho().size();
        if (soLuongHDCho == 5) {
            model.addAttribute("soLuongHD", soLuongHDCho);
            return "redirect:/bumblebee/ban-hang-tai-quay/sell";
        }
        hoaDonService.createHoaDon();
        return "redirect:/bumblebee/ban-hang-tai-quay/sell";
    }

    @RequestMapping("/delete-hoadon/{id}")
    public String deleteHoaDon(@PathVariable("id") UUID id) {
        List<HoaDonChiTiet> listHoaDonCT = hoaDonChiTietService.getListHoaDonCTByIdHoaDon(id);
        hoaDonService.deleteHoaDon(id);
        return "redirect:/bumblebee/ban-hang-tai-quay/sell";
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
        model.addAttribute("khachHang", new KhachHang());
        idHoaDon = id;
        model.addAttribute("searchForm", new SearchForm());
        model.addAttribute("listHoaDonCho", hoaDonService.listHoaDonCho());
        model.addAttribute("listSanPham", chiTietSanPhamService.getList());
        model.addAttribute("listHDCT", hoaDonChiTietService.getListHoaDonCTByIdHoaDon(id));
        model.addAttribute("idHoaDon", this.idHoaDon);
        model.addAttribute("listKhachHang", khachHangService.getAll());
        getTaiKhoan(model);
        model.addAttribute("hoaDon", hoaDonService.getOne(id));
        List<HoaDonChiTiet> list = hoaDonChiTietService.getListHoaDonCTByIdHoaDon(id);
        sumMoney = hoaDonChiTietService.getTotalMoney(list);
        model.addAttribute("sumMoney", sumMoney);

        model.addAttribute("listMauSac", chiTietSanPhamRepo.listMauSac());
        model.addAttribute("listKC", chiTietSanPhamRepo.listKC());
        model.addAttribute("listLoaiGiay", chiTietSanPhamRepo.listLoaiGiay());
        model.addAttribute("listDeGiay", chiTietSanPhamRepo.listDeGiay());
        model.addAttribute("listChatLieu", chiTietSanPhamRepo.lítChatLieu());


        idHoaDonCT = id;
        return "ban_hang_tai_quay/ban-hang";
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
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getOneHoaDon(id);
//        Integer soLuong = hoaDonChiTiet.getSoLuong();
//        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getOne(hoaDonChiTiet.getChiTietSanPham().getId());
//        Integer soLuongTon = chiTietSanPham.getSoLuong();
//        chiTietSanPhamService.updateSoLuongTon(chiTietSanPham.getId(), soLuongTon + soLuong);
//        hoaDonChiTietService.deleteHoaDonCT(id);
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
        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getOne(hoaDonChiTiet.getChiTietSanPham().getId());
        Integer soLuongTon = chiTietSanPham.getSoLuong() + hoaDonChiTiet.getSoLuong();
        if (hoaDonChiTiet != null) {
            hoaDonChiTiet.setSoLuong(soLuong);
            chiTietSanPhamService.updateSoLuongTon(chiTietSanPham.getId(), soLuongTon - soLuong);
            hoaDonChiTietService.saveHoaDonCT(hoaDonChiTiet);
        }
        return "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDon;
    }

    @RequestMapping("/thanhtoan/{idHoaDon}")
    public String thanhToan(Model model, @PathVariable("idHoaDon") UUID id,
                            @RequestParam("soDienThoai")String soDienThoai,
                            @RequestParam("ghiChu")String ghiChu) throws ParseException {
        HoaDon hoaDonThanhToan = hoaDonService.getOne(idHoaDon);
        if (hoaDonThanhToan != null) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String format = sdf.format(date);
            hoaDonThanhToan.setNgayThanhToan(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(format));
            hoaDonThanhToan.setNhanVien(nhanVien);
            hoaDonThanhToan.setTrangThai(1);
            hoaDonThanhToan.setSdt(soDienThoai);
            KhachHang khachHang = khachHangRepository.findKHBySDT(soDienThoai);
            hoaDonThanhToan.setTenNguoiNhan(khachHang.getTen());
            hoaDonThanhToan.setGhiChu(ghiChu);
            hoaDonService.saveHoaDon(hoaDonThanhToan);
            this.sumMoney = 0.0;
            this.idHoaDon = null;
        }
        return "redirect:/bumblebee/ban-hang-tai-quay/sell";
    }

    @RequestMapping("/them-khach-hang")
    public String themKhachHang(Model model, @ModelAttribute("khachHang") KhachHang khachHang) {
        KhachHang addKhachHang = new KhachHang();
//        String formatKhachHang = "KH" + String.format("%06d", maKhachHang);
        Random random = new Random();
        addKhachHang.setMa("HD" + random.nextInt(9999999));
//        maKhachHang++;
        addKhachHang.setTen(khachHang.getTen());
        addKhachHang.setSoDienThoai(khachHang.getSoDienThoai());
        addKhachHang.setTrangThai(1);
        khachHangService.saveKhachHang(addKhachHang);
        return "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDon;
    }
}
