package com.example.Website_sell_soccer_shoes_bumblebee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "DoiTra")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoiTra {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private UUID id;

    @Column(name = "MaDoiTra")
    private String maDoiTra;

    @Column(name = "NgayDoiTra")
    private Date ngayDoiTra;

    @Column(name = "TrangThai")
    private Integer trangThai;

    @OneToOne
    @JoinColumn(name = "IdHoaDon")
    HoaDon hoaDon;

    @ManyToOne()
    @JoinColumn(name = "IdNhanVien")
    private NhanVien nhanVien;

    @OneToOne
    @JoinColumn(name = "IdKhachHang")
    KhachHang khachHang;

    @OneToMany(mappedBy = "doiTra")
    private List<DoiTraChiTiet> doiTraChiTiets;

}
