package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.*;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.ChiTietKhuyenMaiRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.ChiTietSanPhamRepo;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.KhuyenMaiRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.ChiTietKhuyenMaiService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.ChiTietSanPhamService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.KhuyenMaiService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class KhuyenMaiController {

    @Autowired
    private KhuyenMaiService khuyenMaiService;

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    private ChiTietSanPhamRepo chiTietSanPhamRepo;

    @Autowired
    private ChiTietKhuyenMaiService chiTietKhuyenMaiService;

    @Autowired
    private ChiTietKhuyenMaiRepository chiTietKhuyenMaiRepository;

    @Autowired
    private KhuyenMaiRepository khuyenMaiRepository;

    private UUID idKhuyenMai = null;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(0, "Hoạt động");
        dsTrangThai.put(1, "Không hoạt động");
        return dsTrangThai;
    }

    @Data
    public static class SearchForm {
        String donVi;

        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        Date fromDate;

        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        Date toDate;
    }


    @GetMapping("/bumblebee/khuyen-mai/list")
    public String hienThi(Model model) {
        // Quản lý khuyến mại

        model.addAttribute("page", khuyenMaiService.findAll());
        model.addAttribute("searchForm", new SearchForm());

        // Check trạng thái khuyến mại
//        List<ChiTietKhuyenMai> listChiTietKhuyenMai = chiTietKhuyenMaiRepository.updateTrangThaiByNgayKetThuc();
        String donHang = "khuyen-mai";
        model.addAttribute("donHang", donHang);

        model.addAttribute("listCTSP", chiTietSanPhamService.getList());
        model.addAttribute("listMauSac", chiTietSanPhamRepo.listMauSac());
        model.addAttribute("listKC", chiTietSanPhamRepo.listKC());
        model.addAttribute("listLoaiGiay", chiTietSanPhamRepo.listLoaiGiay());
        model.addAttribute("listDeGiay", chiTietSanPhamRepo.listDeGiay());
        model.addAttribute("listChatLieu", chiTietSanPhamRepo.lítChatLieu());
        model.addAttribute("idKM", idKhuyenMai);
        model.addAttribute("view", "../khuyen-mai/khuyen-mai.jsp");
        return "admin/index";
    }

    @GetMapping("/bumblebee/san-pham-khuyen-mai/list")
    public String sanPhamKhuyenMai(Model model) {
        String donHang = "san-pham-khuyen-mai";
        model.addAttribute("donHang", donHang);
        // Sản phẩm khuyến mại
        List<ChiTietKhuyenMai> listCTKM = chiTietKhuyenMaiRepository.findAll();
        model.addAttribute("listCTKM", listCTKM);
        model.addAttribute("view", "../khuyen-mai/san_pham_khuyen_mai.jsp");
        return "admin/index";
    }

    private List<ChiTietSanPham> listCTKM = null;

    @RequestMapping("/bumblebee/khuyen-mai/them-san-pham/{idKM}")
    public String themSanPham(
            Model model,
            @RequestParam(name = "idListCartDetail", required = false) String idListCartDetail,
            @PathVariable(name = "idKM") UUID idKM
//            @RequestParam(name = "ngayBatDau") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayBatDau,
//            @RequestParam(name = "ngayKetThuc") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayKetThuc
    ) throws ParseException {
//        model.addAttribute("ngayBatDau", ngayBatDau);
//        model.addAttribute("ngayKetThuc", ngayKetThuc);
        model.addAttribute("idListCartDetail", idListCartDetail);
        // Lấy list idctsp
        List<UUID> idCartUUIDList = Arrays.asList(idListCartDetail.split(","))
                .stream()
                .map(UUID::fromString)
                .collect(Collectors.toList());
        listCTKM = new ArrayList<>();
        for (UUID id : idCartUUIDList) {
            ChiTietSanPham ctkm = chiTietSanPhamService.getOne(id);
            listCTKM.add(ctkm);
        }

        //Double tongKhuyenMai = 0.0;
        KhuyenMai km = khuyenMaiService.findId(idKM);
        idKhuyenMai = km.getId();
        for (ChiTietSanPham ctsp : listCTKM) {
            ChiTietKhuyenMai chiTietKhuyenMai = chiTietKhuyenMaiService.findCtkmByIdKmAndCtsp(ctsp.getId(), idKM);
            ChiTietKhuyenMai chiTietKhuyenMai1 = chiTietKhuyenMaiService.findIdCTSP(ctsp.getId());
//            // Tổng giá trị khuyến mại đã có
//            for (ChiTietKhuyenMai ctKhuyenMai : chiTietKhuyenMaiService.findIdCTSP(ctsp.getId())) {
//                tongKhuyenMai += ctKhuyenMai.getKhuyenMai().getGiaTri();
//            }
//
//            for (ChiTietKhuyenMai ctKhuyenMai : chiTietKhuyenMaiService.findIdCTSP(ctsp.getId())) {
//                Long count = chiTietKhuyenMaiService.findIdCTSP(ctsp.getId())
//                        .stream()
//                        .filter(ChiTietKhuyenMai -> ChiTietKhuyenMai.equals(ctKhuyenMai.getKhuyenMai().getMaKhuyenMai()))
//                        .count();
//                System.out.println(count);
//            }
//
//
//            tongKhuyenMai = tongKhuyenMai + km.getGiaTri();
//            System.out.println("Tổng khuyến mại: " + tongKhuyenMai);

//            if (ngayBatDau == null && ngayKetThuc == null) {
//                model.addAttribute("error_ngayBatDau", "Ngày bắt đầu không được trống");
//                model.addAttribute("error_ngayKetThuc", "Ngày kết thúc không được trống");
//                model.addAttribute("view", "../khuyen-mai/khuyen-mai.jsp");
//                return "admin/index";
//            }

            // Validate mã khuyến mại giống nhau
            if (chiTietKhuyenMai != null) {
                model.addAttribute("chiTietKhuyenMai", chiTietKhuyenMai);
                model.addAttribute("error", "Bạn đã thêm mã này cho sản phẩm rồi");
                model.addAttribute("view", "../khuyen-mai/khuyen-mai.jsp");
                return "admin/index";
            }

            // Validate Một sản phẩm chỉ đc một khuyến mại
            if (chiTietKhuyenMai1 != null) {
                model.addAttribute("error", "Một sản phẩm chỉ đc một khuyến mại");
                model.addAttribute("view", "../khuyen-mai/khuyen-mai.jsp");
                return "admin/index";
            }

//            // Validate tổng giá trị khuyến mại < 40%
//            else if (tongKhuyenMai > 0.4) {
//                model.addAttribute("error", "Khuyến mại quá 40% rồiii");
//                model.addAttribute("view", "../khuyen-mai/khuyen-mai.jsp");
//                return "admin/index";
//            }

            else {
                ChiTietKhuyenMai ctkm = new ChiTietKhuyenMai();
                ctkm.setCtsp(ctsp);
                ctkm.setKhuyenMai(km);
//                    LocalDateTime localDateTimeNgayBatDau = ngayBatDau;
//                    Instant instant1 = localDateTimeNgayBatDau.atZone(ZoneId.systemDefault()).toInstant();
//                    Date date1 = Date.from(instant1);
//                    ctkm.setNgayBatDau(date1);
//                    LocalDateTime localDateTimeNgayKetThuc = ngayKetThuc;
//                    Instant instant2 = localDateTimeNgayKetThuc.atZone(ZoneId.systemDefault()).toInstant();
//                    Date date2 = Date.from(instant2);
//                    ctkm.setNgayKetThuc(date2);
                ctkm.setTrangThai(0);
                chiTietKhuyenMaiService.save(ctkm);
            }
        }
        return "redirect:/bumblebee/san-pham-khuyen-mai/list";
    }

    @GetMapping("/bumblebee/khuyen-mai/view-add")
    public String viewAdd(Model model, @ModelAttribute("km") KhuyenMai km) {
        model.addAttribute("action", "/bumblebee/khuyen-mai/add");
        model.addAttribute("view", "../khuyen-mai/add_update.jsp");
        return "admin/index";
    }

    @PostMapping("/bumblebee/khuyen-mai/add")
    public String add(
            Model model,
            @Valid @ModelAttribute("km") KhuyenMai km, BindingResult result
//            @RequestParam(name = "donVi") String donVi,
//            @RequestParam(name = "giaTri") Integer giaTri,
//            @RequestParam(name = "ngayBatDau") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayBatDau,
//            @RequestParam(name = "ngayKetThuc") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayKetThuc
    ) throws ParseException {

        if (result.hasErrors()) {
            model.addAttribute("view", "../khuyen-mai/add_update.jsp");
            return "admin/index";
        }

        if (khuyenMaiService.findMa(km.getMaKhuyenMai()) != null) {
            model.addAttribute("mess_Ma", "Mã không được trùng");
            model.addAttribute("view", "../khuyen-mai/add_update.jsp");
            return "admin/index";
        }

        if (km.getDonVi().equals("%")) {
            if (km.getGiaTri() > 70 && 5 > km.getGiaTri()) {
                model.addAttribute("errorGiaTri", "Giá trị tối thiểu là 5% và tối đa 70 %");
                model.addAttribute("view", "../khuyen-mai/add_update.jsp");
                return "admin/index";
            }
            if (km.getGiaTri() <= 0) {
                model.addAttribute("errorGiaTri", "Giá trị phải lớn hơn 0");
                model.addAttribute("view", "../khuyen-mai/add_update.jsp");
                return "admin/index";
            }
        }

        if (km.getDonVi().equals("VNĐ")) {
            if (km.getGiaTri() > 500000 && km.getGiaTri() < 10000) {
                model.addAttribute("errorGiaTri", "Giá trị tối đa 500.000");
                model.addAttribute("view", "../khuyen-mai/add_update.jsp");
                return "admin/index";
            }

            if (km.getGiaTri() <= 0) {
                model.addAttribute("errorGiaTri", "Giá trị phải lớn hơn 0");
                model.addAttribute("view", "../khuyen-mai/add_update.jsp");
                return "admin/index";
            }
        }

        if (km.getNgayBatDau().isAfter(km.getNgayKetThuc())) {
            model.addAttribute("mess_Ngay", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
            model.addAttribute("view", "../khuyen-mai/add_update.jsp");
            System.out.println("date");
            return "admin/index";
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        km.setNgayTao(Calendar.getInstance().getTime());
        System.out.println(km.getMaKhuyenMai());
        khuyenMaiService.save(km);
        return "redirect:/bumblebee/khuyen-mai/list";
    }

    private Date ngayTao = null;
    @GetMapping("/bumblebee/khuyen-mai/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable(name = "id") UUID id, @ModelAttribute("km") KhuyenMai km) {

        KhuyenMai khuyenMai = khuyenMaiService.findId(id);
        ngayTao = khuyenMai.getNgayTao();
        model.addAttribute("km", khuyenMai);
        model.addAttribute("action", "/bumblebee/khuyen-mai/update/" + khuyenMai.getId());

        model.addAttribute("view", "../khuyen-mai/add_update.jsp");
        return "admin/index";
    }


    @PostMapping("/bumblebee/khuyen-mai/update/{id}")
    public String update(Model model, @Valid @ModelAttribute("km") KhuyenMai km, BindingResult result) throws ParseException {
        if (result.hasErrors()) {
            model.addAttribute("view", "../khuyen-mai/add_update.jsp");
            return "admin/index";
        }

        if (km.getDonVi().equals("%")) {
            if (km.getGiaTri() > 70 && 5 > km.getGiaTri()) {
                model.addAttribute("errorGiaTri", "Giá trị tối thiểu là 5% và tối đa 70 %");
                model.addAttribute("view", "../khuyen-mai/add_update.jsp");
                return "admin/index";
            }
            if (km.getGiaTri() <= 0) {
                model.addAttribute("errorGiaTri", "Giá trị phải lớn hơn 0");
                model.addAttribute("view", "../khuyen-mai/add_update.jsp");
                return "admin/index";
            }
        }

        if (km.getDonVi().equals("VNĐ")) {
            if (km.getGiaTri() > 500000 && km.getGiaTri() < 10000) {
                model.addAttribute("errorGiaTri", "Giá trị tối đa 500.000");
                model.addAttribute("view", "../khuyen-mai/add_update.jsp");
                return "admin/index";
            }

            if (km.getGiaTri() <= 0) {
                model.addAttribute("errorGiaTri", "Giá trị phải lớn hơn 0");
                model.addAttribute("view", "../khuyen-mai/add_update.jsp");
                return "admin/index";
            }
        }

        if (km.getNgayBatDau().isAfter(km.getNgayKetThuc())) {
            model.addAttribute("mess_Ngay", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
            model.addAttribute("view", "../khuyen-mai/add_update.jsp");
            System.out.println("date");
            return "admin/index";
        }

        if (km.getNgayKetThuc().isAfter(LocalDateTime.now())) {
            km.setTrangThai(0);
//            List<ChiTietKhuyenMai> chiTietKhuyenMai = chiTietKhuyenMaiRepository.findIdKhuyenMai(km.getId());
//            for (ChiTietKhuyenMai ctkm : chiTietKhuyenMai) {
//                ctkm.setTrangThai(0);
//                chiTietKhuyenMaiService.save(ctkm);
//            }
        } else {
//            List<ChiTietKhuyenMai> chiTietKhuyenMai = chiTietKhuyenMaiRepository.findIdKhuyenMai(km.getId());
//            for (ChiTietKhuyenMai ctkm : chiTietKhuyenMai) {
//                ctkm.setTrangThai(1);
//                chiTietKhuyenMaiService.save(ctkm);
//            }
            km.setTrangThai(1);
        }
//        chiTietKhuyenMaiService.save();
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        String format = sdf.format(date);
//        km.setNgayTao(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(format));
        km.setNgayTao(ngayTao);
        khuyenMaiService.save(km);

        return "redirect:/bumblebee/khuyen-mai/list";
    }

    @GetMapping("/bumblebee/khuyen-mai/view-update-ctkm/{id}")
    public String viewUpdateCTKM(Model model, @PathVariable(name = "id") UUID id) {
        ChiTietKhuyenMai chiTietKhuyenMai = chiTietKhuyenMaiService.findID(id);
        model.addAttribute("ctkm", chiTietKhuyenMai);
        model.addAttribute("action", "/bumblebee/khuyen-mai/update-ctkm/" + chiTietKhuyenMai.getId());

        model.addAttribute("view", "../khuyen-mai/update_ctkm.jsp");
        return "admin/index";
    }

    @PostMapping("/bumblebee/khuyen-mai/update-ctkm/{id}")
    public String updateCTKM(
            Model model,
            @PathVariable(name = "id") UUID id
//            @RequestParam(name = "ngayBatDau") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayBatDau,
//            @RequestParam(name = "ngayKetThuc") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayKetThuc
    ) {
        ChiTietKhuyenMai ctkm = chiTietKhuyenMaiService.findID(id);
//        LocalDateTime localDateTimeNgayBatDau = ngayBatDau;
//        Instant instant1 = localDateTimeNgayBatDau.atZone(ZoneId.systemDefault()).toInstant();
//        Date date1 = Date.from(instant1);
//        ctkm.setNgayBatDau(date1);
//        LocalDateTime localDateTimeNgayKetThuc = ngayKetThuc;
//        Instant instant2 = localDateTimeNgayKetThuc.atZone(ZoneId.systemDefault()).toInstant();
//        Date date2 = Date.from(instant2);
//        ctkm.setNgayKetThuc(date2);
//        if (ngayKetThuc.isAfter(LocalDateTime.now())) {
//            ctkm.setTrangThai(0);
//        } else {
//            ctkm.setTrangThai(1);
//        }

        chiTietKhuyenMaiService.save(ctkm);
        return "redirect:/bumblebee/khuyen-mai/list";
    }

    @RequestMapping("/bumblebee/khuyen-mai/search-khoang-ngay")
    public String searchKhoangNgay(
            Model model,
            @ModelAttribute("searchForm") KhuyenMaiController.SearchForm searchForm
    ) {
//        if (searchForm.fromDate != null && searchForm.toDate != null && searchForm.fromDate.equals(searchForm.toDate)) {
//            // Increment the toDate by one day
//            LocalDate localEndDate = searchForm.toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            localEndDate = localEndDate.plusDays(1);
//            searchForm.toDate = Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//        }

        if (searchForm.fromDate == null && searchForm.toDate == null && searchForm.donVi != null){
            List<KhuyenMai> km = khuyenMaiRepository.searchKMByDonVi(searchForm.donVi);
            model.addAttribute("page", km);
        }

        if (searchForm.fromDate != null && searchForm.toDate != null && searchForm.donVi == null){
            List<KhuyenMai> km = khuyenMaiRepository.searchKMByNgayTao(searchForm.fromDate, searchForm.toDate);
            model.addAttribute("page", km);
        }

        if (searchForm.fromDate != null && searchForm.toDate != null && searchForm.donVi != null){
            List<KhuyenMai> km = khuyenMaiService.searchKMByNgayTaoAndDonVi(searchForm.fromDate, searchForm.toDate, searchForm.donVi);
            model.addAttribute("page", km);
        }

//        if (searchForm.fromDate != null && searchForm.toDate != null && searchForm.fromDate.isAfter(searchForm.toDate)) {
//            LocalDateTime temp = searchForm.fromDate;
//            searchForm.fromDate = searchForm.toDate;
//            searchForm.toDate = temp;
//        }


//        return "redirect:/bumblebee/khuyen-mai/list";
        model.addAttribute("view", "../khuyen-mai/khuyen-mai.jsp");
        return "admin/index";
    }

    @RequestMapping("/bumblebee/chi-tiet-khuyen-mai/search-khoang-ngay")
    public String searchKhoangNgayCTKM(
            Model model,
            @RequestParam(name = "ngayBatDau") String ngayBatDau,
            @RequestParam(name = "ngayKetThuc") String ngayKetThuc
    ) throws ParseException {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat1.parse(ngayBatDau);
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = dateFormat2.parse(ngayKetThuc);
        List<ChiTietKhuyenMai> km = chiTietKhuyenMaiService.searchKhoangNgay(date1, date2);
        model.addAttribute("page", km);
        model.addAttribute("view", "../khuyen-mai/khuyen-mai.jsp");
        return "admin/index";
    }

}
