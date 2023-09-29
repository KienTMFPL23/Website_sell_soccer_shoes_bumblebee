package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.KichCo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface KichCoRepository extends JpaRepository<KichCo, UUID> {
    @Query("select k from KichCo k where  k.loaiSize like ?1 or k.maKichCo like ?1 or ?1 is null")
    Page<KichCo> search(String keyword, Pageable pageable);


}
