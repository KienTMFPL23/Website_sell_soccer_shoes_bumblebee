package com.example.Website_sell_soccer_shoes_bumblebee.controller;


import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDon;
import com.example.Website_sell_soccer_shoes_bumblebee.service.Impl.HoaDonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HoaDonController {

    @Autowired
    private HoaDonServiceImpl hoaDonService;

    @GetMapping("/hoa-don/hien-thi")
    public String index(Model model) {
        model.addAttribute("view", "../hoa_don/list.jsp");
        model.addAttribute("list", hoaDonService.findAll());
        return "/admin/index";
    }
}
