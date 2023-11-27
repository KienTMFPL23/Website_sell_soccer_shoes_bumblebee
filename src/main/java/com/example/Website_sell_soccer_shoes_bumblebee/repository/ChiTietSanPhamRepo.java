package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.dto.ChiTietSanPhamDto;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface ChiTietSanPhamRepo extends JpaRepository<ChiTietSanPham, UUID> {


    @Query(value = "SELECT ctsp FROM ChiTietSanPham ctsp WHERE  ctsp.sanPham.tenSanPham like %?1%")
    Page<ChiTietSanPham> searchCTSP(String keyword, Pageable pageable);

    @Query("SELECT ctsp FROM ChiTietSanPham ctsp WHERE ( ?1 IS NULL OR ctsp.mauSac.id = ?1)")
    Page<ChiTietSanPham> searchByMau(UUID idMau, Pageable pageable);

    @Query("SELECT ctsp FROM ChiTietSanPham ctsp WHERE ( ?1 IS NULL OR ctsp.kichCo.id = ?1)")
    Page<ChiTietSanPham> searchByKichCo(UUID idKC, Pageable pageable);

    @Query("SELECT ctsp FROM ChiTietSanPham ctsp WHERE ( ?1 IS NULL OR ctsp.deGiay.id = ?1)")
    Page<ChiTietSanPham> searchByDeGiay(UUID idDe, Pageable pageable);

    @Query("SELECT ctsp FROM ChiTietSanPham ctsp WHERE ( ?1 IS NULL OR ctsp.loaiGiay.id = ?1)")
    Page<ChiTietSanPham> searchByLG(UUID idLG, Pageable pageable);

    @Query("SELECT ctsp FROM ChiTietSanPham ctsp WHERE ( ?1 IS NULL OR ctsp.chatLieu.id = ?1)")
    Page<ChiTietSanPham> searchByChatLieu(UUID idCL, Pageable pageable);

    @Query(value = "select d from LoaiGiay d where d.tentheloai LIKE :keyword ")
    List<LoaiGiay> search(@Param("keyword") String keyword);

    @Query(value = "select d from LoaiGiay d where d.tentheloai LIKE :keyword and d.trangthai=:trangThai")
    List<LoaiGiay> search22LG(@Param("keyword") String keyword, @Param("trangThai") Integer trangThai);

    @Query(value = "select lg from LoaiGiay lg")
    List<LoaiGiay> listLoaiGiay();

    @Query(value = "select lg from LoaiGiay lg where lg.trangthai=:trangThai")
    List<LoaiGiay> listLG22(@Param("trangThai") Integer trangThai);

    @Query(value = "select de from DeGiay de where de.loaiDe LIKE :keyword ")
    List<DeGiay> searchDG(@Param("keyword") String keyword);

    @Query(value = "select de from DeGiay de where de.loaiDe LIKE :keyword and de.trangThai=:trangThai")
    List<DeGiay> search22DG(@Param("keyword") String keyword, @Param("trangThai") Integer trangThai);


    @Query(value = "select lg from DeGiay lg")
    List<DeGiay> listDeGiay();

    @Query(value = "select d from DeGiay d where d.trangThai=:trangThai")
    List<DeGiay> listDeGiay22(@Param("trangThai") Integer trangThai);

    @Query(value = "select m from MauSac m where m.ten LIKE :keyword ")
    List<MauSac> searchMS(@Param("keyword") String keyword);

    @Query(value = "select m from MauSac m where m.ten LIKE :keyword and m.tt=:trangThai")
    List<MauSac> search22MS(@Param("keyword") String keyword, @Param("trangThai") Integer trangThai);

    @Query(value = "select m from MauSac m where m.tt=:trangThai")
    List<MauSac> listMauSac22(@Param("trangThai") Integer trangThai);

    @Query(value = "select lg from MauSac lg")
    List<MauSac> listMauSac();

    @Query(value = "select c from ChatLieu c where c.ten LIKE :keyword ")
    List<ChatLieu> searchCL(@Param("keyword") String keyword);

    @Query(value = "select c from ChatLieu c where c.ten LIKE :keyword and c.trangThai=:trangThai")
    List<ChatLieu> search22CL(@Param("keyword") String keyword, @Param("trangThai") Integer trangThai);

    @Query(value = "select lg from ChatLieu lg")
    List<ChatLieu> lítChatLieu();

    @Query(value = "select c from ChatLieu c where c.trangThai=:trangThai")
    List<ChatLieu> listChatLieu22(@Param("trangThai") Integer trangThai);

    @Query(value = "select k from KichCo k")
    List<KichCo> listKC();

    @Query(value = "select k from KichCo k where k.trangThai=:trangThai")
    List<KichCo> listKichCo22(@Param("trangThai") Integer trangThai);

    @Query(value = "select k from KichCo k where (:keyword is null or k.size = :keyword)")
    List<KichCo> search2KC(@Param("keyword") Integer size);

    @Query(value = "select k from KichCo k where (:keyword is null or k.size = :keyword) and k.trangThai=:trangThai")
    List<KichCo> search22KC(@Param("keyword") Integer size,@Param("trangThai") Integer trangThai);

    @Query("select ctsp from ChiTietSanPham  ctsp where ctsp.giaBan between ?1 and ?2")
    Page<ChiTietSanPham> getCTSPByGiaBan(Double minPrice, Double maxPrice, Pageable pageable);

    @Query(value = "select * from ChiTietSanPham where Idkichco = ?1", nativeQuery = true)
    Page<ChiTietSanPham> getCTSPBYKC(UUID idKC, Pageable pageable);

    @Query("SELECT DISTINCT c FROM ChiTietSanPham c " +
            "WHERE c.id IN (SELECT MIN(c2.id) FROM ChiTietSanPham c2 " +
            "GROUP BY c2.sanPham.id, c2.mauSac.id) and c.mauSac.id = ?1")
    Page<ChiTietSanPham> getCTSPBYMS(UUID idMS, Pageable pageable);


//    List<KichCo> search2KC(@Param("keyword") Integer keyword);

    // update ctsp modal add
    @Query(value = "select c.IdSP from ChiTietSanPham c join SanPham s on c.IdSP=s.Id where c.Id=?1", nativeQuery = true)
    UUID getOneToAddModal(UUID id);


    @Query("SELECT DISTINCT c FROM ChiTietSanPham c " +
            "WHERE c.id IN (SELECT MIN(c2.id) FROM ChiTietSanPham c2 " +
            "GROUP BY c2.sanPham.id, c2.mauSac.id) and c.loaiGiay.id IN ?1")
    Page<ChiTietSanPham> searchCTSPByLoaiGiayList(List<UUID> idLoaiGiayList, Pageable pageable);


    @Query("select h from HinhAnh h where h.ctsp.id = ?1")
    HinhAnh getHADetail(UUID idCTSP);


    @Query(value = "select * from mausac ms join ChiTietSanPham ctsp on ms.Id = ctsp.IdMauSac " +
            "join SanPham sp on sp.Id = ctsp.IdSP where sp.Id = ?1", nativeQuery = true)
    List<MauSac> getMauBySanPham(UUID idSP);


    // list ctsp theo id
    @Query(value = "select ct from ChiTietSanPham ct where ct.sanPham.id=?1")
    Page<ChiTietSanPham> listCTSP(UUID id, Pageable pageable);

    @Query(value = "select kc.size from kichCo kc join ChiTietSanPham ctsp on kc.Id = ctsp.IdKichCo " +
            "join SanPham sp on sp.Id = ctsp.IdSP where sp.Id = ?1", nativeQuery = true)
    List<String> getKichCoBySanPham(UUID idSP);

    @Query("select kc from KichCo kc where kc.size = ?1")
    KichCo getKichCoBySize(int size);


    @Query(value = "select kc.size from kichCo kc join ChiTietSanPham ctsp on ctsp.IdKichCo = kc.Id\n" +
            "where ctsp.IdMauSac = ?1 \n" +
            "and ctsp.IdSP = ?2", nativeQuery = true)
    List<Integer> getKichCoByMauSacAndSanPham(UUID idMS, UUID idSP);

    @Query(value = "select ctsp.soLuong from ChiTietSanPham ctsp join KichCo kc on kc.Id = ctsp.IdKichCo\n" +
            "            where ctsp.IdMauSac = ?1\n" +
            "            and ctsp.IdSP = ?2 \n" +
            "\t\t\tand kc.size = ?3", nativeQuery = true)
    String getSoLuongByKichCo(UUID idMS, UUID idSP, String size);

    @Query(value = "SELECT ctsp.*, kc.Id AS KichCoId FROM ChiTietSanPham ctsp " +
            "JOIN KichCo kc ON kc.Id = ctsp.IdKichCo " +
            "WHERE ctsp.IdMauSac = :idMS AND ctsp.IdSP = :idSP AND kc.size = :size", nativeQuery = true)
    ChiTietSanPham getCTSPByKichCo(
            @Param("idMS") UUID idMS,
            @Param("idSP") UUID idSP,
            @Param("size") String size
    );

    @Query("SELECT DISTINCT c FROM ChiTietSanPham c " +
            "WHERE c.id IN (SELECT MIN(c2.id) FROM ChiTietSanPham c2 " +
            "GROUP BY c2.sanPham.id, c2.mauSac.id)")
    Page<ChiTietSanPham> get1CTSPByMauSac(Pageable pageable);

    @Query(value = "select * from chitietsanpham where IdSP = ?1 \n" +
            "and IdMauSac = ?2 \n" +
            "and IdKichCo = ?3", nativeQuery = true)
    ChiTietSanPham findctspAddCart(UUID idSP, UUID idMS, UUID idKC);


    @Query(value = "select count(*) from giohangchitiet join giohang on giohangchitiet.idgiohang = giohang.id\n" +
            "where giohang.IdKH = ?1", nativeQuery = true)
    Integer getSLGioHang(UUID idKH);



    @Query(value = "select ctsp from ChiTietSanPham  ctsp where  ctsp.soLuong > 0 and ctsp.trangThai = 1")
    List<ChiTietSanPham> getListCTSPSuDung();

    @Query(value = "select soluong from giohangchitiet" +
            " where IdChiTietSP = ?1 and IdGioHang = ?2",nativeQuery = true)
    Integer getSLGioHangBySPAndGH(UUID idCTSP, UUID idGH);

}
