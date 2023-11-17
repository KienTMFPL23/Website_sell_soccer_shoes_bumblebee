package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.dto.ChiTietSanPhamDto;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ChiTietSanPhamService {

    Integer getSLGioHang(UUID idKH);

    String getSoLuongByKichCo(UUID idMS, UUID idSP, String size);

    ChiTietSanPham findCTSPAddCart(UUID idSP, UUID idMS, UUID idKC);

    List<MauSac> getMauSacBySP(UUID idSP);

    List<String> getKichCoSacBySP(UUID idSP);

    List<Integer> getKichCoByMauSacAndSanPham(UUID idMS, UUID idSP);

    Page<ChiTietSanPham> searchCTSPByLoaiGiayList(List<UUID> idLoaiGiayList, Pageable pageable);

    Page<ChiTietSanPham> getCTSPByKC(UUID idKC, Pageable pageable);

    Page<ChiTietSanPham> getCTSPByMS(UUID idMS,Pageable pageable);

    Page<ChiTietSanPham> getCTSPByGiaBan(Double minPrice, Double maxPrice, Pageable pageable);

    List<ChiTietSanPham> getList();

    Page<ChiTietSanPham> getListSP(Pageable pageable);

    void addKC(ChiTietSanPham qlSanPham);

    void deleteSP(UUID id);

    //seacrchByMau
    Page<ChiTietSanPham> searchByMau(UUID idMau, Pageable pageable);

    Page<ChiTietSanPham> searchKichCo(UUID idKC, Pageable pageable);

    Page<ChiTietSanPham> searchDeGiay(UUID idDe, Pageable pageable);

    Page<ChiTietSanPham> searchCL(UUID idCL, Pageable pageable);

    Page<ChiTietSanPham> searchLoaiGiay(UUID idLG, Pageable pageable);


    ChiTietSanPham getOne(UUID id);

    Page<ChiTietSanPham> searchCTSP(String keyword, Pageable pageable);

    List<LoaiGiay> search2(String keyword);

    List<KichCo> search2KC(Integer size);

    List<KichCo> getListKC();
// v3 updatectsp modal
    UUID getOneToAddModal(UUID id);

    ChiTietSanPham updateSoLuongTon(UUID id,int soLuong);

    ChiTietSanPham updateDelete(UUID id,int soLuong);


    Page<ChiTietSanPham> listCTSP(UUID id, Pageable pageable);


    List<ChiTietSanPham> listCTSPSuDung();

    //13.11.2023
    List<LoaiGiay> listLG22(Integer trangThai);
    List<DeGiay> listDeGiay22(Integer trangThai);
    List<MauSac> listMauSac22(Integer trangThai);
    List<ChatLieu> listChatLieu22(Integer trangThai);
    List<KichCo> listKichCo22(Integer trangThai);
    List<LoaiGiay> search22LG(String keyword, Integer trangThai);
    List<DeGiay> search22DG(String keyword, Integer trangThai);
    List<MauSac> search22MS(String keyword, Integer trangThai);
    List<ChatLieu> search22CL(String keyword, Integer trangThai);
    List<KichCo> search22KC(Integer size, Integer trangThai);

}
