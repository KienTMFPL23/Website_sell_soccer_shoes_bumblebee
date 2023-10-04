package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDon;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

public interface HoaDonService {

     List<HoaDon> listHoaDonCho();

     HoaDon createHoaDon() throws ParseException;

     HoaDon saveHoaDon(HoaDon hoaDon);

     HoaDon deleteHoaDon(UUID id);

     HoaDon getOne(UUID id);
}
