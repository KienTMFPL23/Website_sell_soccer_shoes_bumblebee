package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface SanPhamService {

    List<SanPham> getList();
    Page<SanPham> findAllSP(Pageable pageable);

    Page<SanPham> findByKeyword(String key,Pageable pageable);

    SanPham addSanPham(SanPham sanPham);

    SanPham udpateSanPham(SanPham sanPham);

    SanPham getOne(UUID id);

    SanPham getByMa(String ma);

    List<SanPham> searchSanPham(String keyword);

}
