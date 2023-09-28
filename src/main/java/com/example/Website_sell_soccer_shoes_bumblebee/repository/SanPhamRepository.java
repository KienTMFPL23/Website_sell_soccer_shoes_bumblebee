package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, UUID> {

    @Query("select  sp from SanPham  sp")
    Page<SanPham> findALlSP(Pageable pageable);

    @Query("select  p from SanPham  p where  p.maSanPham =?1")
    SanPham getSanPhamByMaSP(String masp);

    @Query("select p from SanPham p  where p.maSanPham = ?1 or p.tenSanPham = ?1")
    Page<SanPham> findAllByKeWord(String keyword,Pageable pageable);
}
