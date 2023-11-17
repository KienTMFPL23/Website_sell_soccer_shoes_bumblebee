package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChiTietKhuyenMai;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDonChiTiet;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.HoaDonChiTietRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.HoaDonRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService {

    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    HoaDonRepository hoaDonRepository;


    @Override
    public List<HoaDonChiTiet> getHoaDonById(UUID id) {
        return hoaDonChiTietRepository.getListByHoaDon(id);
    }

    @Override
    public List<HoaDonChiTiet> getListHoaDonCTByIdHoaDon(UUID id) {
        return hoaDonChiTietRepository.getListByHoaDon(id);
    }

    @Override
    public HoaDonChiTiet getOneHoaDon(UUID id) {
        return hoaDonChiTietRepository.findById(id).orElse(null);
    }

    @Override
    public HoaDonChiTiet saveHoaDonCT(HoaDonChiTiet hoaDonChiTiet) {
        return hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

    @Override
    public HoaDonChiTiet deleteHoaDonCT(UUID id) {
        HoaDonChiTiet hdct = hoaDonChiTietRepository.findById(id).orElse(null);
        hoaDonChiTietRepository.delete(hdct);
        return hdct;
    }

    @Override
    public HoaDonChiTiet getAndUpdateSanPhamInHDCT(UUID idHoaDon, UUID idSP) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepository.getSanPhamInHDCT(idHoaDon, idSP);
        if (hoaDonChiTiet != null) {
            hoaDonChiTiet.setSoLuong(hoaDonChiTiet.getSoLuong() + 1);
            hoaDonChiTietRepository.save(hoaDonChiTiet);
            return hoaDonChiTiet;
        } else {
            return null;
        }
    }


    @Override
    public Double getTotalMoney(List<HoaDonChiTiet> list) {
        Double sum = 0.0;
        Double sumGoc = 0.0;
        Double sumKhuyenMai = 0.0;
        for (HoaDonChiTiet hdct : list) {
            if (hdct.getChiTietSanPham().getCtkm().isEmpty()) {
                sumGoc += hdct.getDonGia() * hdct.getSoLuong();
            } else {
                sumKhuyenMai += hdct.getDonGiaKhiGiam() * hdct.getSoLuong();
            }
            sum = sumGoc + sumKhuyenMai;
        }

        return sum;
    }

    @Override
    public Double tongTienKhuyenMai(List<HoaDonChiTiet> list) {
        Double sum = 0.0;
        for (HoaDonChiTiet hdct : list) {

        }
        return sum;
    }


    @Override
    public Double getDonGiaKhiGiam(List<ChiTietKhuyenMai> listCTKM) {
        Double donGiaKhiGiam = 0.0;
        for (ChiTietKhuyenMai ctkm : listCTKM) {
            if (ctkm.getKhuyenMai().getDonVi().equals("%")) {
                donGiaKhiGiam = ctkm.getCtsp().getGiaBan() - ((Double.valueOf(ctkm.getKhuyenMai().getGiaTri()) / 100) * ctkm.getCtsp().getGiaBan());
            } else {
                donGiaKhiGiam = ctkm.getCtsp().getGiaBan() - ctkm.getKhuyenMai().getGiaTri();
            }
        }
        return donGiaKhiGiam;
    }


    @Override
    public HoaDonChiTiet save(HoaDonChiTiet hdct) {
        return hoaDonChiTietRepository.save(hdct);
    }

    @Override
    public List<HoaDonChiTiet> saveAll(List<HoaDonChiTiet> hdct) {
        return hoaDonChiTietRepository.saveAll(hdct);
    }

    @Override
    public void deleteByHoaDon(UUID idHoaDon) {
        hoaDonChiTietRepository.deleteHDCTbyHoaDon(idHoaDon);
    }

    @Override
    public List<HoaDonChiTiet> getHoaDonTheoHoaDonChiTiet(UUID id) {
        return hoaDonChiTietRepository.getHoaDonTheoHoaDonChiTiet(id);
    }

    @Override
    public void removeHDCT(UUID idHoaDon) {
        hoaDonChiTietRepository.removeHDCT(idHoaDon);
    }

    @Override
    public void removeHD(UUID idHoaDon) {
        hoaDonChiTietRepository.removeHD(idHoaDon);
    }

    @Override
    public List<HoaDonChiTiet> hoaDonMuonDoi(String maHoaDon) {
        return hoaDonChiTietRepository.listHoaDonMuonDoi(maHoaDon);
    }


}
