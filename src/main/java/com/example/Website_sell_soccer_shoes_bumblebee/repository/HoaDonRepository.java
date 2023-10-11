package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChatLieu;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, UUID> {
    @Query("select hd from HoaDon  hd where hd.trangThai = 0 order by hd.ngayTao")
    List<HoaDon> getListByTrangThai();

    @Query(value = "SELECT hd FROM HoaDon hd where hd.maHoaDon like %?1% or hd.sdt like %?1%")
    Page<HoaDon> search(String keyword, Pageable pageable);
// or hd.khachHang.ten = ?1

    @Query("select hd from HoaDon hd where hd.ngayTao between :startDate and :endDate")
    Page<HoaDon> getALlBetweenDates(Date startDate, Date endDate, Pageable pageable);


}
