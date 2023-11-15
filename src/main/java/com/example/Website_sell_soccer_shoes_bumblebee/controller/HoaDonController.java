package com.example.Website_sell_soccer_shoes_bumblebee.controller;


import com.example.Website_sell_soccer_shoes_bumblebee.entity.*;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.HoaDonRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.GioHangChiTietService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.HoaDonChiTietService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.Impl.HoaDonServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    private HoaDonChiTietService hoaDonChiTietService;


    @Autowired
    private GioHangChiTietService gioHangChiTietService;

    @Data
    public static class SearchForm {
        String keyword = "";

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date fromDate;

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date toDate;

    }

    @Data
    public static class SearchStatus {
        Integer status;
    }

    @ModelAttribute("listHoaDon")
    List<HoaDon> listHoaDon() {
        return hdRepo.findAll();
    }


    @GetMapping("/hoa-don/hien-thi")
    public String index(@RequestParam(defaultValue = "0", name = "page") Integer page, Model model) {
        model.addAttribute("searchForm", new SearchForm());
        model.addAttribute("searchStatus", new SearchStatus());
        Pageable pageable = PageRequest.of(page, 8);
        Page<HoaDon> list = hdRepo.findAll(pageable);
        model.addAttribute("list", list);
        //model.addAttribute("listHD", hdRepo.listHoaDonByTrangThai());
        model.addAttribute("view", "../hoa_don/list.jsp");
        return "/admin/index";
    }

    @RequestMapping("/hoa-don/search-by-status")
    public String searchByChatLieu(@ModelAttribute("searchStatus") SearchStatus searchStatus, @RequestParam(defaultValue = "0" , name = "page") Integer page, Model model) {
        if (page < 0) {
            page = 0;
        }
        Page<HoaDon> hoaDonPage;
        Pageable pageable = PageRequest.of(page, 8);
        if (searchStatus.status > 0 ) {
            hoaDonPage = hoaDonService.searchByStatusBills(searchStatus.status, pageable);
        } else {
            hoaDonPage = hdRepo.findAll(pageable);
        }
        model.addAttribute("searchForm" , new SearchForm());
        model.addAttribute("list", hoaDonPage);
        model.addAttribute("view", "../hoa_don/list.jsp");
        return "/admin/index";
    }

//    @GetMapping("/hoa-don-chi-tiet/hien-thi/{ma}")
//    public String hienThi(Model model, @PathVariable("ma") UUID id) {
//        model.addAttribute("searchForm", new SearchForm());
//        List<HoaDonChiTiet> listHDCT = hoaDonChiTietService.getListHoaDonCTByIdHoaDon(id);
//        Double sumMoney = hoaDonChiTietService.getTotalMoney(listHDCT);
//        model.addAttribute("sumMoney", sumMoney);
//
//        System.out.println("Tổng tiền: " + sumMoney);
//        model.addAttribute("hoaDons", hoaDonChiTietService.getHoaDonById(id));
//        return "/admin/index";
//    }


    @RequestMapping("/hoa-don/search")
    public String search(Model model, @ModelAttribute("searchForm") SearchForm searchForm,
                         @RequestParam(defaultValue = "0", name = "page") int page
    ) {
        Pageable pageable = PageRequest.of(page, 8);
        Page<HoaDon> hoaDons = this.hdRepo.search(searchForm.keyword, pageable);
        model.addAttribute("searchForm", new SearchForm());
        model.addAttribute("list", hoaDons);
        model.addAttribute("view", "../hoa_don/list.jsp");
        return "/admin/index";
    }


    @RequestMapping(value = "/hoa-don/searchDate")
    public String searchDate(Model model,
                             @RequestParam(defaultValue = "0", name = "page") int page,
                             @ModelAttribute("searchForm") SearchForm searchForm
    ) {
        Pageable pageable = PageRequest.of(page, 8);
        Page<HoaDon> listS = this.hoaDonService.searchALlBetweenDates(searchForm.fromDate,  searchForm.toDate = new Date(new Date().getTime() + 86400000), pageable);
        model.addAttribute("searchForm", new SearchForm());
        model.addAttribute("searchStatus", new SearchStatus());
        model.addAttribute("list", listS);
        model.addAttribute("view", "../hoa_don/list.jsp");
        return "/admin/index";
    }


    @RequestMapping(value = "/hoa-don/update/{maHoaDon}", method = RequestMethod.POST)
    public String updateStatus(@PathVariable("maHoaDon") String maHoaDon, @RequestBody() String data) {
        HoaDon hoaDon = hoaDonService.searchHoaDon(maHoaDon);
        data = data.substring(0, data.indexOf("="));
        hoaDon.setTrangThai(Integer.valueOf(data));
        this.hoaDonService.saveHoaDon(hoaDon);
        return "redirect:/hoa-don/hien-thi";
    }

    @GetMapping("/hoa-don/exportExcel")
    public void exportToExcel(HttpServletResponse response) throws Exception {
        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        DateFormat dateFormat = (DateFormat) new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String current = dateFormat.format(new Date());
        String filename = "bills_" + current + ".xls";
        String headerValue = "attachment;filename=" + filename;

        response.setHeader(headerKey, headerValue);

        hoaDonService.exportExcel(response);
    }

}
