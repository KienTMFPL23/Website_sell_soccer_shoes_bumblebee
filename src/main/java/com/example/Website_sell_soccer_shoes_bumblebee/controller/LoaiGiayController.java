package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.DeGiay;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.LoaiGiay;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.LoaiGiayRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.LoaiGiayService;
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
public class LoaiGiayController {
    @Autowired
    LoaiGiayService repo;

    @Autowired
    LoaiGiayRepository repository;

    @Data
    public static class SearchForm {
        String keyword = "";
    }



    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(0, "Hết hàng");
        dsTrangThai.put(1, "Còn hàng");
        return dsTrangThai;
    }

    @RequestMapping("/loai-giay/sort")
    public String sortLG(Model model, @ModelAttribute SearchForm searchForm, @RequestParam(defaultValue = "0") int p) {
        if (p < 0) {
            p = 0;
        }
        Sort sort = Sort.by(Sort.Direction.ASC, "tentheloai");
        Pageable pageable = PageRequest.of(p, 5, sort);
        Page<LoaiGiay> page = repo.getAll(pageable);
        model.addAttribute("page", page);
        model.addAttribute("view", "../loai-giay/list.jsp");
        return "/admin/index";
    }

    @GetMapping("/loai-giay/hien-thi/list")
    public ResponseEntity<?> hienThi(Model model, @ModelAttribute("loaiGiay") LoaiGiay loaiGiay, @RequestParam(defaultValue = "0") int pageNum){
        return ResponseEntity.ok(repository.findAll());
    }

    @RequestMapping("/loai-giay/hien-thi")
    public String index(Model model, @ModelAttribute SearchForm searchForm, @RequestParam(defaultValue = "0") int p) {
        if (p < 0) {
            p = 0;
        }
        Pageable pageable = PageRequest.of(p, 5);
        if (searchForm.keyword.equals("")) {
            Page<LoaiGiay> page = repo.getAll(pageable);
            model.addAttribute("page", page);
        } else {
            Page<LoaiGiay> page = repo.filAllBYKeyWord("%" + searchForm.keyword + "%", pageable);
            model.addAttribute("page", page);
        }
        model.addAttribute("lg", new LoaiGiay());
        model.addAttribute("searchForm", searchForm);
        model.addAttribute("view", "../loai-giay/list.jsp");
        return "/admin/index";
    }

    @RequestMapping("/admin/loai-giay/add")
    public String save(@Valid @ModelAttribute("loaiGiay") LoaiGiay loaiGiay, BindingResult result, Model model, @ModelAttribute SearchForm searchForm) {
        Boolean hasE = result.hasErrors();
        List<LoaiGiay> list = repository.findAll();
        for (int i = 0; i < list.size(); i++) {
            if (loaiGiay.getMa().length() == 0) {
                model.addAttribute("errorMa", "Ma loai giay khong duoc bo trong");
                hasE = true;
            }
            if (list.get(i).getMa().equals(loaiGiay.getMa())) {
                model.addAttribute("errorMa", "Ma loai giay da ton tai");
                hasE = true;
            }
            if (loaiGiay.getMa().length() > 0 && loaiGiay.getMa().length() <4) {
                model.addAttribute("errorMa", "Ma loai giay nhieu hon 4 ki tu");
                hasE = true;
            }
            if (loaiGiay.getMa().length() > 10) {
                model.addAttribute("errorMa", "Ma loai giay it hon 100 ki tu");
                hasE = true;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTentheloai().equals(loaiGiay.getTentheloai())) {
                model.addAttribute("errorTen", "Ten loai giay da ton tai");
                hasE = true;
            }
            if (loaiGiay.getTentheloai().length() > 150) {
                model.addAttribute("errorTen", "Ten loai giay it hon 150 ki tu");
                hasE = true;
            }
            if (loaiGiay.getTentheloai().length() == 0) {
                model.addAttribute("errorTen", "Ten loai giay khong duoc bo trong");
                hasE = true;
            }
        }
        if (hasE) {
            model.addAttribute("loaiGiay", new LoaiGiay());
            model.addAttribute("view", "../loai-giay/form.jsp");
            return "/admin/index";
        }
        loaiGiay.setTrangthai(1);
        repo.addLG(loaiGiay);
        return "redirect:/admin/index";
    }

    @RequestMapping("/admin/loai-giay/update/{id}")
    public String update(@Valid @ModelAttribute("loaiGiay") LoaiGiay loaiGiay, BindingResult result, Model model, @PathVariable UUID id) {
        boolean hasE = result.hasErrors();
        List<LoaiGiay> list = repository.findAll();
        for (int i = 0; i < list.size(); i++) {
            if (loaiGiay.getMa().length() > 10) {
                model.addAttribute("errorMa", "Ma loai giay it hon 100 ki tu");
                hasE = true;
            }
            if (loaiGiay.getMa().length() == 0) {
                model.addAttribute("errorTen", "Ma loai giay khong duoc bo trong");
                hasE = true;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (loaiGiay.getTentheloai().length() > 150) {
                model.addAttribute("errorTen", "Ten loai giay it hon 150 ki tu");
                hasE = true;
            }
            if (loaiGiay.getTentheloai().length() == 0) {
                model.addAttribute("errorTen", "Ten loai giay khong duoc bo trong");
                hasE = true;
            }
        }
        if (hasE) {
            model.addAttribute("loaiGiay", new LoaiGiay());
            model.addAttribute("view", "../loai-giay/update.jsp");
            return "/admin/index";
        }
        repo.updateLG(loaiGiay);
        return "redirect:/loai-giay/hien-thi";
    }

    @RequestMapping("/loai-giay/edit/{id}")
    public String edit(Model model, @PathVariable UUID id) {
        model.addAttribute("id", id);
        model.addAttribute("loaiGiay", repo.getOne(id));
        model.addAttribute("listLG", repository.findAll());
        return "loai-giay/update";

    }

    @RequestMapping("/loai-giay/form")
    public String form(Model model) {
        model.addAttribute("loaiGiay", new LoaiGiay());
//        model.addAttribute("view", "../loai-giay/form.jsp");
//        return "/admin/index";
        return "loai-giay/form";
    }
}
