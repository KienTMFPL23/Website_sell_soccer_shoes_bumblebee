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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

        @DateTimeFormat( pattern="yyyy-MM-dd HH:mm:ss")
        Date fromDate;

        @DateTimeFormat( pattern="yyyy-MM-dd HH:mm:ss")
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

    @ModelAttribute("countHDDoiHang")
    Integer countTTTraHang() {
        return hoaDonService.countHDTraHang();
    }

    @ModelAttribute("countHDDaDoi")
    Integer countTTDT() {
        return hoaDonService.countHDDaHoanTra();
    }

    @ModelAttribute("countHDHuy")
    Integer countTTHuy() {
        return hoaDonService.countHDHuy();
    }

    @ModelAttribute("countHD")
    Integer countTongHoaDon() {
        return hoaDonService.countHD();
    }

    @Autowired
    HoaDonRepository hoaDonRepository;

    @RequestMapping("/don-hang/search-loai-don")
    public String searchLoaiHoaDon(
            @ModelAttribute("searchLoaiDon") SearchLoaiHoaDon searchLoaiHoaDon,
            @RequestParam(defaultValue = "0", name = "p") Integer page,
            @RequestParam(name = "donHang", required = false) String donHang,
            Model model
    ) {
//        if (page < 0) {
//            page = 0;
//        }
//        Page<HoaDon> hoaDonPage;
//        Pageable pageable = PageRequest.of(page, 5);
//
//        if (searchLoaiHoaDon.key >= 0) {
//            hoaDonPage = hoaDonService.searchLoaiHoaDon(searchLoaiHoaDon.key, pageable);
//            model.addAttribute("page", hoaDonPage);
//
//            // Check if donHang is provided and use the corresponding repository method
//            if ("cho-xac-nhan".equals(donHang)) {
//                hoaDonPage = hoaDonRepository.searchLoaiHoaDonChoXacNhan(searchLoaiHoaDon.key, pageable);
//                model.addAttribute("page", hoaDonPage);
//            } else if ("chuan-bi".equals(donHang)) {
//                hoaDonPage = hoaDonRepository.searchLoaiHoaDonChuanBi(searchLoaiHoaDon.key, pageable);
//                model.addAttribute("page", hoaDonPage);
//            } else if ("dang-giao".equals(donHang)) {
//                hoaDonPage = hoaDonRepository.searchLoaiHoaDonDangGiao(searchLoaiHoaDon.key, pageable);
//                model.addAttribute("page", hoaDonPage);
//            } else if ("hoan-thanh".equals(donHang)) {
//                hoaDonPage = hoaDonRepository.searchLoaiHoaDonHoanThanh(searchLoaiHoaDon.key, pageable);
//                model.addAttribute("page", hoaDonPage);
//            } else if ("don-da-doi".equals(donHang)) {
//                hoaDonPage = hoaDonRepository.searchLoaiHoaDonDaTra(searchLoaiHoaDon.key, pageable);
//                model.addAttribute("page", hoaDonPage);
//            } else if ("don-doi".equals(donHang)) {
//                hoaDonPage = hoaDonRepository.searchLoaiHoaDonTraHang(searchLoaiHoaDon.key, pageable);
//                model.addAttribute("page", hoaDonPage);
//            } else if ("huy".equals(donHang)) {
//                hoaDonPage = hoaDonRepository.searchLoaiHoaDonDaHuy(searchLoaiHoaDon.key, pageable);
//                model.addAttribute("page", hoaDonPage);
//            } else if ("all".equals(donHang)) {
//                hoaDonPage = hoaDonService.searchLoaiHoaDon(searchLoaiHoaDon.key, pageable);
//                model.addAttribute("page", hoaDonPage);
//            } else {
//                model.addAttribute("messError", "Không thể tìm kiếm ! Vui lòng xem lại dữ liệu !");
//            }
//        }
//
//        model.addAttribute("searchForm", new SearchForm());
////        model.addAttribute("page", hoaDonPage);
//        model.addAttribute("view", "../don-hang/listdh.jsp");
//        return "/admin/index";

        //1
        if (page < 0) {
            page = 0;
        }
        Page<HoaDon> hoaDonPage;
        Pageable pageable = PageRequest.of(page, 50);
        model.addAttribute("donHang","search-loai-don");

        if (searchLoaiHoaDon.key >= 0) {
            hoaDonPage = hoaDonService.searchLoaiHoaDon(searchLoaiHoaDon.key, pageable);
            model.addAttribute("page", hoaDonPage);

            if (donHang != null && !donHang.isEmpty()) {
                hoaDonPage = searchByDonHang(donHang, searchLoaiHoaDon.key, pageable);
                model.addAttribute("page", hoaDonPage);
            }
        }

        model.addAttribute("searchForm", new SearchForm());
        model.addAttribute("view", "../don-hang/listdh.jsp");
        return "/admin/index";

//        if (page < 0) {
//            page = 0;
//        }
//
//        Page<HoaDon> hoaDonPage;
//        Pageable pageable = PageRequest.of(page, 5);
//
//        if (donHang != null && !donHang.isEmpty()) {
//            switch (donHang) {
//                case "cho-xac-nhan":
//                    hoaDonPage = hoaDonRepository.searchLoaiHoaDonChoXacNhan(searchLoaiHoaDon.key, pageable);
//                    break;
//                case "chuan-bi":
//                    hoaDonPage = hoaDonRepository.searchLoaiHoaDonChuanBi(searchLoaiHoaDon.key, pageable);
//                    break;
//                case "dang-giao":
//                    hoaDonPage = hoaDonRepository.searchLoaiHoaDonDangGiao(searchLoaiHoaDon.key, pageable);
//                    break;
//                case "hoan-thanh":
//                    hoaDonPage = hoaDonRepository.searchLoaiHoaDonHoanThanh(searchLoaiHoaDon.key, pageable);
//                    break;
//                case "don-da-doi":
//                    hoaDonPage = hoaDonRepository.searchLoaiHoaDonDaTra(searchLoaiHoaDon.key, pageable);
//
//                    break;
//                case "don-doi":
//                    hoaDonPage = hoaDonRepository.searchLoaiHoaDonTraHang(searchLoaiHoaDon.key, pageable);
//
//                    break;
//                case "huy":
//                    hoaDonPage = hoaDonRepository.searchLoaiHoaDonDaHuy(searchLoaiHoaDon.key, pageable);
//                    break;
//                case "all":
//                    hoaDonPage = hoaDonService.searchLoaiHoaDon(searchLoaiHoaDon.key, pageable);
//
//                    break;
//                // Thêm các trường hợp khác tương ứng với loại hoá đơn của bạn
//                default:
//                    hoaDonPage = hoaDonService.searchLoaiHoaDon(-1, pageable); // Mặc định nếu không có loại hoá đơn
//
//                    break;
//            }
//        } else {
//            hoaDonPage = hoaDonService.searchLoaiHoaDon(-1, pageable); // Mặc định nếu không có loại hoá đơn
//            model.addAttribute("messError", "Không thể tìm kiếm ! Vui lòng xem lại dữ liệu !");
//        }

//        model.addAttribute("page", hoaDonPage);
//        model.addAttribute("searchForm", new SearchForm());
//        model.addAttribute("view", "../don-hang/listdh.jsp");
//        return "/admin/index";


    }

        private Page<HoaDon> searchByDonHang(String donHang, Integer loaiDonId, Pageable pageable) {
        switch (donHang) {
            case "cho-xac-nhan":
                return hoaDonRepository.searchLoaiHoaDonChoXacNhan(loaiDonId, pageable);
            case "chuan-bi":
                return hoaDonRepository.searchLoaiHoaDonChuanBi(loaiDonId, pageable);
            case "dang-giao":
                return hoaDonRepository.searchLoaiHoaDonDangGiao(loaiDonId, pageable);
            case "hoan-thanh":
                return hoaDonRepository.searchLoaiHoaDonHoanThanh(loaiDonId, pageable);
            case "don-da-doi":
                return hoaDonRepository.searchLoaiHoaDonDaTra(loaiDonId, pageable);
            case "don-doi":
                return hoaDonRepository.searchLoaiHoaDonTraHang(loaiDonId, pageable);
            case "huy":
                return hoaDonRepository.searchLoaiHoaDonDaHuy(loaiDonId, pageable);
            case "all":
                return hoaDonService.searchLoaiHoaDon(loaiDonId, pageable);
            default:
                return new PageImpl<>(Collections.emptyList());
        }
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
        Pageable pageable = PageRequest.of(page, 5);
        String donHang = "all";
        model.addAttribute("donHang", donHang);

        Page<HoaDon> list = hoaDonRepository.findAll(pageable);
        model.addAttribute("page", list);

        model.addAttribute("view", "../don-hang/listdh.jsp");
        return "/admin/index";
    }

    //listChoXacNhan
    @GetMapping("/don-hang/list-cho-xac-nhan")
    public String listChoXacNhan(@RequestParam(defaultValue = "0", name = "p") Integer page, @ModelAttribute("searchForm") SearchForm searchForm, Model model) {

        model.addAttribute("searchLoaiDon", new SearchLoaiHoaDon());
        if (page < 0) {
            page = 0;
        }
        String donHang = "cho-xac-nhan";
        model.addAttribute("donHang", donHang);
        Pageable pageable = PageRequest.of(page, 5);
        Page<HoaDon> donHangCho = hoaDonRepository.donHangChoXacNhan(pageable);
        model.addAttribute("page", donHangCho);

        model.addAttribute("view", "../don-hang/listdh.jsp");
        return "/admin/index";
    }

    @GetMapping("/don-hang/list-chuan-bi")
    public String listChuanBi(@RequestParam(defaultValue = "0", name = "p") Integer page, @ModelAttribute("searchForm") SearchForm searchForm, Model model) {

        model.addAttribute("searchLoaiDon", new SearchLoaiHoaDon());
        if (page < 0) {
            page = 0;
        }
        String donHang = "chuan-bi";
        model.addAttribute("donHang", donHang);

        Pageable pageable = PageRequest.of(page, 5);

        Page<HoaDon> pageChuanBi = hoaDonRepository.donHangDangChuanBi(pageable);
        model.addAttribute("page", pageChuanBi);

        model.addAttribute("view", "../don-hang/listdh.jsp");
        return "/admin/index";
    }

    @GetMapping("/don-hang/list-huy")
    public String listHuy(@RequestParam(defaultValue = "0", name = "p") Integer page, @ModelAttribute("searchForm") SearchForm searchForm, Model model) {

        model.addAttribute("searchLoaiDon", new SearchLoaiHoaDon());
        if (page < 0) {
            page = 0;
        }
        String donHang = "huy";
        model.addAttribute("donHang", donHang);

        Pageable pageable = PageRequest.of(page, 5);

        Page<HoaDon> pageDonDaHuy = hoaDonRepository.donHangDaHuy(pageable);
        model.addAttribute("page", pageDonDaHuy);

        model.addAttribute("view", "../don-hang/listdh.jsp");
        return "/admin/index";
    }

    @GetMapping("/don-hang/list-dang-giao")
    public String listDangGiao(@RequestParam(defaultValue = "0", name = "p") Integer page, @ModelAttribute("searchForm") SearchForm searchForm, Model model) {

        model.addAttribute("searchLoaiDon", new SearchLoaiHoaDon());
        if (page < 0) {
            page = 0;
        }
        String donHang = "dang-giao";
        model.addAttribute("donHang", donHang);

        Pageable pageable = PageRequest.of(page, 5);

        Page<HoaDon> donDangGiao = hoaDonRepository.donHangDangGiao(pageable);
        model.addAttribute("page", donDangGiao);

        model.addAttribute("view", "../don-hang/listdh.jsp");
        return "/admin/index";
    }

    @GetMapping("/don-hang/list-hoan-thanh")
    public String listHoanThanh(@RequestParam(defaultValue = "0", name = "p") Integer page, @ModelAttribute("searchForm") SearchForm searchForm, Model model) {

        model.addAttribute("searchLoaiDon", new SearchLoaiHoaDon());
        if (page < 0) {
            page = 0;
        }
        String donHang = "hoan-thanh";
        model.addAttribute("donHang", donHang);

        Pageable pageable = PageRequest.of(page, 5);

        Page<HoaDon> donHoanThanh = hoaDonRepository.donHangHoanThanh(pageable);
        model.addAttribute("page", donHoanThanh);

        model.addAttribute("view", "../don-hang/listdh.jsp");
        return "/admin/index";
    }

    @GetMapping("/don-hang/list-don-doi")
    public String listDonTra(@RequestParam(defaultValue = "0", name = "p") Integer page, @ModelAttribute("searchForm") SearchForm searchForm, Model model) {

        model.addAttribute("searchLoaiDon", new SearchLoaiHoaDon());
        if (page < 0) {
            page = 0;
        }
        String donHang = "don-doi";
        model.addAttribute("donHang", donHang);

        Pageable pageable = PageRequest.of(page, 5);

        Page<HoaDon> donTra = hoaDonRepository.donHangTra(pageable);
        model.addAttribute("page", donTra);

        model.addAttribute("view", "../don-hang/listdh.jsp");
        return "/admin/index";
    }

    @GetMapping("/don-hang/list-don-da-doi")
    public String listDonDaTra(@RequestParam(defaultValue = "0", name = "p") Integer page, @ModelAttribute("searchForm") SearchForm searchForm, Model model) {

        model.addAttribute("searchLoaiDon", new SearchLoaiHoaDon());
        if (page < 0) {
            page = 0;
        }
        String donHang = "don-da-doi";
        model.addAttribute("donHang", donHang);

        Pageable pageable = PageRequest.of(page, 5);

        Page<HoaDon> donDaTra = hoaDonRepository.donHangDaTra(pageable);
        model.addAttribute("page", donDaTra);

        model.addAttribute("view", "../don-hang/listdh.jsp");
        return "/admin/index";
    }

    // 16.11
    @RequestMapping(value = "/don-hang/searchDate")
    public String searchDate(Model model,
                             @RequestParam(defaultValue = "0", name = "p") int page,
                             @ModelAttribute("searchForm") HoaDonController.SearchForm searchForm
    ) {
        Pageable pageable = PageRequest.of(page, 50);
        if (searchForm.fromDate != null && searchForm.toDate != null && searchForm.fromDate.equals(searchForm.toDate)) {
            LocalDate localEndDate = searchForm.toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            localEndDate = localEndDate.plusDays(1);
            searchForm.toDate = Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        model.addAttribute("donHang","searchDate");
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
    // 6: trả hàng
    // 7 : đã hoàn trả
    // 8 : đã huỷ
    // xác nhận -> đang chuẩn bị
    // loại đơn: 0:on/ 1:tại quầy


    @RequestMapping("/don-hang/update-xac-nhan/{id}")
    public String trangThaiDangChuanBi(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                                       @RequestParam(defaultValue = "0", name = "p") int page, @PathVariable UUID id,
                                       @ModelAttribute("searchForm") HoaDonController.SearchForm searchForm
    ) {
        Pageable pageable = PageRequest.of(page, 5);
        model.addAttribute("searchForm", new HoaDonController.SearchForm());
        Page<HoaDon> donHangCho = hoaDonRepository.donHangChoXacNhan(pageable);

        model.addAttribute("searchLoaiDon", new SearchLoaiHoaDon());
        model.addAttribute("page", donHangCho);
        HoaDon hoaDonDB = hoaDonService.getOne(id);
        if (hoaDonDB != null) {
            hoaDonService.updateHoaDon(id, 2, hoaDonDB);
        }
        System.out.println("Đơn hàng  cập nhật được trạng thái");

//        model.addAttribute("view", "../don-hang/listdh.jsp");
//        return "/admin/index";
//        return "redirect:/don-hang/list-all";
        return "redirect:/don-hang/list-chuan-bi";
    }

    @RequestMapping("/don-hang/update-chuan-bi/{id}")
    public String trangThaiChuanBi(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                                   @RequestParam(defaultValue = "0", name = "p") int page, @PathVariable UUID id,
                                   @ModelAttribute("searchForm") HoaDonController.SearchForm searchForm
    ) {
        Pageable pageable = PageRequest.of(page, 5);
        model.addAttribute("searchForm", new HoaDonController.SearchForm());
        model.addAttribute("searchLoaiDon", new SearchLoaiHoaDon());
        Page<HoaDon> donHangCho = hoaDonRepository.donHangChoXacNhan(pageable);
        model.addAttribute("page", donHangCho);
        HoaDon hoaDonDB = hoaDonService.getOne(id);
        if (hoaDonDB != null) {
            hoaDonService.updateHoaDon(id, 4, hoaDonDB);
        }
        System.out.println("Đơn hàng  cập nhật được trạng thái");
//        model.addAttribute("view", "../don-hang/listdh.jsp");
//        return "/admin/index";
//        return "redirect:/don-hang/xem-don-hang/" + id;
        return "redirect:/don-hang/list-dang-giao";

    }

    @RequestMapping("/don-hang/huy-don-hang/{id}")
    public String huyDonHang(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                             @RequestParam(defaultValue = "0", name = "p") int p, @PathVariable UUID id,
                             @ModelAttribute("searchForm") HoaDonController.SearchForm searchForm
    ) {
        Pageable pageable = PageRequest.of(p, 5);
        model.addAttribute("searchForm", new HoaDonController.SearchForm());
        model.addAttribute("searchLoaiDon", new SearchLoaiHoaDon());
        Page<HoaDon> pageDonDaHuy = hoaDonRepository.donHangDaHuy(pageable);
        model.addAttribute("page", pageDonDaHuy);
        HoaDon hoaDonDB = hoaDonService.getOne(id);
        if (hoaDonDB != null) {
            hoaDonService.updateHoaDon(id, 8, hoaDonDB);
        }
        System.out.println("Đơn hàng  cập nhật được trạng thái");

//        model.addAttribute("view", "../don-hang/listdh.jsp");
//        return "/admin/index";
//        return "redirect:/don-hang/xem-don-hang/" + id;
        return "redirect:/don-hang/list-huy";

    }

    @RequestMapping("/don-hang/dang-giao/{id}")
    public String dangGiaoHang(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                               @RequestParam(defaultValue = "0", name = "p") int p, @PathVariable UUID id,
                               @ModelAttribute("searchForm") HoaDonController.SearchForm searchForm
    ) {
        Pageable pageable = PageRequest.of(p, 5);
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
        return "redirect:/don-hang/hoan-thanh";

    }

    @RequestMapping("/don-hang/doi-hang/{id}")
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
        model.addAttribute("searchLoaiDon", new SearchLoaiHoaDon());
        System.out.println("Đơn hàng không cập nhật được trạng thái");
//        model.addAttribute("view", "../don-hang/listdh.jsp");
//        return "/admin/index";
//        return "redirect:/don-hang/xem-don-hang/" + id;
        return "redirect:/don-hang/list-don-doi";

    }

    @RequestMapping("/don-hang/xac-nhan-giao/{id}")
    public String giaoDVVC(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                           @RequestParam(defaultValue = "0", name = "p") int p, @PathVariable UUID id,
                           @ModelAttribute("searchForm") HoaDonController.SearchForm searchForm
    ) {
        Pageable pageable = PageRequest.of(p, 5);
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
        return "redirect:/don-hang/list-hoan-thanh";

    }

    @RequestMapping("/don-hang/da-doi-hang/{id}")
    public String daTraHang(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                            @RequestParam(defaultValue = "0", name = "p") int p, @PathVariable UUID id,
                            @ModelAttribute("searchForm") HoaDonController.SearchForm searchForm
    ) {
        Pageable pageable = PageRequest.of(p, 5);
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
        return "redirect:/don-hang/list-don-da-doi";

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
