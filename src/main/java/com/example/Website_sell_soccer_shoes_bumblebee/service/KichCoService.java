package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.KichCo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface KichCoService
{
    List<KichCo> getList();
    Page<KichCo> getListKC(Pageable pageable);
    void addKC(KichCo kichCo);
    void deleteKC(UUID id);
    KichCo getOne(UUID id);
    Page<KichCo> searchKH(String keyword, Pageable pageable);
}
