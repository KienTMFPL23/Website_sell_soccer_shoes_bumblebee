//package com.example.Website_sell_soccer_shoes_bumblebee.controller;
//
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.client.RestTemplate;
//
//
//@Controller
//public class GiaoHangController {
//
//    @GetMapping("/call-ghn-api")
//    public String callGHNApi(Model model) {
//        // Tạo một RestTemplate
//        RestTemplate restTemplate = new RestTemplate();
//
//        // Tạo tiêu đề
//        HttpHeaders headers = new HttpHeaders();
////        headers.set("API-Key", "190218");
//        headers.set("Token", "fead4006-77b8-11ee-8bfa-8a2dda8ec551");
//
//        // Tạo đối tượng HttpEntity với tiêu đề
//        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
//
//        // Thực hiện yêu cầu GET hoặc POST với tiêu đề
//        ResponseEntity<String> response = restTemplate.exchange("https://online-gateway.ghn.vn/shiip/public-api", HttpMethod.GET, requestEntity, String.class);
//
//        // Xử lý phản hồi
//        String responseBody = response.getBody();
//
//        // Đưa dữ liệu phản hồi vào model để hiển thị trên trang JSP
//        model.addAttribute("ghnApiResponse", responseBody);
//
//        // Trả về tên trang JSP để hiển thị kết quả
//        return "/giao_hang_nhanh/index"; // Tên trang JSP để hiển thị kết quả
//    }
//}
