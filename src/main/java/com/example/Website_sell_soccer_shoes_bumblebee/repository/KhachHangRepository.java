package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.DeGiay;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, UUID> {

    @Query(value = "select kh from KhachHang kh where kh.id = ?1")
    KhachHang findId(UUID id);

    @Query("select kh from  KhachHang kh where kh.soDienThoai = ?1")
    KhachHang findKHBySDT(String sdt);
}
