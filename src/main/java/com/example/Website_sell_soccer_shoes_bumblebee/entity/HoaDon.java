package com.example.Website_sell_soccer_shoes_bumblebee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
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

//    @NotNull(message = "Ngày tạo không được trống")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "NgayTao")
    private Date ngayTao;


    @Temporal(TemporalType.TIMESTAMP)
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

    @Column(name = "HinhThucThanhToan")
    Integer hinhthucThanhToan;

    @ManyToOne()
    @JoinColumn(name = "IdNV")
    private NhanVien nhanVien;

    @ManyToOne()
    @JoinColumn(name = "IdKH")
    private KhachHang khachHang;

    @OneToMany(mappedBy = "hoaDon")
    private List<HoaDonChiTiet> hoaDons;


}
