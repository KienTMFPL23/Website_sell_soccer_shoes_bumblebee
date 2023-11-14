package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChatLieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChatLieuRepository extends JpaRepository<ChatLieu, UUID> {
    @Query("select cl from ChatLieu  cl where cl.ten =:ten")
    ChatLieu findByTen (@Param("ten") String ten);
    @Query("SELECT cl FROM ChatLieu cl where cl.ten LIKE %?1% or cl.ma like %?1%")
    Page<ChatLieu> search(String key, Pageable pageable);

    Page<ChatLieu> findAll(Pageable pageable );

    @Query("select cl from ChatLieu cl where  cl.ma =?1")

    ChatLieu getSanPhamByMaSP(String maCl);

    //12.11.2023

}
