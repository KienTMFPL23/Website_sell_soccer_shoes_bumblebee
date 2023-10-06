package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.LoaiGiay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface LoaiGiayService {

    List<LoaiGiay> findAll();
    Page<LoaiGiay> getAll(Pageable pageable);
    Page<LoaiGiay> filAllBYKeyWord(String keyword, Pageable pageable);
    LoaiGiay addLG(LoaiGiay loaiGiay);
    LoaiGiay updateLG(LoaiGiay loaiGiay);
    void deleteLG(UUID id);
    LoaiGiay getOne(UUID id);
}
