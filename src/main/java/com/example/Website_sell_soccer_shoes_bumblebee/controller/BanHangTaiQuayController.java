package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChiTietSanPham;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDon;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDonChiTiet;
import com.example.Website_sell_soccer_shoes_bumblebee.service.ChiTietSanPhamService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.HoaDonChiTietService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/bumblebee/ban-hang-tai-quay")
public class BanHangTaiQuayController {

    @Autowired
    HoaDonService hoaDonService;

    @Autowired
    ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    HoaDonChiTietService hoaDonChiTietService;

    private UUID idHoaDon = null;

    @GetMapping("/sell")
    public String banHang(Model model) {
        model.addAttribute("view", "../ban_hang_tai_quay/index.jsp");
        model.addAttribute("listHoaDonCho", hoaDonService.listHoaDonCho());
        model.addAttribute("listSanPham", chiTietSanPhamService.getList());
        return "admin/index";
    }

    @RequestMapping("/create-hoadon")
    public String createHoaDon(Model model) throws ParseException {
        model.addAttribute("view", "../ban_hang_tai_quay/index.jsp");
        Integer soLuongHDCho = hoaDonService.listHoaDonCho().size();
        if (soLuongHDCho >= 5) {
            model.addAttribute("soLuongHD",soLuongHDCho);
            return "redirect:/bumblebee/ban-hang-tai-quay/sell";
        }
        hoaDonService.createHoaDon();
        return "redirect:/bumblebee/ban-hang-tai-quay/sell";
    }

    @RequestMapping("/delete-hoadon/{id}")
    public String deleteHoaDon(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("view", "../ban_hang_tai_quay/index.jsp");
        hoaDonService.deleteHoaDon(id);
        return "redirect:/bumblebee/ban-hang-tai-quay/sell";
    }

    @GetMapping("/hoa-don-chi-tiet/{id}")
    public String hoaDonChiTiet(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("view", "../ban_hang_tai_quay/index.jsp");
        model.addAttribute("listHoaDonCho", hoaDonService.listHoaDonCho());
        model.addAttribute("listSanPham", chiTietSanPhamService.getList());
        model.addAttribute("listHDCT", hoaDonChiTietService.getListHoaDonCTByIdHoaDon(id));
        List<HoaDonChiTiet> list = hoaDonChiTietService.getListHoaDonCTByIdHoaDon(id);
        Double sumMoney = hoaDonChiTietService.getTotalMoney(list);
        model.addAttribute("sumMoney",sumMoney);
        this.idHoaDon = id;
        return "admin/index";
    }

    @RequestMapping("/add-gio-hang/{id}")
    public String themSPGioHang(Model model, @PathVariable("id") UUID id) {
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        HoaDon hoaDon = hoaDonService.getOne(this.idHoaDon);
        ChiTietSanPham ctsp = chiTietSanPhamService.getOne(id);
        if (hoaDonChiTietService.getSanPhamInHDCT(id) != null) {
            hoaDonChiTietService.getAndUpdateSanPhamInHDCT(ctsp.getId());
            Integer soLuong = ctsp.getSoLuong() - 1;
            chiTietSanPhamService.updateSoLuongTon(id, soLuong);
        } else {
            hoaDonChiTiet.setHoaDon(hoaDon);
            hoaDonChiTiet.setChiTietSanPham(ctsp);
            hoaDonChiTiet.setSoLuong(1);
            hoaDonChiTiet.setDonGia(ctsp.getGiaBan());
            hoaDonChiTiet.setTrangThai(1);
            Integer soLuong = ctsp.getSoLuong() - 1;
            chiTietSanPhamService.updateSoLuongTon(id, soLuong);
            hoaDonChiTietService.saveHoaDonCT(hoaDonChiTiet);
        }
        return "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDon;
    }

    @RequestMapping("/delete-hdct/{id}")
    public String deleteHoaDonCT(Model model, @PathVariable("id") UUID id) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getOneHoaDon(id);
        Integer soLuong = hoaDonChiTiet.getSoLuong();
        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getOne(hoaDonChiTiet.getChiTietSanPham().getId());
        Integer soLuongTon = chiTietSanPham.getSoLuong();
        chiTietSanPhamService.updateSoLuongTon(chiTietSanPham.getId(), soLuongTon + soLuong);
        hoaDonChiTietService.deleteHoaDonCT(id);
        return "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDon;
    }

    @RequestMapping("/update-cart/{id}")
    public String updateSLGioHang(@PathVariable("id") UUID id, int soLuong) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getOneHoaDon(id);
        if (hoaDonChiTiet != null) {
            hoaDonChiTiet.setSoLuong(soLuong);
            Integer soLuongMua = hoaDonChiTiet.getSoLuong();
            ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getOne(hoaDonChiTiet.getChiTietSanPham().getId());
            Integer soLuongTon = chiTietSanPham.getSoLuong();
//            chiTietSanPhamService.updateSoLuongTon(chiTietSanPham.getId(),soLuongTon+soLuongMua);
            hoaDonChiTietService.saveHoaDonCT(hoaDonChiTiet);
        }
        return "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDon;
    }
}
