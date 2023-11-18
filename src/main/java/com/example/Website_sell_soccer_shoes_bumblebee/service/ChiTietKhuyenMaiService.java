package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChiTietKhuyenMai;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.KhuyenMai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ChiTietKhuyenMaiService {

    List<ChiTietKhuyenMai> getAll();

    ChiTietKhuyenMai save(ChiTietKhuyenMai ctkm);

    ChiTietKhuyenMai findIdCTSP(UUID idCTSP);

    ChiTietKhuyenMai findID(UUID idCTKM);

    ChiTietKhuyenMai findCtkmByIdKmAndCtsp(UUID idCTSP, UUID idKM);

    List<ChiTietKhuyenMai> searchKhoangNgay(Date ngayBatDau, Date ngayKetThuc);


}
