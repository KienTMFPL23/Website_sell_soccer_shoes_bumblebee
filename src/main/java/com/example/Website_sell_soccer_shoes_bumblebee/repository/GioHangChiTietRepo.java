package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GioHangChiTietRepo extends JpaRepository<GioHangChiTiet, UUID> {


}
