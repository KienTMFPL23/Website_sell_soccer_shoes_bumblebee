package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.DeGiay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface DeGiayRepository extends JpaRepository<DeGiay, UUID> {
//    @Query("select dg from DeGiay  dg where dg.loaiDe =:loaide ")
//    DeGiay findByLoaiDe(@Param("loaide")String loaide);
    @Query(value = "select d.Id, d.Ma, d.LoaiDe, d.TrangThai from DeGiay d ", nativeQuery = true)
    Page<DeGiay> getPage(Pageable pageable);

    @Query(value = "select d from DeGiay d where d.loaiDe LIKE :keyword")
    List<DeGiay> search(@Param("keyword") String keyword);

    @Query(value = "select d from DeGiay d order by d.ma asc ")
    Page<DeGiay> sort(Pageable pageable);

    @Query(value = "select d from DeGiay d where d.ma = ?1")
    DeGiay findByMa(String ma);

    @Query(value = "select d from DeGiay d where d.loaiDe = ?1")
    DeGiay findByLoaiDe(String loaide);
}
