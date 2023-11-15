
package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.TaiKhoan;

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

import java.util.HashMap;
import java.util.Map;

@Controller
public class DangNhapController {

    @Autowired
    private TaiKhoanService taiKhoanService;

    @ModelAttribute("dsRole")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsRole = new HashMap<>();
        dsRole.put(1, "Quản lý");
        dsRole.put(2, "Nhân viên");
        return dsRole;
    }

    @GetMapping("/bumblebee/login")
    public String dangNhap(Model model){
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
        if (result.hasErrors()){
            return "dang_nhap/dang_ky";
        }

        if (!password.equalsIgnoreCase(confirmpassword)) {
            model.addAttribute("messageConfirmPass", "Mật khẩu không trùng khớp");
            return "/dang_nhap/dang_ky";
        }

        taikhoan.setRole(3);
        taiKhoanService.dangKy(taikhoan);
        session.setAttribute("userLogged", taikhoan);
        return "redirect:/bumblebee/login";
    }

    @GetMapping("/bumblebee/logout")
    public String logout(Model model, HttpSession session) {
        session.removeAttribute("userLogged");
        return "redirect:/bumblebee/login";
    }
}
