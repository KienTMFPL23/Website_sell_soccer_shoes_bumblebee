package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.DoiTra;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.DoiTraRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.DoiTraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DoiTraServiceImpl implements DoiTraService {

    @Autowired
    DoiTraRepository doiTraRepo;

    @Override
    public DoiTra saveDoiTra(DoiTra doiTra) {
        return doiTraRepo.save(doiTra);
    }

    @Override
    public DoiTra getOneDoiTra(String maHoaDon) {
        return doiTraRepo.getDoiTra(maHoaDon);
    }

    @Override
    public DoiTra getDoiTraByMa(String maDoiTra) {
        return doiTraRepo.getDoiTraByMa(maDoiTra);
    }

    @Override
    public String maxMaDoiTra() {
        return doiTraRepo.maxMaDoiTra();
    }

    @Override
    public List<DoiTra> listChoXacNhan() {
        return doiTraRepo.getListChoXacNhan();
    }

    @Override
    public List<DoiTra> listDoiTraThanhCong() {
        return doiTraRepo.getListDoiTraThanhCong();
    }

    @Override
    public List<DoiTra> listHuyDoiTra() {
        return doiTraRepo.getListHuyDoiTra();
    }
}
