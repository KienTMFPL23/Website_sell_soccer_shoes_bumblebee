package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.KhachHang;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.KhachHangRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Override
    public KhachHang findId(UUID id) {
        return khachHangRepository.findId(id);
    }

    @Override
    public KhachHang saveKhachHang(KhachHang khachHang) {
        return khachHangRepository.save(khachHang);
    }

    @Override
    public List<KhachHang> getAll() {
        return khachHangRepository.findAll();
    }

    @Override
    public KhachHang findKHBySDT(String sdt) {
        return khachHangRepository.findKHBySDT(sdt);
    }

    @Override
    public KhachHang searchKhachHang(String maKhachHang) {
        return khachHangRepository.searchKhachHang(maKhachHang);
    }

    @Override
    public String searchMaxHoaDon() {
        return khachHangRepository.searchMaxMaHoaDon();
    }
}
