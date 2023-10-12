package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChatLieu;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDon;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDonChiTiet;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.HoaDonChiTietRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.HoaDonRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;

    @Override
    public Page<HoaDon> search(String key, Pageable pageable){
        if(key != null){
            return hoaDonRepository.search(key, pageable);
        }
        return hoaDonRepository.findAll(pageable);
    }


    @Override
    public List<HoaDon> findAll() {
        return hoaDonRepository.findAll();
    }

    @Override
    public List<HoaDon> listHoaDonCho() {
        return hoaDonRepository.getListByTrangThai();
    }

    @Override
    public HoaDon createHoaDon() throws ParseException {
        HoaDon hoaDon = new HoaDon();
        Random random = new Random();
        hoaDon.setMaHoaDon("HD" + random.nextInt(999999));
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String format = sdf.format(date);
        hoaDon.setNgayTao(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(format));
        hoaDon.setTrangThai(0);
        return hoaDonRepository.save(hoaDon);
    }

    @Override
    public HoaDon saveHoaDon(HoaDon hoaDon) {
        return hoaDonRepository.save(hoaDon);
    }

    @Override
    public HoaDon deleteHoaDon(UUID id) {
        HoaDon hoaDon = hoaDonRepository.findById(id).orElse(null);
//        hoaDonChiTietRepository.deleteHDCTById(id);
        if (hoaDon != null){
            hoaDonRepository.delete(hoaDon);
        }
        return hoaDon;
    }

    @Override
    public HoaDon getOne(UUID id) {
        return hoaDonRepository.findById(id).orElse(null);
    }

    @Override
    public List<HoaDon> getId(UUID id) {
        return hoaDonRepository.findId(id);
    }
}
