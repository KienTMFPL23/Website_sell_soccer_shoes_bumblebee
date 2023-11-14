package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.KichCo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface KichCoRepository extends JpaRepository<KichCo, UUID> {
    @Query("select kc from KichCo  kc where kc.size=:size")
    KichCo findBySize(@Param("size") Integer size);
    @Query("select k from KichCo k where   k.maKichCo like ?1 or ?1 is null")
    Page<KichCo> search(String keyword, Pageable pageable);
    KichCo findByMaKichCo(String maKichCo);

}
