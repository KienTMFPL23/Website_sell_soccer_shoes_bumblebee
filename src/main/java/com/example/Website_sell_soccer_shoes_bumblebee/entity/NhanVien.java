package com.example.Website_sell_soccer_shoes_bumblebee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


import java.sql.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "NhanVien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private UUID id;

    @Column(name = "Ma")
    @Size(min = 4, max = 10, message = "Mã phải từ 4 đến 150 kí tự")
    @NotBlank(message = "Mã không được để trống")
    private String ma;

    @Column(name = "Ho")
    @NotBlank(message = "Họ không được để trống")
    @Size(max = 15, message = "Họ không được quá 150 kí tự")
    private String ho;

    @Column(name = "TenDem")
    @NotBlank(message = "Tên đệm không được để trống")
    @Size(max = 15, message = "Tên đệm không được quá 150 kí tự")
    private String tenDem;

    @Column(name = "Ten")
    @NotBlank(message = "Tên không được để trống")
    @Size(max = 15, message = "Tên không được quá 150 kí tự")
    private String ten;

    @Column(name = "GioiTinh")
    @NotNull(message = "Giới tính không được để trống")
    private Boolean gioiTinh;

    @Column(name = "NgaySinh")
    @NotNull(message = "Ngày sinh không được để trống")
    private Date ngaySinh;

    @Column(name = "DiaChi")
    @NotBlank(message = "Địa chỉ không được để trống")
    private String diaChi;

    @Column(name = "SoDienThoai")
    @NotBlank(message = "Số điện thoại không được để trống")
    private String soDienThoai;

    @Column(name = "Email")
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @Column(name = "TrangThai")
    @NotNull(message = "Trạng thái không được để trống")
    private Integer trangThai;

    @OneToOne()
    @JoinColumn(name = "IdTaiKhoan")
    private TaiKhoan taiKhoanNV;
}
