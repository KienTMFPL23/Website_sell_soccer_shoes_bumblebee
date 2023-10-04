package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.service.Impl.TaiKhoanServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DangNhapController {

    @Autowired
    private TaiKhoanServiceImpl taiKhoanService;

    @GetMapping("/bumblebee/login")
    public String dangNhap(Model model){
//        model.addAttribute("taikhoan", new TaiKhoan());
        return "/dang_nhap/dang_nhap";
    }


    @PostMapping("/bumblebee/login")
    public String login(Model model,
                        @ModelAttribute(name = "taikhoan") TaiKhoan taikhoan,
                        HttpSession session) {

        // Login success
        TaiKhoan accountFromDB = taiKhoanService.findByUsernameAndPassword(taikhoan.getUsername(),
                taikhoan.getPassword());

        if (accountFromDB != null && accountFromDB.getRole() == 1) {
            session.setAttribute("userLogged", accountFromDB);
            return "redirect:/admin/dashboard";
        } else if (accountFromDB != null && accountFromDB.getRole() == 2) {
            session.setAttribute("userLogged", accountFromDB);
            return "redirect:/admin/dashboard";
        } else if (accountFromDB != null && accountFromDB.getRole() == 2) {
            session.setAttribute("userLogged", accountFromDB);
            return "redirect:/bumblebee/home";
        }

        // Login false
        model.addAttribute("message1", "Tài khoản hoặc Mật khẩu không đúng !!!");
        model.addAttribute("message2", "Vui lòng thử lại");
        return "/dang-nhap/login";
    }
}
