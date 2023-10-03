package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChatLieu;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.DeGiay;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.ChatLieuRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.ChatLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChatLieuServiceImpl implements ChatLieuService {
    @Autowired
    ChatLieuRepository clRepo;
    public Page<ChatLieu> search(String key, Pageable pageable){
        if(key != null){
            return clRepo.search(key, pageable);
        }
        return clRepo.findAll(pageable);
    }

    public ChatLieu getOne(String maCL) {
        return clRepo.getSanPhamByMaSP(maCL);
    }

    @Override
    public ChatLieu findById(UUID id) {
        return clRepo.findById(id).orElse(null);
    }
}
