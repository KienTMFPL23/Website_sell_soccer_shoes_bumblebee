package com.example.Website_sell_soccer_shoes_bumblebee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "HinhAnh")
public class HinhAnh {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCTSP", referencedColumnName = "id")
    ChiTietSanPham ctsp;

//    @NotBlank(message = "* Mời chọn ảnh !")
    @Column(name = "Tenanh")
    String tenanh;

//    @NotBlank(message = "* Mời chọn ảnh !")
    @Column(name = "DuongDan1")
    String duongdan1;

//    @NotBlank(message = "* Mời chọn ảnh !")
    @Column(name = "DuongDan2")
    String duongdan2;

//    @NotBlank(message = "* Mời chọn ảnh !")
    @Column(name = "DuongDan3")
    String duongdan3;

//    @NotNull(message = "* Mời chọn trạng thái !")
    @Column(name = "Trangthai")
    int trangthai;

}
