
package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.TaiKhoan;
import com.example.Website_sell_soccer_shoes_bumblebee.service.TaiKhoanService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @Autowired
    private TaiKhoanService taiKhoanService;

    @RequestMapping("/admin/dashboard")
    public String hienthi(HttpSession session) {

        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        if (taiKhoan == null) {
            return "redirect:/bumblebee/login";
        } else {
            return "/admin/index";
        }
    }
}

