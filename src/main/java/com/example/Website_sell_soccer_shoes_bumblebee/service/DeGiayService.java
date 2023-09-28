package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.DeGiay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface DeGiayService {

    List<DeGiay> getList();
    Page<DeGiay> getListPage(Pageable pageable);
    DeGiay add(DeGiay deGiay);
    DeGiay findById(UUID id);
    DeGiay findByMa(String ma);
    Page<DeGiay> search(String keyword, Pageable pageable);
    Page<DeGiay> sort(Pageable pageable);
}
