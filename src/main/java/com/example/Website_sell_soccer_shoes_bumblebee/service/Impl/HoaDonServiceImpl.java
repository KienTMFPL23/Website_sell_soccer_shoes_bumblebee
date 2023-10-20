package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.*;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.HoaDonChiTietRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.HoaDonRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.NhanVienRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.HoaDonService;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.math3.analysis.function.Identity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    NhanVienRepository nhanVienRepository;

    @Autowired
    HttpSession session;


    @Override
    public Page<HoaDon> search(String key, Pageable pageable) {
        if (key != null) {
            return hoaDonRepository.search(key, pageable);
        }
        return hoaDonRepository.findAll(pageable);
    }

    @Override
    public Page<HoaDon> searchALlBetweenDates(Date fromDate, Date toDate, Pageable pageable) {
        return hoaDonRepository.searchALlBetweenDates(fromDate, toDate, pageable);
    }

    @Override
    public HoaDon searchHoaDon(String hoaDon) {
        return hoaDonRepository.searchHoaDon(hoaDon);
    }

    @Override
    public List<HoaDon> findAll() {
        return hoaDonRepository.findAll();
    }

    @Override
    public List<HoaDon> listHoaDonCho() {
        return hoaDonRepository.getListByTrangThai();
    }

    private static int maHoaDon = 1;

    @Override
    public HoaDon createHoaDon() throws ParseException {
        HoaDon hoaDon = new HoaDon();
        String formatHoaDon = "HD" + String.format("%08d", maHoaDon);
        hoaDon.setMaHoaDon(formatHoaDon);
        maHoaDon++;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String format = sdf.format(date);
        hoaDon.setNgayTao(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(format));
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        NhanVien nhanVien = nhanVienRepository.findByIdTaiKhoan(taiKhoan.getId());
        hoaDon.setNhanVien(nhanVien);
        hoaDon.setTrangThai(0);
        return hoaDonRepository.save(hoaDon);
    }

    @Override
    public HoaDon saveHoaDon(HoaDon hoaDon) {
        return hoaDonRepository.save(hoaDon);
    }
//    @Override
//    public SanPham udpateSanPham(SanPham sanPham, UUID id) {
//        Optional<SanPham> exitingSanPham = sanPhamRepository.findById(id);
//        if (exitingSanPham.isPresent()) {
//            SanPham spToUpdate = exitingSanPham.get();
//            spToUpdate.setLastModifiedDate(LocalDateTime.now());
//            spToUpdate.setTenSanPham(sanPham.getTenSanPham());
//            spToUpdate.setTrangThai(sanPham.getTrangThai());
//            sanPhamRepository.save(spToUpdate);
//            return spToUpdate;
//        } else {
//            return null;
//        }
//    }
    @Override
    public HoaDon updateHoaDon(UUID id,Integer trangThai,HoaDon hoaDon) {
        Optional<HoaDon> exitingHoaDon = hoaDonRepository.findById(id);
        if (exitingHoaDon.isPresent()) {
            HoaDon hdToUpdate = exitingHoaDon.get();
            hdToUpdate.setSdt(hoaDon.getSdt());
            hdToUpdate.setMaHoaDon(hoaDon.getMaHoaDon());
            hdToUpdate.setGhiChu(hoaDon.getGhiChu());
            hdToUpdate.setDiaChiShip(hoaDon.getDiaChiShip());
            hdToUpdate.setHinhthucThanhToan(hoaDon.getHinhthucThanhToan());
//            hoaDon.set
            hdToUpdate.setTrangThai(trangThai);
            hoaDonRepository.save(hdToUpdate);
            return hdToUpdate;
        } else {
            return null;
        }
    }

    @Override
    public HoaDon deleteHoaDon(UUID id) {
        HoaDon hoaDon = hoaDonRepository.findById(id).orElse(null);
//        hoaDonChiTietRepository.deleteHDCTById(id);
        if (hoaDon != null) {
            hoaDonRepository.delete(hoaDon);
        }
        return hoaDon;
    }

    @Override
    public HoaDon getOne(UUID id) {
        return hoaDonRepository.findById(id).orElse(null);
    }

    @Override
    public List<HoaDon> getId(UUID id) {
        return hoaDonRepository.findId(id);
    }

}
