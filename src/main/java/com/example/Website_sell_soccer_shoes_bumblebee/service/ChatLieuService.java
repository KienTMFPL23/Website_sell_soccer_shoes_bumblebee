package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChatLieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChatLieuService {
    Page<ChatLieu> search(String key, Pageable pageable);

    ChatLieu getOne(String maCL) ;
}
