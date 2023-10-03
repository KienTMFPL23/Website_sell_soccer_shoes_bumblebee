package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/home/bumblebee")
    public String index(Model model){
        model.addAttribute("view","../template_home/home.jsp");
        return "template_home/index";
    }

    @RequestMapping("/bumblebee/detail")
    public String detail(Model model){
        model.addAttribute("view","../template_home/product_detail.jsp");
        return "template_home/index";
    }
}
