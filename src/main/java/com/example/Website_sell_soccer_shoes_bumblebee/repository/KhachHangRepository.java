package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.DeGiay;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDon;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, UUID> {

    @Query(value = "select kh from KhachHang kh where kh.id = ?1")
    KhachHang findId(UUID id);

    @Query("select kh from  KhachHang kh where kh.soDienThoai = ?1")
    KhachHang findKHBySDT(String sdt);

    @Query(value = "select kh from KhachHang kh where kh.ma = ?1")
    KhachHang searchKhachHang(String maKhachHang);

    @Query("select max(kh.ma) from KhachHang kh")
    String searchMaxMaKhachHang();

    @Query("select kh from KhachHang  kh  order by kh.ma")
    List<KhachHang> getListKhachHang();
}
