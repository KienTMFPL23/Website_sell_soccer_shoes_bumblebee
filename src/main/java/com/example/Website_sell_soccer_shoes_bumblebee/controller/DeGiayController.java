package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.DeGiay;
import com.example.Website_sell_soccer_shoes_bumblebee.service.Impl.DeGiayServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Controller
public class DeGiayController {

    @Autowired
    private DeGiayServiceImpl deGiayService;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(0, "Hết hàng");
        dsTrangThai.put(1, "Còn hàng");
        return dsTrangThai;
    }

    @GetMapping("/de-giay/hien-thi")
    public String hienThi(Model model, @ModelAttribute("degiay") DeGiay degiay, @RequestParam(defaultValue = "0") int pageNum){
        if (pageNum < 0){
            pageNum = 0;
        }

        Pageable pageable = PageRequest.of(pageNum, 5);
        Page<DeGiay> page = deGiayService.getListPage(pageable);
        model.addAttribute("listDeGiay", page);
        model.addAttribute("view", "../de_giay/list.jsp");
        return "/admin/index";
    }

    @GetMapping("/de-giay/view-add")
    public String viewAdd(Model model, @ModelAttribute("degiay") DeGiay degiay){
        model.addAttribute("action", "/de-giay/add");
        model.addAttribute("view", "../de_giay/add_update.jsp");
        return "/admin/index";
    }

    @PostMapping("/de-giay/add")
    public String add(Model model, @Valid @ModelAttribute("degiay") DeGiay degiay, BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("view", "../de_giay/add_update.jsp");
            return "/admin/index";
        }

        if (deGiayService.findByMa(degiay.getMa()) != null) {
            model.addAttribute("mess_Ma", "Lỗi! Mã không được trùng");

            model.addAttribute("view", "../de_giay/add_update.jsp");
            return "/admin/index";
        }

        deGiayService.add(degiay);
        model.addAttribute("view", "../de_giay/list.jsp");
        return "redirect:/de-giay/hien-thi";
    }

    @GetMapping("/de-giay/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable(name = "id") UUID id){
        DeGiay deGiay = deGiayService.findById(id);
        model.addAttribute("degiay", deGiay);
        model.addAttribute("action", "/de-giay/update/" + deGiay.getId());
        model.addAttribute("view", "../de_giay/add_update.jsp");
        return "/admin/index";
    }

    @PostMapping("/de-giay/update/{id}")
    public String update(Model model, @Valid @ModelAttribute("degiay") DeGiay degiay, BindingResult result) {
        if (result.hasErrors()) {

            model.addAttribute("view", "../de_giay/add_update.jsp");
            return "/admin/index";
        }

        deGiayService.add(degiay);
        model.addAttribute("view", "../de_giay/list.jsp");
        return "redirect:/de-giay/hien-thi";

    }

    @RequestMapping("/de-giay/search")
    public String search(Model model, @RequestParam(name = "keyword") String keyword, @RequestParam(defaultValue = "0") int pageNum){
        if (pageNum < 0){
            pageNum = 0;
        }
        Pageable pageable = PageRequest.of(pageNum, 5);
        Page<DeGiay> listSearch = deGiayService.search("%" +keyword +"%", pageable);
        model.addAttribute("listSearch", listSearch);
        model.addAttribute("view", "../de_giay/search.jsp");
        return "/admin/index";
    }

    @RequestMapping("/de-giay/sort")
    public <SearchForm> String Sort(@RequestParam(defaultValue = "0") int pageNum, Model model) {
        if (pageNum < 0){
            pageNum = 0;
        }
        Pageable pageable = PageRequest.of(pageNum, 5);
        Page<DeGiay> page = deGiayService.sort(pageable);
        model.addAttribute("listDeGiay", page);
        model.addAttribute("view", "../de_giay/list.jsp");
        return "/admin/index";
    }

}
