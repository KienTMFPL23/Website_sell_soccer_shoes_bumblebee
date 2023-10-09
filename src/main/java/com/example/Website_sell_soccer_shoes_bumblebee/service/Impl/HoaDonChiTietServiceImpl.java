package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDonChiTiet;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.HoaDonChiTietRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService {

    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;


    @Override
    public List<HoaDonChiTiet> getHoaDonById(UUID id) {
        return hoaDonChiTietRepository.getListByHoaDon(id);
    }

    @Override
    public List<HoaDonChiTiet> getListHoaDonCTByIdHoaDon(UUID id) {
        return hoaDonChiTietRepository.getListByHoaDon(id);
    }

    @Override
    public HoaDonChiTiet getOneHoaDon(UUID id) {
        return hoaDonChiTietRepository.findById(id).orElse(null);
    }

    @Override
    public HoaDonChiTiet saveHoaDonCT(HoaDonChiTiet hoaDonChiTiet) {
        return hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

    @Override
    public HoaDonChiTiet deleteHoaDonCT(UUID id) {
        HoaDonChiTiet hdct = hoaDonChiTietRepository.findById(id).orElse(null);
        hoaDonChiTietRepository.delete(hdct);
        return hdct;
    }

    @Override
    public HoaDonChiTiet getAndUpdateSanPhamInHDCT(UUID idSP) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepository.getSanPhamInHDCT(idSP);
        if (hoaDonChiTiet != null) {
            hoaDonChiTiet.setSoLuong(hoaDonChiTiet.getSoLuong() + 1);
            hoaDonChiTietRepository.save(hoaDonChiTiet);
            return hoaDonChiTiet;
        } else {
            return null;
        }
    }

    @Override
    public HoaDonChiTiet getSanPhamInHDCT(UUID idSP) {
        return hoaDonChiTietRepository.getSanPhamInHDCT(idSP);
    }

    @Override
    public Double getTotalMoney(List<HoaDonChiTiet> list) {
        Double sum = 0.0;
        for (HoaDonChiTiet hdct : list){
            sum += hdct.getDonGia() * hdct.getSoLuong();
        }
        return sum;
    }

}
