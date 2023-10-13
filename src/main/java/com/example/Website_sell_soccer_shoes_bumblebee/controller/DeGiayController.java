package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.DeGiay;
import com.example.Website_sell_soccer_shoes_bumblebee.service.Impl.DeGiayServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
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
    public String hienThi(Model model){
        model.addAttribute("view", "../de_giay/list.jsp");
        return "/admin/index";
    }

    @GetMapping("/de-giay/hien-thi/list")
    public ResponseEntity<?> hienThi(Model model, @ModelAttribute("degiay") DeGiay degiay, @RequestParam(defaultValue = "0") int pageNum){

        return ResponseEntity.ok(deGiayService.getList());
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
        return "redirect:/de-giay/hien-thi";

    }


}
