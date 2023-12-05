package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.repository.HoaDonChiTietRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ThongKeController {

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @GetMapping("/bumblebee/thong-ke/san-pham-da-ban")
    public String sanPhamDaBan(Model model) {

        int soSPDaBan = hoaDonChiTietRepository.tinhSoLuongSanPhamDaban();
        int soHoaDonDaBan = hoaDonRepository.tinhTongSoLuongHoaDonDaBan();
        int soLuongSanPhamDaBanThang11 = hoaDonChiTietRepository.tinhSoLuongSanPhamDaBanThang11();
        int soLuongSanPhamDaBanThang12 = hoaDonChiTietRepository.tinhSoLuongSanPhamDaBanThang12();
        int soLuongSanPhamDaBanThang10 = hoaDonChiTietRepository.tinhSoLuongSanPhamDaBanThang10();
        int soLuongSanPhamDaBanThang9 = hoaDonChiTietRepository.tinhSoLuongSanPhamDaBanThang9();
        int soLuongSanPhamDaBanThang8 = hoaDonChiTietRepository.tinhSoLuongSanPhamDaBanThang8();
        int soLuongSanPhamDaBanThang7 = hoaDonChiTietRepository.tinhSoLuongSanPhamDaBanThang7();
        int soLuongSanPhamDaBanThang6 = hoaDonChiTietRepository.tinhSoLuongSanPhamDaBanThang6();
        int soLuongSanPhamDaBanThang5 = hoaDonChiTietRepository.tinhSoLuongSanPhamDaBanThang5();
        int soLuongSanPhamDaBanThang4 = hoaDonChiTietRepository.tinhSoLuongSanPhamDaBanThang4();
        int soLuongSanPhamDaBanThang3 = hoaDonChiTietRepository.tinhSoLuongSanPhamDaBanThang3();
        int soLuongSanPhamDaBanThang2 = hoaDonChiTietRepository.tinhSoLuongSanPhamDaBanThang2();
        int soLuongSanPhamDaBanThang1 = hoaDonChiTietRepository.tinhSoLuongSanPhamDaBanThang1();


        model.addAttribute("soSPDaBan", soSPDaBan);
        model.addAttribute("soHoaDonDaBan", soHoaDonDaBan);
        model.addAttribute("soLuongSanPhamDaBanThang11", soLuongSanPhamDaBanThang11);
        model.addAttribute("soLuongSanPhamDaBanThang12", soLuongSanPhamDaBanThang12);
        model.addAttribute("soLuongSanPhamDaBanThang10", soLuongSanPhamDaBanThang10);
        model.addAttribute("soLuongSanPhamDaBanThang9", soLuongSanPhamDaBanThang9);
        model.addAttribute("soLuongSanPhamDaBanThang8", soLuongSanPhamDaBanThang8);
        model.addAttribute("soLuongSanPhamDaBanThang7", soLuongSanPhamDaBanThang7);
        model.addAttribute("soLuongSanPhamDaBanThang6", soLuongSanPhamDaBanThang6);
        model.addAttribute("soLuongSanPhamDaBanThang5", soLuongSanPhamDaBanThang5);
        model.addAttribute("soLuongSanPhamDaBanThang4", soLuongSanPhamDaBanThang4);
        model.addAttribute("soLuongSanPhamDaBanThang3", soLuongSanPhamDaBanThang3);
        model.addAttribute("soLuongSanPhamDaBanThang2", soLuongSanPhamDaBanThang2);
        model.addAttribute("soLuongSanPhamDaBanThang1", soLuongSanPhamDaBanThang1);

        model.addAttribute("view", "../thong-ke/index.jsp");
        return "admin/index";
    }

    @GetMapping("/bumblebee/thong-ke/don-hang-da-ban")
    public String donHangDaBan(Model model) {
        int soSPDaBan = hoaDonChiTietRepository.tinhSoLuongSanPhamDaban();
        int soHoaDonDaBan = hoaDonRepository.tinhTongSoLuongHoaDonDaBan();
        int soDonHangDaBanThang1 = hoaDonRepository.tinhTongHoaDonDaBanThang1();
        int soDonHangDaBanThang2 = hoaDonRepository.tinhTongHoaDonDaBanThang2();
        int soDonHangDaBanThang3 = hoaDonRepository.tinhTongHoaDonDaBanThang3();
        int soDonHangDaBanThang4 = hoaDonRepository.tinhTongHoaDonDaBanThang4();
        int soDonHangDaBanThang5 = hoaDonRepository.tinhTongHoaDonDaBanThang5();
        int soDonHangDaBanThang6 = hoaDonRepository.tinhTongHoaDonDaBanThang6();
        int soDonHangDaBanThang7 = hoaDonRepository.tinhTongHoaDonDaBanThang7();
        int soDonHangDaBanThang8 = hoaDonRepository.tinhTongHoaDonDaBanThang8();
        int soDonHangDaBanThang9 = hoaDonRepository.tinhTongHoaDonDaBanThang9();
        int soDonHangDaBanThang10 = hoaDonRepository.tinhTongHoaDonDaBanThang10();
        int soDonHangDaBanThang11 = hoaDonRepository.tinhTongHoaDonDaBanThang11();
        int soDonHangDaBanThang12 = hoaDonRepository.tinhTongHoaDonDaBanThang12();

        model.addAttribute("soDonHangDaBanThang1", soDonHangDaBanThang1);
        model.addAttribute("soDonHangDaBanThang2", soDonHangDaBanThang2);
        model.addAttribute("soDonHangDaBanThang3", soDonHangDaBanThang3);
        model.addAttribute("soDonHangDaBanThang4", soDonHangDaBanThang4);
        model.addAttribute("soDonHangDaBanThang5", soDonHangDaBanThang5);
        model.addAttribute("soDonHangDaBanThang6", soDonHangDaBanThang6);
        model.addAttribute("soDonHangDaBanThang7", soDonHangDaBanThang7);
        model.addAttribute("soDonHangDaBanThang8", soDonHangDaBanThang8);
        model.addAttribute("soDonHangDaBanThang9", soDonHangDaBanThang9);
        model.addAttribute("soDonHangDaBanThang10", soDonHangDaBanThang10);
        model.addAttribute("soDonHangDaBanThang11", soDonHangDaBanThang11);
        model.addAttribute("soDonHangDaBanThang12", soDonHangDaBanThang12);
        model.addAttribute("soSPDaBan", soSPDaBan);
        model.addAttribute("soHoaDonDaBan", soHoaDonDaBan);
        model.addAttribute("view", "../thong-ke/don_hang_da_ban.jsp");
        return "admin/index";
    }


}
