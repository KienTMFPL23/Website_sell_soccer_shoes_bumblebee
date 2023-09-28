package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.HinhAnh;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface HinhAnhService {

    Page<HinhAnh> getAllPage (Pageable pageable);
    Page<HinhAnh> findByKeyWord(String keyword, Pageable pageable);
    HinhAnh save(HinhAnh hinhAnh);
    HinhAnh update (HinhAnh hinhAnh);
    HinhAnh getOne (UUID id);
}
