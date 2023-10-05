package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.HinhAnh;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface HinhAnhRepository extends JpaRepository<HinhAnh, UUID> {

    @Query("select h from HinhAnh h")
    Page<HinhAnh> getAllPage(Pageable pageable);

    @Query(value = "select h.DuongDanAnh from HinhAnh h where h.IdCTSP=?1 ", nativeQuery = true)
    List<HinhAnh> getHinhAnh(UUID idCTSP);

    @Query("select h from HinhAnh h where h.tenanh like ?1 or ?1 is null")
    Page<HinhAnh> findByKeyWord(String keyword, Pageable pageable);
}
