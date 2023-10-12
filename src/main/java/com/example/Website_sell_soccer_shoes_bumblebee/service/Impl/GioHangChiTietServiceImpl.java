package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.GioHangChiTiet;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDonChiTiet;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.GioHangChiTietRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.GioHangChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GioHangChiTietServiceImpl implements GioHangChiTietService {

    @Autowired
    private GioHangChiTietRepository repo;

    @Override
    public List<GioHangChiTiet> getListGHCT() {
        return repo.findAll();
    }

    @Override
    public List<GioHangChiTiet> listGHCTByKH(UUID id) {
        return repo.listGHCTByKH(id);
    }

    @Override
    public GioHangChiTiet findId(UUID id) {
        return repo.findId(id);
    }

    @Override
    public Double getTotalMoney(List<GioHangChiTiet> list) {
        Double sum = 0.0;
        for (GioHangChiTiet ghct : list){
            sum += ghct.getDonGia() * ghct.getSoLuong();
        }
        return sum;
    }

    @Override
    public void deleteGHCT(UUID id) {
        repo.deleteById(id);
    }

//    public List<HoaDonChiTiet> saveAll(List<GioHangChiTiet> gioHangChiTiet){
//
//        return repo.saveAll(gioHangChiTiet);
//    }

}