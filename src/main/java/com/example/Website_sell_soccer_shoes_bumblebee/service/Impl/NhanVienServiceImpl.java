package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.NhanVien;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.NhanVienRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    NhanVienRepository nhanVienRepository;

    @Override
    public NhanVien getOne(UUID id) {
        return nhanVienRepository.findByIdTaiKhoan(id) ;
    }

    @Override
    public List<NhanVien> getAll() {
        return nhanVienRepository.findAll();
    }

    @Override
    public NhanVien save(NhanVien nhanVien) {
        return nhanVienRepository.save(nhanVien);
    }

    @Override
    public NhanVien getById(UUID id) {
        return nhanVienRepository.getById(id);
    }

    @Override
    public NhanVien getByMa(String ma) {
        return nhanVienRepository.getByMa(ma);
    }
}
