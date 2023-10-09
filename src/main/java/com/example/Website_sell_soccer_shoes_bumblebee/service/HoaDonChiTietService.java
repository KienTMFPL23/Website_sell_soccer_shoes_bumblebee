package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDonChiTiet;

import java.util.List;
import java.util.UUID;

public interface HoaDonChiTietService {

    List<HoaDonChiTiet> getHoaDonById(UUID id);

    List<HoaDonChiTiet> getListHoaDonCTByIdHoaDon(UUID id);

    HoaDonChiTiet getOneHoaDon(UUID id);

    HoaDonChiTiet saveHoaDonCT(HoaDonChiTiet hoaDonChiTiet);

    HoaDonChiTiet deleteHoaDonCT(UUID id);

    HoaDonChiTiet getAndUpdateSanPhamInHDCT(UUID idSP);

    HoaDonChiTiet getSanPhamInHDCT(UUID idSP);

    Double getTotalMoney(List<HoaDonChiTiet> list);
}
