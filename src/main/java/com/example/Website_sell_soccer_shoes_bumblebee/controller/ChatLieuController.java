package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChatLieu;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.DeGiay;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.ChatLieuRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.Impl.ChatLieuServiceImpl;
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

import java.util.UUID;

@Controller
@RequestMapping("chat-lieu")
public class ChatLieuController {

    @Autowired
    private ChatLieuRepository chatLieuRepo;

    @Autowired
    private ChatLieuServiceImpl clService;

    @Data
    public static class SearchForm {
        String keyword = "";
    }

    @GetMapping("hien-thi")
    public String index(Model model) {

        model.addAttribute("view", "../chat_lieu/list.jsp");
        return "/admin/index";
    }

    @GetMapping("hien-thi/list")
    public ResponseEntity<?> index(@RequestParam(defaultValue = "0", name = "page") Integer page, Model model) {
//        Pageable pageable = PageRequest.of(page, 5);
//        Page<ChatLieu> list = this.chatLieuRepo.findAll(pageable);
//        model.addAttribute("list", list);
//        model.addAttribute("searchForm", new SearchForm());

        return ResponseEntity.ok(chatLieuRepo.findAll());
    }


    @GetMapping("view-add")
    public String viewAdd(Model model, @ModelAttribute("cl") ChatLieu cl) {
        model.addAttribute("action", "/chat-lieu/add");
        model.addAttribute("view", "../chat_lieu/add_update.jsp");
        return "/admin/index";
    }

    @PostMapping("add")
    public String store(Model model,
                        @Valid @ModelAttribute("cl") ChatLieu cl,
                        BindingResult result
    ) {
        Boolean hasError = result.hasErrors();
        ChatLieu product = clService.getOne(cl.getMa());
        if (product != null) {
            hasError = true;
            model.addAttribute("maspError", "Vui lòng không nhập trùng mã");
            model.addAttribute("view", "../chat_lieu/add_update.jsp");
            return "/admin/index";
        }
        if (hasError) {
            // Báo lỗi
            model.addAttribute("view", "../chat_lieu/add_update.jsp");
            return "/admin/index";
        }
        this.chatLieuRepo.save(cl);
        model.addAttribute("view", "../chat_lieu/list.jsp");
        model.addAttribute("searchForm", new SearchForm());
        return "redirect:/chat-lieu/hien-thi";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") UUID id, Model model, @ModelAttribute("cl") ChatLieu cl) {
        ChatLieu chatLieu = clService.findById(id);
        model.addAttribute("cl", chatLieu);
        model.addAttribute("action", "/chat-lieu/update/" + chatLieu.getId());
        model.addAttribute("view", "../chat_lieu/add_update.jsp");
        return "/admin/index";
    }

    @PostMapping("update/{id}")
    public String update(@PathVariable("id") UUID id, Model model,
                         @Valid @ModelAttribute("cl") ChatLieu cl,
                         BindingResult result
    ) {
        Boolean hasError = result.hasErrors();
        if (hasError) {
            // Báo lỗi
            model.addAttribute("view", "../chat_lieu/add_update.jsp");
            return "/admin/index";
        }

        this.chatLieuRepo.save(cl);
        model.addAttribute("view", "../chat_lieu/list.jsp");
        model.addAttribute("searchForm", new SearchForm());
        return "redirect:/chat-lieu/hien-thi";
    }

    @GetMapping("search")
    public String search(Model model, @ModelAttribute("searchForm") SearchForm searchForm,
                         @RequestParam(defaultValue = "0", name = "page") Integer page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<ChatLieu> list = this.chatLieuRepo.search(searchForm.keyword, pageable);
        model.addAttribute("list", list);
        model.addAttribute("vm", new ChatLieu());
        model.addAttribute("view", "../chat_lieu/list.jsp");
        return "/admin/index";
    }

    @GetMapping("sort")
    public String Sort(Model model, @ModelAttribute("searchForm") SearchForm searchForm,
                       @RequestParam(defaultValue = "0") Integer page) {
        Sort sort = Sort.by(Sort.Direction.ASC, "ten");
        Pageable pageable = PageRequest.of(page, 5, sort);
        Page<ChatLieu> list = chatLieuRepo.findAll(pageable);
        model.addAttribute("list", list);
        model.addAttribute("vm", new ChatLieu());
        model.addAttribute("view", "../chat_lieu/list.jsp");
        return "/admin/index";
    }
}
