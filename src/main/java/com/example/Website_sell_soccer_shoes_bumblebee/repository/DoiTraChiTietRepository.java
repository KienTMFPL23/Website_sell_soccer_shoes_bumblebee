package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.dto.DoiTraChiTietCustom;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.DoiTra;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.DoiTraChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
            "                        WHERE hd.id = ?1", nativeQuery = true)
    List<DoiTraChiTietCustom> listDoiTraCTCustom(UUID idHoaDon);

    @Query("select dtct from DoiTraChiTiet  dtct where dtct.doiTra.id = ?1 and  dtct.chiTietSanPham.id = ?2")
    DoiTraChiTiet getDoiTraCT(UUID idDoiTra, UUID idCTSP);

    @Query("select dtct from DoiTraChiTiet  dtct where dtct.doiTra.hoaDon.maHoaDon = ?1")
    List<DoiTraChiTiet> listDoiTraCTByHoaDon(String maHoaDon);

    @Query("select dtct from DoiTraChiTiet dtct where dtct.doiTra.id = ?1 and dtct.doiTra.trangThai = 2")
    List<DoiTraChiTiet> listTraHang(UUID idHoaDon);

    @Query(value = "select * from DoiTraChiTiet where LyDoDoiTra like N'%Sản phẩm lỗi%'", nativeQuery = true)
    Page<DoiTraChiTiet> findSanPhamLoi(Pageable pageable);

    @Query("select dtct from DoiTraChiTiet dtct where dtct.doiTra.id = ?1 and dtct.doiTra.trangThai = 1")
    List<DoiTraChiTiet> listSanPhamDoi(UUID idHoaDon);

    @Query("select dt from DoiTraChiTiet dt  where dt.lyDoDoiTra like '%Sản phẩm lỗi%' and dt.chiTietSanPham.sanPham.tenSanPham like '%1%'")
    Page<DoiTraChiTiet> searchSanPhamLoi (String tenSP, Pageable pageable);

}
