package com.example.Website_sell_soccer_shoes_bumblebee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "GioHang")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GioHang {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private UUID id;

    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "NgayThanhToan")
    private Date ngayThanhToan;

    @Column(name = "TrangThai")
    private Integer trangThai;

    @OneToMany(mappedBy = "gioHang")
    private List<GioHangChiTiet> gioHangChiTiet;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdKH", referencedColumnName = "id")
    private KhachHang khachHang;

}
