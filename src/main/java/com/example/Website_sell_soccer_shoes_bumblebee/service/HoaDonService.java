package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

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

    HoaDon updateHoaDon(UUID id, Integer trangThai, HoaDon hoaDon);

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

    Page<HoaDon> searchLoaiHoaDon(Integer key, Pageable pageable);

    //13.11.2023
    Integer countHDCho();

    Integer countHDXacNhan();

    Integer countHDGiaoDVVC();

    Integer countHDDangGiao();

    Integer countHDHoanThanh();

    Integer countHDTraHang();

    Integer countHDDaHoanTra();

    Integer countHDHuy();

    Integer countHD();

    Page<HoaDon> searchLoaiHoaDonChoXacNhan(@Param("loaiDon") Integer loaiDon, Pageable pageable);

    Page<HoaDon> searchLoaiHoaDonDaHuy(@Param("loaiDon") Integer loaiDon, Pageable pageable);

    Page<HoaDon> searchLoaiHoaDonChuanBi(@Param("loaiDon") Integer loaiDon, Pageable pageable);

    Page<HoaDon> searchLoaiHoaDonGiaoDVVC(@Param("loaiDon") Integer loaiDon, Pageable pageable);

    Page<HoaDon> searchLoaiHoaDonDangGiao(@Param("loaiDon") Integer loaiDon, Pageable pageable);

    Page<HoaDon> searchLoaiHoaDonHoanThanh(@Param("loaiDon") Integer loaiDon, Pageable pageable);

    Page<HoaDon> searchLoaiHoaDonTraHang(@Param("loaiDon") Integer loaiDon, Pageable pageable);

    Page<HoaDon> searchLoaiHoaDonDaTra(@Param("loaiDon") Integer loaiDon, Pageable pageable);

}
