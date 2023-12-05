
package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.GioHang;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDon;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.KhachHang;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.TaiKhoan;

import com.example.Website_sell_soccer_shoes_bumblebee.repository.GioHangRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.KhachHangService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.TaiKhoanService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class DangNhapController {

    @Autowired
    private TaiKhoanService taiKhoanService;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private GioHangRepository gioHangRepository;

    @ModelAttribute("dsRole")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsRole = new HashMap<>();
        dsRole.put(1, "Quản lý");
        dsRole.put(2, "Nhân viên");
        return dsRole;
    }

    @GetMapping("/bumblebee/login")
    public String dangNhap(Model model) {
        model.addAttribute("taikhoan", new TaiKhoan());
        return "/dang_nhap/dang_nhap";
    }


    @PostMapping("/bumblebee/login")
    public String login(Model model,
                        @ModelAttribute(name = "taikhoan") TaiKhoan taikhoan,
                        HttpSession session) {

        // Login success
        TaiKhoan taiKhoanDB = taiKhoanService.findByUsernameAndPassword(taikhoan.getUsername(),
                taikhoan.getPassword());

        if (taiKhoanDB != null && taiKhoanDB.getRole() == 1) {
            session.setAttribute("userLogged", taiKhoanDB);
            return "redirect:/admin/dashboard";
        } else if (taiKhoanDB != null && taiKhoanDB.getRole() == 2) {
            session.setAttribute("userLogged", taiKhoanDB);
            return "redirect:/admin/dashboard";
        } else if (taiKhoanDB != null && taiKhoanDB.getRole() == 3) {
            session.setAttribute("userLogged", taiKhoanDB);
            return "redirect:/bumblebee/home";
        } else {
            session.setAttribute("userLogged", null);
        }

        // Login false
        model.addAttribute("message1", "Tài khoản hoặc Mật khẩu không đúng !!!");
        model.addAttribute("message2", "Vui lòng thử lại");
        return "/dang_nhap/dang_nhap";
    }

    @GetMapping("/bumblebee/register")
    public String register(Model model) {

        model.addAttribute("taikhoan", new TaiKhoan());
        return "/dang_nhap/dang_ky";
    }

    @PostMapping("/bumblebee/register")
    public String register(Model model,
                           @Valid @ModelAttribute(name = "taikhoan") TaiKhoan taikhoan, BindingResult result,
                           HttpSession session,
                           @RequestParam(name = "password") String password,
                           @RequestParam(name = "confirmpassword") String confirmpassword
    ) {
        if (result.hasErrors()) {
            return "dang_nhap/dang_ky";
        }

        if (!password.equalsIgnoreCase(confirmpassword)) {
            model.addAttribute("messageConfirmPass", "Mật khẩu không trùng khớp");
            return "/dang_nhap/dang_ky";
        }

        taikhoan.setRole(3);
        taiKhoanService.dangKy(taikhoan);
        session.setAttribute("userLogged", taikhoan);
        return "redirect:/bumblebee/thong-tin-nguoi-dung";
    }

    @ModelAttribute("dsGioiTinh")
    public Map<Boolean, String> getDsGioiTinh() {
        Map<Boolean, String> dsGioiTinh = new HashMap<>();
        dsGioiTinh.put(true, "Nam");
        dsGioiTinh.put(false, "Nữ");
        return dsGioiTinh;
    }

    @GetMapping("/bumblebee/thong-tin-nguoi-dung")
    public String thongTinNguoiDung(Model model, HttpSession session, @ModelAttribute(name = "kh") KhachHang kh) {
        return "/dang_nhap/thong_tin_nguoi_dung";
    }

    @PostMapping("/bumblebee/thong-tin-nguoi-dung")
    public String luuThongTinNguoiDung
            (Model model,
             HttpSession session,
             @ModelAttribute(name = "kh") KhachHang kh,
             @RequestParam(name = "thanhPho") String thanhPho,
             @RequestParam(name = "huyen") String huyen,
             @RequestParam(name = "xa") String xa
             ) {
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        Random random = new Random();
        kh.setMa("KH" + random.nextInt());
        kh.setTaiKhoanKH(taiKhoan);
        kh.setDiaChi(xa + " - " + huyen + " - " + thanhPho);
        kh.setTrangThai(0);
        khachHangService.saveKhachHang(kh);

        GioHang gioHang = new GioHang();
        gioHang.setKhachHang(kh);
        gioHang.setNgayTao(new Date());
        gioHang.setTrangThai(0);
        gioHangRepository.save(gioHang);
        return "redirect:/bumblebee/login";
    }

    @GetMapping("/bumblebee/logout")
    public String logout(Model model, HttpSession session) {
        session.removeAttribute("userLogged");
        return "redirect:/bumblebee/login";
    }


}
