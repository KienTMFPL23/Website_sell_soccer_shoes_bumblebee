package com.example.Website_sell_soccer_shoes_bumblebee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "HoaDon")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private UUID id;

    @Column(name = "MaHD")
    private String maHoaDon;

    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "NgayThanhToan")
    private Date ngayThanhToan;

    @Column(name = "DiaChiShip")
    private String diaChiShip;

    @Column(name = "SoDienThoai")
    private String sdt;

    @Column(name = "TenNguoiNhan")
    private String tenNguoiNhan;

    @Column(name = "GhiChu")
    private String ghiChu;

    @Column(name = "TrangThai")
    private Integer trangThai;

    @ManyToOne()
    @JoinColumn(name = "IdNV")
    private NhanVien nhanVien;

    @ManyToOne()
    @JoinColumn(name = "IdKH")
    private KhachHang khachHang;
}
