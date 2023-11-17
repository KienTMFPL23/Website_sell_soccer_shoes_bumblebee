package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface MauSacReponsitory  extends JpaRepository<MauSac, UUID> {
    @Query("select ms from MauSac  ms where ms .ten=:ten")
    MauSac findByTen(@Param("ten")String ten);
    @Query("SELECT ms FROM MauSac ms where ms.ten LIKE %?1% or ms.ma like %?1%")
    Page<MauSac> search(String key,Pageable pageable);
    Page<MauSac> findAll(Pageable pageable );
    @Query(value = "select ms from MauSac ms order by ms.ma asc ")
    Page<MauSac> sort(Pageable pageable);
}
