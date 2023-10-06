package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.MauSac;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.MauSacReponsitory;
import com.example.Website_sell_soccer_shoes_bumblebee.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MauSacServiceImpl  implements MauSacService {
    @Autowired
    MauSacReponsitory msr;
    public Page<MauSac> search(String key, Pageable pageable){
        if(key != null){
            return msr.search(key,pageable);
        }
        return msr.findAll(pageable);
    }

    @Override
    public List<MauSac> getAll() {
        return msr.findAll();
    }
}
