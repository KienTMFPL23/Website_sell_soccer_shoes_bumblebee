package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.DeGiay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface DeGiayService {

    List<DeGiay> getList();
    Page<DeGiay> getListPage(Pageable pageable);
    DeGiay add(DeGiay deGiay);
    DeGiay findById(UUID id);
    DeGiay findByMa(String ma);
    List<DeGiay> search(String keyword);
    Page<DeGiay> sort(Pageable pageable);
    List<DeGiay> getExcelDataAsList();
    int saveExcelData(List<DeGiay> deGiay);
    void uploadFile(MultipartFile file);
}
