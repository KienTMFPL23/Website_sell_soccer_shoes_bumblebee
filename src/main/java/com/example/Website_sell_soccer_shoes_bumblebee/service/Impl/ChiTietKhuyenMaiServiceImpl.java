package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChiTietKhuyenMai;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.ChiTietKhuyenMaiRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.ChiTietKhuyenMaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChiTietKhuyenMaiServiceImpl implements ChiTietKhuyenMaiService {

    @Autowired
    private ChiTietKhuyenMaiRepository repo;

    @Override
    public Page<ChiTietKhuyenMai> getAll(Pageable pageable) {
        return repo.getAll(pageable);
    }

    @Override
    public ChiTietKhuyenMai save(ChiTietKhuyenMai ctkm) {
        return repo.save(ctkm);
    }

    @Override
    public List<ChiTietKhuyenMai> findIdCTSP(UUID idCTSP) {
        return repo.findIdCTSP(idCTSP);
    }

    @Override
    public ChiTietKhuyenMai findID(UUID idCTKM) {
        return repo.findId(idCTKM);
    }

    @Override
    public ChiTietKhuyenMai findCtkmByIdKmAndCtsp(UUID idCTSP, UUID idKM) {
        return repo.findCtkmByIdKmAndCtsp(idCTSP, idKM);
    }
}
