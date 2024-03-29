package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDon;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.KhuyenMai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface KhuyenMaiRepository extends JpaRepository<KhuyenMai, UUID> {

    @Query(value = "select km from KhuyenMai km")
    Page<KhuyenMai> getAll(Pageable pageable);

    @Query(value = "select km from KhuyenMai km where km.id = ?1")
    KhuyenMai findId(UUID id);

    @Query(value = "select km from KhuyenMai km where km.maKhuyenMai = ?1")
    KhuyenMai findMa(String ma);

    @Query(value = "SELECT *\n" +
            "FROM KhuyenMai\n" +
            "WHERE NgayKetThuc < GETDATE()", nativeQuery = true)
    List<KhuyenMai> khuyenMaiHetHan();

    @Query("select  km from KhuyenMai  km where (?1 is null or km.donVi=?1) and (?2 is null or km.trangThai=?2) ")
    List<KhuyenMai> searchHDByDonViAndTrangThai(String donVi, Integer trangThai);

    @Query("select  km from KhuyenMai  km where (?1  is null or km.trangThai=?1) ")
    List<KhuyenMai> searchHDByTrangThai(Integer trangThai);


}
