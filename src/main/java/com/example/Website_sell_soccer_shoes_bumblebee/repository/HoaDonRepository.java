package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChatLieu;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChiTietSanPham;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDon;
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
public interface HoaDonRepository extends JpaRepository<HoaDon, UUID> {
    @Query("select hd from HoaDon  hd where hd.trangThai = 0 order by hd.ngayTao")
    List<HoaDon> getListByTrangThai();

    @Query(value = "SELECT hd FROM HoaDon hd where hd.maHoaDon like %?1% or hd.sdt like %?1%  or hd.tenNguoiNhan like %?1% ") //or hd.nhanVien.ten like %?1%
    Page<HoaDon> search(String keyword, Pageable pageable);


    @Query("select hd from HoaDon hd where (?1 IS NULL OR hd.ngayTao >= ?1) AND (?2 IS NULL OR hd.ngayTao <= ?2)")
    Page<HoaDon> searchALlBetweenDates(Date fromDate,Date toDate, Pageable pageable);

    @Query("select hd from HoaDon hd where hd.id =?1")
    List<HoaDon> findId(UUID id);


    @Query(value = "select hd from HoaDon hd where hd.maHoaDon = ?1")
    HoaDon searchHoaDon(String hoaDon);

    @Query("SELECT hd FROM HoaDon hd WHERE (?1 IS NULL OR hd.trangThai = ?1)")
    Page<HoaDon> searchByStatusBills(int status, Pageable pageable);

    @Query("SELECT hd.trangThai FROM HoaDon hd")
    HoaDon listHoaDonByTrangThai();
}
