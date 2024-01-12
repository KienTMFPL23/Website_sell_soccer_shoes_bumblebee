package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDon;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDonChiTiet;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.HoaDonRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.ChiTietSanPhamService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HoaDonScheduler {

    @Autowired
    HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Scheduled(fixedDelay = 7200000) // Định kỳ 1 phút
    public void auToDeleteHoaDonCho() {
        // Xóa danh sách hóa đơn
        List<HoaDon> listHDC = hoaDonRepository.getListByTrangThai();
        for (HoaDon hd : listHDC) {
            List<HoaDonChiTiet> listHoaDonCT = hoaDonChiTietService.getListHoaDonCTByIdHoaDon(hd.getId());
            if (listHoaDonCT.size() != 0) {
                for (HoaDonChiTiet hdct : listHoaDonCT) {
                    hoaDonChiTietService.deleteHoaDonCT(hdct.getId());
                    chiTietSanPhamService.updateDelete(hdct.getChiTietSanPham().getId(), hdct.getSoLuong());
                }
            }
            hoaDonRepository.delete(hd);
        }
    }
}
