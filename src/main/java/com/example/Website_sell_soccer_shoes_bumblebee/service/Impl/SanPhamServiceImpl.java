package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.SanPham;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.SanPhamRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Override
    public List<SanPham> getList() {
        return sanPhamRepository.findAll();
    }

    @Override
    public Page<SanPham> findAllSP(Pageable pageable) {
        return sanPhamRepository.findALlSP(pageable);
    }

    @Override
    public Page<SanPham> findByKeyword(String key, Pageable pageable) {
        return sanPhamRepository.findAllByKeWord(key,pageable);
    }

    @Override
    public SanPham addSanPham(SanPham sanPham) {
        return sanPhamRepository.save(sanPham) ;
    }

    @Override
    public SanPham udpateSanPham(SanPham sanPham) {
        return sanPhamRepository.save(sanPham);
    }

    @Override
    public SanPham getOne(UUID id) {
        return sanPhamRepository.findById(id).orElse(null);
    }

    @Override
    public SanPham getByMa(String ma) {
        return sanPhamRepository.getSanPhamByMaSP(ma);
    }

    @Override
    public List<SanPham> searchSanPham(String keyword) {
        return sanPhamRepository.searchSanPham(keyword);
    }
}
