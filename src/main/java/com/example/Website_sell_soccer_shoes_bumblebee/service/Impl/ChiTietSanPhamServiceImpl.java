package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChiTietSanPham;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.KichCo;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.LoaiGiay;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.QLSanPham;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.ChiTietSanPhamRepo;
import com.example.Website_sell_soccer_shoes_bumblebee.service.ChiTietSanPhamService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {
    @Autowired
    ChiTietSanPhamRepo repo;
    @Autowired
    HttpServletRequest request;

    @Override
    public List<ChiTietSanPham> getList() {
        return repo.findAll();
    }

    @Override
    public Page<ChiTietSanPham> getListSP(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public void addKC(ChiTietSanPham qlSanPham) {
        repo.save(qlSanPham);
    }

    @Override
    public void deleteSP(UUID id) {
        repo.deleteById(id);
    }

    @Override
    public Page<ChiTietSanPham> searchByMau(UUID idMau, Pageable pageable) {
        return repo.searchByMau(idMau, pageable);
    }

    @Override
    public Page<ChiTietSanPham> searchKichCo(UUID idKC, Pageable pageable) {
        return repo.searchByKichCo(idKC, pageable);
    }

    @Override
    public Page<ChiTietSanPham> searchDeGiay(UUID idDe, Pageable pageable) {
        return repo.searchByDeGiay(idDe, pageable);
    }

    @Override
    public ChiTietSanPham updateSoLuongTon(UUID id, int soLuong) {
        ChiTietSanPham chiTietSanPham = repo.findById(id).orElse(null);
        if (chiTietSanPham != null) {
            chiTietSanPham.setSoLuong(soLuong);
            repo.save(chiTietSanPham);
            return chiTietSanPham;
        } else {
            return null;
        }
    }

    @Override
    public Page<ChiTietSanPham> searchCL(UUID idCL, Pageable pageable) {
        return repo.searchByChatLieu(idCL, pageable);
    }

    @Override
    public Page<ChiTietSanPham> searchLoaiGiay(UUID idLG, Pageable pageable) {
        return repo.searchByLG(idLG, pageable);
    }

    @Override
    public ChiTietSanPham getOne(UUID id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Page<ChiTietSanPham> searchCTSP(String keyword, Pageable pageable) {
        return repo.searchCTSP(keyword, pageable);
    }

    @Override
    public List<LoaiGiay> search2(String keyword) {
        return repo.search(keyword);
    }

    @Override
    public List<KichCo> search2KC(Integer size) {
        return repo.search2KC(size);
    }

    @Override
    public List<KichCo> getListKC() {
        return repo.listKC();
    }

    @Override
    public UUID getOneToAddModal(UUID id) {
        return repo.getOneToAddModal(id);
    }

}
