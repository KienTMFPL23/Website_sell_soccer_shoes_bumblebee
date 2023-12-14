package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.DoiTra;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.DoiTraRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.DoiTraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<DoiTra> listDoiTraThanhCong(Integer page)
    {
        Pageable pageable = PageRequest.of(page,5);
        return doiTraRepo.getListDoiTraThanhCong(pageable);
    }

    @Override
    public Page<DoiTra> listHuyDoiTra(Integer page) {
        Pageable pageable = PageRequest.of(page,5);
        return doiTraRepo.getListHuyDoiTra(pageable);
    }

    @Override
    public void huyDoiTra(UUID id) {
        DoiTra doiTra = doiTraRepo.findById(id).orElse(null);
        if (doiTra != null){
            doiTraRepo.delete(doiTra);
        }
    }
}
