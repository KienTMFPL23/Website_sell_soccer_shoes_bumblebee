package com.example.Website_sell_soccer_shoes_bumblebee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ChiTietSanPham")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChiTietSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    UUID id;

    @ManyToOne()
    @JoinColumn(name = "Idsp")
    SanPham sanPham;


    @ManyToOne()
    @JoinColumn(name = "Idmausac")
    @NotNull(message = "Mời chọn màu sắc")
    MauSac mauSac;

    @ManyToOne()
    @JoinColumn(name = "Idtheloai")
    @NotNull(message = "Mời chọn loại giầy")
    LoaiGiay loaiGiay;

    @ManyToOne()
    @JoinColumn(name = "Idkichco")
    @NotNull(message = "Mời chọn kích cỡ")
    KichCo kichCo;

    @ManyToOne()
    @JoinColumn(name = "Idchatlieu")
    @NotNull(message = "Mời chọn chất liệu")
    ChatLieu chatLieu;

    @ManyToOne()
    @JoinColumn(name = "Iddegiay")
    @NotNull(message = "Mời chọn đế giầy")
    DeGiay deGiay;


    @Column(name = "GiaBan")
    @DecimalMin(value = "0.00", inclusive = false, message = "Giá bán không hợp lệ")
    @DecimalMax(value = "9999999999.99", inclusive = false, message = "Giá bán không hợp lệ")
    @NotNull(message = "không để trống giá bán !")
    Double giaBan;

    @Column(name = "Soluong")
    @Min(value = 0, message = "Số lượng không hợp lệ")
    @Max(value = 999999, message = "Số lượng không hợp lệ")
    @NotNull(message = "không để trống số lượng !")
    Integer soLuong;


    @Column(name = "MoTaCT")
    @NotBlank(message = "không để trống mô tả !")
    String moTaCT;

    @Column(name = "NgayTao")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date ngayTao;


    @Column(name = "Trangthai")
    @NotNull(message = "Mời chọn trạng thái !")
    Integer trangThai;

    @OneToOne(mappedBy = "ctsp")
    HinhAnh hinhAnhs;

    @OneToMany(mappedBy = "ctsp")
    private List<GioHangChiTiet> gioHangChiTiet;

    @OneToMany(mappedBy = "ctsp")
    private List<ChiTietKhuyenMai> ctkm;

    public void loadFromViewModel(QLSanPham vm) {
        this.setChatLieu(vm.getChatLieu());
        this.setDeGiay(vm.getDeGiay());
        this.setGiaBan(vm.getGiaBan());
        this.setKichCo(vm.getKichCo());
        this.setSanPham(vm.getSanPham());
        this.setTrangThai(vm.getTrangThai());
        this.setMoTaCT(vm.getMoTaCT());
        this.setSoLuong(vm.getSoLuong());
        this.setNgayTao(vm.getNgayTao());
        this.setLoaiGiay(vm.getLoaiGiay());
        this.setMauSac(vm.getMauSac());
    }


}
