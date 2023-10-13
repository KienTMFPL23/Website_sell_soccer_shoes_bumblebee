package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChiTietSanPham;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.DeGiay;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HinhAnh;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface HinhAnhService {

    List<HinhAnh> getAll();

    List<ChiTietSanPham> getAllCTSP();

    List<ChiTietSanPham> searchCTSP(UUID keyword);

    HinhAnh findById(UUID id);

    String getImageUploadPath();

    Page<HinhAnh> listHinhAnh(Pageable pageable);

    UUID getIdHinhAnh(UUID keyword);

    HinhAnh getHinhAnh(UUID id);

    UUID getIdCTSP(UUID id);

    SanPham getSanPhamByIDCTSP(UUID idCTSP);
}
