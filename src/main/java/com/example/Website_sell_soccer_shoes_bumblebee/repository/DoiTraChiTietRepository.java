package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.DoiTraChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DoiTraChiTietRepository extends JpaRepository<DoiTraChiTiet, UUID> {

    @Query("select dtct from DoiTraChiTiet  dtct where dtct.doiTra.id = ?1")
    List<DoiTraChiTiet> listDoiTraCTByID(UUID idDoiTra);

}
