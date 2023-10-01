package com.example.Website_sell_soccer_shoes_bumblebee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "ChiTietSanPham")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChiTietSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    UUID id;

    @ManyToOne()
    @JoinColumn(name = "Idsp")
    SanPham sanPham;


    @ManyToOne()
    @JoinColumn(name = "Idmausac")
    @NotNull(message = "Mời chọn màu sắc")
    MauSac mauSac;

    @ManyToOne()
    @JoinColumn(name = "Idloaigiay")
    @NotNull(message = "Mời chọn loại giầy")
    LoaiGiay loaiGiay;

    @ManyToOne()
    @JoinColumn(name = "Idkichco")
    @NotNull(message = "Mời chọn kích cỡ")
    KichCo kichCo;

    @ManyToOne()
    @JoinColumn(name = "Idchatlieu")
    @NotNull(message = "Mời chọn chất liệu")
    ChatLieu chatLieu;

    @ManyToOne()
    @JoinColumn(name = "Iddegiay")
    @NotNull(message = "Mời chọn đế giầy")
    DeGiay deGiay;

    @ManyToOne()
    @JoinColumn(name = "Idhinhanh")
//    @NotNull(message = "Mời chọn hình ảnh")
    HinhAnh hinhAnh;

    @Column(name = "Giagoc")
    @NotNull(message = "không để trống")
    Double giaGoc;

    @Column(name = "Giaban")
    @NotNull(message = "không để trống")
    Double giaBan;

    @Column(name = "Soluong")
    @NotNull(message = "không để trống")
    Integer soLuong;


    @Column(name = "MoTaCT")
    @NotBlank(message = "không để trống")
    String moTaCT;
    @Column(name = "DangGiay")
    @NotBlank(message = "không để trống")
    String dangGiay;


    @Column(name = "Trangthai")
    @NotNull(message = "không để trống")
    Integer trangThai;

    public void loadFromViewModel(QLSanPham vm) {
        this.setHinhAnh(vm.getHinhAnh());
        this.setChatLieu(vm.getChatLieu());
        this.setDeGiay(vm.getDeGiay());
        this.setGiaGoc(vm.getGiaGoc());
        this.setGiaBan(vm.getGiaBan());
        this.setKichCo(vm.getKichCo());
        this.setDangGiay(vm.getDangGiay());
        this.setSanPham(vm.getSanPham());
        this.setTrangThai(vm.getTrangThai());
        this.setMoTaCT(vm.getMoTaCT());
        this.setSoLuong(vm.getSoLuong());
        this.setLoaiGiay(vm.getLoaiGiay());
        this.setMauSac(vm.getMauSac());
    }

//    public ChiTietSanPham(SanPham sanPham, ChatLieu chatLieu, DeGiay deGiay, LoaiGiay loaiGiay, Double donGia, MultipartFile hinhAnh, KichCo kichCo, MauSac mauSac, String moTaCT, Double donGia1) {
//    }


}
