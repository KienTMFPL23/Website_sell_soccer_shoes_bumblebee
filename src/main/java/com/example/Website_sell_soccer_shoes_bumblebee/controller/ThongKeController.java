package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThongKeController {

    @GetMapping("/bumblebee/thong-ke")
    public String index(Model model){
        model.addAttribute("view", "../thong-ke/index.jsp");
        return "admin/index";
    }
}
