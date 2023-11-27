package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.DoiTraChiTiet;

import java.util.List;
import java.util.UUID;

public interface DoiTraChiTietService {

    DoiTraChiTiet saveDoiTraCT(DoiTraChiTiet doiTraChiTiet);

    List<DoiTraChiTiet> listDoiTraCTById(UUID idDoiTra);

    void removeDoiTraCT(UUID id);
}
