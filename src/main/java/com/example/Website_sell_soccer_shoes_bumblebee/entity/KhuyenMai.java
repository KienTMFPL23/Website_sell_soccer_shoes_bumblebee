package com.example.Website_sell_soccer_shoes_bumblebee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "KhuyenMai")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class KhuyenMai {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private UUID id;

    @Column(name = "MaKhuyenMai")
    @Size(min = 4, max = 100, message = "Mã phải từ 4 đến 150 kí tự")
    @NotBlank(message = "Mã không được để trống")
    private String maKhuyenMai;

    @Column(name = "TenKhuyenMai")
    @NotBlank(message = "Tên không được để trống")
    @Size(max = 150, message = "Tên không được quá 150 kí tự")
    private String tenKhuyenMai;

    @Column(name = "GiaTri")
    @NotNull(message = "Giá trị không được để trống")
    private Integer giaTri;

    @Column(name = "DonVi")
    @NotNull(message = "Đơn vị không được để trống")
    private String donVi;

    @Column(name = "NgayTao")
    //@NotNull(message = "Ngày tạo không được để trống")
    private Date ngayTao;

    @Column(name = "TrangThai")
    @NotNull(message = "Trạng thái không được để trống")
    private Integer trangThai;

    @OneToMany(mappedBy = "khuyenMai")
    private List<ChiTietKhuyenMai> ctkm;
}
