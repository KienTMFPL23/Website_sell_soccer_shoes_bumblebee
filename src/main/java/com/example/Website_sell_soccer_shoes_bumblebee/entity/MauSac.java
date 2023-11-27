package com.example.Website_sell_soccer_shoes_bumblebee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "MauSac")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MauSac {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private UUID id;
    @NotBlank(message = "không để trống mã")
    @Column(name = "Ma")
    private String ma;
    @NotBlank(message = "không để trống tên")
    @Column(name = "TenMau")
    private String ten;
    @NotNull(message = "Vui lòng chọn !")
    @Column(name = "TrangThai")
    private Integer tt;
}
