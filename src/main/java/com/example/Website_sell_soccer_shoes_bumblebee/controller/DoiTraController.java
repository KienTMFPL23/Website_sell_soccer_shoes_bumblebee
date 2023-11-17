package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDonChiTiet;
import com.example.Website_sell_soccer_shoes_bumblebee.service.HoaDonChiTietService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DoiTraController {

    @Autowired
    HoaDonChiTietService hoaDonChiTietService;

    @Data
    public static class SearchForm{
        String key = "";
    }
    @GetMapping("/bumblebee/doi-hang/list")
    public String danhSachDoiHang(Model model){
        model.addAttribute("view","../doi-tra/danh-sach-doi-tra.jsp");
        return "/admin/index";
    }

    @GetMapping("/bumblebee/doi-hang")
    public String doiHang(Model model){
        model.addAttribute("view","../doi-tra/doi-hang.jsp");
        model.addAttribute("SearchForm",new SearchForm());
        return "/admin/index";
    }

    @PostMapping("/bumblebee/don-hang/search")
    public String getDonHang(Model model, @ModelAttribute("SearchForm")SearchForm searchForm){
        model.addAttribute("view","../doi-tra/doi-hang.jsp");
        List<HoaDonChiTiet> listSPMuonDoi = hoaDonChiTietService.hoaDonMuonDoi(searchForm.key);
        model.addAttribute("listHDCT", listSPMuonDoi);
        return "/admin/index";
    }

}
