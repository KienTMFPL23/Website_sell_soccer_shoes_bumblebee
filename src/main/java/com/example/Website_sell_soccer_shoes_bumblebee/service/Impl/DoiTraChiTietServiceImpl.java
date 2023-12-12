package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.dto.DoiTraChiTietCustom;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.DoiTraChiTiet;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.DoiTraChiTietRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.DoiTraChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DoiTraChiTietServiceImpl implements DoiTraChiTietService {

    @Autowired
    DoiTraChiTietRepository doiTraCTRepo;

    @Override
    public DoiTraChiTiet saveDoiTraCT(DoiTraChiTiet doiTraChiTiet) {
        return doiTraCTRepo.save(doiTraChiTiet);
    }

    @Override
    public List<DoiTraChiTiet> listDoiTraCTById(UUID idDoiTra) {
        return doiTraCTRepo.listDoiTraCTByID(idDoiTra);
    }

    @Override
    public void removeDoiTraCT(UUID id) {
        DoiTraChiTiet dtct = doiTraCTRepo.findById(id).orElse(null);
        if (dtct != null){
             doiTraCTRepo.delete(dtct);
        }
    }

    @Override
    public List<DoiTraChiTietCustom> listDoiTraCTCustom(UUID idHoaDon) {
        return doiTraCTRepo.listDoiTraCTCustom(idHoaDon);
    }

    @Override
    public DoiTraChiTiet getDoiTraCT(UUID idDoiTra, UUID idCTSP) {
        return doiTraCTRepo.getDoiTraCT(idDoiTra,idCTSP);
    }

    @Override
    public List<DoiTraChiTiet> listDoiTraCTByHoaDon(String maHoaDon) {
        return doiTraCTRepo.listDoiTraCTByHoaDon(maHoaDon);
    }

    @Override
    public List<DoiTraChiTiet> listDoiTraCTByIdHoaDon(UUID idHoaDon) {
        return doiTraCTRepo.listTraHang(idHoaDon);
    }

    @Override
    public List<DoiTraChiTiet> findSanPhamLoi() {
        return doiTraCTRepo.findSanPhamLoi();
    }

    @Override
    public List<DoiTraChiTiet> listSanPhamDoi(UUID idHoaDon) {
        return doiTraCTRepo.listSanPhamDoi(idHoaDon);
    }
}
