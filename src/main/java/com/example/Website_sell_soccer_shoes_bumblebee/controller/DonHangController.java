package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDon;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDonChiTiet;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.LoaiGiay;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.HoaDonRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.HoaDonChiTietService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.HoaDonService;
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
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class DonHangController {

    @Autowired
    HoaDonService hoaDonService;
    @Autowired
    HoaDonServiceImpl hoaDonServiceImpl;

    @Data
    public static class SearchLoaiHoaDon {
        Integer key;
    }

    @ModelAttribute("dsLoaiDon")
    public Map<Integer, String> getDSTrangThai() {
        Map<Integer, String> dsLoaiDon = new HashMap<>();
        dsLoaiDon.put(0, "Bán Online");
        dsLoaiDon.put(1, "Bán tại quầy");
        return dsLoaiDon;
    }

    @Data
    public static class SearchForm {
        String keyword = "";

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date fromDate;

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date toDate;
    }

    @ModelAttribute("countHDCho")
    Integer countTTCho() {
        return hoaDonService.countHDCho();
    }

    @ModelAttribute("countHDXacNhan")
    Integer countTTXacNhan() {
        return hoaDonService.countHDXacNhan();
    }

    @ModelAttribute("countHDGiaoDVVC")
    Integer countTTGiaoDVVC() {
        return hoaDonService.countHDGiaoDVVC();
    }

    @ModelAttribute("countHDDangGiao")
    Integer countTTDangGiao() {
        return hoaDonService.countHDDangGiao();
    }

    @ModelAttribute("countHDHT")
    Integer countTTHoanThanh() {
        return hoaDonService.countHDHoanThanh();
    }

    @ModelAttribute("countHDTraHang")
    Integer countTTTraHang() {
        return hoaDonService.countHDTraHang();
    }

    @ModelAttribute("countHDDaTra")
    Integer countTTDT() {
        return hoaDonService.countHDDaHoanTra();
    }

    @ModelAttribute("countHDHuy")
    Integer countTTHuy() {
        return hoaDonService.countHDHuy();
    }

    @Autowired
    HoaDonRepository hoaDonRepository;

    @RequestMapping("/don-hang/search-loai-don")
    public String searchLoaiHoaDon(@ModelAttribute("searchLoaiDon") SearchLoaiHoaDon searchLoaiHoaDon, @RequestParam(defaultValue = "0", name = "page") Integer page, Model model) {
        if (page < 0) {
            page = 0;
        }
        Page<HoaDon> hoaDonPage;
        Pageable pageable = PageRequest.of(page, 8);
        if (searchLoaiHoaDon.key >= 0) {
            hoaDonPage = hoaDonService.searchLoaiHoaDon(searchLoaiHoaDon.key, pageable);
        } else {
            hoaDonPage = hoaDonRepository.findAll(pageable);
        }
        model.addAttribute("searchForm", new SearchForm());

        model.addAttribute("page", hoaDonPage);
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

    @GetMapping("/don-hang/exportExcel")
    public void exportToExcel(HttpServletResponse response) throws Exception {
        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        DateFormat dateFormat = (DateFormat) new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String current = dateFormat.format(new Date());
        String filename = "bills_" + current + ".xls";
        String headerValue = "attachment;filename=" + filename;

        response.setHeader(headerKey, headerValue);

        hoaDonServiceImpl.exportExcel(response);
    }

    @GetMapping("/don-hang/list-all")
    public String getAllDonHang(@RequestParam(defaultValue = "0", name = "p") Integer page, Model model) {
        model.addAttribute("searchForm", new SearchForm());
        model.addAttribute("searchLoaiDon", new SearchLoaiHoaDon());
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
        Pageable pageable = PageRequest.of(page, 10);
        Page<HoaDon> listS = this.hoaDonService.searchALlBetweenDates(searchForm.fromDate, searchForm.toDate, pageable);
        model.addAttribute("searchLoaiDon", new SearchLoaiHoaDon());
        model.addAttribute("searchForm", new HoaDonController.SearchForm());
        model.addAttribute("page", listS);
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
    //loại đơn: 0:on/ 1:tại quầy


    @RequestMapping("/don-hang/update-xac-nhan/{id}")
    public String trangThaiDangChuanBi(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                                       @RequestParam(defaultValue = "0", name = "p") int page, @PathVariable UUID id,
                                       @ModelAttribute("searchForm") HoaDonController.SearchForm searchForm
    ) {
        Pageable pageable = PageRequest.of(page, 10);
        model.addAttribute("searchForm", new HoaDonController.SearchForm());
        Page<HoaDon> donHangCho = hoaDonRepository.donHangChoXacNhan(pageable);

        model.addAttribute("searchLoaiDon", new SearchLoaiHoaDon());
        model.addAttribute("listChoXacNhan", donHangCho);
        HoaDon hoaDonDB = hoaDonService.getOne(id);
        if (hoaDonDB != null) {
            hoaDonService.updateHoaDon(id, 2, hoaDonDB);
        }
        System.out.println("Đơn hàng  cập nhật được trạng thái");

//        model.addAttribute("view", "../don-hang/listdh.jsp");
//        return "/admin/index";
        return "redirect:/don-hang/list-all";
    }

    @RequestMapping("/don-hang/update-chuan-bi/{id}")
    public String trangThaiChuanBi(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                                   @RequestParam(defaultValue = "0", name = "p") int page, @PathVariable UUID id,
                                   @ModelAttribute("searchForm") HoaDonController.SearchForm searchForm
    ) {
        Pageable pageable = PageRequest.of(page, 10);
        model.addAttribute("searchForm", new HoaDonController.SearchForm());
        model.addAttribute("searchLoaiDon", new SearchLoaiHoaDon());
        Page<HoaDon> donHangCho = hoaDonRepository.donHangChoXacNhan(pageable);
        model.addAttribute("listChoXacNhan", donHangCho);
        HoaDon hoaDonDB = hoaDonService.getOne(id);
        if (hoaDonDB != null) {
            hoaDonService.updateHoaDon(id, 3, hoaDonDB);
        }
        System.out.println("Đơn hàng  cập nhật được trạng thái");
//        model.addAttribute("view", "../don-hang/listdh.jsp");
//        return "/admin/index";
//        return "redirect:/don-hang/xem-don-hang/" + id;
        return "redirect:/don-hang/list-all";

    }

    @RequestMapping("/don-hang/huy-don-hang/{id}")
    public String huyDonHang(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                             @RequestParam(defaultValue = "0", name = "p") int p, @PathVariable UUID id,
                             @ModelAttribute("searchForm") HoaDonController.SearchForm searchForm
    ) {
        Pageable pageable = PageRequest.of(p, 10);
        model.addAttribute("searchForm", new HoaDonController.SearchForm());
        model.addAttribute("searchLoaiDon", new SearchLoaiHoaDon());
        Page<HoaDon> pageDonDaHuy = hoaDonRepository.donHangDaHuy(pageable);
        model.addAttribute("listHuy", pageDonDaHuy);
        HoaDon hoaDonDB = hoaDonService.getOne(id);
        if (hoaDonDB != null) {
            hoaDonService.updateHoaDon(id, 8, hoaDonDB);
        }
        System.out.println("Đơn hàng  cập nhật được trạng thái");

//        model.addAttribute("view", "../don-hang/listdh.jsp");
//        return "/admin/index";
//        return "redirect:/don-hang/xem-don-hang/" + id;
        return "redirect:/don-hang/list-all";

    }

    @RequestMapping("/don-hang/dang-giao/{id}")
    public String dangGiaoHang(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                               @RequestParam(defaultValue = "0", name = "p") int p, @PathVariable UUID id,
                               @ModelAttribute("searchForm") HoaDonController.SearchForm searchForm
    ) {
        Pageable pageable = PageRequest.of(p, 10);
        model.addAttribute("searchForm", new HoaDonController.SearchForm());
        model.addAttribute("searchLoaiDon", new SearchLoaiHoaDon());
        HoaDon hoaDonDB = hoaDonService.getOne(id);
        if (hoaDonDB != null) {
            // reuturn 4
            hoaDonService.updateHoaDon(id, 5, hoaDonDB);
        }
        System.out.println("Đơn hàng  cập nhật được trạng thái");

//        model.addAttribute("view", "../don-hang/listdh.jsp");
//        return "/admin/index";
//        return "redirect:/don-hang/xem-don-hang/" + id;
        return "redirect:/don-hang/list-all";

    }

    @RequestMapping("/don-hang/tra-hang/{id}")
    public String traHang(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                          @RequestParam(defaultValue = "0", name = "p") int p, @PathVariable UUID id,
                          @ModelAttribute("searchForm") HoaDonController.SearchForm searchForm
    ) {
        Pageable pageable = PageRequest.of(p, 10);
        model.addAttribute("searchForm", new HoaDonController.SearchForm());
        HoaDon hoaDonDB = hoaDonService.getOne(id);
        if (hoaDonDB != null) {
            hoaDonService.updateHoaDon(id, 6, hoaDonDB);
        }
        model.addAttribute("searchLoaiDon", new SearchLoaiHoaDon());
        System.out.println("Đơn hàng không cập nhật được trạng thái");
//        model.addAttribute("view", "../don-hang/listdh.jsp");
//        return "/admin/index";
//        return "redirect:/don-hang/xem-don-hang/" + id;
        return "redirect:/don-hang/list-all";

    }

    @RequestMapping("/don-hang/xac-nhan-giao/{id}")
    public String giaoDVVC(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                           @RequestParam(defaultValue = "0", name = "p") int p, @PathVariable UUID id,
                           @ModelAttribute("searchForm") HoaDonController.SearchForm searchForm
    ) {
        Pageable pageable = PageRequest.of(p, 10);
        model.addAttribute("searchLoaiDon", new SearchLoaiHoaDon());
        model.addAttribute("searchForm", new HoaDonController.SearchForm());
        HoaDon hoaDonDB = hoaDonService.getOne(id);
        if (hoaDonDB != null) {
            hoaDonService.updateHoaDon(id, 5, hoaDonDB);
        }
        System.out.println("Đơn hàng không cập nhật được trạng thái");
//        model.addAttribute("view", "../don-hang/listdh.jsp");
//        return "/admin/index";
//        return "redirect:/don-hang/xem-don-hang/" + id;
        return "redirect:/don-hang/list-all";

    }

    @RequestMapping("/don-hang/da-tra-hang/{id}")
    public String daTraHang(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                            @RequestParam(defaultValue = "0", name = "p") int p, @PathVariable UUID id,
                            @ModelAttribute("searchForm") HoaDonController.SearchForm searchForm
    ) {
        Pageable pageable = PageRequest.of(p, 10);
        model.addAttribute("searchLoaiDon", new SearchLoaiHoaDon());
        model.addAttribute("searchForm", new HoaDonController.SearchForm());
        HoaDon hoaDonDB = hoaDonService.getOne(id);
        if (hoaDonDB != null) {
            hoaDonService.updateHoaDon(id, 7, hoaDonDB);
        }
        System.out.println("Đơn hàng không cập nhật được trạng thái");
//        model.addAttribute("view", "../don-hang/listdh.jsp");
//        return "/admin/index";
//        return "redirect:/don-hang/xem-don-hang/" + id;
        return "redirect:/don-hang/list-all";

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
