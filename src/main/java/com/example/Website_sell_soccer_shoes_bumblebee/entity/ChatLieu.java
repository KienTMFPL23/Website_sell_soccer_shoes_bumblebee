package com.example.Website_sell_soccer_shoes_bumblebee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Entity
@Table(name = "ChatLieu")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChatLieu {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private UUID id;

    @Column(name = "MaChatLieu")
    @NotBlank(message = "không để trống")
    @Length(min = 4,max = 50, message = "Không dưới 5 kí tự và quá 50 kí tự")
    private String ma;

    @Column(name = "TenChatLieu")
    @NotBlank(message = "không để trống")
    @Length(max = 100, message = "Không quá 100 kí tự")
    private String ten;

    @Column(name = "TrangThai")
    private Integer trangThai;

}
