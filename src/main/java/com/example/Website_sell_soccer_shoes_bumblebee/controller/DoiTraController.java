package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.dto.ChiTietSanPhamCustom;
import com.example.Website_sell_soccer_shoes_bumblebee.dto.ChiTietSanPhamDto;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChiTietSanPham;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDonChiTiet;
import com.example.Website_sell_soccer_shoes_bumblebee.service.ChiTietSanPhamService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.HoaDonChiTietService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.HoaDonService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.SanPhamService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
public class DoiTraController {

    @Autowired
    HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    HoaDonService hoaDonService;

    @Autowired
    SanPhamService sanPhamService;

    @Data
    public static class SearchForm{
        String key = "";
    }

    private UUID idCTSP = null;
    @GetMapping("/bumblebee/doi-hang/list")
    public String danhSachDoiHang(Model model){
        model.addAttribute("view","../doi-tra/danh-sach-doi-tra.jsp");
        model.addAttribute("listDuDK",hoaDonService.danhSachHDDuDK());
        return "/admin/index";
    }

    @GetMapping("/bumblebee/doi-hang")
    public String doiHang(Model model){
        model.addAttribute("view","../doi-tra/doi-hang.jsp");
        model.addAttribute("SearchForm",new SearchForm());
        return "/admin/index";
    }

    @GetMapping("/bumblebee/don-hang/search")
    public String getDonHang(Model model, @ModelAttribute("SearchForm")SearchForm searchForm){
        model.addAttribute("view","../doi-tra/doi-hang.jsp");
        List<HoaDonChiTiet> listSPMuonDoi = hoaDonChiTietService.hoaDonMuonDoi(searchForm.key);
        model.addAttribute("listHDCT", listSPMuonDoi);
        return "/admin/index";
    }

    @GetMapping("/bumblebee/don-hang/{maHoaDon}")
    public String chiTietDonHang(Model model,@PathVariable("maHoaDon")String maHoaDon){
        model.addAttribute("view","../doi-tra/doi-hang.jsp");
        List<HoaDonChiTiet> listSPMuonDoi =hoaDonChiTietService.listHDCTByMaHD(maHoaDon);
        model.addAttribute("listHDCT", listSPMuonDoi);
        return "/admin/index";
    }

    @GetMapping("/bumblebee/don-hang/searchByQr/{maHD}")
    public String searchHoaDon(Model model, @PathVariable("maHD")String maHD){
        model.addAttribute("view","../doi-tra/doi-hang.jsp");
        model.addAttribute("SearchForm",new SearchForm());
        List<HoaDonChiTiet> lstHoaDonCT = hoaDonChiTietService.listHDCTByMaHD(maHD);
        model.addAttribute("listHDCT",lstHoaDonCT);
        return "/admin/index";
    }

    @GetMapping("/bumblebee/don-hang/list-sp/{id}")
    public ResponseEntity<?> getListByMa(Model model, @PathVariable("id") UUID id){
        ChiTietSanPham ctsp = chiTietSanPhamService.getOne(id);
        List<ChiTietSanPhamCustom> lstSanPham = chiTietSanPhamService.listSPCungLoai(ctsp.getGiaBan());
        return ResponseEntity.ok(lstSanPham);
    }
}
