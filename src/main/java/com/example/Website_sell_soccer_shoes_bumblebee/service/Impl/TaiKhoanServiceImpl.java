package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.TaiKhoan;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.TaiKhoanRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaiKhoanServiceImpl implements TaiKhoanService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Override
    public TaiKhoan findByUsernameAndPassword(String username, String password) {
        return taiKhoanRepository.findUsernameAndPassword(username, password);
    }

    @Override
    public TaiKhoan dangKy(TaiKhoan taiKhoan) {
        return taiKhoanRepository.save(taiKhoan);
    }

    @Override
    public TaiKhoan findId(UUID id) {
        return taiKhoanRepository.findById(id).orElse(null);
    }

    @Override
    public TaiKhoan save(TaiKhoan taiKhoan) {
        return taiKhoanRepository.save(taiKhoan);
    }
}
