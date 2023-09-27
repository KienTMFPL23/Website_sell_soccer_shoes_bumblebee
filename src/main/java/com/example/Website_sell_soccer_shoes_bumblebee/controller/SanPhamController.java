package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SanPhamController {
    @GetMapping("/")
    public String hienthi(){
        return "/san_pham/add";
    }
}
