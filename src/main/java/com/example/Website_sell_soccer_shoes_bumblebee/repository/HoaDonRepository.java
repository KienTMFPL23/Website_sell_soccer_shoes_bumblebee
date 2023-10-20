package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChatLieu;
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

    @Query("select hd from HoaDon hd where hd.id =?1")
    HoaDon hoaDonFindId(UUID id);

    @Query(value = "select hd from HoaDon hd where hd.maHoaDon = ?1")
    HoaDon searchHoaDon(String hoaDon);
// đơn hàng
    @Query("select hd from HoaDon hd where  hd.trangThai=5 ")
    Page<HoaDon> donHangDaHuy(Pageable pageable);

    @Query("select hd from HoaDon hd where  hd.trangThai=2 ")
    Page<HoaDon> donHangDangChuanBi(Pageable pageable);

    @Query("select hd from HoaDon hd where  hd.trangThai=6 ")
    Page<HoaDon> donHangTra(Pageable pageable);

    @Query("select hd from HoaDon hd where  hd.trangThai=7 ")
    Page<HoaDon> donHangDaTra(Pageable pageable);

    @Query("select hd from HoaDon hd where  hd.trangThai=1 ")
    Page<HoaDon> donHangChoXacNhan(Pageable pageable);

    @Query("select hd from HoaDon hd where  hd.trangThai=3 ")
    Page<HoaDon> donHangDangGiao(Pageable pageable);

    @Query("select hd from HoaDon hd where  hd.trangThai=4 ")
    Page<HoaDon> donHangHoanThanh(Pageable pageable);


    @Query(value = "select hd from HoaDon hd where hd.loaiHoaDon = 1 and hd.khachHang.id = ?1 order by hd.ngayTao DESC")
    List<HoaDon> listHoaDonMua(UUID idKH);


    @Query(value = "select hd from HoaDon hd where hd.loaiHoaDon = 1 and hd.khachHang.id = ?1 and hd.trangThai = 1 order by hd.ngayTao DESC ")
    List<HoaDon> listHoaDonChoThanhToan(UUID idKH);

    @Query(value = "select hd from HoaDon hd where hd.loaiHoaDon = 1 and hd.khachHang.id = ?1 and hd.trangThai = 2 order by hd.ngayTao DESC")
    List<HoaDon> listHoaDonDangChuanBi(UUID idKH);

    @Query(value = "select hd from HoaDon hd where hd.loaiHoaDon = 1 and hd.khachHang.id = ?1 and hd.trangThai = 3 order by hd.ngayTao DESC")
    List<HoaDon> listHoaDonDangGiao(UUID idKH);

    @Query(value = "select hd from HoaDon hd where hd.loaiHoaDon = 1 and hd.khachHang.id = ?1 and hd.trangThai = 4 order by hd.ngayTao DESC")
    List<HoaDon> listHoaDonHoanThanh(UUID idKH);

    @Query(value = "select hd from HoaDon hd where hd.loaiHoaDon = 1 and hd.khachHang.id = ?1 and hd.trangThai = 5 order by hd.ngayTao DESC")
    List<HoaDon> listHoaDonDaHuy(UUID idKH);

    @Query(value = "select hd from HoaDon hd where hd.loaiHoaDon = 1 and hd.khachHang.id = ?1 and hd.trangThai = 6 order by hd.ngayTao DESC")
    List<HoaDon> listHoaDonTraHang(UUID idKH);

    @Query(value = "select hd from HoaDon hd where hd.loaiHoaDon = 1 and hd.khachHang.id = ?1 and hd.trangThai = 7 order by hd.ngayTao DESC")
    List<HoaDon> listHoaDonDaHoanTra(UUID idKH);


}
