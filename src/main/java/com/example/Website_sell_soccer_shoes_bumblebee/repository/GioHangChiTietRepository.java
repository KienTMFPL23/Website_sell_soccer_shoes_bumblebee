package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChiTietSanPham;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.DeGiay;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, UUID> {

    @Query("select ghct from GioHangChiTiet ghct where ghct.ctsp.id = ?1 and ghct.gioHang.khachHang.id = ?2")
    GioHangChiTiet findId(UUID id, UUID idKH);

    @Query("select ghct from GioHangChiTiet ghct where ghct.gioHang.khachHang.id =?1")
    List<GioHangChiTiet> listGHCTByKH(UUID id);
}
