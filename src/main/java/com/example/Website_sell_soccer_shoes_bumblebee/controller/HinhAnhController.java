package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.HinhAnh;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.LoaiGiay;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.HinhAnhRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.LoaiGiayRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.HinhAnhService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.LoaiGiayService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
public class HinhAnhController {

    @Autowired
    HinhAnhService service;

    @Autowired
    HinhAnhRepository repository;

    @Data
    public static class SearchForm {
        String keyword = "";
    }

    @RequestMapping("/hinh-anh/sort")
    public String sortLG(Model model, @ModelAttribute LoaiGiayController.SearchForm searchForm, @RequestParam(defaultValue = "0") int p) {
        if (p < 0) {
            p = 0;
        }
        Sort sort = Sort.by(Sort.Direction.ASC, "tenanh");
        Pageable pageable = PageRequest.of(p, 5, sort);
        Page<HinhAnh> page = service.getAllPage(pageable);
        model.addAttribute("page", page);
        return "hinh-anh/list";
    }

    @RequestMapping("/admin/hinh-anh")
    public String index(Model model, @ModelAttribute LoaiGiayController.SearchForm searchForm, @RequestParam(defaultValue = "0") int p) {
        if (p < 0) {
            p = 0;
        }
        Pageable pageable = PageRequest.of(p, 5);
        if (searchForm.keyword.equals("")) {
            Page<HinhAnh> page = service.getAllPage(pageable);
            model.addAttribute("page", page);
        } else {
            Page<HinhAnh> page = service.findByKeyWord("%" + searchForm.keyword + "%", pageable);
            model.addAttribute("page", page);
        }
        model.addAttribute("lg", new LoaiGiay());
        model.addAttribute("searchForm", searchForm);
        return "hinh-anh/list";
    }

    @RequestMapping("/admin/hinh-anh/add")
    public String save(@Valid @ModelAttribute("hinhAnh") HinhAnh hinhAnh, BindingResult result, Model model, @ModelAttribute SearchForm searchForm) {
        Boolean hasE = result.hasErrors();
        List<HinhAnh> list = repository.findAll();
        for (int i = 0; i < list.size(); i++) {
            if (hinhAnh.getTenanh().length() == 0) {
                model.addAttribute("errorTen", "Ten hinh anh khong duoc bo trong");
                hasE = true;
            }
            if (list.get(i).getTenanh().equals(hinhAnh.getTenanh())) {
                model.addAttribute("errorTen", "Ten hinh anh da ton tai");
                hasE = true;
            }
        }
        if (hasE) {
            model.addAttribute("hinhAnh", new LoaiGiay());
            return "hinh-anh/form";
        }
        hinhAnh.setTrangthai(1);
        repository.save(hinhAnh);
        return "redirect:/admin/hinh-anh";
    }

    @RequestMapping("/admin/hinh-anh/update/{id}")
    public String update(@Valid @ModelAttribute("hinhAnh") HinhAnh hinhAnh, BindingResult result, Model model, @PathVariable UUID id) {
        boolean hasE = result.hasErrors();
        List<HinhAnh> list = repository.findAll();
        for (int i = 0; i < list.size(); i++) {
            if (hinhAnh.getTenanh().length() == 0) {
                model.addAttribute("errorTen", "Ten hinh anh khong duoc bo trong");
                hasE = true;
            }
            if (list.get(i).getTenanh().equals(hinhAnh.getTenanh())) {
                model.addAttribute("errorTen", "Ten hinh anh da ton tai");
                hasE = true;
            }
        }
        if (hasE) {
            model.addAttribute("loaiGiay", new LoaiGiay());
            model.addAttribute("view", "../loai-giay/view-update.jsp");
            return "loai-giay/update";
        }
       repository.save(hinhAnh);
        return "redirect:/admin/hinh-anh";
    }

    @RequestMapping("/hinh-anh/edit/{id}")
    public String edit(Model model, @PathVariable UUID id) {
        model.addAttribute("id", id);
        model.addAttribute("hinhAnh", service.getOne(id));
        model.addAttribute("listHA", repository.findAll());
        return "hinh-anh/update";

    }

    @RequestMapping("/hinh-anh/form")
    public String form(Model model) {
        model.addAttribute("hinhAnh", new HinhAnh());
        return "hinh-anh/form";
    }
}
