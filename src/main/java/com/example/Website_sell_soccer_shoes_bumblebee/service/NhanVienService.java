package com.example.Website_sell_soccer_shoes_bumblebee.service;


import com.example.Website_sell_soccer_shoes_bumblebee.entity.NhanVien;

import java.util.List;
import java.util.UUID;

public interface NhanVienService {
    NhanVien getOne(UUID id);

    List<NhanVien> getAll();

    NhanVien save(NhanVien nhanVien);

    NhanVien getById(UUID id);

    NhanVien getByMa(String ma);
}
