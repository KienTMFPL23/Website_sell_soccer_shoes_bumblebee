package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChatLieu;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.DeGiay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ChatLieuService {
    Page<ChatLieu> search(String key, Pageable pageable);

    ChatLieu getOne(String maCL) ;

    ChatLieu findById(UUID id);
}
