package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChatLieu;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

public interface HoaDonService {
     Page<HoaDon> search(String key, Pageable pageable);


     Page<HoaDon> getALlBetweenDates(Date startDate, Date endDate, Pageable pageable);


     List<HoaDon> findAll();

     List<HoaDon> listHoaDonCho();

     HoaDon createHoaDon() throws ParseException;

     HoaDon saveHoaDon(HoaDon hoaDon);

     HoaDon deleteHoaDon(UUID id);

     HoaDon getOne(UUID id);
}
