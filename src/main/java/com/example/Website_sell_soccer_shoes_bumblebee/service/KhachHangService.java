package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.KhachHang;

import java.util.UUID;

public interface KhachHangService {
    KhachHang saveKhachHang(KhachHang khachHang);
    KhachHang findId(UUID id);
}
