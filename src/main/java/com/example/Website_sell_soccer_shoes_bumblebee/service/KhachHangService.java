package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.KhachHang;

import java.util.List;
import java.util.UUID;

public interface KhachHangService {
    KhachHang findId(UUID id);
    KhachHang saveKhachHang(KhachHang khachHang);
    List<KhachHang> getAll();

    KhachHang findKHBySDT(String sdt);

    KhachHang searchKhachHang(String maKhachHang);

    String searchMaxKH();

    void createMaKH(KhachHang khachHang);

    List<KhachHang>  getAllKHOderBy();
}
