package com.example.Website_sell_soccer_shoes_bumblebee.entity;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChiTietSanPham;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.DeGiay;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.KichCo;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.SanPham;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Idsp")
    SanPham sanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Idmausac")
    @NotNull(message = "* Mời chọn màu sắc")
    MauSac mauSac;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Idtheloai")
    @NotNull(message = "* Mời chọn loại giày")
    LoaiGiay loaiGiay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Idkichco")
    @NotNull(message = "* Mời chọn kích cỡ")
    KichCo kichCo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Idchatlieu")
    @NotNull(message = "* Mời chọn chất liệu")
    ChatLieu chatLieu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Iddegiay")
    @NotNull(message = "* Mời chọn đế giày")
    DeGiay deGiay;

    @Column(name = "Giaban")
    @DecimalMin(value = "0.00", inclusive = false, message = "* Giá bán không hợp lệ")
    @DecimalMax(value = "9999999999.99", inclusive = false, message = "* Giá bán không hợp lệ")
    @NotNull(message = "* không để trống giá bán !")
    Double giaBan;

    @Column(name = "Soluong")
    @NotNull(message = "* không để trống số lượng !")
    @Min(value = 0, message = "* Số lượng không hợp lệ")
    @Max(value = 999999, message = "* Số lượng không hợp lệ")
    Integer soLuong;


    @Column(name = "MoTaCT")
    @NotBlank(message = "* không để trống mô tả !")
    String moTaCT;

    @Column(name = "NgayTao")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date ngayTao;

    @Column(name = "Trangthai")
    @NotNull(message = "* Mời chọn trạng thái !")
    Integer trangThai;
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "ctsp")
    HinhAnh hinhAnhs;

    @Override
    public String toString() {
        return sanPham.getTenSanPham();
    }

    public void loadFromDomainModel(ChiTietSanPham domain) {

        this.setChatLieu(domain.getChatLieu());
        this.setDeGiay(domain.getDeGiay());
        this.setGiaBan(domain.getGiaBan());
        this.setKichCo(domain.getKichCo());
        this.setSanPham(domain.getSanPham());
        this.setTrangThai(domain.getTrangThai());
        this.setMoTaCT(domain.getMoTaCT());
        this.setNgayTao(domain.getNgayTao());
        this.setSoLuong(domain.getSoLuong());
        this.setLoaiGiay(domain.getLoaiGiay());
        this.setMauSac(domain.getMauSac());
    }

}
