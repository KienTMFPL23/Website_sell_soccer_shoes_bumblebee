package com.example.Website_sell_soccer_shoes_bumblebee.controller;


import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChatLieu;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDon;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDonChiTiet;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.HoaDonChiTietRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.HoaDonRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.Impl.HoaDonChiTietServiceImpl;
import com.example.Website_sell_soccer_shoes_bumblebee.service.Impl.HoaDonServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class HoaDonController {

    @Autowired
    private HoaDonRepository hdRepo;

    @Autowired
    private HoaDonServiceImpl hoaDonService;

    @Autowired
    private HoaDonChiTietServiceImpl hdctService;

    @Autowired
    private HoaDonChiTietRepository hdctRepo;

    @Data
    public static class SearchForm {
        String keyword = "";
    }

    @Data
    public static class SearchDate {
        Date startDate;
        Date endDate;
    }

    @GetMapping("/hoa-don/hien-thi")
    public String index(@RequestParam(defaultValue = "0", name = "page") Integer page, Model model) {
        model.addAttribute("searchForm", new SearchForm());
        model.addAttribute("searchDate", new SearchDate());
        Pageable pageable = PageRequest.of(page, 10);
        Page<HoaDon> list = hdRepo.findAll(pageable);
        model.addAttribute("list", list);
        model.addAttribute("view", "../hoa_don/list.jsp");
        return "/admin/index";
    }

    @GetMapping("/hoa-don-chi-tiet/hien-thi/{ma}")
    public String hienThi(Model model, HttpSession session, @PathVariable("ma") UUID id) {
        model.addAttribute("searchForm", new SearchForm());
        model.addAttribute("searchDate", new SearchDate());
        session.setAttribute("listHDCT", hdctService.getHoaDonById(id));
        return "/admin/index";
    }


    @RequestMapping("/hoa-don/search")
    public String search(Model model, @ModelAttribute("searchForm") SearchForm searchForm,
                         @RequestParam(defaultValue = "0", name = "page") int page
    ) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<HoaDon> hoaDons = this.hdRepo.search(searchForm.keyword, pageable);
        model.addAttribute("searchDate", new SearchDate());
        model.addAttribute("list", hoaDons);
        model.addAttribute("view", "../hoa_don/list.jsp");
        return "/admin/index";
    }

    @RequestMapping(value = "/hoa-don/searchDate")
    public String searchDate(Model model, @ModelAttribute("searchDate") SearchDate searchDate,
                             @RequestParam(defaultValue = "0", name = "page") int page, BindingResult bindingResult
    ) {
        model.addAttribute("searchForm", new SearchForm());
        Pageable pageable = PageRequest.of(page, 5);
        String sDate = "09-10-2023";
        String eDate = "11-10-2023";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date sdate = dateFormat.parse(sDate, new ParsePosition(0));
        Date edate = dateFormat.parse(eDate, new ParsePosition(0));
        System.out.println(searchDate.startDate + "-"+searchDate.endDate);
        Page<HoaDon> hoaDons = this.hoaDonService.getALlBetweenDates(sdate, edate, pageable);
        model.addAttribute("list", hoaDons);
        model.addAttribute("view", "../hoa_don/list.jsp");
        return "/admin/index";
    }


}
