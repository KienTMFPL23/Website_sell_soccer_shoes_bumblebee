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

    @Column(name = "DuongDan1")
    String duongdan1;

    @Column(name = "DuongDan2")
    String duongdan2;

    @Column(name = "DuongDan3")
    String duongdan3;

    @Column(name = "DuongDan4")
    String duongdan4;

    @Column(name = "DuongDan5")
    String duongdan5;

    @Column(name = "Trangthai")
    int trangthai;
}
