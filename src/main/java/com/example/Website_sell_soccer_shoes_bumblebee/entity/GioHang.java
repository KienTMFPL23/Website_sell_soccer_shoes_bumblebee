package com.example.Website_sell_soccer_shoes_bumblebee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Giohang")
public class GioHang {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @Column(name = "NgayTao")
    Date ngayTao;

    @Column(name = "NgayThanhToan")
    Date ngayThanhToan;

    @ManyToOne()
    @JoinColumn(name = "IdKH")
    KhachHang khachHang;

}
