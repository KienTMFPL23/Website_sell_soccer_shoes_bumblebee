package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.*;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.*;
import com.example.Website_sell_soccer_shoes_bumblebee.service.*;

import com.example.Website_sell_soccer_shoes_bumblebee.service.Impl.ExcelServiceImpl;
import com.example.Website_sell_soccer_shoes_bumblebee.utils.QRCodeGenerator;
import com.google.zxing.WriterException;
import jakarta.servlet.http.HttpServletRequest;
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
import org.springframework.util.FileCopyUtils;
//import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Controller
public class ChiTietSanPhamController {
    @Autowired
    ChiTietSanPhamService service;
    @Autowired
    DeGiayRepository deGiayRepo;
    @Autowired
    ExcelServiceImpl excelService;
    @Autowired
    ChatLieuRepository chatLieuRepo;
    @Autowired
    KichCoService kichCoService;
    @Autowired
    LoaiGiayRepository loaiGiayRepo;
    @Autowired
    MauSacReponsitory mauSacReponsitories;
    @Autowired
    SanPhamRepository sanPhamRepo;
    @Autowired
    ChiTietSanPhamRepo chiTietSanPhamRepo;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDSTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt động");
        dsTrangThai.put(0, "Ngưng Hoạt động");
        return dsTrangThai;
    }

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

    @ModelAttribute("dsGioiTinh")
    public Map<Boolean, String> getDsGioiTinh() {
        Map<Boolean, String> dsGT = new HashMap<>();
        dsGT.put(true, "Nam");
        dsGT.put(false, "Nữ");
        return dsGT;
    }

    @Data
    public static class SearchFormSP {
        String keyword;
    }

    @Data
    public static class SearchFormSPByMau {
        UUID idMau;
    }

    @Data
    public static class SearchDeGiay {
        UUID idDe;
    }

    @Data
    public static class SearchKC {
        UUID idKC;
    }

    @Data
    public static class SearchLoaiGiay {
        UUID idLG;
    }

    @Data
    public static class SearchChatlieu {
        UUID idChatLieu;
    }

    @Data
    public static class SortFormSP {
        String key = "";
    }

    @ModelAttribute("listSP")
    List<SanPham> listSP() {
        return sanPhamRepo.findAll();
    }

    @RequestMapping("/chi-tiet-san-pham/hien-thi")

    public String hienThiSanPham(@ModelAttribute("sortForm") SortFormSP sortFormSP, @ModelAttribute("sanpham") QLSanPham sp, @RequestParam(defaultValue = "0") int p, Model model) throws IOException, WriterException {

        if (p < 0) {
            p = 0;
        }

        Pageable pageable = PageRequest.of(p, 5);
        Page<ChiTietSanPham> qlSanPhamPage = service.getListSP(pageable);
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("lg", new SearchLoaiGiay());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("searchForm", new SearchFormSP());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("searchDG", new SearchDeGiay());

        return "/admin/index";
    }

    // search 2 loại giầy
    @GetMapping("/chi-tiet-san-pham/search2-loai-giay")
    public ResponseEntity<?> search2LoaiGiay(@RequestParam(name = "keyword") String keyword) {

        if (keyword == null || keyword == "") {
            return ResponseEntity.ok(chiTietSanPhamRepo.listLoaiGiay());
        } else {
            return ResponseEntity.ok(service.search2("%" + keyword + "%"));
        }
    }

    @GetMapping("/chi-tiet-san-pham/search2-kich-co")
    public ResponseEntity<List<KichCo>> search2KichCo(@RequestParam(name = "keyword", required = false) Integer size) {
        List<KichCo> result;
        if (size != null) {
            // Xử lý khi 'size' có giá trị
            result = service.search2KC(size);
        } else {
            // Xử lý khi 'size' là null (không được truyền vào)
            result = kichCoService.getList();
        }
        return ResponseEntity.ok(result);
    }

    // search 2 màu sắc
    @GetMapping("/chi-tiet-san-pham/search2-mau-sac")
    public ResponseEntity<?> search2MS(@RequestParam(name = "keyword") String ten) {

        if (ten == null || ten.equals("")) {
            return ResponseEntity.ok(mauSacReponsitories.findAll());
        } else {
            return ResponseEntity.ok(chiTietSanPhamRepo.searchMS("%" + ten + "%"));
        }
    }

    // search 2 đế giầy
    @GetMapping("/chi-tiet-san-pham/search2-de-giay")
    public ResponseEntity<?> search2DG(@RequestParam(name = "keyword") String loaiDe) {

        if (loaiDe == null || loaiDe.equals("")) {
            return ResponseEntity.ok(deGiayRepo.findAll());
        } else {
            return ResponseEntity.ok(chiTietSanPhamRepo.searchDG("%" + loaiDe + "%"));
        }
    }

    // search 2 chất liệu
    @GetMapping("/chi-tiet-san-pham/search2-chat-lieu")
    public ResponseEntity<?> search2CL(@RequestParam(name = "keyword") String ten) {

        if (ten == null || ten.equals("")) {
            return ResponseEntity.ok(chatLieuRepo.findAll());
        } else {
            return ResponseEntity.ok(chiTietSanPhamRepo.searchCL("%" + ten + "%"));
        }
    }


    // 12/11/2023
