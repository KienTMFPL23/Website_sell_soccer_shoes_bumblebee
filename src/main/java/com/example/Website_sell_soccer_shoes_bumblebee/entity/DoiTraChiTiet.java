package com.example.Website_sell_soccer_shoes_bumblebee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "DoiTraChiTiet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoiTraChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private UUID id;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "DonGia")
    private Double donGia;

    @Column(name = "LyDoDoiTra")
    private String lyDoDoiTra;

    @Column(name = "TrangThai")
    private Integer trangThai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdChiTietSP")
    ChiTietSanPham chiTietSanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdDoiTra")
    DoiTra doiTra;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdHDCT")
    HoaDonChiTiet hoaDonChiTiet;
}
