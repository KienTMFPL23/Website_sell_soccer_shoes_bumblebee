package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDon;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDonChiTiet;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.HoaDonRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.HoaDonChiTietService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.HoaDonService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class DonHangController {

    @Autowired
    HoaDonService hoaDonService;

    @Data
    public static class SearchForm {
        String keyword = "";

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date fromDate;

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date toDate;
    }

    @Autowired
    HoaDonRepository hoaDonRepository;

    @GetMapping("/don-hang/list-all")
    public String getAllDonHang(@RequestParam(defaultValue = "0", name = "p") Integer page, Model model) {
        model.addAttribute("searchForm", new SearchForm());
        if (page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, 10);

        Page<HoaDon> list = hoaDonRepository.findAll(pageable);
        model.addAttribute("page", list);

        Page<HoaDon> donHangCho = hoaDonRepository.donHangChoXacNhan(pageable);
        model.addAttribute("listChoXacNhan", donHangCho);

        Page<HoaDon> pageDonDaHuy = hoaDonRepository.donHangDaHuy(pageable);
        model.addAttribute("listHuy", pageDonDaHuy);

        Page<HoaDon> pageChuanBi = hoaDonRepository.donHangDangChuanBi(pageable);
        model.addAttribute("listChuanBi", pageChuanBi);

        Page<HoaDon> donDangGiao = hoaDonRepository.donHangDangGiao(pageable);
        model.addAttribute("listDonGiao", donDangGiao);

        Page<HoaDon> donHoanThanh = hoaDonRepository.donHangHoanThanh(pageable);
        model.addAttribute("listHoanThanh", donHoanThanh);

        Page<HoaDon> donTra = hoaDonRepository.donHangTra(pageable);
        model.addAttribute("listDonTra", donTra);

        Page<HoaDon> donDaTra = hoaDonRepository.donHangDaTra(pageable);
        model.addAttribute("listDonDaTra", donDaTra);


        model.addAttribute("view", "../don-hang/listdh.jsp");
        return "/admin/index";
    }

    @RequestMapping(value = "/don-hang/searchDate")
    public String searchDate(Model model,
                             @RequestParam(defaultValue = "0", name = "p") int page,
                             @ModelAttribute("searchForm") HoaDonController.SearchForm searchForm
    ) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<HoaDon> listS = this.hoaDonService.searchALlBetweenDates(searchForm.fromDate, searchForm.toDate, pageable);
        model.addAttribute("searchForm", new HoaDonController.SearchForm());
        model.addAttribute("page", listS);
        model.addAttribute("view", "../don-hang/listdh.jsp");
        return "/admin/index";
    }
    //hinh thức thanh toán: 0: tại quầy; 1: khi nhận hàng ; 2: paypal
    //trạng thái: theo phần hình thức thanh toán; bán on chờ xác nhận; bán off-> hoàn thành
    // 1: chờ xác nhận
    // 2: xác nhận-> đng chuẩn bị
    // 3: giao cho đơn vị vận chuyển
    // 4: đang giao
    // 5: hoàn thành
    //6: trả hàng
    //7 : đã hoàn trả
    //8 : đã huỷ
    // xác nhận -> đang chuẩn bị


    @RequestMapping("/don-hang/update-xac-nhan/{id}")
    public String trangThaiDangChuanBi(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                                       @RequestParam(defaultValue = "0", name = "p") int page, @PathVariable UUID id,
                                       @ModelAttribute("searchForm") HoaDonController.SearchForm searchForm
    ) {
        Pageable pageable = PageRequest.of(page, 5);
        model.addAttribute("searchForm", new HoaDonController.SearchForm());
        Page<HoaDon> donHangCho = hoaDonRepository.donHangChoXacNhan(pageable);
        model.addAttribute("listChoXacNhan", donHangCho);
        HoaDon hoaDonDB = hoaDonService.getOne(id);
        if (hoaDonDB != null) {
            hoaDonService.updateHoaDon(id, 2, hoaDonDB);
        }
        System.out.println("Đơn hàng  cập nhật được trạng thái");

//        model.addAttribute("view", "../don-hang/listdh.jsp");
//        return "/admin/index";
        return "redirect:/don-hang/xem-don-hang/" + id;
    }

    @RequestMapping("/don-hang/update-chuan-bi/{id}")
    public String trangThaiChuanBi(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                                   @RequestParam(defaultValue = "0", name = "p") int page, @PathVariable UUID id,
                                   @ModelAttribute("searchForm") HoaDonController.SearchForm searchForm
    ) {
        Pageable pageable = PageRequest.of(page, 5);
        model.addAttribute("searchForm", new HoaDonController.SearchForm());
        Page<HoaDon> donHangCho = hoaDonRepository.donHangChoXacNhan(pageable);
        model.addAttribute("listChoXacNhan", donHangCho);
        HoaDon hoaDonDB = hoaDonService.getOne(id);
        if (hoaDonDB != null) {
            hoaDonService.updateHoaDon(id, 3, hoaDonDB);
        }
        System.out.println("Đơn hàng  cập nhật được trạng thái");
//        model.addAttribute("view", "../don-hang/listdh.jsp");
//        return "/admin/index";
        return "redirect:/don-hang/xem-don-hang/" + id;

    }

    @RequestMapping("/don-hang/huy-don-hang/{id}")
    public String huyDonHang(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                             @RequestParam(defaultValue = "0", name = "p") int p, @PathVariable UUID id,
                             @ModelAttribute("searchForm") HoaDonController.SearchForm searchForm
    ) {
        Pageable pageable = PageRequest.of(p, 5);
        model.addAttribute("searchForm", new HoaDonController.SearchForm());
        Page<HoaDon> pageDonDaHuy = hoaDonRepository.donHangDaHuy(pageable);
        model.addAttribute("listHuy", pageDonDaHuy);
        HoaDon hoaDonDB = hoaDonService.getOne(id);
        if (hoaDonDB != null) {
            hoaDonService.updateHoaDon(id, 8, hoaDonDB);
        }
        System.out.println("Đơn hàng  cập nhật được trạng thái");

//        model.addAttribute("view", "../don-hang/listdh.jsp");
//        return "/admin/index";
        return "redirect:/don-hang/xem-don-hang/" + id;

    }

    @RequestMapping("/don-hang/dang-giao/{id}")
    public String dangGiaoHang(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                               @RequestParam(defaultValue = "0", name = "p") int p, @PathVariable UUID id,
                               @ModelAttribute("searchForm") HoaDonController.SearchForm searchForm
    ) {
        Pageable pageable = PageRequest.of(p, 5);
        model.addAttribute("searchForm", new HoaDonController.SearchForm());

        HoaDon hoaDonDB = hoaDonService.getOne(id);
        if (hoaDonDB != null) {
            hoaDonService.updateHoaDon(id, 4, hoaDonDB);
        }
        System.out.println("Đơn hàng  cập nhật được trạng thái");

//        model.addAttribute("view", "../don-hang/listdh.jsp");
//        return "/admin/index";
        return "redirect:/don-hang/xem-don-hang/" + id;

    }

    @RequestMapping("/don-hang/tra-hang/{id}")
    public String traHang(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                          @RequestParam(defaultValue = "0", name = "p") int p, @PathVariable UUID id,
                          @ModelAttribute("searchForm") HoaDonController.SearchForm searchForm
    ) {
        Pageable pageable = PageRequest.of(p, 5);
        model.addAttribute("searchForm", new HoaDonController.SearchForm());
        HoaDon hoaDonDB = hoaDonService.getOne(id);
        if (hoaDonDB != null) {
            hoaDonService.updateHoaDon(id, 6, hoaDonDB);
        }
        System.out.println("Đơn hàng không cập nhật được trạng thái");
//        model.addAttribute("view", "../don-hang/listdh.jsp");
//        return "/admin/index";
        return "redirect:/don-hang/xem-don-hang/" + id;

    }
    @RequestMapping("/don-hang/xac-nhan-giao/{id}")
    public String giaoDVVC(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                          @RequestParam(defaultValue = "0", name = "p") int p, @PathVariable UUID id,
                          @ModelAttribute("searchForm") HoaDonController.SearchForm searchForm
    ) {
        Pageable pageable = PageRequest.of(p, 5);
        model.addAttribute("searchForm", new HoaDonController.SearchForm());
        HoaDon hoaDonDB = hoaDonService.getOne(id);
        if (hoaDonDB != null) {
            hoaDonService.updateHoaDon(id, 5, hoaDonDB);
        }
        System.out.println("Đơn hàng không cập nhật được trạng thái");
//        model.addAttribute("view", "../don-hang/listdh.jsp");
//        return "/admin/index";
        return "redirect:/don-hang/xem-don-hang/" + id;

    }

    @RequestMapping("/don-hang/da-tra-hang/{id}")
    public String daTraHang(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                            @RequestParam(defaultValue = "0", name = "p") int p, @PathVariable UUID id,
                            @ModelAttribute("searchForm") HoaDonController.SearchForm searchForm
    ) {
        Pageable pageable = PageRequest.of(p, 5);
        model.addAttribute("searchForm", new HoaDonController.SearchForm());
        HoaDon hoaDonDB = hoaDonService.getOne(id);
        if (hoaDonDB != null) {
            hoaDonService.updateHoaDon(id, 7, hoaDonDB);
        }
        System.out.println("Đơn hàng không cập nhật được trạng thái");
//        model.addAttribute("view", "../don-hang/listdh.jsp");
//        return "/admin/index";
        return "redirect:/don-hang/xem-don-hang/" + id;

    }

    //view chờ xác nhận . (xem đơn hàng)
    @GetMapping("/don-hang/xem-don-hang/{id}")
    public String donHangView(@PathVariable UUID id, Model model) {
        HoaDon hoaDon = hoaDonRepository.findById(id).orElse(null);
        model.addAttribute("hoaDon", hoaDon);
        model.addAttribute("view", "../don-hang/xem-don-hang.jsp");
        return "/admin/index";
    }
}
