package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang, UUID> {
}
