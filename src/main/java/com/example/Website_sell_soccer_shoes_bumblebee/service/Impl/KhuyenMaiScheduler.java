package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChiTietKhuyenMai;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.KhuyenMai;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.ChiTietKhuyenMaiRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.KhuyenMaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class KhuyenMaiScheduler {

    @Autowired
    private KhuyenMaiRepository repo;

    @Autowired
    private ChiTietKhuyenMaiRepository chiTietKhuyenMaiRepository;

    @Scheduled(fixedRate = 60000) // Định kỳ 1 phút
    public void updateKhuyenMaiStatus() {
        List<KhuyenMai> khuyenMaiHetHan = repo.khuyenMaiHetHan();
        for (KhuyenMai khuyenMai : khuyenMaiHetHan) {
            khuyenMai.setTrangThai(1);
            repo.save(khuyenMai);

            List<ChiTietKhuyenMai> listCTKM = chiTietKhuyenMaiRepository.findIdKhuyenMai(khuyenMai.getId());
            for (ChiTietKhuyenMai ctkm : listCTKM) {
                chiTietKhuyenMaiRepository.delete(ctkm);
            }
        }
    }


}
