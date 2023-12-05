package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, UUID> {
    @Query("select nv from NhanVien  nv where nv.taiKhoanNV.id = ?1")
    NhanVien findByIdTaiKhoan(UUID id);

    @Query("select nv from NhanVien  nv where nv.id = ?1")
    NhanVien getById(UUID id);

    @Query("select nv from NhanVien  nv where nv.ma = ?1")
    NhanVien getByMa(String ma);
}
