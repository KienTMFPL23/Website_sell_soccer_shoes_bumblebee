package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.HinhAnh;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.HinhAnhRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.HinhAnhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class HinhAnhServiceImpl implements HinhAnhService {

    @Autowired
    HinhAnhRepository repository;

    @Override
    public Page<HinhAnh> getAllPage(Pageable pageable) {
        return repository.getAllPage(pageable);
    }

    @Override
    public Page<HinhAnh> findByKeyWord(String keyword, Pageable pageable) {
        return repository.findByKeyWord(keyword,pageable);
    }

    @Override
    public HinhAnh save(HinhAnh hinhAnh) {
        return repository.save(hinhAnh);
    }

    @Override
    public HinhAnh update(HinhAnh hinhAnh) {
        return repository.save(hinhAnh);
    }

    @Override
    public HinhAnh getOne(UUID id) {
        return repository.findById(id).orElse(null);
    }
}
