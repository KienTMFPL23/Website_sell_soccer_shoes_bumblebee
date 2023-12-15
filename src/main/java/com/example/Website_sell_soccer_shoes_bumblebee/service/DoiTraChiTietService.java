package com.example.Website_sell_soccer_shoes_bumblebee.service;

import com.example.Website_sell_soccer_shoes_bumblebee.dto.DoiTraChiTietCustom;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.DoiTraChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface DoiTraChiTietService {

    DoiTraChiTiet saveDoiTraCT(DoiTraChiTiet doiTraChiTiet);

    List<DoiTraChiTiet> listDoiTraCTById(UUID idDoiTra);

    DoiTraChiTiet removeDoiTraCT(UUID id);

    List<DoiTraChiTietCustom> listDoiTraCTCustom(UUID idHoaDon);

    DoiTraChiTiet getDoiTraCT(UUID idDoiTra,UUID idCTSP);

    List<DoiTraChiTiet> listDoiTraCTByHoaDon(String maHoaDon);

    List<DoiTraChiTiet> listDoiTraCTByIdHoaDon(UUID idHoaDon);

    Page<DoiTraChiTiet> findSanPhamLoi(Integer page);

    List<DoiTraChiTiet> listSanPhamDoi(UUID idHoaDon );

    DoiTraChiTiet getOneDoiTraCT(UUID idDoiTraCT);

    void deleteDoiTraCT(UUID idDoiTra);

    Double getToTalDoiTra(List<DoiTraChiTiet> lstDoiTraCT);
}
