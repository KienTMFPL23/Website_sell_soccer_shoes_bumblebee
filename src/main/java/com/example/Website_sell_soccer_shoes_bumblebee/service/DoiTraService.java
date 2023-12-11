package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.DoiTra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface DoiTraService {

    DoiTra saveDoiTra(DoiTra doiTra);

    DoiTra getOneDoiTra(String maHoaDon);

    DoiTra getDoiTraByMa(String maDoiTra);

    String maxMaDoiTra();

    List<DoiTra> listChoXacNhan();

    Page<DoiTra> listDoiTraThanhCong(Integer page);

    Page<DoiTra> listHuyDoiTra(Integer page);

    void huyDoiTra(UUID id);
}
