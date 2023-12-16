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

        Integer trangThai;
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

        model.addAttribute("searchForm", new SearchForm());
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

            // Validate Một sản phẩm chỉ đc một khuyến mại
//            if (chiTietKhuyenMai1 != null) {
//                model.addAttribute("error", "Một sản phẩm chỉ đc một khuyến mại");
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

            if (km.getGiaTri() > ctsp.getGiaBan()){
                model.addAttribute("error", "Không thêm được");
                model.addAttribute("view", "../khuyen-mai/khuyen-mai.jsp");
                return "admin/index";
            }

            else {
                ChiTietKhuyenMai ctkm = new ChiTietKhuyenMai();
                ctkm.setCtsp(ctsp);
                ctkm.setKhuyenMai(km);

                if (km.getDonVi().equals("VNÐ")) {
                    Double giaKhuyenMai = ctsp.getGiaBan() - km.getGiaTri();
                    ctkm.setGiaKhuyenMai(giaKhuyenMai);
                }

                if (km.getDonVi().equals("%")) {
                    Double giaKhuyenMai = ctsp.getGiaBan() - ((Double.valueOf(km.getGiaTri()) / 100) * ctsp.getGiaBan());
                    ctkm.setGiaKhuyenMai(giaKhuyenMai);
                }


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

        if (km.getDonVi().contains("%")) {
            if (km.getGiaTri() > 70 || 5 > km.getGiaTri()) {
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

        if (km.getDonVi().contains("VNĐ")) {
            if (km.getGiaTri() > 500000 || km.getGiaTri() < 10000) {
                model.addAttribute("errorGiaTri", "Giá trị tối thiểu là 10.000 và tối đa 500.000");
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

        khuyenMaiService.save(km);

        return "redirect:/bumblebee/khuyen-mai/list";
    }

    //    private Date ngayTao = null;
    @GetMapping("/bumblebee/khuyen-mai/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable(name = "id") UUID id) {

        KhuyenMai khuyenMai = khuyenMaiService.findId(id);
//        ngayTao = khuyenMai.getNgayTao();
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

        if (km.getDonVi().contains("%")) {
            if (km.getGiaTri() > 70 || 5 > km.getGiaTri()) {
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

        if (km.getDonVi().contains("VNĐ")) {
            if (km.getGiaTri() > 500000 || km.getGiaTri() < 10000) {
                model.addAttribute("errorGiaTri", "Giá trị tối thiểu là 10.000 và tối đa 500.000");
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

        if (LocalDateTime.now().isAfter(km.getNgayKetThuc())) {
            model.addAttribute("mess_Ngay", "Ngày kết thúc phải lớn hơn ngày hiện tại");
            model.addAttribute("view", "../khuyen-mai/add_update.jsp");
            return "admin/index";
        }


        KhuyenMai khuyenMai = khuyenMaiService.findId(km.getId());
        khuyenMai.setTrangThai(km.getTrangThai());
        khuyenMai.setNgayKetThuc(km.getNgayKetThuc());
        khuyenMai.setMaKhuyenMai(km.getMaKhuyenMai());
        khuyenMai.setTenKhuyenMai(km.getTenKhuyenMai());
        khuyenMai.setDonVi(km.getDonVi());
        khuyenMai.setGiaTri(km.getGiaTri());
        khuyenMai.setNgayBatDau(km.getNgayBatDau());
        khuyenMaiService.save(khuyenMai);

        if (km.getTrangThai() == 1) {
            List<ChiTietKhuyenMai> chiTietKhuyenMai = chiTietKhuyenMaiRepository.findIdKhuyenMai(km.getId());
            for (ChiTietKhuyenMai ctkm : chiTietKhuyenMai) {
                ctkm.setTrangThai(km.getTrangThai());
                chiTietKhuyenMaiService.save(ctkm);
            }
        } else {
            List<ChiTietKhuyenMai> listCTKmByIdKM = chiTietKhuyenMaiRepository.findIdKhuyenMai(km.getId());
            List<ChiTietKhuyenMai> listCTKMByTrangThai0 = chiTietKhuyenMaiRepository.ctkmByTrangThai0();


            for (ChiTietKhuyenMai ctkm1 : listCTKmByIdKM) {
                System.out.println("aa " + ctkm1.getCtsp().getId());
            }

            List<ChiTietKhuyenMai> listRemove = new ArrayList<>();

            for (ChiTietKhuyenMai ctkm1 : listCTKmByIdKM) {
                for (ChiTietKhuyenMai ctkm2 : listCTKMByTrangThai0) {
                    if (ctkm1.getCtsp().getId().equals(ctkm2.getCtsp().getId())) {
                        listRemove.add(ctkm1);
                    }
                }
            }

            for (ChiTietKhuyenMai chiTietKhuyenMai : listRemove) {
                listCTKmByIdKM.remove(chiTietKhuyenMai);
            }

            for (ChiTietKhuyenMai ctkm : listCTKmByIdKM) {
                ctkm.setTrangThai(km.getTrangThai());
                chiTietKhuyenMaiService.save(ctkm);
            }

        }
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

    @RequestMapping("/bumblebee/khuyen-mai/update-ctkm-hoat-dong/{id}")
    public String updateCTKMHoatDong(
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
        ctkm.setTrangThai(0);
        chiTietKhuyenMaiService.save(ctkm);
        return "redirect:/bumblebee/san-pham-khuyen-mai/list";
    }

    @RequestMapping("/bumblebee/khuyen-mai/update-ctkm-khong-hoat-dong/{id}")
    public String updateCTKMKhongHoatDong(
            Model model,
            @PathVariable(name = "id") UUID id
//            @RequestParam(name = "ngayBatDau") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayBatDau,
//            @RequestParam(name = "ngayKetThuc") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayKetThuc
    ) {
        ChiTietKhuyenMai ctkm = chiTietKhuyenMaiService.findID(id);
        ctkm.setTrangThai(1);
        chiTietKhuyenMaiService.save(ctkm);
        return "redirect:/bumblebee/san-pham-khuyen-mai/list";
    }

    @RequestMapping("/bumblebee/khuyen-mai/search")
    public String searchKhoangNgay(
            Model model,
            @ModelAttribute("searchForm") KhuyenMaiController.SearchForm searchForm
    ) {
        List<KhuyenMai> km = null;
        if (searchForm.donVi.equals("")) {

            km = khuyenMaiRepository.searchHDByTrangThai(searchForm.trangThai);

        } else {

            km = khuyenMaiRepository.searchHDByDonViAndTrangThai(searchForm.donVi, searchForm.trangThai);

        }
        model.addAttribute("page", km);
        model.addAttribute("listCTSP", chiTietSanPhamService.getList());
        model.addAttribute("view", "../khuyen-mai/khuyen-mai.jsp");
        return "admin/index";
    }

}
