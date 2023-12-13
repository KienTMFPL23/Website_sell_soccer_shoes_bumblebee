package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChiTietKhuyenMai;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.KhuyenMai;
import com.example.Website_sell_soccer_shoes_bumblebee.service.ChiTietKhuyenMaiService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface ChiTietKhuyenMaiRepository extends JpaRepository<ChiTietKhuyenMai, UUID> {

    @Query(value = "select ctkm from ChiTietKhuyenMai ctkm")
    Page<ChiTietKhuyenMai> getAll(Pageable pageable);

    @Query(value = "select ctkm from ChiTietKhuyenMai ctkm where ctkm.ctsp.id = ?1")
    ChiTietKhuyenMai findIdCTSP(UUID idCTSP);

    @Query(value = "select ctkm from ChiTietKhuyenMai ctkm where ctkm.khuyenMai.id = ?1")
    List<ChiTietKhuyenMai> findIdKhuyenMai(UUID idKM);

    @Query(value = "select ctkm from ChiTietKhuyenMai ctkm where ctkm.id = ?1")
    ChiTietKhuyenMai findId(UUID id);

    @Query(value = "select ctkm from ChiTietKhuyenMai ctkm where ctkm.ctsp.id = ?1 and ctkm.khuyenMai.id = ?2 and ctkm.khuyenMai.trangThai = 1")
    ChiTietKhuyenMai findCtkmByIdKmAndCtsp(UUID idCTSP, UUID idKM);

    @Query(value = "update ChiTietKhuyenMai set TrangThai = 1 where NgayKetThuc < getdate()", nativeQuery = true)
    List<ChiTietKhuyenMai> updateTrangThaiByNgayKetThuc();

    @Query("select ctkm from ChiTietKhuyenMai ctkm where ctkm.khuyenMai.trangThai = 1")
    List<ChiTietKhuyenMai> ctkmByTrangThai1();

    @Query("select ctkm from ChiTietKhuyenMai ctkm where ctkm.trangThai = 0")
    List<ChiTietKhuyenMai> ctkmByTrangThai0();




}
