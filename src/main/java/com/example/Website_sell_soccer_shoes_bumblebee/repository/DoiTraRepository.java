package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.DoiTra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DoiTraRepository extends JpaRepository<DoiTra, UUID> {
    @Query("select dt from DoiTra dt where dt.hoaDon.maHoaDon = ?1")
    DoiTra getDoiTra(String maHoaDon);

    @Query("select dt from DoiTra dt where dt.maDoiTra = ?1")
    DoiTra getDoiTraByMa(String maDoiTra);

    @Query("select max(dt.maDoiTra) from DoiTra dt")
    String maxMaDoiTra();

    @Query("select dt from DoiTra dt where dt.trangThai = 2")
    Page<DoiTra> getListDoiTraThanhCong(Pageable pageable);

    @Query("select dt from DoiTra dt where dt.trangThai = 1")
    Page<DoiTra> getListHuyDoiTra(Pageable pageable);

    @Query("select dt from DoiTra dt where dt.trangThai = 0")
    List<DoiTra> getListChoXacNhan();

    @Query("select dt from DoiTra dt where (?1 is null or dt.hoaDon.maHoaDon = ?1) and dt.trangThai=2 ")
    Page<DoiTra> searchTraHang(String maHD,Pageable pageable);

    @Query("select dt from DoiTra dt where (?1 is null or dt.hoaDon.maHoaDon = ?1) and dt.trangThai=1 ")
    Page<DoiTra> searchHuyDoiTra(String maHD,Pageable pageable);
}
