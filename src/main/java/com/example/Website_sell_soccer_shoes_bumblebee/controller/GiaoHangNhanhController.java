package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.KhachHang;
import com.example.Website_sell_soccer_shoes_bumblebee.service.Impl.GiaoHangNhanhServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;

@Controller
public class GiaoHangNhanhController {

    @Autowired
    private GiaoHangNhanhServiceImpl giaoHangNhanhService;

    @GetMapping("/getAll")
    public String getAll(Model model) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, KhachHang> map = objectMapper.readValue(giaoHangNhanhService.callGhnApi().getBody(),Map.class);
        System.out.println(giaoHangNhanhService.callGhnApi().getBody());
        String diaChi = map.get("ProvinceName").toString();
        System.out.println("Địa chỉ: " +diaChi);
        model.addAttribute("list", diaChi);
        return "/giao_hang_nhanh/index";
    }


}
