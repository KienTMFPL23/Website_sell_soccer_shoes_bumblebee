package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChiTietKhuyenMai;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChiTietSanPham;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDon;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDonChiTiet;

import java.util.List;
import java.util.UUID;

public interface HoaDonChiTietService {

    List<HoaDonChiTiet> getHoaDonById(UUID id);

    List<HoaDonChiTiet> getListHoaDonCTByIdHoaDon(UUID id);

    HoaDonChiTiet getOneHoaDon(UUID id);

    HoaDonChiTiet saveHoaDonCT(HoaDonChiTiet hoaDonChiTiet);

    HoaDonChiTiet deleteHoaDonCT(UUID id);

    HoaDonChiTiet getAndUpdateSanPhamInHDCT(UUID idHoaDon,UUID idSP);

    Double getTotalMoney(List<HoaDonChiTiet> list);

    Double tongTienKhuyenMai(List<HoaDonChiTiet> list);

    HoaDonChiTiet save(HoaDonChiTiet hdct);

    List<HoaDonChiTiet> saveAll(List<HoaDonChiTiet> hdct);

    void deleteByHoaDon(UUID idHoaDon);

    List<HoaDonChiTiet> getHoaDonTheoHoaDonChiTiet(UUID id);

    void removeHDCT(UUID idHoaDon);
    void removeHD(UUID idHoaDon);


    List<HoaDonChiTiet> hoaDonMuonDoi(String maHoaDon);

    Double getDonGiaKhiGiam(List<ChiTietKhuyenMai> listCTKM);

}
