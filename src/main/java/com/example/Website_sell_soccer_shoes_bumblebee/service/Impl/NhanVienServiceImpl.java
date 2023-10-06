package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.NhanVien;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.NhanVienRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    NhanVienRepository nhanVienRepository;

    @Override
    public NhanVien getOne(UUID id) {
        return nhanVienRepository.findByIdTaiKhoan(id) ;
    }
}
