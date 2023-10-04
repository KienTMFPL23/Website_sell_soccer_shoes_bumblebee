package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDon;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.HoaDonRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Override
    public List<HoaDon> findAll() {
        return hoaDonRepository.findAll();
    }

    @Override
    public List<HoaDon> listHoaDonCho() {
        return hoaDonRepository.getListByTrangThai();
    }

    @Override
    public HoaDon createHoaDon() {
        HoaDon hoaDon = new HoaDon();
        Random random = new Random();
        hoaDon.setMaHoaDon("HD" + random.nextInt(999999));
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        hoaDon.setNgayTao(date);
        hoaDon.setTrangThai(0);
        return hoaDonRepository.save(hoaDon);
    }

    @Override
    public HoaDon saveHoaDon(HoaDon hoaDon) {
        return null;
    }

    @Override
    public HoaDon deleteHoaDon(UUID id) {
        HoaDon hoaDon = hoaDonRepository.findById(id).orElse(null);
        if (hoaDon != null){
            hoaDonRepository.delete(hoaDon);
        }
        return hoaDon;
    }
}
