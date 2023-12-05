package com.example.Website_sell_soccer_shoes_bumblebee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "KhachHang")
@ToString
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private UUID id;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "Ho")
    private String ho;

    @Column(name = "TenDem")
    private String tenDem;

    @NotBlank(message = "Không để trống tên !")
    @Size(max = 50, message = "Tên không được quá 50 ký tự")
    @Column(name = "Ten")
    private String ten;

    @Column(name = "GioiTinh")
    private Boolean gioiTinh;

    @Column(name = "NgaySinh")
    private Date ngaySinh;

    @Column(name = "DiaChi")
    private String diaChi;

    @NotBlank(message = "không để trống số điện thoại !")
    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại phải bắt đầu từ 0 và có đúng 10 chữ số")
    @Column(name = "SoDienThoai")
    private String soDienThoai;

    @Column(name = "Email")
    private String email;

    @Column(name = "TrangThai")
    private Integer trangThai;

    @OneToOne()
    @JoinColumn(name = "IdTaiKhoan")
    private TaiKhoan taiKhoanKH;

    @OneToOne(mappedBy = "khachHang")
    private GioHang gioHang;

    @Override
    public String toString() {
        return ho + tenDem + ten;
    }
}
