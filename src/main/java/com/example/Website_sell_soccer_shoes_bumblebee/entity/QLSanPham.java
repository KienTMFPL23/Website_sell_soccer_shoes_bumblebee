package com.example.Website_sell_soccer_shoes_bumblebee.entity;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChiTietSanPham;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.DeGiay;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.KichCo;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.SanPham;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Entity
@Table(name = "ChiTietSanPham")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QLSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    UUID id;

    @ManyToOne()
    @JoinColumn(name = "Idsp")
    SanPham sanPham;

    @ManyToOne()
    @JoinColumn(name = "Idmausac")
    MauSac mauSac;

    @ManyToOne()
    @JoinColumn(name = "Idloaigiay")
    LoaiGiay loaiGiay;

    @ManyToOne()
    @JoinColumn(name = "Idkichco")
    KichCo kichCo;

    @ManyToOne()
    @JoinColumn(name = "Idchatlieu")
    ChatLieu chatLieu;

    @ManyToOne()
    @JoinColumn(name = "Iddegiay")
    DeGiay deGiay;

    @ManyToOne()
    @JoinColumn(name = "Idhinhanh")
    HinhAnh hinhAnh;

    @Column(name = "Giagoc")
//    @NotNull(message = "không để trống")
    Double giaGoc;

    @Column(name = "Giaban")
//    @NotNull(message = "không để trống")
    Double giaBan;

    @Column(name = "Soluong")
//    @NotNull(message = "không để trống")
    Integer soLuong;


    @Column(name = "MoTaCT")
//    @NotBlank(message = "không để trống")
    String moTaCT;

    @Column(name = "Trangthai")
//    @NotNull(message = "không để trống")
    Integer trangThai;
    @Column(name = "DangGiay")
//    @NotBlank(message = "không để trống")
    String dangGiay;


    @Override
    public String toString() {
        return sanPham.getTenSanPham();
    }

    public void loadFromDomainModel(ChiTietSanPham domain) {
        this.setHinhAnh(hinhAnh);
        this.setChatLieu(domain.getChatLieu());
        this.setDeGiay(domain.getDeGiay());
        this.setGiaGoc(domain.getGiaGoc());
        this.setGiaBan(domain.getGiaBan());
        this.setDangGiay(domain.getDangGiay());
        this.setKichCo(domain.getKichCo());
        this.setSanPham(domain.getSanPham());
        this.setTrangThai(domain.getTrangThai());
        this.setMoTaCT(domain.getMoTaCT());
        this.setSoLuong(domain.getSoLuong());
        this.setLoaiGiay(domain.getLoaiGiay());
        this.setMauSac(domain.getMauSac());
    }
}