package com.example.Website_sell_soccer_shoes_bumblebee.dto;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDetailDTO {
    private SanPham sp;
    private KichCo kc;
    private LoaiGiay tl;
    private ChatLieu cl;
    private DeGiay deGiay;
    private MauSac mauSac;
    private double gia;
    private int soLuong;
    private String mota;
    private int trangthai;
}
