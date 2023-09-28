package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.SanPham;
import com.example.Website_sell_soccer_shoes_bumblebee.service.SanPhamService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class SanPhamController {

    @Autowired
    SanPhamService sanPhamService;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDSTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt động");
        dsTrangThai.put(0, "Ngừng Hoạt động");
        return dsTrangThai;
    }

    @Getter
    @Setter
    public static class SearchForm {
        String keyword = "";
    }
    @GetMapping("/san-pham/hien-thi")
    public String hienThi(Model model, @RequestParam(defaultValue = "0")int p){
        if (p < 0){
            p =0;
        }
        Pageable pageable = PageRequest.of(p,5);
        Page<SanPham> page = sanPhamService.findAllSP(pageable);
        model.addAttribute("search", new SearchForm());
        model.addAttribute("page",page);
        return "san_pham/list_san_pham";
    }

    @RequestMapping("/san-pham/search")
    public String search(Model model, @ModelAttribute("search") SearchForm searchForm, @RequestParam(defaultValue = "0") int p) {
        if (p < 0) {
            p = 0;
        }
        Pageable pageable = PageRequest.of(p, 5);
        Page<SanPham> page = sanPhamService.findByKeyword(searchForm.keyword, pageable);
        model.addAttribute("page", page);
//        model.addAttribute("view", "../san_pham/index.jsp");
        return "san_pham/list_san_pham";
    }

    @GetMapping("/san-pham/view-add")
    public String viewAdd(Model model, @ModelAttribute("SP") SanPham sanPham) {
        model.addAttribute("action", "/san-pham/add");
        return "san_pham/view_add_update";
    }

    @GetMapping("/san-pham/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id, @ModelAttribute("SP") SanPham sanPham) {
        SanPham product = sanPhamService.getOne(id);
        model.addAttribute("action", "/san-pham/update/" + product.getId());
        model.addAttribute("SP",product);
        SanPham sp = sanPhamService.getOne(id);
        return "san_pham/view_add_update";
    }
    @PostMapping("/san-pham/add")
    public String add(Model model,@Valid @ModelAttribute("SP")SanPham sanPham, BindingResult result){
        Boolean hasError = result.hasErrors();
        SanPham product = sanPhamService.getByMa(sanPham.getMaSanPham());
        if (product != null) {
            hasError = true;
            model.addAttribute("maspError", "Vui lòng không nhập trùng mã");
            model.addAttribute("view", "../san_pham/view_add_update.jsp");
            return "admin/index";
        }
        if (hasError) {
            model.addAttribute("view", "../san_pham/view_add_update.jsp");
            return "admin/index";
        }
        sanPhamService.addSanPham(sanPham);
        return "redirect:/san-pham/hien-thi";
    }

    @PostMapping("/san-pham/update/{id}")
    public String udpate(@PathVariable("id") UUID id, Model model, @Valid @ModelAttribute("SP") SanPham sanPham,
                         BindingResult result){
//        Boolean hasError = result.hasErrors();
//        if (sanPham.getMaSanPham().trim().length() < 5) {
//            hasError = true;
//            model.addAttribute("erorLenghMa", "Mã sản phẩm phải lớn hơn hoặc bằng 5");
//            model.addAttribute("view", "../san_pham/view_add_update.jsp");
//            return "admin/index";
//        }
//        if (hasError) {
//            model.addAttribute("view", "../san_pham/view_add_update.jsp");
//            return "admin/index";
//        }
        SanPham sp = sanPhamService.getOne(id);
        sp.setMaSanPham(sanPham.getMaSanPham());
        sp.setTenSanPham(sanPham.getTenSanPham());
        sp.setTrangThai(sanPham.getTrangThai());
        sanPhamService.udpateSanPham(sp);
        return "redirect:/san-pham/hien-thi";
    }
}
