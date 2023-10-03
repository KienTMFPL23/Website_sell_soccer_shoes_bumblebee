package com.example.Website_sell_soccer_shoes_bumblebee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "DeGiay")
public class DeGiay {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="Id")
    private UUID id;

    @Column(name="Ma")
    @Size(min = 4, max = 100, message = "Mã phải từ 4 đến 150 kí tự")
    @NotBlank(message = "Mã không được để trống")
    private String ma;

    @Column(name="LoaiDe")
    @NotBlank(message = "Loại đế không được để trống")
    @Size(max = 150, message = "Loại đế không được quá 150 kí tự")
    private String loaiDe;

    @Column(name="TrangThai")
    @NotNull(message = "Trạng thái không được để trống")
    private Integer trangThai;
}
