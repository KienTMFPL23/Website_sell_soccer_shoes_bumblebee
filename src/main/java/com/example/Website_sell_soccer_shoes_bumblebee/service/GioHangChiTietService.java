package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.GioHangChiTiet;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDonChiTiet;

import java.util.List;
import java.util.UUID;

public interface GioHangChiTietService {

    List<GioHangChiTiet> getListGHCT();

    List<GioHangChiTiet> listGHCTByKH(UUID id);

    GioHangChiTiet findId(UUID id, UUID idKH);

    Double getTotalMoney(List<GioHangChiTiet> list);

    void deleteGHCT(UUID id);

}