// search 2 loại giầy
    @GetMapping("/chi-tiet-san-pham/search22-loai-giay")
    public ResponseEntity<?> search22LoaiGiay(@RequestParam(name = "keyword") String keyword) {

        if (keyword == null || keyword == "") {
            return ResponseEntity.ok(service.listLG22(1));
        } else {
            return ResponseEntity.ok(service.search22LG("%" + keyword + "%", 1));
        }
    }

    @GetMapping("/chi-tiet-san-pham/search22-kich-co")
    public ResponseEntity<List<KichCo>> search22KichCo(@RequestParam(name = "keyword", required = false) Integer size) {
        List<KichCo> result;
        if (size != null) {
            // Xử lý khi 'size' có giá trị
            result = service.search22KC(size, 1);
        } else {
            // Xử lý khi 'size' là null (không được truyền vào)
            result = service.listKichCo22(1);
        }
        return ResponseEntity.ok(result);
    }

    // search 2 màu sắc
    @GetMapping("/chi-tiet-san-pham/search22-mau-sac")
    public ResponseEntity<?> search22MS(@RequestParam(name = "keyword") String ten) {

        if (ten == null || ten.equals("")) {
            return ResponseEntity.ok(service.listMauSac22(1));
        } else {
            return ResponseEntity.ok(chiTietSanPhamRepo.search22MS("%" + ten + "%", 1));
        }
    }

    // search 2 đế giầy
    @GetMapping("/chi-tiet-san-pham/search22-de-giay")
    public ResponseEntity<?> search22DG(@RequestParam(name = "keyword") String loaiDe) {

        if (loaiDe == null || loaiDe.equals("")) {
            return ResponseEntity.ok(service.listDeGiay22(1));
        } else {
            return ResponseEntity.ok(chiTietSanPhamRepo.search22DG("%" + loaiDe + "%", 1));
        }
    }

    // search 2 chất liệu
    @GetMapping("/chi-tiet-san-pham/search22-chat-lieu")
    public ResponseEntity<?> search22CL(@RequestParam(name = "keyword") String ten) {

        if (ten == null || ten.equals("")) {
            return ResponseEntity.ok(service.listChatLieu22(1));
        } else {
            return ResponseEntity.ok(chiTietSanPhamRepo.search22CL("%" + ten + "%", 1));
        }
    }

    //

    @RequestMapping("/chi-tiet-san-pham/search")
    public String searchSP(@ModelAttribute("searchForm") SearchFormSP searchFormSP, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Page<ChiTietSanPham> qlSanPhamPage;
        Pageable pageable = PageRequest.of(p, 5);
        if (searchFormSP.keyword != null && !searchFormSP.keyword.equals("")) {
            qlSanPhamPage = service.searchCTSP(searchFormSP.keyword, pageable);
        } else {
            qlSanPhamPage = service.getListSP(pageable);
        }
        model.addAttribute("lg", new SearchLoaiGiay());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchDG", new SearchDeGiay());
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("sanpham", new QLSanPham());
        model.addAttribute("sortForm", new SortFormSP());
        return "/admin/index";
    }

    // filer with combobox mau-sac
    @RequestMapping("/chi-tiet-san-pham/search-by-mausac")
    public String searchByMau(@ModelAttribute("searchFormByMau") SearchFormSPByMau searchFormSPByMau, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Page<ChiTietSanPham> qlSanPhamPage;
        Pageable pageable = PageRequest.of(p, 5);
        if (searchFormSPByMau.idMau != null && !searchFormSPByMau.idMau.equals("")) {
            qlSanPhamPage = service.searchByMau(searchFormSPByMau.idMau, pageable);
        } else {
            qlSanPhamPage = service.getListSP(pageable);
        }
        model.addAttribute("lg", new SearchLoaiGiay());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchDG", new SearchDeGiay());
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchForm", new SearchFormSP());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("sanpham", new QLSanPham());
        model.addAttribute("sortForm", new SortFormSP());
        return "/admin/index";
    }

    // filer with combobox kich co
    @RequestMapping("/chi-tiet-san-pham/search-by-kichco")
    public String searchByKC(@ModelAttribute("searchKC") SearchKC searchKC, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Page<ChiTietSanPham> qlSanPhamPage;
        Pageable pageable = PageRequest.of(p, 5);
        if (searchKC.idKC != null && !searchKC.idKC.equals("")) {
            qlSanPhamPage = service.searchKichCo(searchKC.idKC, pageable);
        } else {
            qlSanPhamPage = service.getListSP(pageable);
        }
        model.addAttribute("lg", new SearchLoaiGiay());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchForm", new SearchFormSP());
        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("sanpham", new QLSanPham());
        model.addAttribute("searchDG", new SearchDeGiay());
        model.addAttribute("sortForm", new SortFormSP());
        return "/admin/index";
    }

    // filer with combobox de giay
    @RequestMapping("/chi-tiet-san-pham/search-by-degiay")
    public String searchByDeGiay(@ModelAttribute("searchDG") SearchDeGiay searchDeGiay, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Page<ChiTietSanPham> qlSanPhamPage;
        Pageable pageable = PageRequest.of(p, 5);
        if (searchDeGiay.idDe != null && !searchDeGiay.idDe.equals("")) {
            qlSanPhamPage = service.searchDeGiay(searchDeGiay.idDe, pageable);
        } else {
            qlSanPhamPage = service.getListSP(pageable);
        }
        model.addAttribute("lg", new SearchLoaiGiay());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchForm", new SearchFormSP());
        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("sanpham", new QLSanPham());
        model.addAttribute("sortForm", new SortFormSP());
        return "/admin/index";
    }

    // Lỗi đâu Nam chịu
    @RequestMapping("/chi-tiet-san-pham/listLoaiGiay")
    public String listLoaiGiay(@ModelAttribute("lg") SearchLoaiGiay searchLoaiGiay, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Page<ChiTietSanPham> qlSanPhamPage;
        Pageable pageable = PageRequest.of(p, 5);
        if (searchLoaiGiay.idLG != null && !searchLoaiGiay.idLG.equals("")) {
            qlSanPhamPage = service.searchLoaiGiay(searchLoaiGiay.idLG, pageable);
        } else {
            qlSanPhamPage = service.getListSP(pageable);
        }
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("searchDG", new SearchDeGiay());
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchForm", new SearchFormSP());
        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("sanpham", new QLSanPham());
        model.addAttribute("sortForm", new SortFormSP());
        return "/admin/index";
    }


    // filer with combobox chat lieu
    @RequestMapping("/chi-tiet-san-pham/search-by-chatlieu")
    public String searchByChatLieu(@ModelAttribute("searchChatLieu") SearchChatlieu searchChatlieu, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Page<ChiTietSanPham> qlSanPhamPage;
        Pageable pageable = PageRequest.of(p, 5);
        if (searchChatlieu.idChatLieu != null && !searchChatlieu.idChatLieu.equals("")) {
            qlSanPhamPage = service.searchCL(searchChatlieu.idChatLieu, pageable);
        } else {
            qlSanPhamPage = service.getListSP(pageable);
        }
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("searchDG", new SearchDeGiay());
        model.addAttribute("lg", new SearchLoaiGiay());

        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchForm", new SearchFormSP());
        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("sanpham", new QLSanPham());
        model.addAttribute("sortForm", new SortFormSP());
        return "/admin/index";
    }

    // filer with combobox loai giay
    @RequestMapping("/chi-tiet-san-pham/search-by-loaigiay")
    public String searchByLoaiGiay(@ModelAttribute("lg") SearchLoaiGiay searchLoaiGiay, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Page<ChiTietSanPham> qlSanPhamPage;
        Pageable pageable = PageRequest.of(p, 5);
        if (searchLoaiGiay.idLG != null && !searchLoaiGiay.idLG.equals("")) {
            qlSanPhamPage = service.searchLoaiGiay(searchLoaiGiay.idLG, pageable);
        } else {
            qlSanPhamPage = service.getListSP(pageable);
        }
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("searchDG", new SearchDeGiay());
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        model.addAttribute("page", qlSanPhamPage);
        model.addAttribute("searchForm", new SearchFormSP());
        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("sanpham", new QLSanPham());
        model.addAttribute("sortForm", new SortFormSP());
        return "/admin/index";
    }

    @RequestMapping("/chi-tiet-san-pham/sort")
    public String sort(@ModelAttribute("sortForm") SortFormSP sortFormSP, @ModelAttribute("searchForm") SearchFormSP searchFormSP, @RequestParam(defaultValue = "0") int p, Model model) {
        if (p < 0) {
            p = 0;
        }
        Sort sort;
        model.addAttribute("searchDG", new SearchDeGiay());
        model.addAttribute("searchChatLieu", new SearchChatlieu());
        model.addAttribute("searchKC", new SearchKC());
        model.addAttribute("lg", new SearchLoaiGiay());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("searchFormByMau", new SearchFormSPByMau());
        sort = sortFormSP.key.equals("giaBan") ? Sort.by(Sort.Direction.DESC, "giaBan") : Sort.by(Sort.Direction.DESC, "giaGoc");
        Pageable pageable = PageRequest.of(p, 5, sort);
        Page<ChiTietSanPham> qlSanPhamPage = service.getListSP(pageable);
        model.addAttribute("page", qlSanPhamPage);

        System.out.println(sortFormSP.key);
        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        model.addAttribute("sanpham", new QLSanPham());
        return "/admin/index";
    }


    @RequestMapping("/chi-tiet-san-pham/update/{id}")
    public String updateKC(Model model, @Valid @ModelAttribute("sanpham") QLSanPham qlSanPham, BindingResult result, RedirectAttributes redirectAttributes) throws IOException, WriterException {
        model.addAttribute("lg", new LoaiGiay());
        model.addAttribute("vm", new ChatLieu());
        model.addAttribute("degiay", new DeGiay());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("ms", new MauSac());
        model.addAttribute("kichco", new KichCo());
        if (result.hasErrors()) {
            model.addAttribute("mess", "Lỗi! Vui lòng kiểm tra các trường trên !");
            model.addAttribute("view", "../chi-tiet-san-pham/add_update.jsp");
            return "/admin/index";
        }

        UUID idSP = service.getOneToAddModal(qlSanPham.getId());
        SanPham sp2 = sanPhamRepo.findById(idSP).orElse(null);
        model.addAttribute("tensp", sp2.getTenSanPham());

        ChiTietSanPham ctsp = service.getOne(qlSanPham.getId());
        qlSanPham.setNgayTao(ctsp.getNgayTao());
        ctsp.loadFromViewModel(qlSanPham);

        service.addKC(ctsp);
        //generate code qr

        String documentsPath = System.getProperty("user.home") + File.separator + "Documents";
        String qrCodeFolderPath = documentsPath + File.separator + "QRCode";
        new File(qrCodeFolderPath).mkdirs(); // Tạo thư mục "QRCode" nếu chưa tồn tại

        // Lưu QR code vào thư mục "QRCode" trong "Documents"
        QRCodeGenerator.generatorQRCode(ctsp, qrCodeFolderPath);

        //
//        redirectAttributes.addFlashAttribute("redirectUrl","/chi-tiet-san-pham/hien-thi");
        model.addAttribute("view", "../chi-tiet-san-pham/list.jsp");
        return "redirect:/chi-tiet-san-pham/hien-thi";
    }

    @RequestMapping("/chi-tiet-san-pham/view-update/{id}")
    public String viewUpdate(@PathVariable("id") UUID id, Model model) {
        ChiTietSanPham sp = service.getOne(id);

        model.addAttribute("lg", new LoaiGiay());
        model.addAttribute("vm", new ChatLieu());
        model.addAttribute("degiay", new DeGiay());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("ms", new MauSac());
        model.addAttribute("kichco", new KichCo());


        UUID idSP = service.getOneToAddModal(id);
        SanPham sp2 = sanPhamRepo.findById(idSP).orElse(null);
        model.addAttribute("tensp", sp2.getTenSanPham());

        model.addAttribute("action2", "/chi-tiet-san-pham/kich-co/add/" + id);
        model.addAttribute("action3", "/chi-tiet-san-pham/mau-sac/add/" + id);
        model.addAttribute("action4", "/chi-tiet-san-pham/loai-giay/add/" + id);
        model.addAttribute("action5", "/chi-tiet-san-pham/de-giay/add/" + id);
        model.addAttribute("action6", "/chi-tiet-san-pham/chat-lieu/add/" + id);
        model.addAttribute("action", "/chi-tiet-san-pham/update/" + sp.getId());
        model.addAttribute("sanpham", sp);
        model.addAttribute("view", "../chi-tiet-san-pham/add_update.jsp");
        return "/admin/index";
    }
