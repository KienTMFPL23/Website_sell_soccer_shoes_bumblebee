package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, UUID> {
    @Query("select hd from HoaDon  hd where hd.trangThai = 0")
    List<HoaDon> getListByTrangThai();
}
