package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.KhuyenMai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface KhuyenMaiService {

    Page<KhuyenMai> getAll(Pageable pageable);

    List<KhuyenMai> findAll();

    KhuyenMai save(KhuyenMai km);

    KhuyenMai findId(UUID id);

    KhuyenMai findMa(String ma);

    List<KhuyenMai> searchKhoangNgay(Date ngayBatDau, Date ngayKetThuc);
}
