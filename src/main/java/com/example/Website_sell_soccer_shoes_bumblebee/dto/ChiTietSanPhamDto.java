package com.example.Website_sell_soccer_shoes_bumblebee.dto;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChiTietSanPhamDto {

    private UUID id;
    private SanPham sanPham;
    private MauSac mauSac;
    private LoaiGiay loaiGiay;
    private KichCo kichCo;
    private ChatLieu chatLieu;
    private DeGiay deGiay;
    private Double giaBan;
    private Integer soLuong;
    private String moTaCT;
    private Integer trangThai;
    private String hinhAnh;
    private Date ngayTao;

    private List<String> mauSacList;
    private List<String> kichCoList;
    private List<Double> giaBanList;
    private List<Integer> soLuongList;
    private List<Boolean> trangThaiList;
    public void addBienThe(String mauSac, String kichCo, Double giaBan, Integer soLuong, Boolean trangThai) {
        if (mauSacList == null) {
            mauSacList = new ArrayList<>();
        }
        mauSacList.add(mauSac);

        if (kichCoList == null) {
            kichCoList = new ArrayList<>();
        }
        kichCoList.add(kichCo);

        if (giaBanList == null) {
            giaBanList = new ArrayList<>();
        }
        giaBanList.add(giaBan);

        if (soLuongList == null) {
            soLuongList = new ArrayList<>();
        }
        soLuongList.add(soLuong);

        if (trangThaiList == null) {
            trangThaiList = new ArrayList<>();
        }
        trangThaiList.add(trangThai);
    }
    public void loadFromViewModel(QLSanPham vm) {
        this.setChatLieu(vm.getChatLieu());
        this.setDeGiay(vm.getDeGiay());
        this.setGiaBan(vm.getGiaBan());
        this.setKichCo(vm.getKichCo());
        this.setSanPham(vm.getSanPham());
        this.setTrangThai(vm.getTrangThai());
        this.setMoTaCT(vm.getMoTaCT());
        this.setSoLuong(vm.getSoLuong());
        this.setNgayTao(vm.getNgayTao());
        this.setLoaiGiay(vm.getLoaiGiay());
        this.setMauSac(vm.getMauSac());
    }
}
