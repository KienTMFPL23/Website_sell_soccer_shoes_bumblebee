package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.dto.DoiTraChiTietCustom;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.DoiTra;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.DoiTraChiTiet;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.DoiTraChiTietRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.DoiTraChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public DoiTraChiTiet removeDoiTraCT(UUID id) {
        DoiTraChiTiet dtct = doiTraCTRepo.findById(id).orElse(null);
        if (dtct != null){
             doiTraCTRepo.delete(dtct);
        }
        return dtct;
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
    public Page<DoiTraChiTiet> findSanPhamLoi(Integer page) {
        Pageable pageable= PageRequest.of(page,5);
        return doiTraCTRepo.findSanPhamLoi(pageable);
    }

    @Override
    public List<DoiTraChiTiet> listSanPhamDoi(UUID idHoaDon) {
        return doiTraCTRepo.listSanPhamDoi(idHoaDon);
    }

    @Override
    public DoiTraChiTiet getOneDoiTraCT(UUID idDoiTraCT) {
        DoiTraChiTiet dtct = doiTraCTRepo.findById(idDoiTraCT).orElse(null);
        if (dtct != null){
            return dtct;
        }
        return dtct;
    }

    @Override
    public void deleteDoiTraCT(UUID idDoiTra) {
        DoiTraChiTiet doiTraChiTiet = doiTraCTRepo.findById(idDoiTra).orElse(null);
        if (doiTraChiTiet != null){
            doiTraCTRepo.delete(doiTraChiTiet);
        }
    }

    @Override
    public Double getToTalDoiTra(List<DoiTraChiTiet> lstDoiTraCT) {
        Double sum = 0.0;
        for (DoiTraChiTiet doiTraChiTiet : lstDoiTraCT){
            if (doiTraChiTiet.getHoaDonChiTiet().getDonGiaKhiGiam() != 0){
                Double phanTramGiam = 100 - (doiTraChiTiet.getHoaDonChiTiet().getDonGiaKhiGiam() / doiTraChiTiet.getHoaDonChiTiet().getDonGia() * 100);
                if (phanTramGiam < 50){
                    if ((doiTraChiTiet.getLyDoDoiTra().equals("Khách không ứng ý"))){
                        sum += doiTraChiTiet.getDonGia() * doiTraChiTiet.getSoLuong() * 0.9;
                    }else {
                        sum +=  doiTraChiTiet.getDonGia() * doiTraChiTiet.getSoLuong();
                    }
                }
            }else {
                if ((doiTraChiTiet.getLyDoDoiTra().equals("Khách không ưng ý"))){
                    sum += doiTraChiTiet.getDonGia() * doiTraChiTiet.getSoLuong() * 0.9;
                }else {
                    sum +=  doiTraChiTiet.getDonGia() * doiTraChiTiet.getSoLuong();
                }
            }
        }
        return sum;
    }

    @Override
    public Double getToTalDoi(List<DoiTraChiTiet> lstDoiTraCT) {
        Double sumMoney = 0.0;
        for (DoiTraChiTiet doiTraChiTiet : lstDoiTraCT){
            sumMoney +=  doiTraChiTiet.getDonGia() * doiTraChiTiet.getSoLuong();
        }
        return sumMoney;
    }

    @Override
    public DoiTraChiTiet getSanPhamInDoiTra(UUID idDoiTra, UUID idCTSP) {
        return doiTraCTRepo.getSanPhamInDoiTra(idDoiTra,idCTSP);
    }

    @Override
    public Integer getSoLuongDTMax(UUID idHoaDonCT) {
        return doiTraCTRepo.getSoLuongDoiTraMax(idHoaDonCT);
    }

    @Override
    public Integer getSoLuongDTMaxInSP(UUID idDoiTra) {
        Integer soLuong = 0;
        List<DoiTraChiTiet> listDTCT = doiTraCTRepo.listDoiTraCTByID(idDoiTra);
        for (DoiTraChiTiet lst : listDTCT){
            soLuong += lst.getSoLuong();
        }
        return soLuong;
    }

    @Override
    public Integer getMaxSLDoi(UUID idDoiTra, UUID idHDCT) {
        Integer soLuong = 0;
        List<DoiTraChiTiet> listDTCT = doiTraCTRepo.listDoiTraCTByDoiTraAndHoaDon(idDoiTra,idHDCT);
        for (DoiTraChiTiet lst : listDTCT){
            soLuong += lst.getSoLuong();
        }
        return soLuong;
    }
}
