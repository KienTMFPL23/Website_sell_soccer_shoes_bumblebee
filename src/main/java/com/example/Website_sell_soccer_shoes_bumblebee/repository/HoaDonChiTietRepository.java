package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, UUID> {
    @Query("select hdct from HoaDonChiTiet  hdct where hdct.hoaDon.id = ?1")
    List<HoaDonChiTiet> getListByHoaDon(UUID id);

    @Query(value = "delete  from HoaDonChiTiet  where HoaDonChiTiet.IdHoaDon = ?1", nativeQuery = true)
    List<HoaDonChiTiet> deleteHDCTById(UUID id);


    @Query("select hdct from HoaDonChiTiet hdct where hdct.chiTietSanPham.id = ?1")
    HoaDonChiTiet getSanPhamInHDCT(UUID idSP);


    @Query("select hdct from HoaDonChiTiet hdct where hdct.hoaDon.id = ?1 and hdct.chiTietSanPham.id = ?2")
    HoaDonChiTiet getSanPhamInHDCT(UUID idHoaDon,UUID idSP);

//    @Query(value = "select hdct from HoaDonChiTiet hdct  where HoaDonChiTiet.IdHoaDon = ?1", nativeQuery = true)
//    List<HoaDonChiTiet> findHoaDonChiTietByIdHoaDon(UUID id);
}
