package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChatLieu;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.KhachHang;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.KhachHangRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.Impl.KhachHangServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class KhachHangController {

    @Autowired
    private KhachHangRepository khachHangRepository;

    @GetMapping("/khach-hang/hien-thi")
    public String index(Model model) {
        List<KhachHang> list = khachHangRepository.findAll();
        model.addAttribute("list", list);
        model.addAttribute("view", "../khach_hang/list.jsp");
        return "/admin/index";
    }


}
