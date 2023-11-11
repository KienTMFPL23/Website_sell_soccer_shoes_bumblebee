package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

public interface HoaDonService {
    Page<HoaDon> search(String key, Pageable pageable);

    HoaDon searchHoaDon(String hoaDon);

    Page<HoaDon> searchALlBetweenDates(Date fromDate, Date toDate, Pageable pageable);

    List<HoaDon> findAll();

    List<HoaDon> listHoaDonCho();

    HoaDon createHoaDon() throws ParseException;

    Page<HoaDon> searchByStatusBills(int status, Pageable pageable);

    HoaDon saveHoaDon(HoaDon hoaDon);

    HoaDon updateHoaDon(UUID id,Integer trangThai,HoaDon hoaDon);

    HoaDon deleteHoaDon(UUID id);

    HoaDon getOne(UUID id);

    List<HoaDon> getId(UUID id);

    List<HoaDon> listHoaDonMua(UUID idKH);

    List<HoaDon> listHoaDonChoThanhToan(UUID idKH);

    List<HoaDon> listHoaDonDangChuanBi(UUID idKH);

    List<HoaDon> listHoaDonDangGiao(UUID idKH);

    List<HoaDon> listHoaDonHoanThanh(UUID idKH);

    List<HoaDon> listHoaDonDaHuy(UUID idKH);

    List<HoaDon> listHoaDonTraHang(UUID idKH);

    List<HoaDon> listHoaDonDaHoanTra(UUID idKH);

    HoaDon hoaDonFindId(UUID id);

    Page<HoaDon> searchLoaiHoaDon(Integer key,Pageable pageable);


}
