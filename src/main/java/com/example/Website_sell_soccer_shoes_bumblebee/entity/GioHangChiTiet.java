package com.example.Website_sell_soccer_shoes_bumblebee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GioHangChiTiet")
public class GioHangChiTiet {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @ManyToOne()
    @JoinColumn(name = "IdChiTietSP")
    ChiTietSanPham ctsp;

    @ManyToOne()
    @JoinColumn(name = "IdGioHang")
    GioHang gioHang;

    @Column(name = "SoLuong")
    int soLuong;

    @Column(name = "DonGia")
    Double donGia;

    @Column(name = "DonGiaKhiGiam")
    Double donGiaKhiGiam;

}
