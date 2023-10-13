package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.GioHang;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface GioHangRepo extends JpaRepository<GioHang, UUID> {

    @Query(value = "select * from giohang where IdKH =?1",nativeQuery = true)
    GioHang getGioHang (UUID idKH);

    @Query("select ghct from GioHangChiTiet ghct where ghct.gioHang.id =?1")
    List<GioHangChiTiet> getGioHangChiTiet(UUID idGH);


}
