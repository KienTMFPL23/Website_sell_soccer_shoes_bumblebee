package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.service.HoaDonChiTietService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/bumblebee/doi-tra")
public class DoiTraController {

    @Autowired
    HoaDonService hoaDonService;

    @Autowired
    HoaDonChiTietService hoaDonChiTietService;


    @GetMapping("/hien-thi")
    public String hienThi(Model model){
        model.addAttribute("view", "../doi-tra/index.jsp");
        model.addAttribute("listHoaDon",hoaDonService.listHoaDonThanhToan());
        return "admin/index";
    }
    @GetMapping("/hoa-don/{id}")
    public String doiTra(Model model, @PathVariable("id")UUID id){
        model.addAttribute("listHDCT",hoaDonChiTietService.getListHoaDonCTByIdHoaDon(id));
        model.addAttribute("hoaDon",hoaDonService.getOne(id));
        model.addAttribute("listHoaDon",hoaDonService.listHoaDonThanhToan());
        return "doi-tra/doi-tra-hang";
    }

}
