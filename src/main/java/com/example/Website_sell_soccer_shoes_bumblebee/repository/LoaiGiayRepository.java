package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.LoaiGiay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface LoaiGiayRepository extends JpaRepository<LoaiGiay, UUID> {
    @Query(value = "select * from LoaiGiay  lg where lg.tentheloai =:tentheloai",nativeQuery = true)
    LoaiGiay findbyten(@Param("tentheloai") String tentheloai);

    @Query(value = "select * from LoaiGiay  lg where lg.ma =:ma",nativeQuery = true)
    LoaiGiay findByMa(@Param("ma") String tentheloai);

    @Query(value = "select * from LoaiGiay l where l.ma like ?1 or l.tentheloai like ?1 or ?1 is null",nativeQuery = true)
    Page<LoaiGiay> searchByKeyWord(String keyword, Pageable pageable);

    @Query(value = "select * from LoaiGiay l",nativeQuery = true)
    Page<LoaiGiay> getAll(Pageable pageable);
}
