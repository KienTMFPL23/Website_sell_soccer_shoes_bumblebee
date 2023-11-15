//package com.example.Website_sell_soccer_shoes_bumblebee.controller;
//
//import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChatLieu;
//import com.example.Website_sell_soccer_shoes_bumblebee.entity.KhachHang;
//import com.example.Website_sell_soccer_shoes_bumblebee.repository.KhachHangRepository;
//import com.example.Website_sell_soccer_shoes_bumblebee.service.Impl.KhachHangServiceImpl;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.UUID;
//
//@Controller
//public class KhachHangController {
//
//    @Autowired
//    private KhachHangRepository khachHangRepository;
//
//    @Autowired
//    private KhachHangServiceImpl khachHangService;
//
//    @GetMapping("/khach-hang/hien-thi")
//    public String index(@RequestParam(defaultValue = "0", name = "page") Integer page,Model model) {
//        Pageable pageable = PageRequest.of(page, 5);
//        Page<KhachHang> list = this.khachHangRepository.findAll(pageable);
//        model.addAttribute("list", list);
//        model.addAttribute("view", "../khach_hang/list.jsp");
//        return "/admin/index";
//    }
//
////    @GetMapping("/hien-thi/list")
////    public ResponseEntity<?> index() {
////        return ResponseEntity.ok(khachHangRepository.findAll());
////    }
//
//    @GetMapping("/khach-hang/view-add")
//    public String viewAdd(Model model, @ModelAttribute("kh") KhachHang kh) {
//        model.addAttribute("action", "/khach-hang/add");
//        model.addAttribute("view", "../khach_hang/add_update.jsp");
//        return "/admin/index";
//    }
//
//    @PostMapping("/khach-hang/add")
//    public String store(Model model,
//                        @Valid @ModelAttribute("kh") KhachHang kh,
//                        BindingResult result
//    ) {
//        Boolean hasError = result.hasErrors();
//        KhachHang product = khachHangService.getOne(kh.getMa());
//        if (product != null) {
//            hasError = true;
//            model.addAttribute("maspError", "Vui lòng không nhập trùng mã");
//            model.addAttribute("view", "../khach_hang/add_update.jsp");
//            return "/admin/index";
//        }
//        if (hasError) {
//            // Báo lỗi
//            model.addAttribute("view", "../khach_hang/add_update.jsp");
//            return "/admin/index";
//        }
//        this.khachHangRepository.save(kh);
//        model.addAttribute("view", "../khach_hang/list.jsp");
////        model.addAttribute("searchForm", new ChatLieuController.SearchForm());
//        return "redirect:/khach-hang/hien-thi";
//    }
////
////    @GetMapping("edit/{id}")
////    public String edit(@PathVariable("id") UUID id, Model model, @ModelAttribute("cl") ChatLieu cl) {
////        ChatLieu chatLieu = clService.findById(id);
////        model.addAttribute("cl", chatLieu);
////        model.addAttribute("action", "/chat-lieu/update/" + chatLieu.getId());
////        model.addAttribute("view", "../chat_lieu/add_update.jsp");
////        return "/admin/index";
////    }
////
////    @PostMapping("update/{id}")
////    public String update(@PathVariable("id") UUID id, Model model,
////                         @Valid @ModelAttribute("cl") ChatLieu cl,
////                         BindingResult result
////    ) {
////        Boolean hasError = result.hasErrors();
////        if (hasError) {
////            // Báo lỗi
////            model.addAttribute("view", "../chat_lieu/add_update.jsp");
////            return "/admin/index";
////        }
////
////        this.chatLieuRepo.save(cl);
////        model.addAttribute("view", "../chat_lieu/list.jsp");
////        model.addAttribute("searchForm", new ChatLieuController.SearchForm());
////        return "redirect:/chat-lieu/hien-thi";
////    }
////
////    @GetMapping("search")
////    public String search(Model model, @ModelAttribute("searchForm") ChatLieuController.SearchForm searchForm,
////                         @RequestParam(defaultValue = "0", name = "page") Integer page) {
////        Pageable pageable = PageRequest.of(page, 5);
////        Page<ChatLieu> list = this.chatLieuRepo.search(searchForm.keyword, pageable);
////        model.addAttribute("list", list);
////        model.addAttribute("vm", new ChatLieu());
////        model.addAttribute("view", "../chat_lieu/list.jsp");
////        return "/admin/index";
////    }
//
//}
