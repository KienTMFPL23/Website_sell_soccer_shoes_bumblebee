package com.example.Website_sell_soccer_shoes_bumblebee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "khong de trong ma")
    @Column(name = "Ma")
    private String ma;
    @NotBlank(message = "khong de trong ten")
    @Column(name = "TenMau")
    private String ten;
    @Column(name = "TrangThai")
    private Integer tt;
}
