package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.dto.ChiTietSanPhamDto;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.*;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.ChiTietSanPhamRepo;
import com.example.Website_sell_soccer_shoes_bumblebee.service.ChiTietSanPhamService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {

    @Autowired
    ChiTietSanPhamRepo repo;

    @Autowired
    HttpServletRequest request;


    @Override
    public Integer getSLGioHang(UUID idKH) {
        return repo.getSLGioHang(idKH);
    }

    @Override
    public String getSoLuongByKichCo(UUID idMS, UUID idSP, String size) {
        return repo.getSoLuongByKichCo(idMS, idSP, size);
    }

    @Override
    public ChiTietSanPham findCTSPAddCart(UUID idSP, UUID idMS, UUID idKC) {
        return repo.findctspAddCart(idSP, idMS, idKC);
    }

    @Override
    public List<MauSac> getMauSacBySP(UUID idSP) {
        return repo.getMauBySanPham(idSP);
    }

    @Override
    public List<String> getKichCoSacBySP(UUID idSP) {
        return repo.getKichCoBySanPham(idSP);
    }

    @Override
    public List<Integer> getKichCoByMauSacAndSanPham(UUID idMS, UUID idSP) {
        return repo.getKichCoByMauSacAndSanPham(idMS, idSP);
    }

    @Override
    public Page<ChiTietSanPham> searchCTSPByLoaiGiayList(List<UUID> idLoaiGiayList, Pageable pageable) {
        return repo.searchCTSPByLoaiGiayList(idLoaiGiayList, pageable);
    }

    @Override
    public Page<ChiTietSanPham> getCTSPByKC(UUID idKC, Pageable pageable) {
        return repo.getCTSPBYKC(idKC, pageable);
    }

    @Override
    public Page<ChiTietSanPham> getCTSPByMS(UUID idMS, Pageable pageable) {
        return repo.getCTSPBYMS(idMS, pageable);
    }

    @Override
    public Page<ChiTietSanPham> getCTSPByGiaBan(Double minPrice, Double maxPrice, Pageable pageable) {
        return repo.getCTSPByGiaBan(minPrice, maxPrice, pageable);
    }

    @Override
    public List<ChiTietSanPham> getList() {
        return repo.findAll();
    }

    @Override
    public Page<ChiTietSanPham> getListSP(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public void addKC(ChiTietSanPham qlSanPham) {
        repo.save(qlSanPham);
    }

    @Override
    public void deleteSP(UUID id) {
        repo.deleteById(id);
    }

    @Override
    public Page<ChiTietSanPham> searchByMau(UUID idMau, Pageable pageable) {
        return repo.searchByMau(idMau, pageable);
    }

    @Override
    public Page<ChiTietSanPham> searchKichCo(UUID idKC, Pageable pageable) {
        return repo.searchByKichCo(idKC, pageable);
    }

    @Override
    public Page<ChiTietSanPham> searchDeGiay(UUID idDe, Pageable pageable) {
        return repo.searchByDeGiay(idDe, pageable);
    }

    @Override
    public ChiTietSanPham updateSoLuongTon(UUID id, int soLuong) {
        ChiTietSanPham chiTietSanPham = repo.findById(id).orElse(null);
        if (chiTietSanPham != null) {
            chiTietSanPham.setSoLuong(soLuong);
            repo.save(chiTietSanPham);
            return chiTietSanPham;
        } else {
            return null;
        }
    }

    @Override
    public ChiTietSanPham updateDelete(UUID id, int soLuong) {
        ChiTietSanPham chiTietSanPham = repo.findById(id).orElse(null);
        if (chiTietSanPham != null) {
            chiTietSanPham.setSoLuong(soLuong + chiTietSanPham.getSoLuong());
            repo.save(chiTietSanPham);
            return chiTietSanPham;
        } else {
            return null;
        }

    }

    @Override
    public Page<ChiTietSanPham> listCTSP(UUID id, Pageable pageable) {
        return repo.listCTSP(id, pageable);
    }

    @Override

    public List<ChiTietSanPham> listCTSPSuDung() {
        return repo.getListCTSPSuDung();
    }
    public List<LoaiGiay> listLG22(Integer trangThai) {
        return repo.listLG22(trangThai);
    }

    @Override
    public List<DeGiay> listDeGiay22(Integer trangThai) {
        return repo.listDeGiay22(trangThai);
    }

    @Override
    public List<MauSac> listMauSac22(Integer trangThai) {
        return repo.listMauSac22(trangThai);
    }

    @Override
    public List<ChatLieu> listChatLieu22(Integer trangThai) {
        return repo.listChatLieu22(trangThai);
    }

    @Override
    public List<KichCo> listKichCo22(Integer trangThai) {
        return repo.listKichCo22(trangThai);
    }

    @Override
    public List<LoaiGiay> search22LG(String keyword, Integer trangThai) {
        return repo.search22LG(keyword, trangThai);
    }

    @Override
    public List<DeGiay> search22DG(String keyword, Integer trangThai) {
        return repo.search22DG(keyword, trangThai);
    }

    @Override
    public List<MauSac> search22MS(String keyword, Integer trangThai) {
        return repo.search22MS(keyword, trangThai);
    }

    @Override
    public List<ChatLieu> search22CL(String keyword, Integer trangThai) {
        return repo.search22CL(keyword, trangThai);
    }

    @Override
    public List<KichCo> search22KC(Integer size, Integer trangThai) {
        return repo.search22KC(size, trangThai);

    }

    @Override
    public Page<ChiTietSanPham> searchCL(UUID idCL, Pageable pageable) {
        return repo.searchByChatLieu(idCL, pageable);
    }

    @Override
    public Page<ChiTietSanPham> searchLoaiGiay(UUID idLG, Pageable pageable) {
        return repo.searchByLG(idLG, pageable);
    }

    @Override
    public ChiTietSanPham getOne(UUID id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Page<ChiTietSanPham> searchCTSP(String keyword, Pageable pageable) {
        return repo.searchCTSP(keyword, pageable);
    }

    @Override
    public List<LoaiGiay> search2(String keyword) {
        return repo.search(keyword);
    }

    @Override
    public List<KichCo> search2KC(Integer size) {
        return repo.search2KC(size);
    }

    @Override
    public List<KichCo> getListKC() {
        return repo.listKC();
    }

    @Override
    public UUID getOneToAddModal(UUID id) {
        return repo.getOneToAddModal(id);
    }

}
