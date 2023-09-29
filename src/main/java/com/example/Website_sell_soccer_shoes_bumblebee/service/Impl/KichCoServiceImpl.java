package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.KichCo;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.KichCoRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.KichCoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class KichCoServiceImpl implements KichCoService {
    @Autowired
    KichCoRepository repo;

    @Override
    public List<KichCo> getList() {
        return repo.findAll();
    }

    @Override
    public Page<KichCo> getListKC(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public void addKC(KichCo kichCo) {

        repo.save(kichCo);
    }

    @Override
    public void deleteKC(UUID id) {
        repo.deleteById(id);
    }

    @Override
    public KichCo getOne(UUID id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Page<KichCo> searchKH(String keyword, Pageable pageable) {
        return repo.search(keyword, pageable);
    }
}
