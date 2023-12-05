package com.example.Website_sell_soccer_shoes_bumblebee.repository;

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

//    @Query(value = "select km from KhuyenMai km where km.ngayTao between :ngayBatDau and :ngayKetThuc")
//    List<KhuyenMai> searchKhoangNgay(@Param("ngayBatDau") Date ngayBatDau, @Param("ngayKetThuc") Date ngayKetThuc);

        @Query("select km from KhuyenMai km where (?1 is null or km.ngayTao>=?1) and (?2 is null or km.ngayTao<=?2) and (?3 is null or km.donVi like ?3) order by km.ngayTao desc ")
    List<KhuyenMai> searchKMByNgayTaoAndDonVi(Date fromDate, Date toDate, String donVi);

    @Query("select km from KhuyenMai km where km.ngayTao between :fromDate and :toDate order by km.ngayTao desc ")
    List<KhuyenMai> searchKMByNgayTao(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

    @Query("select km from KhuyenMai km where (?1 is null or km.donVi like ?1) order by km.ngayTao desc ")
    List<KhuyenMai> searchKMByDonVi(String donVi);
//    @Query("SELECT km FROM KhuyenMai km WHERE (:fromDate IS NULL OR km.ngayTao >= :fromDate) AND (:toDate IS NULL OR km.ngayTao <= :toDate) AND (:donVi IS NULL OR km.donVi = :donVi) ORDER BY km.ngayTao DESC")
//    List<KhuyenMai> searchKMByNgayTaoAndDonVi(@Param("fromDate") LocalDateTime fromDate, @Param("toDate") LocalDateTime toDate, @Param("donVi") String donVi);

    @Query(value = "select km from KhuyenMai km where km.ngayKetThuc = ?1 and km.trangThai = 0")
    List<KhuyenMai> findByNgayKetThucBeforeAndTrangThai(LocalDateTime ngayKetThuc);

}
