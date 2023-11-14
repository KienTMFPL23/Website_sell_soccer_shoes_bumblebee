package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.LoaiGiay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface LoaiGiayRepository extends JpaRepository<LoaiGiay, UUID> {
    @Query("select lg from LoaiGiay  lg where lg.tentheloai =:tentheloai")
    LoaiGiay findbyten(@Param("tentheloai") String tentheloai);
    @Query("select l from LoaiGiay l where l.ma like ?1 or l.tentheloai like ?1 or ?1 is null")
    public Page<LoaiGiay> searchByKeyWord(String keyword, Pageable pageable);

    @Query("select l from LoaiGiay l")
    public Page<LoaiGiay> getAll(Pageable pageable);
}
