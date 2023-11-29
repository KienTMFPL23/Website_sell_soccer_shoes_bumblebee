package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.dto.DoiTraChiTietCustom;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.DoiTraChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DoiTraChiTietRepository extends JpaRepository<DoiTraChiTiet, UUID> {

    @Query("select dtct from DoiTraChiTiet  dtct where dtct.doiTra.id = ?1")
    List<DoiTraChiTiet> listDoiTraCTByID(UUID idDoiTra);

    @Query(value = "select   sp.TenSanPham,dtct.SoLuong,dtct.DonGia, ms.TenMau, kc.Size,dtct.LyDoDoiTra,dtct.TrangThai\n" +
            "from DoiTraChiTiet dtct join DoiTra dt on dtct.IdDoiTra = dt.Id\n" +
            "                        join ChiTietSanPham ctsp on dtct.IdChiTietSP = ctsp.Id\n" +
            "                        join SanPham sp on ctsp.IdSP = sp.Id\n" +
            "                        join MauSac ms on ctsp.IdMauSac = ms.Id\n" +
            "                        join KichCo kc on ctsp.IdKichCo= kc.Id\n" +
            "                        join HoaDonChiTiet hdct on hdct.Id = dtct.IdHDCT\n" +
            "                        join HoaDon hd on hd.Id = hdct.IdHoaDon\n" +
            "                        WHERE hd.id = ?1",nativeQuery = true)
    List<DoiTraChiTietCustom> listDoiTraCTCustom(UUID idHoaDon);

}