package com.example.Website_sell_soccer_shoes_bumblebee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "LoaiGiay")
public class LoaiGiay {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @NotBlank(message = "không để trống mã")
    String ma;

    @NotBlank(message = "không để trống tên")
    String tentheloai;

    @NotNull(message = "Chọn trạng thái")
    Integer trangthai;

}
