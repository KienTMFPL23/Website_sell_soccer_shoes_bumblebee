package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.KichCo;
import com.example.Website_sell_soccer_shoes_bumblebee.service.KichCoService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class KichCoController {
    @Autowired
    KichCoService service;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt động");
        dsTrangThai.put(0, "Không hoạt động");
        return dsTrangThai;
    }

    @ModelAttribute("dsGioiTinh")
    public Map<Boolean, String> getDsGioiTinh() {
        Map<Boolean, String> dsGT = new HashMap<>();
        dsGT.put(true, "Nam");
        dsGT.put(false, "Nữ");
        return dsGT;
    }

    @Data
    public static class SearchForm {
        String keyword;


    }
    @GetMapping("/kich-co/hien-thi")
    public String hienThi(Model model){
        model.addAttribute("view", "../kich-co/list.jsp");
        return "/admin/index";
    }

    @GetMapping("/kich-co/hien-thi/list")
    public ResponseEntity<?> hienthi(@ModelAttribute("kichco") KichCo kichCo, @RequestParam(defaultValue = "0") int p, Model model) {
//        List<KichCo> listKC = service.getList();
//        if (p < 0) {
//            p = 0;
//        }
//        Pageable pageable = PageRequest.of(p, 5);
//        Page<KichCo> page = service.getListKC(pageable);
//        model.addAttribute("page", page);
//        model.addAttribute("listKC", listKC);
//        model.addAttribute("searchForm", new SearchForm());
//        model.addAttribute("view", "../kich-co/list.jsp");
        return ResponseEntity.ok(service.getList());
    }

    @RequestMapping("/kich-co/search")
    public String Search(@ModelAttribute("searchForm") SearchForm searchForm, @RequestParam(defaultValue = "0") int p, Model model) {
        List<KichCo> listKC = service.getList();
        if (p < 0) {
            p = 0;
        }
        Pageable pageable = PageRequest.of(p, 5);
        Page<KichCo> page = service.searchKH(searchForm.keyword, pageable);
        model.addAttribute("page", page);
        model.addAttribute("view", "../kich-co/list.jsp");
        return "/admin/index";

    }

    @RequestMapping("/kich-co/sort")
    public String Sort(@ModelAttribute("searchForm") SearchForm searchForm, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Sort sort = Sort.by(Sort.Direction.ASC, "size");
        Pageable pageable = PageRequest.of(p, 5, sort);
        Page<KichCo> page = service.searchKH(searchForm.keyword, pageable);
        model.addAttribute("page", page);
        model.addAttribute("view","../kich-co/list.jsp");
        return  "/admin/index";
    }

    @RequestMapping("/kich-co/view-add")
    public String viewAdd(@ModelAttribute("kichco") KichCo kichCo, Model model) {
        model.addAttribute("action", "/kich-co/add");
        model.addAttribute("view","../kich-co/add_update.jsp");
        return  "/admin/index";
//        return "/kich-co/add_update";
    }

    @RequestMapping("/kich-co/add")
    public String addKC(Model model, @Valid @ModelAttribute("kichco") KichCo kichCo, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("mess", "Lỗi! Vui lòng kiểm tra các trường trên !");
            model.addAttribute("view","../kich-co/add_update.jsp");
            return  "/admin/index";
//            return "/kich-co/add_update";
        }
//        if (kichCo.getMaKichCo() == null) {
//            model.addAttribute("messMa", "Lỗi! Vui lòng kiểm tra mã không được trùng !");
//
//            return "/kich-co/add_update";
//        }
        service.addKC(kichCo);
        model.addAttribute("view","../kich-co/index.jsp");
        return "redirect:/kich-co/hien-thi";

    }

    @RequestMapping("/kich-co/update/{id}")
    public String updateKC(Model model, @Valid @ModelAttribute("kichco") KichCo kichCo, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("mess", "Lỗi! Vui lòng kiểm tra các trường trên !");
            model.addAttribute("view","../kich-co/add_update.jsp");
            return  "/admin/index";
        }

        service.addKC(kichCo);
        return "redirect:/kich-co/hien-thi";
    }

//    @RequestMapping("/kich-co/remove/{id}")
//    public String removeKC(@ModelAttribute("kichco") KichCo kichCo, Model model) {
//        service.deleteKC(kichCo.getId());
//        return "redirect:/kich-co/hien-thi";
//    }

    @RequestMapping("/kich-co/view-update/{id}")
    public String viewUpdate(@PathVariable("id") UUID id, Model model) {
        KichCo co = service.getOne(id);
        model.addAttribute("action", "/kich-co/update/" + co.getId());
        model.addAttribute("kichco", co);
        model.addAttribute("view","../kich-co/add_update.jsp");
        return  "/admin/index";
    }

}

