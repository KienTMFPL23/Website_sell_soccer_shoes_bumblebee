package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Query(value = "select lg from LoaiGiay lg")
    List<LoaiGiay> listLoaiGiay();

    @Query(value = "select de from DeGiay de where de.loaiDe LIKE :keyword ")
    List<DeGiay> searchDG(@Param("keyword") String keyword);

    @Query(value = "select lg from DeGiay lg")
    List<DeGiay> listDeGiay();

    @Query(value = "select m from MauSac m where m.ten LIKE :keyword ")
    List<MauSac> searchMS(@Param("keyword") String keyword);

    @Query(value = "select lg from MauSac lg")
    List<MauSac> listMauSac();

    @Query(value = "select c from ChatLieu c where c.ten LIKE :keyword ")
    List<ChatLieu> searchCL(@Param("keyword") String keyword);

    @Query(value = "select lg from ChatLieu lg")
    List<ChatLieu> lítChatLieu();

    @Query(value = "select k from KichCo k")
    List<KichCo> listKC();

    @Query(value = "select k from KichCo k where k.size =:sizeKC ")
    List<KichCo> search2KC(@Param("sizeKC") Integer size);


//    @Query("select ctsp from ChiTietSanPham  ctsp where  ctsp.sanPham.tenSanPham =? 1")
//    List<ChiTietSanPham> filterBySanPham(String tenSanPham);
//
//    @Query("select ctsp from ChiTietSanPham  ctsp where  ctsp.mauSac.ten =? 1")
//    List<ChiTietSanPham> filterByMauSac(String tenMau);

//    @Query("select ctsp from ChiTietSanPham  ctsp where  ctsp.kichCo.size =? 1")
//    List<ChiTietSanPham> filterByKichCo(String size);
//
//    @Query("select ctsp from ChiTietSanPham  ctsp where  ctsp.loaiGiay.tentheloai =? 1")
//    List<ChiTietSanPham> filterByLoaiGiay(String loaiGiay);


}
