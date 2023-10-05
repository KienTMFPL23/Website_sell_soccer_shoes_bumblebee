package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChiTietSanPham;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.DeGiay;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HinhAnh;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface HinhAnhRepository extends JpaRepository<HinhAnh, UUID> {

    @Query(value = "select h from HinhAnh h where h.id = ?1")
    HinhAnh findId(UUID id);

    @Query("select ctsp from ChiTietSanPham ctsp")
    List<ChiTietSanPham> getAllCSP();

    @Query(value = "select ctsp.id from ChiTietSanPham ctsp where ctsp.id LIKE :keyword", nativeQuery = true)
    List<ChiTietSanPham> searchCTSP(UUID keyword);
}


