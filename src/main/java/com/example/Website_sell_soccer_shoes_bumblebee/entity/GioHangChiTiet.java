package com.example.Website_sell_soccer_shoes_bumblebee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.UUID;

@Entity
@Table(name = "GioHangChiTiet")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GioHangChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private UUID id;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "DonGia")
    private Double donGia;

    @Column(name = "DonGiaKhiGiam")
    private Double donGiaKhiGiam;

    @ManyToOne()
    @JoinColumn(name = "IdGioHang")
    private GioHang gioHang;

    @ManyToOne()
    @JoinColumn(name = "IdChiTietSP")
    private ChiTietSanPham ctsp;
}
