package com.example.Website_sell_soccer_shoes_bumblebee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "SanPham")
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private UUID id;

    @Column(name = "MaSanPham")
    @Length(min = 5,message = "Mã sản phẩm phải lớn hơn hoặc bằng 5")
    @Length(max = 100,message = "Mã sản phẩm phải nhỏ hơn hoặc bằng 100")
    @NotBlank(message = "Không được để trống")
    private String maSanPham;

    @NotBlank(message = "Không được để trống")
    @Length(max = 150,message = "Tên sản phẩm phải nhỏ hơn hoặc bằng 150")
    @Column(name = "TenSanPham")
    private String tenSanPham;

    @NotNull(message = "Vui lòng chọn trạng thái")
    @Column(name = "TrangThai")
    private Integer trangThai;
}
