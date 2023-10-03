package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bumblebee")
public class BanHangTaiQuayController {

    @GetMapping("/ban-hang-tai-quay")
    public String banHang(Model model){
        model.addAttribute("view","../ban_hang_tai_quay/index.jsp");
        return "admin/index";
    }
}
