package com.example.Website_sell_soccer_shoes_bumblebee.dto;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChiTietSanPhamDto {

    private UUID id;
    private SanPham sanPham;
    private MauSac mauSac;
    private LoaiGiay loaiGiay;
    private KichCo kichCo;
    private ChatLieu chatLieu;
    private DeGiay deGiay;
    private Double giaBan;
    private Integer soLuong;
    private String moTaCT;
    private Integer trangThai;
    private String hinhAnh;
}
