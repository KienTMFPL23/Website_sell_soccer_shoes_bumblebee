package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @RequestMapping("/admin/dashboard")
    public String hienthi() {

        return "/admin/index";
    }
}
