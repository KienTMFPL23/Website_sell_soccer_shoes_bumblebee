package com.example.Website_sell_soccer_shoes_bumblebee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "HinhAnh")
public class HinhAnh {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @ManyToOne()
    @JoinColumn(name = "IdCTSP")
    ChiTietSanPham ctsp;
    @Column(name = "Tenanh")
    String tenanh;

    @Column(name = "Duongdananh")
    String duongdan;
    @Column(name = "Trangthai")
    int trangthai;
}
