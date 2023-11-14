package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.KhachHang;
import com.example.Website_sell_soccer_shoes_bumblebee.service.Impl.GiaoHangNhanhServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.asm.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Controller
public class GiaoHangNhanhController {

    @Autowired
    private GiaoHangNhanhServiceImpl giaoHangNhanhService;

    @RequestMapping("/tinhthanh")
    public String getTinhThanhList(Model model) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String response = giaoHangNhanhService.callGhnApi().getBody();

        try {
            Map<String, Object> responseMap = objectMapper.readValue(response, Map.class);

            if (responseMap.containsKey("data")) {
                List<Map<String, Object>> provinces = (List<Map<String, Object>>) responseMap.get("data");
                List<String> provinceIDs = new ArrayList<>();
                List<String> provinceNames = new ArrayList<>();

                for (Map<String, Object> province : provinces) {
//                    String provinceID = String.valueOf(province.get("ProvinceID"));
                    String provinceName = String.valueOf(province.get("ProvinceName"));
//                    provinceIDs.add(provinceID);
                    provinceNames.add(provinceName);
                    List<Map<String, Object>> districts = (List<Map<String, Object>>) province.get("Districts");

                }
                model.addAttribute("provinceIDs", provinceIDs);
                model.addAttribute("provinceNames", provinceNames);
            } else {
                System.out.println("Error: Response does not contain 'data' field");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "/giao_hang_nhanh/index";
//        ObjectMapper objectMapper = new ObjectMapper();
//        String response = giaoHangNhanhService.callGhnApi().getBody();
//
//        try {
//            Map<String, Object> responseMap = objectMapper.readValue(response, Map.class);
//
//            if (responseMap.containsKey("data")) {
//                List<Map<String, Object>> provinces = (List<Map<String, Object>>) responseMap.get("data");
//
//                // Tạo một danh sách chứa thông tin tỉnh, huyện và xã
//                List<Map<String, Object>> locations = new ArrayList<>();
//
//                for (Map<String, Object> province : provinces) {
//                    String provinceName = String.valueOf(province.get("ProvinceName"));
//                    if (province.containsKey("Districts") && province.get("Districts") != null) {
//                        // Trích xuất danh sách huyện tương ứng với tỉnh
//                        List<Map<String, Object>> districts = (List<Map<String, Object>>) province.get("Districts");
//
//                        for (Map<String, Object> district : districts) {
//                            String districtName = String.valueOf(district.get("DistrictName"));
//
//                            // Trích xuất danh sách xã tương ứng với huyện
//                            if (district.containsKey("Wards") && district.get("Wards") != null) {
//                                List<Map<String, Object>> wards = (List<Map<String, Object>>) district.get("Wards");
//
//                                Map<String, Object> location = new HashMap<>();
//                                location.put("provinceName", provinceName);
//                                location.put("districtName", districtName);
//                                location.put("wards", wards);
//
//                                locations.add(location);
//                            }
//                        }
//                    }
//                }
//                model.addAttribute("locations", locations);
//            } else {
//                System.out.println("Error: Response does not contain 'data' field");
//            }
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        return "/giao_hang_nhanh/index";
    }

    @GetMapping("/giao-hang-getAll")
    public String getAll(Model model) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, KhachHang> map = objectMapper.readValue(giaoHangNhanhService.callGhnApi().getBody(), Map.class);
        System.out.println(giaoHangNhanhService.callGhnApi().getBody());
        String diaChi = map.get("ProvinceName").toString();
        System.out.println("Địa chỉ: " + diaChi);
        model.addAttribute("list", diaChi);
        return "/giao_hang_nhanh/index";

    }


}
