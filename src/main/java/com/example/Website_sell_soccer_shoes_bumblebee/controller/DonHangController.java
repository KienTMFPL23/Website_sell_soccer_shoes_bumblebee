package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.*;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.*;
import com.example.Website_sell_soccer_shoes_bumblebee.service.*;
import com.example.Website_sell_soccer_shoes_bumblebee.service.Impl.HoaDonServiceImpl;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
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

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.List;

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

    @Autowired
    ChatLieuRepository chatLieuRepo;
    @Autowired
    KichCoService kichCoService;
    @Autowired
    LoaiGiayRepository loaiGiayRepo;

    @Autowired
    DeGiayRepository deGiayRepo;

    @Autowired
    MauSacReponsitory mauSacReponsitories;
    @Autowired
    ChiTietSanPhamService chiTietSanPhamService;

    @ModelAttribute("listDeGiay")
    List<DeGiay> listDeGiay() {
        return deGiayRepo.findAll();
    }

    @ModelAttribute("listChatLieu")
    List<ChatLieu> listChatLieu() {
        return chatLieuRepo.findAll();
    }

    @ModelAttribute("listKichCo")
    List<KichCo> listKichCo() {
        return kichCoService.getList();
    }

    @ModelAttribute("listMau")
    List<MauSac> listMauSac() {
        return mauSacReponsitories.findAll();
    }

    @ModelAttribute("listLoaiGiay")
    List<LoaiGiay> listLoaiGiay() {
        return loaiGiayRepo.findAll();
    }

    @ModelAttribute("listSanPham")
    List<ChiTietSanPham> listSP() {
        return chiTietSanPhamService.listCTSPSuDung();
    }

    @Data
    public static class SearchForm {
        Integer keyword;

        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        Date fromDate;

        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
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
    private Double sumMoney = 0.0;

    private UUID idHoaDon = null;
    private UUID idHoaDonCT = null;
    private UUID idCTSP = null;
    @Autowired
    HoaDonChiTietService hoaDonChiTietService;

    @RequestMapping("/don-hang/search-loai-don")
    public String searchLoaiHoaDon(
            @ModelAttribute("searchLoaiDon") SearchLoaiHoaDon searchLoaiHoaDon,
            @RequestParam(defaultValue = "0", name = "p") Integer page,
            @RequestParam(name = "donHang", required = false) String donHang,
            Model model
    ) {

        //1
        if (page < 0) {
            page = 0;
        }
        Page<HoaDon> hoaDonPage;
        Pageable pageable = PageRequest.of(page, 50);
        model.addAttribute("donHang", "search-loai-don");

        if (searchLoaiHoaDon.key >= 0) {
            hoaDonPage = hoaDonService.searchLoaiHoaDon(searchLoaiHoaDon.key, pageable);
            model.addAttribute("page", hoaDonPage);

//            if (donHang != null && !donHang.isEmpty()) {
            hoaDonPage = searchByDonHang(donHang, searchLoaiHoaDon.key, pageable);
            model.addAttribute("page", hoaDonPage);
//            }
        }

        model.addAttribute("searchForm", new SearchForm());
        model.addAttribute("view", "../don-hang/listdh.jsp");
        return "/admin/index";


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
        Pageable pageable = PageRequest.of(page, 10);
        String donHang = "all";
        model.addAttribute("donHang", donHang);

        Page<HoaDon> list = hoaDonRepository.findAllDonHang(pageable);
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

    //    @RequestMapping(value = "/don-hang/search-don-hang")
//    public String searchDonHang(Model model,
//                                @RequestParam(defaultValue = "0", name = "p") int page,
//                                @RequestParam(name = "donHang", required = false) String donHang,
//                                @ModelAttribute("searchForm") DonHangController.SearchForm searchForm
//    ) {
//        Pageable pageable = PageRequest.of(page, 50);
////        if (donHang == null) {
////            donHang = ""; // or set it to a default value
////        }
//
//        if (searchForm.fromDate != null && searchForm.toDate != null && searchForm.fromDate.equals(searchForm.toDate)) {
//            LocalDate localEndDate = searchForm.toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            localEndDate = localEndDate.plusDays(1);
//            searchForm.toDate = Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//        }
//        Page<HoaDon> listS = null;
//        if (donHang.equals("cho-xac-nhan")) {
//            listS = this.hoaDonService.searchHDByNgayTaoAndLoaiDonTT(searchForm.fromDate, searchForm.toDate, 1, searchForm.keyword, pageable);
//
//        } else if (donHang.equals("chuan-bi")) {
//            listS = this.hoaDonService.searchHDByNgayTaoAndLoaiDonTT(searchForm.fromDate, searchForm.toDate, 2, searchForm.keyword, pageable);
//
//        } else if (donHang.equals("don-da-doi")) {
//            listS = this.hoaDonService.searchHDByNgayTaoAndLoaiDonTT(searchForm.fromDate, searchForm.toDate, 7, searchForm.keyword, pageable);
//
//        } else if (donHang.equals("don-doi")) {
//            listS = this.hoaDonService.searchHDByNgayTaoAndLoaiDonTT(searchForm.fromDate, searchForm.toDate, 6, searchForm.keyword, pageable);
//
//        } else if (donHang.equals("dang-giao")) {
//            listS = this.hoaDonService.searchHDByNgayTaoAndLoaiDonTT(searchForm.fromDate, searchForm.toDate, 4, searchForm.keyword, pageable);
//
//        } else if (donHang.equals("huy")) {
//            listS = this.hoaDonService.searchHDByNgayTaoAndLoaiDonTT(searchForm.fromDate, searchForm.toDate, 8, searchForm.keyword, pageable);
//
//        } else if (donHang.equals("hoan-thanh")) {
//            listS = this.hoaDonService.searchHDByNgayTaoAndLoaiDonTT(searchForm.fromDate, searchForm.toDate, 5, searchForm.keyword, pageable);
//
//        } else if (donHang.equals(" ")) {
//            model.addAttribute("mess", "VHiện không có đơn hàng. Vui lòng thử lại !");
//        } else {
//            listS = this.hoaDonService.searchHDByNgayTaoAndLoaiDon(searchForm.fromDate, searchForm.toDate, searchForm.keyword, pageable);
//        }
//        model.addAttribute("page", listS);
//
//
//        model.addAttribute("view", "../don-hang/listdh.jsp");
//        return "/admin/index";
//    }
    @RequestMapping(value = "/don-hang/search-don-hang")
    public String searchDonHang(Model model,
                                @RequestParam(defaultValue = "0", name = "p") int page,
                                @RequestParam(name = "donHang", required = false) String donHang,
                                @ModelAttribute("searchForm") DonHangController.SearchForm searchForm
    ) {
        Pageable pageable = PageRequest.of(page, 10);

        if (searchForm.fromDate != null && searchForm.toDate != null && searchForm.fromDate.equals(searchForm.toDate)) {
            LocalDate localEndDate = searchForm.toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            localEndDate = localEndDate.plusDays(1);
            searchForm.toDate = Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }

        Page<HoaDon> listS = null;
        if (" ".equals(donHang)) {
            model.addAttribute("mess", "Hiện không có đơn hàng. Vui lòng thử lại !");
        } else if ("all".equals(donHang)) {
            listS = hoaDonRepository.searchHDByNgayTaoAndLoaiDon(searchForm.fromDate, searchForm.toDate, searchForm.keyword, pageable);
        } else {
            // Use the same search method for all states
            listS = this.hoaDonService.searchHDByNgayTaoAndLoaiDonTT(searchForm.fromDate, searchForm.toDate, searchForm.keyword, getTrangThaiLoaiDonId(donHang), pageable);
        }

        model.addAttribute("page", listS);
        model.addAttribute("donHang", donHang);
        model.addAttribute("view", "../don-hang/listdh.jsp");
        return "/admin/index";
    }

    private int getTrangThaiLoaiDonId(String donHang) {
        // Map donHang values to corresponding LoaiDonId
        switch (donHang) {
            case "cho-xac-nhan":
                return 1;
            case "chuan-bi":
                return 2;
            case "don-da-doi":
                return 7;
            case "don-doi":
                return 6;
            case "dang-giao":
                return 4;
            case "huy":
                return 8;
            case "hoan-thanh":
                return 5;
            default:
                return 0; // Set a default value or handle as needed
        }
    }
    //hinh thức thanh toán: 0: tại quầy; 1: cod; 2: vn pay
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
                                       @RequestParam(value = "ghiChu", required = false) String ghiChu,
                                       @ModelAttribute("searchForm") HoaDonController.SearchForm searchForm

    ) {
        Pageable pageable = PageRequest.of(page, 10);
        model.addAttribute("searchForm", new HoaDonController.SearchForm());
        Page<HoaDon> donHangCho = hoaDonRepository.donHangChoXacNhan(pageable);

        model.addAttribute("searchLoaiDon", new SearchLoaiHoaDon());
        model.addAttribute("page", donHangCho);
        HoaDon hoaDonDB = hoaDonService.getOne(id);
        if (hoaDonDB != null) {
            hoaDonService.updateHoaDon(id, 2, hoaDonDB);
            if (ghiChu != null) {
                hoaDonDB.setGhiChu(ghiChu);
                hoaDonService.saveHoaDon(hoaDonDB);
            }
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
                                   @RequestParam(value = "ghiChu", required = false) String ghiChu,
                                   @ModelAttribute("searchForm") HoaDonController.SearchForm searchForm
    ) {
        Pageable pageable = PageRequest.of(page, 10);
        model.addAttribute("searchForm", new HoaDonController.SearchForm());
        model.addAttribute("searchLoaiDon", new SearchLoaiHoaDon());
        Page<HoaDon> donHangCho = hoaDonRepository.donHangChoXacNhan(pageable);
        model.addAttribute("page", donHangCho);
        HoaDon hoaDonDB = hoaDonService.getOne(id);
        if (hoaDonDB != null) {
            hoaDonService.updateHoaDon(id, 4, hoaDonDB);
            if (ghiChu != null) {
                hoaDonDB.setGhiChu(ghiChu);
                hoaDonService.saveHoaDon(hoaDonDB);
            }
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
                             @RequestParam(value = "ghiChu", required = false) String ghiChu,
                             @ModelAttribute("searchForm") HoaDonController.SearchForm searchForm
    ) {
        Pageable pageable = PageRequest.of(p, 10);
        model.addAttribute("searchForm", new HoaDonController.SearchForm());
        model.addAttribute("searchLoaiDon", new SearchLoaiHoaDon());
        Page<HoaDon> pageDonDaHuy = hoaDonRepository.donHangDaHuy(pageable);
        model.addAttribute("page", pageDonDaHuy);

        if (ghiChu != null && !ghiChu.trim().isEmpty()) {

            HoaDon hoaDonDB = hoaDonService.getOne(id);
            if (hoaDonDB != null) {
                hoaDonService.updateHoaDon(id, 8, hoaDonDB);
                hoaDonDB.setGhiChu(ghiChu);
                hoaDonRepository.save(hoaDonDB);
                List<HoaDonChiTiet> listHoaDonCT = hoaDonChiTietService.getListHoaDonCTByIdHoaDon(id);
                if (listHoaDonCT.size() != 0) {
                    for (HoaDonChiTiet hdct : listHoaDonCT) {
                        chiTietSanPhamService.updateDelete(hdct.getChiTietSanPham().getId(), hdct.getSoLuong());
                    }
                }
            }
            return "redirect:/don-hang/list-huy";
        } else {
            model.addAttribute("errorGhiChu", "Vui lòng nhập ghi chú trước khi huỷ!");
            return "/don-hang/list-huy";
        }
//        model.addAttribute("view", "../don-hang/listdh.jsp");
//        return "/admin/index";
//        return "redirect:/don-hang/xem-don-hang/" + id;
//        return "redirect:/don-hang/list-huy";

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
        return "redirect:/don-hang/hoan-thanh";

    }

    @RequestMapping("/don-hang/doi-hang/{id}")
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
        return "redirect:/don-hang/list-don-doi";

    }

    @RequestMapping("/don-hang/xac-nhan-giao/{id}")
    public String giaoDVVC(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                           @RequestParam(defaultValue = "0", name = "p") int p, @PathVariable UUID id,
                           @RequestParam(value = "ghiChu", required = false) String ghiChu,
                           @ModelAttribute("searchForm") HoaDonController.SearchForm searchForm
    ) {
        Pageable pageable = PageRequest.of(p, 10);
        model.addAttribute("searchLoaiDon", new SearchLoaiHoaDon());
        model.addAttribute("searchForm", new HoaDonController.SearchForm());
        HoaDon hoaDonDB = hoaDonService.getOne(id);
        if (ghiChu != null && !ghiChu.trim().isEmpty()) {
            if (hoaDonDB != null) {
                hoaDonService.updateHoaDon(id, 5, hoaDonDB);
                hoaDonDB.setGhiChu(ghiChu);
                hoaDonRepository.save(hoaDonDB);
            }
            return "redirect:/don-hang/list-hoan-thanh";

        } else {
            model.addAttribute("errorGhiChu", "Vui lòng nhập ghi chú trước khi xác nhận giao hàng!");
            return "/don-hang/list-hoan-thanh";

        }
//        model.addAttribute("view", "../don-hang/listdh.jsp");
//        return "/admin/index";
//        return "redirect:/don-hang/xem-don-hang/" + id;
    }

    @RequestMapping("/don-hang/da-doi-hang/{id}")
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
        return "redirect:/don-hang/list-don-da-doi";

    }


    //view chờ xác nhận . (xem đơn hàng)
    @GetMapping("/don-hang/xem-don-hang/{id}")
    public String donHangView(@PathVariable UUID id, Model model) {
        HoaDon hoaDon = hoaDonRepository.findById(id).orElse(null);
        model.addAttribute("hoaDon", hoaDon);
        idHoaDon = hoaDon.getId();
//        List<HoaDonChiTiet> list = hoaDonChiTietService.getListHoaDonCTByIdHoaDon(id);
//        sumMoney = hoaDonChiTietService.getTotalMoney(list);
//        model.addAttribute("sumMoney", sumMoney);
        List<HoaDonChiTiet> list = hoaDonChiTietService.getListHoaDonCTByIdHoaDon(id);
        sumMoney = hoaDonChiTietService.getTotalMoney(list);
        if (sumMoney < 0) {
            sumMoney = 0.0;
            model.addAttribute("sumMoney", sumMoney);
        } else {
            model.addAttribute("sumMoney", sumMoney);
        }
        model.addAttribute("view", "../don-hang/xem-don-hang.jsp");
        return "/admin/index";
    }
//23


    @RequestMapping("/don-hang/add-don-hang/{id}")
    public String themSanPham(Model model, @PathVariable("id") UUID id) {
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        HoaDon hoaDon = hoaDonService.getOne(idHoaDon);
//        if (idHoaDon == null) {
//            return "redirect:/bumblebee/ban-hang-tai-quay/sell";
//        }
        this.idCTSP = id;
        model.addAttribute("sumMoney", sumMoney);
        ChiTietSanPham sp = chiTietSanPhamService.getOne(id);
        HoaDonChiTiet sanPhamInHDCT = hoaDonChiTietService.getAndUpdateSanPhamInHDCT(this.idHoaDon, id);

        if (sanPhamInHDCT != null) {
            Integer soLuong = sp.getSoLuong() - 1;
            chiTietSanPhamService.updateSoLuongTon(id, soLuong);
            hoaDonChiTietService.saveHoaDonCT(hoaDonChiTiet);
            return "redirect:/don-hang/xem-don-hang/" + this.idHoaDon;
        } else {
            hoaDonChiTiet.setHoaDon(hoaDon);
            hoaDonChiTiet.setChiTietSanPham(sp);
            hoaDonChiTiet.setSoLuong(1);
            hoaDonChiTiet.setDonGia(sp.getGiaBan());
            hoaDonChiTiet.setTrangThai(2);
            if (sp.getCtkm() != null) {
                Double donGiaKhiGiam = hoaDonChiTietService.getDonGiaKhiGiam(sp.getCtkm());
                hoaDonChiTiet.setDonGiaKhiGiam(donGiaKhiGiam);
            } else {
                hoaDonChiTiet.setDonGiaKhiGiam(0.0);
            }
            Integer soLuong = sp.getSoLuong() - 1;
            chiTietSanPhamService.updateSoLuongTon(id, soLuong);
            hoaDonChiTietService.saveHoaDonCT(hoaDonChiTiet);
        }
        return "redirect:/don-hang/xem-don-hang/" + this.idHoaDon;
    }

    //update-cart
    @RequestMapping("/don-hang/update-cart/{id}")
    public String updateSLGioHang(@PathVariable("id") UUID id, int soLuong) {

        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getOneHoaDon(id);
        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getOne(hoaDonChiTiet.getChiTietSanPham().getId());
        Integer soLuongTon = chiTietSanPham.getSoLuong() + hoaDonChiTiet.getSoLuong();
        if (soLuong > soLuongTon) {
            hoaDonChiTiet.setSoLuong(soLuongTon);
            chiTietSanPhamService.updateSoLuongTon(chiTietSanPham.getId(), 0);
            hoaDonChiTietService.saveHoaDonCT(hoaDonChiTiet);
            return "redirect:/don-hang/xem-don-hang/" + this.idHoaDon;
        }
        if (soLuong <= 0) {
            hoaDonChiTiet.setSoLuong(1);
            chiTietSanPhamService.updateSoLuongTon(chiTietSanPham.getId(), soLuongTon - 1);
            hoaDonChiTietService.saveHoaDonCT(hoaDonChiTiet);
            return "redirect:/don-hang/xem-don-hang/" + this.idHoaDon;
        }
        if (hoaDonChiTiet != null) {
            hoaDonChiTiet.setSoLuong(soLuong);
            chiTietSanPhamService.updateSoLuongTon(chiTietSanPham.getId(), soLuongTon - soLuong);
            hoaDonChiTietService.saveHoaDonCT(hoaDonChiTiet);
        }
        return "redirect:/don-hang/xem-don-hang/" + this.idHoaDon;
    }

    @RequestMapping("/don-hang/delete-hdct/{id}")
    public String deleteDonHangCT(Model model, @PathVariable("id") UUID id) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getOneHoaDon(id);
        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getOne(hoaDonChiTiet.getChiTietSanPham().getId());
        chiTietSanPhamService.updateDelete(chiTietSanPham.getId(), hoaDonChiTiet.getSoLuong());
        hoaDonChiTietService.deleteHoaDonCT(id);
        return "redirect:/don-hang/xem-don-hang/" + this.idHoaDon;
    }

    @RequestMapping("/don-hang/print/{id}")
    public void xuatFilePdf(HttpServletResponse response, Model model, @PathVariable("id") UUID id, @ModelAttribute("hoaDon") HoaDon hoaDon) throws ParseException {

        HoaDon hoaDonThanhToan = hoaDonService.getOne(id);

        List<HoaDonChiTiet> listHoaDon1 = hoaDonChiTietService.getHoaDonTheoHoaDonChiTiet(id);
        try{
            Document document = new Document();
            document.setPageSize(PageSize.A4);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document, baos);
            PdfWriter.getInstance(document, baos);



            ////
            document.open();

            String qrCodeData = hoaDonThanhToan.getMaHoaDon();
            BarcodeQRCode qrCode = new BarcodeQRCode(qrCodeData, 200, 250, null);
            com.itextpdf.text.Image qrCodeImage = qrCode.getImage();
//
            qrCodeImage.setAbsolutePosition(400, 190);

            document.add(qrCodeImage);
            Font largeFont = new Font(Font.FontFamily.TIMES_ROMAN, 25f, Font.BOLD);
            ////////////// hoá đơn
            Paragraph HoaDon = new Paragraph(" BUMBLEBEE SHOES" + "\n", largeFont);

            HoaDon.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(HoaDon);
            Font titleFont = new Font(BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 18, Font.BOLD, BaseColor.BLACK);
            Paragraph tieuDeThongTin = new Paragraph("Thông tin đơn hàng ", titleFont);
            document.add(tieuDeThongTin);
//           khoảng trắng
            Paragraph KhoangTrang1 = new Paragraph("                                                         ");
            document.add(KhoangTrang1);
//      Mã đơn hàng
            Font titleFont1 = new Font(BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 18, Font.BOLD, BaseColor.BLACK);
            Paragraph maDonHang = new Paragraph("Mã Đơn hàng : " + hoaDonThanhToan.getMaHoaDon(), titleFont);
            document.add(maDonHang);
            if(hoaDonThanhToan.getLoaiHoaDon()==1){
                Paragraph loaiHoaDon = new Paragraph("Loại Hoá Đơn : Bán tại quầy   " ,titleFont);
                document.add(loaiHoaDon);
            }else{
                Paragraph loaiHoaDon = new Paragraph("Loại Hoá Đơn : Bán online   " ,titleFont);
                document.add(loaiHoaDon);
            }
            Paragraph KhoangTrang = new Paragraph("                                                         ");
            document.add(KhoangTrang);
            Paragraph tenKhachHang = null;
            KhachHang khachHang = hoaDonThanhToan.getKhachHang();
            if (khachHang != null) {
                tenKhachHang = new Paragraph("Tên Khách Hàng : " + hoaDonThanhToan.getTenNguoiNhan(), titleFont);
            } else {
                tenKhachHang = new Paragraph("Tên Khách Hàng : Khách hàng vãn lai", titleFont);
            }

            document.add(tenKhachHang);

            Paragraph sdt = new Paragraph("Số Điện Thoại : " + hoaDonThanhToan.getSdt(), titleFont);
            document.add(sdt);
            if(hoaDonThanhToan.getDiaChiShip()!=null){
                Paragraph diaChi = new Paragraph("Địa Chỉ : " + hoaDonThanhToan.getDiaChiShip(), titleFont);
                document.add(diaChi);
            }else{
                Paragraph diaChi = new Paragraph("Địa Chỉ : " + "Khách mua tại quầy ", titleFont);
                document.add(diaChi);
            }

            PdfContentByte cb = writer.getDirectContent();
            cb.setColorStroke(BaseColor.PINK); // Set the line color to pink
            cb.moveTo(50, 300); // Starting point of the line (x, y)
            cb.lineTo(550, 300); // Ending point of the line (x, y)
            cb.setLineWidth(2); // Set the line width
            cb.stroke();
            Paragraph thongTinSanPham = new Paragraph("Thông tin sản phẩm ", titleFont);
            document.add(thongTinSanPham);
            Paragraph KhoangTrang2 = new Paragraph("                                                         ");
            document.add(KhoangTrang2);
//        bảng sản phẩm
            PdfPTable productTable = new PdfPTable(6);
            productTable.setWidthPercentage(100);
            float[] columnWidths = {3f, 4f, 1f, 1f,2f,2f};
            productTable.setWidths(columnWidths);

            productTable.addCell(createTableCell("Tên sản phẩm", titleFont));
            productTable.addCell(createTableCell("Màu sắc", titleFont));
            productTable.addCell(createTableCell("Size", titleFont));
            productTable.addCell(createTableCell("Số lượng", titleFont));
            productTable.addCell(createTableCell("Giá tiền", titleFont));
            productTable.addCell(createTableCell("Thành tiền", titleFont));
            double tongTien = 0.0;
            List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonTheoHoaDonChiTiet(id);
            for (HoaDonChiTiet hoaDonChiTiet : listHoaDonChiTiet) {
                productTable.addCell(createTableCell(hoaDonChiTiet.getChiTietSanPham().getSanPham().getTenSanPham(), titleFont));
                productTable.addCell(createTableCell(hoaDonChiTiet.getChiTietSanPham().getMauSac().getTen(), titleFont));
                productTable.addCell(createTableCell(String.valueOf(hoaDonChiTiet.getChiTietSanPham().getKichCo().getSize()), titleFont));
                productTable.addCell(createTableCell(String.valueOf(hoaDonChiTiet.getSoLuong()), titleFont));
                productTable.addCell(createTableCell(String.valueOf(hoaDonChiTiet.getDonGia()), titleFont));
                productTable.addCell(createTableCell(String.valueOf(hoaDonChiTiet.getDonGia() * hoaDonChiTiet.getSoLuong()), titleFont));
                double giaTriSanPham = hoaDonChiTiet.getDonGia() * hoaDonChiTiet.getSoLuong();
                tongTien += giaTriSanPham;
            }

            document.add(productTable);
            Paragraph dong1 = new Paragraph("==========================================================================");
            document.add(dong1);
            Paragraph TongCong = new Paragraph("Tổng Hoá Đơn       :    " + tongTien + "VNĐ", titleFont);

            document.add(TongCong);
            document.close();
//
//

            byte[] pdfBytes = baos.toByteArray();
            response.setContentType("application/pdf");
            response.setContentLength(pdfBytes.length);

            // Thay đổi giá trị "inline" thành "attachment"
            // Thay đổi giá trị "inline" thành "attachment"
            response.setHeader("Content-Disposition", "attachment; filename=" + hoaDonThanhToan.getMaHoaDon() + ".pdf");


            response.getOutputStream().write(pdfBytes);
            response.getOutputStream().flush();
            response.getOutputStream().close();
//
        } catch (Exception e) {
            e.printStackTrace();
        }
}

    private PdfPCell createTableCell(String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }
}
