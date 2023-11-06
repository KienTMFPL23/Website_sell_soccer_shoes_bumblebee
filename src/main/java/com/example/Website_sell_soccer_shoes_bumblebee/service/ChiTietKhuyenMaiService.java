package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChiTietKhuyenMai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ChiTietKhuyenMaiService {

    Page<ChiTietKhuyenMai> getAll(Pageable pageable);

    ChiTietKhuyenMai save(ChiTietKhuyenMai ctkm);

    List<ChiTietKhuyenMai> findIdCTSP(UUID idCTSP);

    ChiTietKhuyenMai findID(UUID idCTKM);

    ChiTietKhuyenMai findCtkmByIdKmAndCtsp(UUID idCTSP, UUID idKM);
}
