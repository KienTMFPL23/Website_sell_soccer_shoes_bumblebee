package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MauSacService {
    Page<MauSac> search(String key, Pageable pageable);
    List<MauSac> getAll();
}
