package com.example.Website_sell_soccer_shoes_bumblebee.controller;


import com.example.Website_sell_soccer_shoes_bumblebee.entity.GioHang;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.NhanVien;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.TaiKhoan;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.GioHangRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.TaiKhoanRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.NhanVienService;
import com.itextpdf.forms.xfdf.Mode;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

@Controller
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private GioHangRepository gioHangRepository;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(0, "Hoạt động");
        dsTrangThai.put(1, "Không hoạt động");
        return dsTrangThai;
    }

    @ModelAttribute("dsGioiTinh")
    public Map<Boolean, String> getDsGioiTinh() {
        Map<Boolean, String> dsGioiTinh = new HashMap<>();
        dsGioiTinh.put(true, "Nam");
        dsGioiTinh.put(false, "Nữ");
        return dsGioiTinh;
    }

    @GetMapping("/nhan-vien/thong-tin")
    public String thongTin(Model model) {
        List<NhanVien> list = nhanVienService.getAll();
        String donHang = "thong-tin-nhan-vien";
        model.addAttribute("list", list);
        model.addAttribute("donHang", donHang);
        model.addAttribute("view", "../nhan-vien/nhan_vien.jsp");
        return "/admin/index";
    }

//    @GetMapping("/nhan-vien/view-add")
//    public String viewAdd(Model model, @ModelAttribute("nv") NhanVien nv) {
//        model.addAttribute("action", "/nhan-vien/add");
//        model.addAttribute("view", "../nhan-vien/add_update.jsp");
//        return "/admin/index";
//    }
//
//    @PostMapping("/nhan-vien/add")
//    public String add(Model model, @Valid @ModelAttribute("nv") NhanVien nv, BindingResult result) throws ParseException {
//
//        if (result.hasErrors()) {
//            model.addAttribute("view", "../nhan-vien/add_update.jsp");
//            return "/admin/index";
//        }
//
//        if (nhanVienService.getByMa(nv.getMa()) != null) {
//            model.addAttribute("mess_Ma", "Lỗi! Mã không được trùng");
//            model.addAttribute("view", "../nhan-vien/add_update.jsp");
//            return "/admin/index";
//        }
//
//
////        nv.setNgaySinh(nv.getNgaySinh());
//        nhanVienService.save(nv);
//        return "redirect:/nhan-vien/thong-tin";
//    }

    @GetMapping("/nhan-vien/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable(name = "id") UUID id) {
        NhanVien nv = nhanVienService.getById(id);
        model.addAttribute("nv", nv);
        model.addAttribute("action", "/nhan-vien/update/" + nv.getId());

        model.addAttribute("view", "../nhan-vien/add_update.jsp");
        return "/admin/index";
    }

    @PostMapping("/nhan-vien/update/{id}")
    public String update(Model model, @Valid @ModelAttribute("nv") NhanVien nv, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("view", "../nhan-vien/add_update.jsp");

            return "/admin/index";
        }

        nhanVienService.save(nv);
        return "redirect:/nhan-vien/thong-tin";

    }

    @GetMapping("/nhan-vien/tai-khoan")
    public String taiKhoan(Model model, @ModelAttribute("nv") NhanVien nv) {
        List<TaiKhoan> list = taiKhoanRepository.listByRole2();
        String donHang = "tai-khoan-nhan-vien";
        model.addAttribute("list", list);
        model.addAttribute("donHang", donHang);
        model.addAttribute("view", "../nhan-vien/tai_khoan.jsp");
        return "/admin/index";
    }

    @ModelAttribute("dsRole")
    public Map<Integer, String> getDsRole() {
        Map<Integer, String> dsRole = new HashMap<>();
        dsRole.put(2, "Nhân viên");
        return dsRole;
    }

    @GetMapping("/nhan-vien/tai-khoan/view-add")
    public String viewAddTaiKhoan(Model model, @ModelAttribute("tk") TaiKhoan tk) {
        model.addAttribute("action", "/nhan-vien/tai-khoan/add");
        model.addAttribute("view", "../nhan-vien/tai_khoan_nv_add_update.jsp");
        return "/admin/index";
    }

    @PostMapping("/nhan-vien/tai-khoan/add")
    public String addTaiKhoan(Model model, @Valid @ModelAttribute("tk") TaiKhoan tk, BindingResult result) throws ParseException {

        if (result.hasErrors()) {
            model.addAttribute("view", "../nhan-vien/tai_khoan_nv_add_update.jsp");
            return "/admin/index";
        }

        if (taiKhoanRepository.getByUsername(tk.getUsername()) != null) {
            model.addAttribute("mess_Ma", "Lỗi! Username không được trùng");
            model.addAttribute("view", "../nhan-vien/tai_khoan_nv_add_update.jsp");
            return "/admin/index";
        }

//        nv.setNgaySinh(nv.getNgaySinh());
        taiKhoanRepository.save(tk);

        return "redirect:/nhan-vien/tai-khoan";
    }

    @GetMapping("/nhan-vien/tai-khoan/view-update/{id}")
    public String viewUpdateTaiKhoan(Model model, @PathVariable(name = "id") UUID id) {
        TaiKhoan tk = taiKhoanRepository.getById(id);
        model.addAttribute("tk", tk);
        model.addAttribute("action", "/nhan-vien/tai-khoan/update/" + tk.getId());

        model.addAttribute("view", "../nhan-vien/tai_khoan_nv_add_update.jsp");
        return "/admin/index";
    }

    @PostMapping("/nhan-vien/tai-khoan/update/{id}")
    public String updateTaiKhoan(Model model, @Valid @ModelAttribute("tk") TaiKhoan tk, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("view", "../nhan-vien/tai_khoan_nv_add_update.jsp");

            return "/admin/index";
        }

        taiKhoanRepository.save(tk);
        return "redirect:/nhan-vien/tai-khoan";

    }

    @RequestMapping("/nhan-vien/them-thong-tin/{idNV}")
    public String themThongTinNV(Model model, @Valid @ModelAttribute("nv") NhanVien nv, @PathVariable(name = "idNV") UUID idNV,
                                 BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("view", "../nhan-vien/add_update.jsp");
            return "/admin/index";
        }

        if (nhanVienService.getByMa(nv.getMa()) != null) {
            model.addAttribute("mess_Ma", "Lỗi! Mã không được trùng");
            model.addAttribute("view", "../nhan-vien/add_update.jsp");
            return "/admin/index";
        }
        TaiKhoan taiKhoan = taiKhoanRepository.getById(idNV);
        nv.setTaiKhoanNV(taiKhoan);
        nhanVienService.save(nv);
        GioHang gioHang = new GioHang();
        gioHang.setNhanVien(nv);
        gioHang.setNgayTao(new Date());
        gioHang.setTrangThai(0);
        gioHangRepository.save(gioHang);
        return "redirect:/nhan-vien/thong-tin";
    }
}