//update list-spct

    @RequestMapping("/chi-tiet-san-pham/update-sp/{id}")
    public String updateSPCT(Model model, @Valid @ModelAttribute("sanpham") QLSanPham qlSanPham, BindingResult result, RedirectAttributes redirectAttributes) throws IOException, WriterException {
        model.addAttribute("lg", new LoaiGiay());
        model.addAttribute("vm", new ChatLieu());
        model.addAttribute("degiay", new DeGiay());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("ms", new MauSac());
        model.addAttribute("kichco", new KichCo());
        if (result.hasErrors()) {
            model.addAttribute("mess", "Lỗi! Vui lòng kiểm tra các trường trên !");
            model.addAttribute("view", "../chi-tiet-san-pham/add_update.jsp");
            return "/admin/index";
        }

        UUID idSP = service.getOneToAddModal(qlSanPham.getId());
        SanPham sp2 = sanPhamRepo.findById(idSP).orElse(null);
        model.addAttribute("tensp", sp2.getTenSanPham());
        model.addAttribute("searchForm", new SearchFormSP());

        ChiTietSanPham ctsp = service.getOne(qlSanPham.getId());
        qlSanPham.setNgayTao(ctsp.getNgayTao());
        ctsp.loadFromViewModel(qlSanPham);

        service.addKC(ctsp);
        //generate code qr
        String documentsPath = System.getProperty("user.home") + File.separator + "Documents";
        String qrCodeFolderPath = documentsPath + File.separator + "QRCode";
        new File(qrCodeFolderPath).mkdirs(); // Tạo thư mục "QRCode" nếu chưa tồn tại

        // Lưu QR code vào thư mục "QRCode" trong "Documents"
        QRCodeGenerator.generatorQRCode(ctsp, qrCodeFolderPath);

        //
//        redirectAttributes.addFlashAttribute("redirectUrl","/chi-tiet-san-pham/list-san-pham/" + idSP);
        model.addAttribute("view", "../chi-tiet-san-pham/list-spct.jsp");
        return "redirect:/chi-tiet-san-pham/list-san-pham/" + idSP;
    }

    @RequestMapping("/chi-tiet-san-pham/view-update-ctsp/{id}")
    public String viewUpdateCTSP(@PathVariable("id") UUID id, Model model) {
        ChiTietSanPham sp = service.getOne(id);

        model.addAttribute("lg", new LoaiGiay());
        model.addAttribute("vm", new ChatLieu());
        model.addAttribute("degiay", new DeGiay());
        model.addAttribute("SP", new SanPham());
        model.addAttribute("ms", new MauSac());
        model.addAttribute("kichco", new KichCo());


        UUID idSP = service.getOneToAddModal(id);
        SanPham sp2 = sanPhamRepo.findById(idSP).orElse(null);
        model.addAttribute("tensp", sp2.getTenSanPham());

        model.addAttribute("action2", "/chi-tiet-san-pham/kich-co/add/" + id);
        model.addAttribute("action3", "/chi-tiet-san-pham/mau-sac/add/" + id);
        model.addAttribute("action4", "/chi-tiet-san-pham/loai-giay/add/" + id);
        model.addAttribute("action5", "/chi-tiet-san-pham/de-giay/add/" + id);
        model.addAttribute("action6", "/chi-tiet-san-pham/chat-lieu/add/" + id);
        model.addAttribute("action", "/chi-tiet-san-pham/update-sp/" + sp.getId());
        model.addAttribute("sanpham", sp);
        model.addAttribute("view", "../chi-tiet-san-pham/add_update.jsp");
        model.addAttribute("view", "../chi-tiet-san-pham/add_update.jsp");
        return "/admin/index";
    }

    //    // modal
    @Autowired
    SanPhamService sanPhamService;

    @RequestMapping("/chi-tiet-san-pham/loai-giay/add/{id}")
    @ResponseBody
    public Map<String, Object> save(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("lg") LoaiGiay loaiGiay, BindingResult result) {
        Boolean hasE = result.hasErrors();
        Map<String, Object> response = new HashMap<>();
        List<LoaiGiay> list = loaiGiayRepo.findAll();
        UUID idSP = service.getOneToAddModal(id);
        SanPham sanPham2 = sanPhamRepo.findById(idSP).orElse(null);
        model.addAttribute("idsp", idSP);
        model.addAttribute("tensp", sanPham2.getTenSanPham());
        if (result.hasErrors()) {
            response.put("status", "error4");
            response.put("errors", getErrors(result));
            return response;
        }

        if (loaiGiayRepo.findByMa(loaiGiay.getMa()) != null) {
            result.rejectValue("ma", "duplicate4", "Lỗi! Mã không được trùng");
            response.put("status", "error4");
            response.put("errors", getErrors(result));
            response.put("field", "ma");
            return response;
        }
        if (loaiGiayRepo.findbyten(loaiGiay.getTentheloai()) != null) {
            result.rejectValue("tentheloai", "duplicate4", "Lỗi! Tên không được trùng");
            response.put("status", "error4");
            response.put("errors", getErrors(result));
            response.put("field", "tentheloai");
            return response;
        }

        loaiGiayRepo.save(loaiGiay);
        response.put("status", "success");
        return response;

    }

    @RequestMapping("/chi-tiet-san-pham/kich-co/add/{id}")
    @ResponseBody
    public Map<String, Object> addKC(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("kichco") KichCo kichCo, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        UUID idSP = service.getOneToAddModal(id);
        SanPham sanPham2 = sanPhamRepo.findById(idSP).orElse(null);
        model.addAttribute("idsp", idSP);
        model.addAttribute("tensp", sanPham2.getTenSanPham());
        if (result.hasErrors()) {
            response.put("status", "error3");
            response.put("errors", getErrors(result));
            return response;
        }

        if (kichCoService.findByMaKichCo(kichCo.getMaKichCo()) != null) {
            result.rejectValue("maKichCo", "duplicate3", "Lỗi! Mã không được trùng");
            response.put("status", "error3");
            response.put("errors", getErrors(result));
            response.put("field", "maKichCo");
            return response;
        }
        if (kichCoService.findBySize(kichCo.getSize()) != null) {
            result.rejectValue("size", "duplicate3", "Lỗi! Size không được trùng");
            response.put("status", "error3");
            response.put("errors", getErrors(result));
            response.put("field", "size");
            return response;
        }

        kichCoService.addKC(kichCo);
        response.put("status", "success");
        return response;
    }

    @PostMapping("/chi-tiet-san-pham/mau-sac/add/{id}")
    @ResponseBody
    public Map<String, Object> add(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("ms") MauSac ms, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        UUID idSP = service.getOneToAddModal(id);
        SanPham sanPham2 = sanPhamRepo.findById(idSP).orElse(null);
        model.addAttribute("idsp", idSP);
        model.addAttribute("tensp", sanPham2.getTenSanPham());

        if (result.hasErrors()) {
            response.put("status", "error2");
            response.put("errors", getErrors(result));
            return response;
        }

        if (mauSacReponsitories.findByMa(ms.getMa()) != null) {
            result.rejectValue("ma", "duplicate2", "Lỗi! Mã không được trùng");
            response.put("status", "error2");
            response.put("errors", getErrors(result));
            response.put("field", "ma"); // Thêm trường field để xác định lỗi của trường nào
            return response;
        }
        if (mauSacReponsitories.findByTen(ms.getTen()) != null) {
            result.rejectValue("ten", "duplicate2", "Lỗi! Tên màu không được trùng");
            response.put("status", "error2");
            response.put("errors", getErrors(result));
            response.put("field", "ten"); // Thêm trường field để xác định lỗi của trường nào
            return response;
        }

        mauSacReponsitories.save(ms);
        response.put("status", "success");
        return response;
    }

    @Autowired
    ChatLieuService chatLieuService;

    @RequestMapping("/chi-tiet-san-pham/chat-lieu/add/{id}")
    @ResponseBody
    public Map<String, Object> store(Model model, @PathVariable("id") UUID id,
                                     @Valid @ModelAttribute("vm") ChatLieu cl,
                                     BindingResult result
    ) {
        Map<String, Object> response = new HashMap<>();
        UUID idSP = service.getOneToAddModal(id);
        SanPham sanPham2 = sanPhamRepo.findById(idSP).orElse(null);
        model.addAttribute("idsp", idSP);
        model.addAttribute("tensp", sanPham2.getTenSanPham());
//        model.addAttribute("ms", new MauSac());
//        model.addAttribute("degiay", new DeGiay());
//        model.addAttribute("kichco", new KichCo());
//        model.addAttribute("lg", new LoaiGiay());
        if (result.hasErrors()) {
            response.put("status", "error1");
            response.put("errors", getErrors(result));
            return response;
        }

        if (chatLieuRepo.findByMa(cl.getMa()) != null) {
            result.rejectValue("ma", "duplicate1", "Lỗi! Mã không được trùng");
            response.put("status", "error1");
            response.put("errors", getErrors(result));
            response.put("field", "ma"); // Thêm trường field để xác định lỗi của trường nào
            return response;
        }
        if (chatLieuRepo.findByTen(cl.getTen()) != null) {
            result.rejectValue("ten", "duplicate1", "Lỗi! Tên chất liệu không được trùng");
            response.put("status", "error1");
            response.put("errors", getErrors(result));
            response.put("field", "ten"); // Thêm trường field để xác định lỗi của trường nào
            return response;
        }

        chatLieuRepo.save(cl);
        response.put("status", "success");
        return response;
    }

    //
    private List<String> getErrors(BindingResult result) {
        List<String> errors = new ArrayList<>();
        result.getFieldErrors().forEach(error -> errors.add(error.getField() + ": " + error.getDefaultMessage()));
        return errors;
    }

    @PostMapping("/chi-tiet-san-pham/de-giay/add/{id}")
    @ResponseBody
    public Map<String, Object> add(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("degiay") DeGiay degiay, BindingResult result) {
        UUID idSP = service.getOneToAddModal(id);
        Map<String, Object> response = new HashMap<>();
        SanPham sanPham2 = sanPhamRepo.findById(idSP).orElse(null);
        model.addAttribute("idsp", idSP);
        model.addAttribute("tensp", sanPham2.getTenSanPham());
//        model.addAttribute("ms", new MauSac());
//        model.addAttribute("vm", new ChatLieu());
//        model.addAttribute("kichco", new KichCo());
//        model.addAttribute("lg", new LoaiGiay());
//        if (result.hasErrors()) {
//            model.addAttribute("view", "../chi-tiet-san-pham/add_update.jsp");
//            return "redirect:/chi-tiet-san-pham/view-update/" + id;
////            return "/admin/index";
//        }
        if (result.hasErrors()) {
            response.put("status", "error");
            response.put("errors", getErrors(result));
            return response;
        }

        if (deGiayRepo.findByMa(degiay.getMa()) != null) {
            result.rejectValue("ma", "duplicate", "Lỗi! Mã không được trùng");
            response.put("status", "error");
            response.put("errors", getErrors(result));
            response.put("field", "ma"); // Thêm trường field để xác định lỗi của trường nào
            return response;
        }
        if (deGiayRepo.findByLoaiDe(degiay.getLoaiDe()) != null) {
            result.rejectValue("loaiDe", "duplicate", "Lỗi! Loại đế không được trùng");
            response.put("status", "error");
            response.put("errors", getErrors(result));
            response.put("field", "loaiDe"); // Thêm trường field để xác định lỗi của trường nào
            return response;
        }

        deGiayRepo.save(degiay);
        response.put("status", "success");
        return response;
//        model.addAttribute("view", "../chi-tiet-san-pham/add_update.jsp");
//        return "redirect:/chi-tiet-san-pham/view-update/" + id;
//        return "/admin/index";
    }


    // hình ảnh

    @GetMapping("/chi-tiet-san-pham/hinh-anh/view-add/{id}")
    public String viewAdd(Model model, @ModelAttribute("hinhAnh") HinhAnh hinhAnh, @PathVariable("id") UUID id) {
        ChiTietSanPham ctsp = service.getOne(id);
        model.addAttribute("idctsp", id);

        UUID idHinhANh = hinhAnhRepository.getIdHinhAnh(id);
        SanPham sp = hinhAnhService.getSanPhamByIDCTSP(id);
        if (idHinhANh != null) {
            HinhAnh hinhAnh2 = hinhAnhService.getHinhAnh(id);
            model.addAttribute("listHinhAnh", hinhAnh2);

            model.addAttribute("action4", "/hinh-anh-spct/update/" + idHinhANh);
            model.addAttribute("view", "../hinh-anh/add_update.jsp");
            return "/admin/index";

        } else {
            // Các xử lý khác nếu không tìm thấy idHinhAnh
            model.addAttribute("ctsp", ctsp);
            model.addAttribute("action4", "/chi-tiet-san-pham/hinh-anh/add/" + ctsp.getId());
            model.addAttribute("view", "../hinh-anh/add_update.jsp");
            return "/admin/index";
        }
    }

    @GetMapping("/chi-tiet-san-pham/hinh-anh-sp/view-add/{id}")
    public String viewAddHinhAnh(Model model, @ModelAttribute("hinhAnh") HinhAnh hinhAnh, @PathVariable("id") UUID id) {
        ChiTietSanPham ctsp = service.getOne(id);
        model.addAttribute("idctsp", id);

        UUID idHinhANh = hinhAnhRepository.getIdHinhAnh(id);
        SanPham sp = hinhAnhService.getSanPhamByIDCTSP(id);
        if (idHinhANh != null) {
            HinhAnh hinhAnh2 = hinhAnhService.getHinhAnh(id);
            model.addAttribute("listHinhAnh", hinhAnh2);

            model.addAttribute("action4", "/hinh-anh-ctsp/update/" + idHinhANh);
            model.addAttribute("view", "../hinh-anh/add_update.jsp");
            return "/admin/index";


        } else {
            // Các xử lý khác nếu không tìm thấy idHinhAnh
            model.addAttribute("ctsp", ctsp);
            model.addAttribute("action4", "/chi-tiet-san-pham/hinh-anh-sp/add/" + ctsp.getId());
            model.addAttribute("view", "../hinh-anh/add_update.jsp");
            return "/admin/index";
        }
    }

    @Autowired
    HinhAnhService hinhAnhService;
    @Autowired
    HinhAnhRepository hinhAnhRepository;

    @PostMapping("/chi-tiet-san-pham/hinh-anh/add/{id}")
    public String save(Model model,
                       @RequestParam(name = "tenanh") MultipartFile tenanh,
                       @RequestParam(name = "duongdan1") MultipartFile duongdan1,
                       @RequestParam(name = "duongdan2") MultipartFile duongdan2,
                       @RequestParam(name = "duongdan3") MultipartFile duongdan3,
//                       @RequestParam(name = "duongdan4") MultipartFile duongdan4,
//                       @RequestParam(name = "duongdan5") MultipartFile duongdan5,
                       @PathVariable UUID id,
                       @RequestParam(name = "ctsp") ChiTietSanPham ctsp
    ) {
        HinhAnh hinhAnh = new HinhAnh();
        hinhAnh.setCtsp(ctsp);

        try {
            // Lấy đường dẫn tới thư mục lưu trữ tệp tin ảnh từ cấu hình
            String uploadPath = hinhAnhService.getImageUploadPath();

            // Tạo thư mục lưu trữ nếu chưa tồn tại
            Path uploadDir = Paths.get(uploadPath);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            // Lưu trữ các tệp tin ảnh và sử dụng tên tệp tin làm đường dẫn
            MultipartFile[] imageFiles = {tenanh, duongdan1, duongdan2, duongdan3};
            for (int i = 0; i < imageFiles.length; i++) {
                MultipartFile file = imageFiles[i];
                if (file != null && !file.isEmpty()) {
                    String fileName = file.getOriginalFilename().toLowerCase(); // Sử dụng tên tệp tin làm đường dẫn
                    Path filePath = uploadDir.resolve(fileName);
                    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                    // Gán tên tệp tin ảnh và đường dẫn tới các thuộc tính tương ứng của đối tượng HinhAnh
                    switch (i) {
                        case 0:
                            hinhAnh.setTenanh(fileName);
                            break;
                        case 1:
                            hinhAnh.setDuongdan1(fileName);
                            break;
                        case 2:
                            hinhAnh.setDuongdan2(fileName);
                            break;
                        case 3:
                            hinhAnh.setDuongdan3(fileName);
                            break;
//                        case 4:
//                            hinhAnh.setDuongdan4(fileName);
//                            break;
//                        case 5:
//                            hinhAnh.setDuongdan5(fileName);
//                            break;

                        default:
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        hinhAnhRepository.save(hinhAnh);
        return "redirect:/chi-tiet-san-pham/hien-thi";
    }

    @PostMapping("/chi-tiet-san-pham/hinh-anh-sp/add/{id}")
    public String saveHinhAnhCTSP(Model model,
                                  @RequestParam(name = "tenanh") MultipartFile tenanh,
                                  @RequestParam(name = "duongdan1") MultipartFile duongdan1,
                                  @RequestParam(name = "duongdan2") MultipartFile duongdan2,
                                  @RequestParam(name = "duongdan3") MultipartFile duongdan3,
//                                  @RequestParam(name = "duongdan4") MultipartFile duongdan4,
//                                  @RequestParam(name = "duongdan5") MultipartFile duongdan5,
                                  @PathVariable UUID id,
                                  @RequestParam(name = "ctsp") ChiTietSanPham ctsp
    ) {
        HinhAnh hinhAnh = new HinhAnh();
        hinhAnh.setCtsp(ctsp);
        SanPham sp = hinhAnhService.getSanPhamByIDCTSP(id);
        try {
            // Lấy đường dẫn tới thư mục lưu trữ tệp tin ảnh từ cấu hình
            String uploadPath = hinhAnhService.getImageUploadPath();

            // Tạo thư mục lưu trữ nếu chưa tồn tại
            Path uploadDir = Paths.get(uploadPath);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            // Lưu trữ các tệp tin ảnh và sử dụng tên tệp tin làm đường dẫn
            MultipartFile[] imageFiles = {tenanh, duongdan1, duongdan2, duongdan3};
            for (int i = 0; i < imageFiles.length; i++) {
                MultipartFile file = imageFiles[i];
                if (file != null && !file.isEmpty()) {
                    String fileName = file.getOriginalFilename().toLowerCase(); // Sử dụng tên tệp tin làm đường dẫn
                    Path filePath = uploadDir.resolve(fileName);
                    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                    // Gán tên tệp tin ảnh và đường dẫn tới các thuộc tính tương ứng của đối tượng HinhAnh
                    switch (i) {
                        case 0:
                            hinhAnh.setTenanh(fileName);
                            break;
                        case 1:
                            hinhAnh.setDuongdan1(fileName);
                            break;
                        case 2:
                            hinhAnh.setDuongdan2(fileName);
                            break;
                        case 3:
                            hinhAnh.setDuongdan3(fileName);
                            break;
//                        case 4:
//                            hinhAnh.setDuongdan4(fileName);
//                            break;
//                        case 5:
//                            hinhAnh.setDuongdan5(fileName);
//                            break;

                        default:
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        hinhAnhRepository.save(hinhAnh);
        return "redirect:/chi-tiet-san-pham/list-san-pham/" + sp.getId();
    }

    @PostMapping("/chi-tiet-san-pham/upload")
    public String uploadExcelFile(@RequestParam("file") MultipartFile file, Model model) {
        try {
            // Kiểm tra loại tệp Excel
//            List<ChiTietSanPham> persons = ExcelUtil.readExcel(file);
//
//            for (ChiTietSanPham person : persons) {
//                if (StringUtils.isEmpty(person.getSoLuong())) {
//                    model.addAttribute("error", "Trường 'số lượng' không được để trống.");
//                    return "forward:/chi-tiet-san-pham/hien-thi";
//                }if(StringUtils.isEmpty())
//                // Kiểm tra các trường thuộc tính khác và xử lý tương tự nếu cần.
//            }

            if (!file.getOriginalFilename().endsWith(".xls") && !file.getOriginalFilename().endsWith(".xlsx")) {
                model.addAttribute("error", "Vui lòng chọn một tệp Excel (.xls hoặc .xlsx).");
                return "redirect:/chi-tiet-san-pham/hien-thi";
            }

            // Tiếp tục xử lý nếu là tệp Excel hợp lệ
            excelService.saveDataFromExcel(file);
            model.addAttribute("success", "Dữ liệu đã được chèn thành công.");
            return "redirect:/chi-tiet-san-pham/hien-thi";
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Đã xảy ra lỗi: " + e.getMessage());
            return "forward:/chi-tiet-san-pham/hien-thi";
        }
    }


}
