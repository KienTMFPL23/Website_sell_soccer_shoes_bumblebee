package com.example.Website_sell_soccer_shoes_bumblebee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "KichCo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class KichCo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private UUID id;

    @Column(name = "Makichco")
    @NotBlank(message = "Không để trống")
    @Size(min = 5, max = 100, message = "Mã tối đa 150 kí tự, tối thiểu 5 kí tự")
    private String maKichCo;

    @NotNull(message = "Không để trống")
    @Min(value = 5, message = "Size phải lớn hơn hoặc bằng 5")
    @Max(value = 100, message = "Size phải nhỏ hơn hoặc bằng 100")
    @Column(name = "Size")
    private Integer size;

    @NotNull(message = "Mời chọn giới tính")
    @Column(name = "Gioitinh")
    private Boolean gioiTinh;

    @NotNull(message = "Chọn trạng thái")
    @Column(name = "Trangthai")
    private Integer trangThai;


}
