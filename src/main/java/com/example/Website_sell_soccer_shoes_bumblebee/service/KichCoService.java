package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.KichCo;
import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface KichCoService
{
    List<KichCo> getList();
    Page<KichCo> getListKC(Pageable pageable);
    KichCo findBySize( Integer size);
    KichCo findByMaKichCo(String maKichCo);
    void addKC(KichCo kichCo);
    void deleteKC(UUID id);
    KichCo getOne(UUID id);
    Page<KichCo> searchKH(String keyword, Pageable pageable);
}
