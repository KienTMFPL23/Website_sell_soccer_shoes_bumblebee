package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.DoiTra;

import java.util.List;
import java.util.UUID;

public interface DoiTraService {

    DoiTra saveDoiTra(DoiTra doiTra);

    DoiTra getOneDoiTra(String maHoaDon);

    DoiTra getDoiTraByMa(String maDoiTra);

    String maxMaDoiTra();

    List<DoiTra> listChoXacNhan();

    List<DoiTra> listDoiTraThanhCong();

    List<DoiTra> listHuyDoiTra();

    void huyDoiTra(UUID id);
}
