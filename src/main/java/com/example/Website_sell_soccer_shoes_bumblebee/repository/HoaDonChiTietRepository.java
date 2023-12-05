package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.dto.ChiTietSanPhamCustom;
import com.example.Website_sell_soccer_shoes_bumblebee.dto.HoaDonChiTietCustom;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDonChiTiet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, UUID> {
    @Query("select hdct from HoaDonChiTiet  hdct where hdct.hoaDon.id = ?1")
    List<HoaDonChiTiet> getListByHoaDon(UUID id);

    @Query("select hdct from HoaDonChiTiet hdct where hdct.hoaDon.id = ?1 and hdct.chiTietSanPham.id = ?2")
    HoaDonChiTiet getSanPhamInHDCT(UUID idHoaDon, UUID idSP);

    @Query(value = "delete from HoaDonChiTiet  where HoaDonChiTiet.IdHoaDon = ?1 delete from HoaDon  where Id = ?1", nativeQuery = true)
    void deleteHDCTbyHoaDon(UUID id);

    @Query("select  hdct from HoaDonChiTiet  hdct where hdct.hoaDon.id=?1")
    List<HoaDonChiTiet> getHoaDonTheoHoaDonChiTiet(UUID idHoaDonChiTiet);

    @Query(value = "ALTER TABLE HoaDonChiTiet NOCHECK CONSTRAINT FK_HoaDonChiTiet_HoaDon DELETE FROM HoaDonChiTiet where IdHoaDon = ?1"
            , nativeQuery = true)
    void removeHDCT(UUID idHoaDon);

    @Query(value = "DELETE FROM HoaDon where Id = ?1" +
            "ALTER TABLE HoaDonChiTiet WITH CHECK CHECK CONSTRAINT FK_HoaDonChiTiet_HoaDon", nativeQuery = true)
    void removeHD(UUID idHoaDon);


    @Query("select hdct from HoaDonChiTiet  hdct where  hdct.hoaDon.maHoaDon like %?1%")
    List<HoaDonChiTiet> listHoaDonMuonDoi(String key);

    @Query(value = "select hdct from HoaDonChiTiet  hdct where hdct.chiTietSanPham.id = ?1")
    HoaDonChiTiet getHDCtByIdCTSP(UUID idCTSP);

    @Query("select hdct from HoaDonChiTiet  hdct where hdct.hoaDon.maHoaDon = ?1")
    List<HoaDonChiTiet> getListHDCTByMaHD(String maHoaDon);

    @Query("select hdct from HoaDonChiTiet  hdct where hdct.hoaDon.id = ?1 and hdct.chiTietSanPham.id = ?2")
    HoaDonChiTiet getHDCTDoiTra(UUID maHoaDon, UUID idSP);

    // Thống kê
    @Query("SELECT SUM(hdct.soLuong) FROM HoaDonChiTiet hdct")
    int tinhSoLuongSanPhamDaban();

    @Query(value =
            "\tSELECT ISNULL(SUM(HDCT.SoLuong), 0) AS SoLuongSanPhamDaBan\n" +
                    "FROM HoaDon HD\n" +
                    "JOIN HoaDonChiTiet HDCT ON HD.Id = HDCT.IdHoaDon\n" +
                    "WHERE YEAR(HD.NgayThanhToan) = YEAR(GETDATE()) \n" +
                    "    AND MONTH(HD.NgayThanhToan) = 1",
            nativeQuery = true)
    int tinhSoLuongSanPhamDaBanThang1();

    @Query(value =
            "\tSELECT ISNULL(SUM(HDCT.SoLuong), 0) AS SoLuongSanPhamDaBan\n" +
                    "FROM HoaDon HD\n" +
                    "JOIN HoaDonChiTiet HDCT ON HD.Id = HDCT.IdHoaDon\n" +
                    "WHERE YEAR(HD.NgayThanhToan) = YEAR(GETDATE()) \n" +
                    "    AND MONTH(HD.NgayThanhToan) = 2",
            nativeQuery = true)
    int tinhSoLuongSanPhamDaBanThang2();

    @Query(value =
            "\tSELECT ISNULL(SUM(HDCT.SoLuong), 0) AS SoLuongSanPhamDaBan\n" +
                    "FROM HoaDon HD\n" +
                    "JOIN HoaDonChiTiet HDCT ON HD.Id = HDCT.IdHoaDon\n" +
                    "WHERE YEAR(HD.NgayThanhToan) = YEAR(GETDATE()) \n" +
                    "    AND MONTH(HD.NgayThanhToan) = 3",
            nativeQuery = true)
    int tinhSoLuongSanPhamDaBanThang3();

    @Query(value =
            "\tSELECT ISNULL(SUM(HDCT.SoLuong), 0) AS SoLuongSanPhamDaBan\n" +
                    "FROM HoaDon HD\n" +
                    "JOIN HoaDonChiTiet HDCT ON HD.Id = HDCT.IdHoaDon\n" +
                    "WHERE YEAR(HD.NgayThanhToan) = YEAR(GETDATE()) \n" +
                    "    AND MONTH(HD.NgayThanhToan) = 4",
            nativeQuery = true)
    int tinhSoLuongSanPhamDaBanThang4();

    @Query(value =
            "\tSELECT ISNULL(SUM(HDCT.SoLuong), 0) AS SoLuongSanPhamDaBan\n" +
                    "FROM HoaDon HD\n" +
                    "JOIN HoaDonChiTiet HDCT ON HD.Id = HDCT.IdHoaDon\n" +
                    "WHERE YEAR(HD.NgayThanhToan) = YEAR(GETDATE()) \n" +
                    "    AND MONTH(HD.NgayThanhToan) = 5",
            nativeQuery = true)
    int tinhSoLuongSanPhamDaBanThang5();

    @Query(value =
            "\tSELECT ISNULL(SUM(HDCT.SoLuong), 0) AS SoLuongSanPhamDaBan\n" +
                    "FROM HoaDon HD\n" +
                    "JOIN HoaDonChiTiet HDCT ON HD.Id = HDCT.IdHoaDon\n" +
                    "WHERE YEAR(HD.NgayThanhToan) = YEAR(GETDATE()) \n" +
                    "    AND MONTH(HD.NgayThanhToan) = 6",
            nativeQuery = true)
    int tinhSoLuongSanPhamDaBanThang6();

    @Query(value =
            "\tSELECT ISNULL(SUM(HDCT.SoLuong), 0) AS SoLuongSanPhamDaBan\n" +
                    "FROM HoaDon HD\n" +
                    "JOIN HoaDonChiTiet HDCT ON HD.Id = HDCT.IdHoaDon\n" +
                    "WHERE YEAR(HD.NgayThanhToan) = YEAR(GETDATE()) \n" +
                    "    AND MONTH(HD.NgayThanhToan) = 7",
            nativeQuery = true)
    int tinhSoLuongSanPhamDaBanThang7();

    @Query(value =
            "\tSELECT ISNULL(SUM(HDCT.SoLuong), 0) AS SoLuongSanPhamDaBan\n" +
                    "FROM HoaDon HD\n" +
                    "JOIN HoaDonChiTiet HDCT ON HD.Id = HDCT.IdHoaDon\n" +
                    "WHERE YEAR(HD.NgayThanhToan) = YEAR(GETDATE()) \n" +
                    "    AND MONTH(HD.NgayThanhToan) = 8",
            nativeQuery = true)
    int tinhSoLuongSanPhamDaBanThang8();

    @Query(value =
            "\tSELECT ISNULL(SUM(HDCT.SoLuong), 0) AS SoLuongSanPhamDaBan\n" +
                    "FROM HoaDon HD\n" +
                    "JOIN HoaDonChiTiet HDCT ON HD.Id = HDCT.IdHoaDon\n" +
                    "WHERE YEAR(HD.NgayThanhToan) = YEAR(GETDATE()) \n" +
                    "    AND MONTH(HD.NgayThanhToan) = 9",
            nativeQuery = true)
    int tinhSoLuongSanPhamDaBanThang9();

    @Query(value =
            "\tSELECT ISNULL(SUM(HDCT.SoLuong), 0) AS SoLuongSanPhamDaBan\n" +
                    "FROM HoaDon HD\n" +
                    "JOIN HoaDonChiTiet HDCT ON HD.Id = HDCT.IdHoaDon\n" +
                    "WHERE YEAR(HD.NgayThanhToan) = YEAR(GETDATE()) \n" +
                    "    AND MONTH(HD.NgayThanhToan) = 10",
            nativeQuery = true)
    int tinhSoLuongSanPhamDaBanThang10();

    @Query(value =
            "\tSELECT ISNULL(SUM(HDCT.SoLuong), 0) AS SoLuongSanPhamDaBan\n" +
                    "FROM HoaDon HD\n" +
                    "JOIN HoaDonChiTiet HDCT ON HD.Id = HDCT.IdHoaDon\n" +
                    "WHERE YEAR(HD.NgayThanhToan) = YEAR(GETDATE()) \n" +
                    "    AND MONTH(HD.NgayThanhToan) = 11",
            nativeQuery = true)
    int tinhSoLuongSanPhamDaBanThang11();

    @Query(value =
            "\tSELECT ISNULL(SUM(HDCT.SoLuong), 0) AS SoLuongSanPhamDaBan\n" +
                    "FROM HoaDon HD\n" +
                    "JOIN HoaDonChiTiet HDCT ON HD.Id = HDCT.IdHoaDon\n" +
                    "WHERE YEAR(HD.NgayThanhToan) = YEAR(GETDATE()) \n" +
                    "    AND MONTH(HD.NgayThanhToan) = 12",
            nativeQuery = true)
    int tinhSoLuongSanPhamDaBanThang12();

    @Query(value = "\tSELECT\n" +
            "    COUNT(*) AS SoLuongSanPhamDaBan\n" +
            "FROM\n" +
            "    HoaDon hd\n" +
            "JOIN\n" +
            "    HoaDonChiTiet hdct ON hd.Id = hdct.IdHoaDon\n" +
            "WHERE\n" +
            "    hd.TrangThai = 1\n" +
            "    AND hd.NgayThanhToan BETWEEN :fromDate AND :toDate;", nativeQuery = true)
    int tinhSoSanPhamDaBanTheoKhoangNgay(LocalDateTime fromDate, LocalDateTime toDate);

    @Query(value = "select  sp.TenSanPham,hdtc.SoLuong,hdtc.DonGia, ms.TenMau, kc.Size,hdtc.TrangThai\n" +
            "            from HoaDonChiTiet hdtc join HoaDon hd on hdtc.IdHoaDon = hd.Id\n" +
            "            join ChiTietSanPham ctsp on hdtc.IdChiTietSP = ctsp.Id\n" +
            "            join SanPham sp on ctsp.IdSP = sp.Id\n" +
            "            join MauSac ms on ctsp.IdMauSac = ms.Id\n" +
            "            join KichCo kc on ctsp.IdKichCo= kc.Id\n" +
            "            where hdtc.IdHoaDon = ?1",nativeQuery = true)
    List<HoaDonChiTietCustom> listHoaDonCTCustom(UUID idHoaDon);

    @Query("delete from HoaDonChiTiet hdct where hdct.hoaDon.id = ?1")
    void deletHoaDonCTById(UUID idHoaDon);
}
