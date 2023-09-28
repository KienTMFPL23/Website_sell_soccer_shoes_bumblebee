package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.DeGiay;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.DeGiayRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.DeGiayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class DeGiayServiceImpl implements DeGiayService {

    @Autowired
    private DeGiayRepository deGiayRepository;

    @Override
    public List<DeGiay> getList() {
        return deGiayRepository.findAll();
    }

    @Override
    public Page<DeGiay> getListPage(Pageable pageable) {
        return deGiayRepository.getPage(pageable);
    }

    @Override
    public DeGiay add(DeGiay deGiay) {
        return deGiayRepository.save(deGiay);
    }

    @Override
    public DeGiay findById(UUID id) {
        return deGiayRepository.findById(id).orElse(null);
    }

    @Override
    public DeGiay findByMa(String ma) {
        return deGiayRepository.findByMa(ma);
    }


    @Override
    public Page<DeGiay> search(String keyword, Pageable pageable) {
        return deGiayRepository.search(keyword, pageable);
    }

    @Override
    public Page<DeGiay> sort(Pageable pageable) {
        return deGiayRepository.sort(pageable);
    }
}
