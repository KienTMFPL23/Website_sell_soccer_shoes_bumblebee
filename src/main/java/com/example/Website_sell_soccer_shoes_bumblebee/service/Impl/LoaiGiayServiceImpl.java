package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.LoaiGiay;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.LoaiGiayRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.LoaiGiayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LoaiGiayServiceImpl implements LoaiGiayService {

    @Autowired
    LoaiGiayRepository repository;

    @Override
    public List<LoaiGiay> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<LoaiGiay> getAll(Pageable pageable) {
        return repository.getAll(pageable);
    }

    @Override
    public Page<LoaiGiay> filAllBYKeyWord(String keyword, Pageable pageable) {
        return repository.searchByKeyWord(keyword,pageable);
    }

    @Override
    public LoaiGiay addLG(LoaiGiay loaiGiay) {
        return repository.save(loaiGiay);
    }

    @Override
    public LoaiGiay updateLG(LoaiGiay loaiGiay) {
        return repository.save(loaiGiay);
    }

    @Override
    public void deleteLG(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public LoaiGiay getOne(UUID id) {
        return repository.findById(id).orElse(null);
    }
}
