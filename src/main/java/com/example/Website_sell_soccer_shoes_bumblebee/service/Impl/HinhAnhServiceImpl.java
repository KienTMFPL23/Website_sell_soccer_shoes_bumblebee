package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChiTietSanPham;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HinhAnh;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.ChiTietSanPhamRepo;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.HinhAnhRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.HinhAnhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
@Service
public class HinhAnhServiceImpl implements HinhAnhService {

    @Autowired
    HinhAnhRepository repository;

    @Autowired
    ChiTietSanPhamRepo chiTietSanPhamRepo;

    @Override
    public List<HinhAnh> getAll() {
        return repository.findAll();
    }

    @Override
    public List<ChiTietSanPham> getAllCTSP() {
        return chiTietSanPhamRepo.findAll();
    }

    @Override
    public List<ChiTietSanPham> searchCTSP(UUID keyword) {
        return repository.searchCTSP(keyword);
    }

    @Override
    public HinhAnh findById(UUID id) {
        return repository.findId(id);
    }


}
