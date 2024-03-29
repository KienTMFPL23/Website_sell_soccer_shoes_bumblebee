package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.KhuyenMai;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.KhuyenMaiRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.KhuyenMaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class KhuyenMaiServiceImpl implements KhuyenMaiService {

    @Autowired
    private KhuyenMaiRepository repo;

    @Override
    public Page<KhuyenMai> getAll(Pageable pageable) {
        return repo.getAll(pageable);
    }

    @Override
    public List<KhuyenMai> findAll() {
        return repo.findAll();
    }

    @Override
    public KhuyenMai save(KhuyenMai km) {
        return repo.save(km);
    }

    @Override
    public KhuyenMai findId(UUID id) {
        return repo.findId(id);
    }

    @Override
    public KhuyenMai findMa(String ma) {
        return repo.findMa(ma);
    }

    @Override
    public LocalDateTime convertDateToLocalDateTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }


}
