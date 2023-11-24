package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.*;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.ChiTietKhuyenMaiRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.ChiTietSanPhamRepo;
import com.example.Website_sell_soccer_shoes_bumblebee.service.ChiTietKhuyenMaiService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.ChiTietSanPhamService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.KhuyenMaiService;
import jakarta.validation.Valid;
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
import java.text.SimpleDateFormat;
import java.time.Instant;
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


    private UUID idKhuyenMai = null;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(0, "Hoạt động");
        dsTrangThai.put(1, "Không hoạt động");
        return dsTrangThai;
    }


    @GetMapping("/bumblebee/khuyen-mai/list")
    public String hienThi(Model model) {
        // Quản lý khuyến mại

        model.addAttribute("page", khuyenMaiService.findAll());
        // Sản phẩm khuyến mại
        List<ChiTietKhuyenMai> listCTKM = chiTietKhuyenMaiRepository.findAll();

        // Check trạng thái khuyến mại
//        List<ChiTietKhuyenMai> listChiTietKhuyenMai = chiTietKhuyenMaiRepository.updateTrangThaiByNgayKetThuc();
        model.addAttribute("listCTKM", listCTKM);
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


    private List<ChiTietSanPham> listCTKM = null;

    @RequestMapping("/bumblebee/khuyen-mai/them-san-pham/{idKM}")
    public String themSanPham(
            Model model,
            @RequestParam(name = "idListCartDetail", required = false) String idListCartDetail,
            @PathVariable(name = "idKM") UUID idKM,
            @RequestParam(name = "ngayBatDau") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayBatDau,
            @RequestParam(name = "ngayKetThuc") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayKetThuc
    ) throws ParseException {
        model.addAttribute("ngayBatDau", ngayBatDau);
        model.addAttribute("ngayKetThuc", ngayKetThuc);
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

            if (ngayBatDau == null && ngayKetThuc == null) {
                model.addAttribute("error_ngayBatDau", "Ngày bắt đầu không được trống");
                model.addAttribute("error_ngayKetThuc", "Ngày kết thúc không được trống");
                model.addAttribute("view", "../khuyen-mai/khuyen-mai.jsp");
                return "admin/index";
            }

            // Validate mã khuyến mại giống nhau
            if (chiTietKhuyenMai != null) {
                model.addAttribute("chiTietKhuyenMai", chiTietKhuyenMai);
                model.addAttribute("error", "Bạn đã thêm mã này cho sản phẩm rồi");
                model.addAttribute("view", "../khuyen-mai/khuyen-mai.jsp");
                return "admin/index";
            } else

                // Validate Một sản phẩm chỉ đc một khuyến mạ
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
                    LocalDateTime localDateTimeNgayBatDau = ngayBatDau;
                    Instant instant1 = localDateTimeNgayBatDau.atZone(ZoneId.systemDefault()).toInstant();
                    Date date1 = Date.from(instant1);
                    ctkm.setNgayBatDau(date1);
                    LocalDateTime localDateTimeNgayKetThuc = ngayKetThuc;
                    Instant instant2 = localDateTimeNgayKetThuc.atZone(ZoneId.systemDefault()).toInstant();
                    Date date2 = Date.from(instant2);
                    ctkm.setNgayKetThuc(date2);
                    ctkm.setTrangThai(0);
                    chiTietKhuyenMaiService.save(ctkm);
                }
        }
        return "redirect:/bumblebee/khuyen-mai/list";
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
            @Valid @ModelAttribute("km") KhuyenMai km, BindingResult result,
            @RequestParam(name = "donVi") String donVi,
            @RequestParam(name = "giaTri") Integer giaTri
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

        if (donVi.equals("%")) {
            if (giaTri > 100) {
                model.addAttribute("errorGiaTri", "Giá trị không đươc nhập quá 100");
                model.addAttribute("view", "../khuyen-mai/add_update.jsp");
                return "admin/index";
            }
        }

        if (giaTri <= 0) {
            model.addAttribute("errorGiaTri", "Giá trị phải lớn hơn 0");
            model.addAttribute("view", "../khuyen-mai/add_update.jsp");
            return "admin/index";
        }


        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String format = sdf.format(date);
        km.setNgayTao(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(format));
        khuyenMaiService.save(km);
        return "redirect:/bumblebee/khuyen-mai/list";
    }

    @GetMapping("/bumblebee/khuyen-mai/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable(name = "id") UUID id, @ModelAttribute("km") KhuyenMai km) {
        KhuyenMai khuyenMai = khuyenMaiService.findId(id);
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
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String format = sdf.format(date);
        km.setNgayTao(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(format));
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
            @PathVariable(name = "id") UUID id,
            @RequestParam(name = "ngayBatDau") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayBatDau,
            @RequestParam(name = "ngayKetThuc") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayKetThuc
    ) {
        ChiTietKhuyenMai ctkm = chiTietKhuyenMaiService.findID(id);
        LocalDateTime localDateTimeNgayBatDau = ngayBatDau;
        Instant instant1 = localDateTimeNgayBatDau.atZone(ZoneId.systemDefault()).toInstant();
        Date date1 = Date.from(instant1);
        ctkm.setNgayBatDau(date1);
        LocalDateTime localDateTimeNgayKetThuc = ngayKetThuc;
        Instant instant2 = localDateTimeNgayKetThuc.atZone(ZoneId.systemDefault()).toInstant();
        Date date2 = Date.from(instant2);
        ctkm.setNgayKetThuc(date2);
        if (ngayKetThuc.isAfter(LocalDateTime.now())) {
            ctkm.setTrangThai(0);
        } else {
            ctkm.setTrangThai(1);
        }

        chiTietKhuyenMaiService.save(ctkm);
        return "redirect:/bumblebee/khuyen-mai/list";
    }

    @RequestMapping ("/bumblebee/khuyen-mai/search-khoang-ngay")
    public String searchKhoangNgay(
            Model model,
            @RequestParam(name = "ngayBatDau") String ngayBatDau,
            @RequestParam(name = "ngayKetThuc") String ngayKetThuc
            ) throws ParseException {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat1.parse(ngayBatDau);
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = dateFormat2.parse(ngayKetThuc);
        List<KhuyenMai> km = khuyenMaiService.searchKhoangNgay(date1, date2);
        model.addAttribute("page", km);
        model.addAttribute("view", "../khuyen-mai/khuyen-mai.jsp");
        return "admin/index";
    }

    @RequestMapping ("/bumblebee/chi-tiet-khuyen-mai/search-khoang-ngay")
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
