package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.dto.ChiTietSanPhamCustom;
import com.example.Website_sell_soccer_shoes_bumblebee.dto.ChiTietSanPhamDto;
import com.example.Website_sell_soccer_shoes_bumblebee.dto.DoiTraChiTietCustom;
import com.example.Website_sell_soccer_shoes_bumblebee.dto.HoaDonChiTietCustom;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.*;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.DoiTraChiTietRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.DoiTraRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    DoiTraService doiTraService;

    @Autowired
    DoiTraChiTietService doiTraChiTietService;

    @Autowired
    NhanVienService nhanVienService;

    @Autowired
    HttpSession session;

    private UUID idCTSP = null;
    private String maHoaDon = null;
    private UUID idDoiTra = null;
    private NhanVien nhanVien = null;
    private String nameNhanVien = "";
    private List<ChiTietSanPham> listCTSP = null;
    private UUID idHoaDon = null;
    private List<Integer> listSoLuong = new ArrayList<>();

    @Getter
    @Setter
    public static class SearchDoiTra {
        String keyword;
    }

    @Autowired
    DoiTraRepository doiTraRepository;

    @RequestMapping("/bumblebee/doi-hang/search-san-pham-loi")
    public String searchSPLoi(Model model, @ModelAttribute("searchDT") SearchDoiTra searchDoiTra, @RequestParam(defaultValue = "0", name = "p") Integer p) {
        if (p <= 0) {
            p = 0;
        }
        model.addAttribute("view", "../doi-tra/danh-sach-san-pham-loi.jsp");
        Pageable pageable = PageRequest.of(p, 5);
        model.addAttribute("listDoiTra", doiTraChiTietRepository.searchSanPhamLoi(searchDoiTra.keyword, pageable));
        return "/admin/index";
    }

    @RequestMapping("/bumblebee/doi-hang/search-doi-hang")
    public String searchDoiHang(Model model, @ModelAttribute("searchDT") SearchDoiTra searchDoiTra, @RequestParam(defaultValue = "0", name = "p") Integer p) {
        if (p <= 0) {
            p = 0;
        }
        model.addAttribute("view", "../doi-tra/danh-sach-doi.jsp");
        Pageable pageable = PageRequest.of(p, 5);
        model.addAttribute("listDoiTra", doiTraRepository.searchHuyDoiTra(searchDoiTra.keyword, pageable));
        return "/admin/index";
    }

    @Autowired
    DoiTraChiTietRepository doiTraChiTietRepository;

    @RequestMapping("/bumblebee/doi-hang/search-tra-hang")
    public String searchTraHang(Model model, @ModelAttribute("searchDT") SearchDoiTra searchDoiTra, @RequestParam(defaultValue = "0", name = "p") Integer p) {
        if (p <= 0) {
            p = 0;
        }
        model.addAttribute("view", "../doi-tra/danh-sach-doi-tra.jsp");
        Pageable pageable = PageRequest.of(p, 5);
        model.addAttribute("listDoiTra", doiTraRepository.searchTraHang(searchDoiTra.keyword, pageable));
        return "/admin/index";
    }

    @GetMapping("/bumblebee/doi-hang/list-san-pham-loi")
    public String danhSachSanPhamLoi(Model model, @RequestParam(defaultValue = "0", name = "p") Integer p) {
        model.addAttribute("view", "../doi-tra/danh-sach-san-pham-loi.jsp");
        if (p <= 0) {
            p = 0;
        }
        model.addAttribute("searchDT", new SearchDoiTra());

        model.addAttribute("listDoiTra", doiTraChiTietService.findSanPhamLoi(p));
        return "/admin/index";
    }

    @GetMapping("/bumblebee/doi-hang/list-doi-hang")
    public String danhSachHuy(Model model, @RequestParam(defaultValue = "0", name = "p") Integer p) {
        if (p <= 0) {
            p = 0;
        }
        model.addAttribute("view", "../doi-tra/danh-sach-doi.jsp");
//        model.addAttribute("listDuDK", hoaDonService.danhSachHDDuDK());
        model.addAttribute("searchDT", new SearchDoiTra());
        model.addAttribute("listDoiTra", doiTraService.listHuyDoiTra(p));
        return "/admin/index";
    }

    @GetMapping("/bumblebee/doi-hang/list-tra-hang")
    public String danhSachThanhCong(Model model, @RequestParam(defaultValue = "0", name = "p") Integer p) {
        if (p <= 0) {
            p = 0;
        }
        model.addAttribute("searchDT", new SearchDoiTra());

        model.addAttribute("view", "../doi-tra/danh-sach-doi-tra.jsp");
        model.addAttribute("listDoiTra", doiTraService.listDoiTraThanhCong(p));
        return "/admin/index";
    }

    @GetMapping("/bumblebee/don-hang/tao-doi-tra/{maHD}")
    public String hienThiDoiTra(Model model, @ModelAttribute("doiTraCT") DoiTraChiTiet doiTraCT, @PathVariable("maHD") String maHD) {
        model.addAttribute("view", "../doi-tra/tra-hang.jsp");
        HoaDon hoaDon = hoaDonService.searchHoaDon(maHD);
        model.addAttribute("searchDT", new SearchDoiTra());

        model.addAttribute("hoaDonMua", hoaDon);
        List<HoaDonChiTiet> lstHoaDonCT = hoaDonChiTietService.listHDCTByMaHD(maHD);
        model.addAttribute("listHDCT", lstHoaDonCT);
        Double sumMoney = hoaDonChiTietService.getTotalMoney(lstHoaDonCT);
        model.addAttribute("sumMoney", sumMoney);
        this.maHoaDon = maHD;
        return "/admin/index";
    }

    @GetMapping("/bumblebee/don-hang/{maHD}")
    public String searchHoaDon(Model model, @PathVariable("maHD") String maHD) {
        getTaiKhoan(model);
        model.addAttribute("searchDT", new SearchDoiTra());

        List<HoaDon> listHoaDonDuDK = hoaDonService.danhSachHDDuDK();
        if (listHoaDonDuDK != null) {
            for (HoaDon hd : listHoaDonDuDK) {
                if (hd.getMaHoaDon().equals(maHD)) {
                    return "redirect:/bumblebee/don-hang/tao-doi-tra/" + maHD;
                }
            }
            model.addAttribute("notFound", "Khong tim thay");
            model.addAttribute("error", "Hóa đơn không đủ điều kiện đổi trả!");
            return "forward:/bumblebee/doi-hang/list-tra-hang";
        } else {
            model.addAttribute("error", "Hóa đơn không đủ điều kiện đổi trả!");

            model.addAttribute("notFound", "Hóa đơn không đủ điều kiện đổi trả!");
            return "forward:/bumblebee/doi-hang/list-tra-hang";
        }
    }

    @GetMapping("/bumblebee/don-hang/list-sp/{id}")
    public ResponseEntity<?> getListByMa(Model model, @PathVariable("id") UUID id) {
        ChiTietSanPham ctsp = chiTietSanPhamService.getOne(id);
        model.addAttribute("searchDT", new SearchDoiTra());
        this.idCTSP = id;
        List<ChiTietSanPhamCustom> lstSanPham = chiTietSanPhamService.listSPCungLoai(ctsp.getGiaBan());
        return ResponseEntity.ok(lstSanPham);
    }

    @GetMapping("/bumblebee/don-hang/list-sp-km/{id}")
    public ResponseEntity<?> getListByMaSPKM(Model model, @PathVariable("id") UUID id) {
        ChiTietSanPham ctsp = chiTietSanPhamService.getOne(id);
        model.addAttribute("searchDT", new SearchDoiTra());
        this.idCTSP = id;
        List<ChiTietSanPhamCustom> lstSanPham = chiTietSanPhamService.listCTSPKhuyenMai(ctsp.getSanPham().getId());
        return ResponseEntity.ok(lstSanPham);
    }

    @GetMapping("/bumblebee/don-hang/list-hoa-don-ct/{id}")
    public ResponseEntity<?> listHoaDonCTCustom(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("searchDT", new SearchDoiTra());

        List<HoaDonChiTietCustom> lstHoaDonCT = hoaDonChiTietService.listHoaDonCTCustom(id);
        return ResponseEntity.ok(lstHoaDonCT);
    }

    @GetMapping("/bumblebee/don-hang/list-doi-tra-ct/{id}")
    public ResponseEntity<?> listDoiTraCTCustom(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("searchDT", new SearchDoiTra());

        List<DoiTraChiTietCustom> lstDoiTraCT = doiTraChiTietService.listDoiTraCTCustom(id);
        return ResponseEntity.ok(lstDoiTraCT);
    }

    private List<String> listlyDo = new ArrayList<>();

    private void createDoiTraCT(DoiTra doiTra, List<String> lydo) {

        for (int h = 0; h < listCTSP.size(); h++) {
            DoiTraChiTiet doiTraChiTiet = new DoiTraChiTiet();
            HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getHDCTDoiTra(this.idHoaDon, listCTSP.get(h).getId());
            doiTraChiTiet.setChiTietSanPham(listCTSP.get(h));
            doiTraChiTiet.setHoaDonChiTiet(hoaDonChiTiet);
            doiTraChiTiet.setSoLuong(listSoLuong.get(h));
            doiTraChiTiet.setDoiTra(doiTra);
            doiTraChiTiet.setLyDoDoiTra(lydo.get(h));

            if (hoaDonChiTiet.getDonGiaKhiGiam() == 0 || hoaDonChiTiet.getDonGiaKhiGiam() == null) {
                hoaDonChiTiet.getDonGia();
                doiTraChiTiet.setDonGia(hoaDonChiTiet.getDonGia());
            } else {
                doiTraChiTiet.setDonGia(hoaDonChiTiet.getDonGiaKhiGiam());
            }

            doiTraChiTiet.setTrangThai(2);
            doiTraChiTietService.saveDoiTraCT(doiTraChiTiet);
        }
        this.idDoiTra = doiTra.getId();
    }

    private static int maDoiTra = 1;

    private void createMaDoiTraAuto(DoiTra doiTra, HoaDon hoaDon) throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String format = sdf.format(date);
        doiTra.setNgayDoiTra(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(format));
        doiTra.setHoaDon(hoaDon);
        doiTra.setNhanVien(nhanVien);
        doiTraService.saveDoiTra(doiTra);
        this.idHoaDon = hoaDon.getId();
    }

    private void createDoiSanPham(UUID idSanPham, DoiTra doiTra, HoaDon hoaDon) {
        DoiTraChiTiet doiTraChiTiet = new DoiTraChiTiet();
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getHDCTDoiTra(hoaDon.getId(), this.idCTSP);
        ChiTietSanPham ctsp = chiTietSanPhamService.getOne(idSanPham);
        DoiTraChiTiet getSPInDoiTra = doiTraChiTietService.getSanPhamInDoiTra(doiTra.getId(), idSanPham);
        Integer soLuongDT = doiTraChiTietService.getMaxSLDoi(idDoiTra, hoaDonChiTiet.getId());
        Integer soLuongHD = hoaDonChiTiet.getSoLuong();
        if (getSPInDoiTra != null) {
            if (getSPInDoiTra.getSoLuong() < soLuongHD) {
                if (getSPInDoiTra.getHoaDonChiTiet() == hoaDonChiTiet) {
                    getSPInDoiTra.setSoLuong(getSPInDoiTra.getSoLuong() + 1);
                    doiTraChiTietService.saveDoiTraCT(getSPInDoiTra);
                    this.idDoiTra = doiTra.getId();
                }
            }
        } else {
            if (soLuongDT < hoaDonChiTiet.getSoLuong()) {
                doiTraChiTiet.setChiTietSanPham(ctsp);
                doiTraChiTiet.setHoaDonChiTiet(hoaDonChiTiet);
                doiTraChiTiet.setSoLuong(1);
                doiTraChiTiet.setDoiTra(doiTra);
                doiTraChiTiet.setDonGia(ctsp.getGiaBan());
                doiTraChiTiet.setTrangThai(1);
                doiTraChiTietService.saveDoiTraCT(doiTraChiTiet);
                this.idDoiTra = doiTra.getId();
            }
        }
    }


    @RequestMapping("/bumblebee/don-hang/huy")
    public String huyDoiHang() {
        DoiTra doiTra = doiTraService.getOneDoiTra(this.maHoaDon);
        List<DoiTraChiTiet> list = doiTraChiTietService.listDoiTraCTByHoaDon(this.maHoaDon);
        if (list.size() != 0) {
            for (DoiTraChiTiet dtct : list) {
                doiTraChiTietService.deleteDoiTraCT(dtct.getId());
            }
        }
        doiTraService.huyDoiTra(doiTra.getId());
        return "redirect:/bumblebee/don-hang/tao-doi-tra/" + this.maHoaDon;
    }

    @RequestMapping("/bumblebee/don-hang/update-so-Luong/{id}")
    public String updateSoLuong(@RequestParam("soLuong") Integer soLuong, @PathVariable("id") UUID id) {
        DoiTraChiTiet doiTraChiTiet = doiTraChiTietService.getOneDoiTraCT(id);
        Integer soLuongDaMua = doiTraChiTiet.getHoaDonChiTiet().getSoLuong();
//        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getOne(doiTraChiTiet.getChiTietSanPham().getId());
//        Integer soLuongTon = chiTietSanPham.getSoLuong() + doiTraChiTiet.getSoLuong();
        Integer soLuongDT = doiTraChiTietService.getSoLuongDTMaxInSP(doiTraChiTiet.getDoiTra().getId());
        if (soLuong <= 0) {
            doiTraChiTiet.setSoLuong(1);
            doiTraChiTietService.saveDoiTraCT(doiTraChiTiet);
        } else if (soLuongDT + soLuong - doiTraChiTiet.getSoLuong() <= soLuongDaMua) {
            doiTraChiTiet.setSoLuong(soLuong);
            doiTraChiTietService.saveDoiTraCT(doiTraChiTiet);
        }
//        if (soLuong > soLuongDaMua) {
//                doiTraChiTiet.setSoLuong(soLuongDaMua);
//                doiTraChiTietService.saveDoiTraCT(doiTraChiTiet);
//            } else if (soLuong <= 0) {
//                doiTraChiTiet.setSoLuong(1);
//                doiTraChiTietService.saveDoiTraCT(doiTraChiTiet);
//            } else {
//                doiTraChiTiet.setSoLuong(soLuong);
//                doiTraChiTietService.saveDoiTraCT(doiTraChiTiet);
////            chiTietSanPhamService.updateSoLuongTon(chiTietSanPham.getId(), soLuongTon - soLuong);
//            }
        return "redirect:/bumblebee/don-hang/doi-san-pham";
    }

    @RequestMapping("/bumblebee/don-hang/update-ly-do/{id}")
    public String updateLyDo(@RequestParam("lyDoDoiTra") String lyDoDoiTra, @PathVariable("id") UUID id) {
        DoiTraChiTiet doiTraChiTiet = doiTraChiTietService.getOneDoiTraCT(id);
        doiTraChiTiet.setLyDoDoiTra(lyDoDoiTra);
        doiTraChiTietService.saveDoiTraCT(doiTraChiTiet);
        return "redirect:/bumblebee/don-hang/doi-san-pham";
    }

    @RequestMapping("/bumblebee/don-hang/create-doi-tra/{idSanPham}")
    public String createDoiTra(Model model, @PathVariable("idSanPham") UUID idSanPham
    ) {
        model.addAttribute("searchDT", new SearchDoiTra());

        model.addAttribute("view", "../doi-tra/tra-hang.jsp");

        DoiTra checkDoiTra = doiTraService.getOneDoiTra(this.maHoaDon);
        HoaDon hoaDon = hoaDonService.searchHoaDon(this.maHoaDon);

        if (checkDoiTra != null) {
            this.idDoiTra = checkDoiTra.getId();
            createDoiSanPham(idSanPham, checkDoiTra, hoaDon);
        } else {
            DoiTra doiTra = new DoiTra();
            doiTra.setHoaDon(hoaDon);
            doiTra.setNhanVien(nhanVien);
            doiTraService.saveDoiTra(doiTra);
            createDoiSanPham(idSanPham, doiTra, hoaDon);
            this.idDoiTra = doiTra.getId();
        }
        return "redirect:/bumblebee/don-hang/doi-san-pham";
    }

    @RequestMapping("/bumblebee/don-hang/xac-nhan-doi")
    public String xacNhanDoiHang(Model model) throws ParseException {
        model.addAttribute("searchDT", new SearchDoiTra());

        DoiTra doiTra = doiTraService.getOneDoiTra(this.maHoaDon);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String format = sdf.format(date);
        doiTra.setNgayDoiTra(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(format));
        doiTra.setTrangThai(1);
        doiTraService.saveDoiTra(doiTra);
        HoaDon hoaDon = hoaDonService.searchHoaDon(this.maHoaDon);
        this.idHoaDon = hoaDon.getId();
        hoaDon.setTrangThai(7);
        hoaDonService.saveHoaDon(hoaDon);


        List<DoiTraChiTiet> lstDoiTraCT = doiTraChiTietService.listDoiTraCTById(this.idDoiTra);
        for (DoiTraChiTiet doitraCT : lstDoiTraCT) {
            DoiTraChiTiet doiTraChiTiet = doiTraChiTietService.getDoiTraCT(doitraCT.getDoiTra().getId(), doitraCT.getChiTietSanPham().getId());
            HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getHDCTDoiTra(this.idHoaDon, doitraCT.getHoaDonChiTiet().getChiTietSanPham().getId());
            hoaDonChiTiet.setTrangThai(0);
            doiTraChiTiet.setHoaDonChiTiet(hoaDonChiTiet);
            hoaDonChiTietService.saveHoaDonCT(hoaDonChiTiet);
            doiTraChiTietService.saveDoiTraCT(doiTraChiTiet);
            if (doiTraChiTiet.getLyDoDoiTra().equals("Sản phẩm lỗi")) {
                chiTietSanPhamService.updateDelete(doiTraChiTiet.getChiTietSanPham().getId(), doiTraChiTiet.getSoLuong());
            } else {
                if (doiTraChiTiet.getHoaDonChiTiet().getChiTietSanPham().getId() != doiTraChiTiet.getChiTietSanPham().getId()) {
                    ChiTietSanPham sanPhamTra = chiTietSanPhamService.getOne(doiTraChiTiet.getHoaDonChiTiet().getChiTietSanPham().getId());
                    ChiTietSanPham sanPhamDoi = chiTietSanPhamService.getOne(doiTraChiTiet.getChiTietSanPham().getId());
                    chiTietSanPhamService.updateDelete(sanPhamTra.getId(), doiTraChiTiet.getSoLuong());
                    chiTietSanPhamService.updateSoLuongTon(sanPhamDoi.getId(), sanPhamDoi.getSoLuong() - doiTraChiTiet.getSoLuong());
                }
            }
        }

        return "redirect:/bumblebee/doi-hang/list-doi-hang";
    }

    @RequestMapping("/bumblebee/don-hang/create-tra-hang")
    public String createTraHang(Model model,
                                @RequestParam(name = "idListCartDetail", required = false) String idListCartDetail,
                                @RequestParam("lyDoDoiTra") String[] lyDoDoiTra,
                                @RequestParam("soLuong") Integer[] soLuong) throws ParseException {
        model.addAttribute("searchDT", new SearchDoiTra());

        model.addAttribute("view", "../doi-tra/doi-hang.jsp");
        DoiTra doiTra = new DoiTra();
        HoaDon hoaDon = hoaDonService.searchHoaDon(this.maHoaDon);
        createMaDoiTraAuto(doiTra, hoaDon);
        this.idDoiTra = doiTra.getId();
        List<UUID> idCartUUIDList = Arrays.asList(idListCartDetail.split(","))
                .stream()
                .map(UUID::fromString)
                .collect(Collectors.toList());
        listCTSP = new ArrayList<>();
        listSoLuong = Arrays.asList(soLuong)
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        listlyDo = Arrays.asList(lyDoDoiTra)
                .stream()
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
        for (int i = 0; i < idCartUUIDList.size(); i++) {
            ChiTietSanPham ctkm = chiTietSanPhamService.getOne(idCartUUIDList.get(i));
            listCTSP.add(ctkm);
        }

        DoiTra checkDoiTra = doiTraService.getOneDoiTra(this.maHoaDon);
        checkDoiTra.setTrangThai(2);
        doiTraService.saveDoiTra(checkDoiTra);
        createDoiTraCT(checkDoiTra, listlyDo);
        hoaDon.setTrangThai(6);
        hoaDonService.saveHoaDon(hoaDon);
        for (int j = 0; j < listCTSP.size(); j++) {
            HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getHDCTDoiTra(this.idHoaDon, listCTSP.get(j).getId());
            hoaDonChiTiet.setTrangThai(0);
            hoaDonChiTietService.saveHoaDonCT(hoaDonChiTiet);
            if (!listlyDo.get(j).equals("Sản phẩm lỗi")) {
                ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getOne(listCTSP.get(j).getId());
                Integer soLuongTra = listSoLuong.get(j);
                chiTietSanPhamService.updateDelete(chiTietSanPham.getId(), soLuongTra);
            }
        }
        return "redirect:/bumblebee/doi-hang/list-tra-hang";
    }

    private void getTaiKhoan(Model model) {
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        nhanVien = nhanVienService.getOne(taiKhoan.getId());
        model.addAttribute("username", taiKhoan.getUsername());
        nameNhanVien = nhanVien.getHo() + " " + nhanVien.getTenDem() + " " + nhanVien.getTen();
        model.addAttribute("fullNameStaff", nameNhanVien);
    }


    @RequestMapping("/bumblebee/don-hang/remove-doi-tra/{id}")
    public String removeDoiTraCT(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("searchDT", new SearchDoiTra());

        doiTraChiTietService.removeDoiTraCT(id);

        return "redirect:/bumblebee/don-hang/doi-san-pham";
    }

    @RequestMapping("/bumblebee/doi-hang/list-tra-hang")
    public String huyDoiTra(Model model) {
        model.addAttribute("searchDT", new SearchDoiTra());
        doiTraService.huyDoiTra(this.idDoiTra);
        return "redirect:/bumblebee/doi-hang/list";
    }

    @RequestMapping("/bumblebee/tra-hang/chi-tiet/{id}")
    public String chiTietTraHang(Model model, @ModelAttribute("doiTraCT") DoiTraChiTiet doiTraCT, @PathVariable("id") UUID id) {
        model.addAttribute("view", "../doi-tra/detail-doi-tra.jsp");
        List<HoaDonChiTiet> lstHoaDonCT = hoaDonChiTietService.getListHoaDonCTByIdHoaDon(id);
        Double sum = hoaDonChiTietService.getTotalMoney(lstHoaDonCT);
        model.addAttribute("sumHoaDon", sum);
        model.addAttribute("searchDT", new SearchDoiTra());
        List<DoiTraChiTiet> lstSanPhamTra = doiTraChiTietService.listDoiTraCTByIdHoaDon(id);
        Double sumDoiTra = doiTraChiTietService.getToTalDoiTra(lstSanPhamTra);
        HoaDon hoaDon = hoaDonService.getOne(id);
        model.addAttribute("hoaDon", hoaDon);
        model.addAttribute("listHoaDonCT", lstHoaDonCT);
        model.addAttribute("listDoiTraCT", lstSanPhamTra);
        model.addAttribute("sumDoiTra", sumDoiTra);
        return "admin/index";
    }

    @RequestMapping("/bumblebee/doi-hang/chi-tiet/{id}")
    public String chiTietDoiHang(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("view", "../doi-tra/detail-doi-hang.jsp");
        List<HoaDonChiTiet> lstHoaDonCT = hoaDonChiTietService.getListHoaDonCTByIdHoaDon(id);
        Double sum = hoaDonChiTietService.getTotalMoney(lstHoaDonCT);
        model.addAttribute("sumHoaDon", sum);
        model.addAttribute("searchDT", new SearchDoiTra());
        List<DoiTraChiTiet> lstSanPhamTra = doiTraChiTietService.listDoiTraCTByIdHoaDon(id);

        List<DoiTraChiTiet> lstSanPhamDoi = doiTraChiTietService.listSanPhamDoi(id);
        Double sumSanPhamDoi = doiTraChiTietService.getToTalDoiTra(lstSanPhamDoi);
        model.addAttribute("sumSanPhamDoi", sumSanPhamDoi);
        HoaDon hoaDon = hoaDonService.getOne(id);
        model.addAttribute("hoaDon", hoaDon);
        model.addAttribute("listHoaDonCT", lstHoaDonCT);
        model.addAttribute("listDoiTraCT", lstSanPhamTra);
        model.addAttribute("listSanPhamDoi", lstSanPhamDoi);
        return "admin/index";
    }

    @GetMapping("/bumblebee/don-hang/doi-san-pham")
    public String doiHang(Model model) {
        model.addAttribute("view", "../doi-tra/doi-hang.jsp");
        model.addAttribute("maHoaDon", this.maHoaDon);
        model.addAttribute("idDoiTra", this.idDoiTra);
        List<HoaDonChiTiet> lstHoaDonCT = hoaDonChiTietService.listHDCTByMaHD(this.maHoaDon);
        model.addAttribute("listHDCT", lstHoaDonCT);
        Double sumMoney = hoaDonChiTietService.getTotalMoney(lstHoaDonCT);
        model.addAttribute("sumMoney", sumMoney);
        model.addAttribute("listDoiTraCT", doiTraChiTietService.listDoiTraCTByHoaDon(this.maHoaDon));
        return "/admin/index";
    }

    @RequestMapping("/bumblebee/doi-hang/print/{id}")
    public void print(HttpServletResponse response, @PathVariable("id") UUID id, Model model) throws ParseException {

        HoaDon hoaDonDoiTra = hoaDonService.getOne(id);
        model.addAttribute("searchDT", new SearchDoiTra());

        List<HoaDonChiTiet> listHoaDon1 = hoaDonChiTietService.getHoaDonTheoHoaDonChiTiet(id);
        List<DoiTraChiTiet> listDoiTra = doiTraChiTietService.listDoiTraCTByIdHoaDon(id);
        try {
            Document document = new Document();
            document.setPageSize(PageSize.A4);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document, baos);
            PdfWriter.getInstance(document, baos);
            ////
            document.open();
            String qrCodeData = hoaDonDoiTra.getMaHoaDon();
            BarcodeQRCode qrCode = new BarcodeQRCode(qrCodeData, 200, 250, null);
            com.itextpdf.text.Image qrCodeImage = qrCode.getImage();
//
            qrCodeImage.setAbsolutePosition(400, 80);
            document.add(qrCodeImage);
            Font largeFont = new Font(Font.FontFamily.TIMES_ROMAN, 25f, Font.BOLD);
            ////////////// hoá đơn
            Paragraph HoaDon = new Paragraph(" BUMBLEBEE SHOES" + "\n", largeFont);

            HoaDon.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(HoaDon);
//
            Paragraph maHoaDon = new Paragraph(hoaDonDoiTra.getMaHoaDon());
            maHoaDon.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(maHoaDon);
            Font titleFont = new Font(BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 18, Font.BOLD, BaseColor.BLACK);
            Paragraph tieuDeThongTin = new Paragraph("HOÁ ĐƠN BÁN HÀNG  ", titleFont);
            document.add(tieuDeThongTin);

//            Thông tin hoá dơn
            Font titleFont1 = new Font(BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 15, Font.NORMAL, BaseColor.BLACK);
            Paragraph nhanVien = new Paragraph("Nhân Viên bán hàng : " + hoaDonDoiTra.getNhanVien().getHo() + " " + hoaDonDoiTra.getNhanVien().getTenDem() + " " + hoaDonDoiTra.getNhanVien().getTen(), titleFont1);
            document.add(nhanVien);

            if (hoaDonDoiTra.getKhachHang() != null && hoaDonDoiTra.getKhachHang().getTen() != null) {
                Paragraph khachHang = new Paragraph("Khách hàng : " + hoaDonDoiTra.getKhachHang().getTen(), titleFont1);
                document.add(khachHang);
            } else {
                Paragraph khachHang = new Paragraph("Khách hàng : khách vãn lai", titleFont1);
                document.add(khachHang);
            }


            Paragraph sdt = new Paragraph("Số điện thoại : " + hoaDonDoiTra.getSdt(), titleFont1);
            document.add(sdt);
            Font titleFont2 = new Font(BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 18, Font.BOLD, BaseColor.BLACK);
            Paragraph danhSachMua = new Paragraph("DANH SÁCH SẢN PHẨM KHÁCH MUA", titleFont2);
            danhSachMua.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(danhSachMua);
            Paragraph KhoangTrang = new Paragraph("                                                         ");
            document.add(KhoangTrang);
            PdfPTable productTable = new PdfPTable(6);
            productTable.setWidthPercentage(100);
            float[] columnWidths = {3f, 3f, 2f, 2f, 2f, 2f};
            productTable.setWidths(columnWidths);

            productTable.addCell(createTableCell("Tên sản phẩm", titleFont1));
            productTable.addCell(createTableCell("Màu sắc", titleFont1));
            productTable.addCell(createTableCell("Kích cỡ", titleFont1));
            productTable.addCell(createTableCell("Số lượng", titleFont1));
            productTable.addCell(createTableCell("Giá tiền", titleFont1));
            productTable.addCell(createTableCell("Thành tiền", titleFont1));
            double tongTien = 0.0;
            List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonTheoHoaDonChiTiet(id);
            for (HoaDonChiTiet hoaDonChiTiet : listHoaDonChiTiet) {
                productTable.addCell(createTableCell(hoaDonChiTiet.getChiTietSanPham().getSanPham().getTenSanPham(), titleFont1));
                productTable.addCell(createTableCell(hoaDonChiTiet.getChiTietSanPham().getMauSac().getTen(), titleFont1));
                productTable.addCell(createTableCell(String.valueOf(hoaDonChiTiet.getChiTietSanPham().getKichCo().getSize()), titleFont1));
                productTable.addCell(createTableCell(String.valueOf(hoaDonChiTiet.getSoLuong()), titleFont1));
                productTable.addCell(createTableCell(String.valueOf(hoaDonChiTiet.getDonGia()), titleFont1));
                productTable.addCell(createTableCell(String.valueOf(hoaDonChiTiet.getDonGia() * hoaDonChiTiet.getSoLuong()), titleFont1));
                double giaTriSanPham = hoaDonChiTiet.getDonGia() * hoaDonChiTiet.getSoLuong();
                tongTien += giaTriSanPham;
            }
            document.add(productTable);

//
            Paragraph KhoangTrang1 = new Paragraph("                                                         ");
            document.add(KhoangTrang1);

//            Font titleFont2 = new Font(BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 18, Font.BOLD, BaseColor.BLACK);
            Paragraph danhSachTra = new Paragraph("DANH SÁCH SẢN PHẨM KHÁCH TRẢ", titleFont2);
            danhSachTra.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(danhSachTra);
            Paragraph KhoangTrang2 = new Paragraph("                                                         ");
            document.add(KhoangTrang2);
            PdfPTable productTable1 = new PdfPTable(8);
            productTable1.setWidthPercentage(100);
            float[] columnWidths2 = {4f, 2f, 2f, 2f, 2f, 2f, 4f, 2f}; // Adjusted for 8 columns
            productTable1.setWidths(columnWidths2);
            productTable1.addCell(createTableCell("Tên sản phẩm", titleFont1));
            productTable1.addCell(createTableCell("Màu sắc", titleFont1));
            productTable1.addCell(createTableCell("Kích cỡ", titleFont1));
            productTable1.addCell(createTableCell("Số lượng", titleFont1));
            productTable1.addCell(createTableCell(" Đơn giá ", titleFont1));
            productTable1.addCell(createTableCell("Thành tiền", titleFont1));
            productTable1.addCell(createTableCell("Nhân viên thực hiện", titleFont1));
            productTable1.addCell(createTableCell("Lí do", titleFont1));
            double tongTienTra = 0.0;

            for (DoiTraChiTiet doiTraChiTiet : listDoiTra) {

                productTable1.addCell(createTableCell(doiTraChiTiet.getChiTietSanPham().getSanPham().getTenSanPham(), titleFont1));
                productTable1.addCell(createTableCell(doiTraChiTiet.getChiTietSanPham().getMauSac().getTen(), titleFont1));
                productTable1.addCell(createTableCell(String.valueOf(doiTraChiTiet.getChiTietSanPham().getKichCo().getSize()), titleFont1));
                productTable1.addCell(createTableCell(String.valueOf(doiTraChiTiet.getSoLuong()), titleFont1));
                if (doiTraChiTiet.getHoaDonChiTiet().getDonGiaKhiGiam() == null || doiTraChiTiet.getHoaDonChiTiet().getDonGiaKhiGiam() == 0) {
                    productTable1.addCell(createTableCell(String.valueOf(doiTraChiTiet.getDonGia()), titleFont1));
                    productTable1.addCell(createTableCell(String.valueOf(doiTraChiTiet.getDonGia() * doiTraChiTiet.getSoLuong()), titleFont1));
                    double giaTriSanPham = doiTraChiTiet.getDonGia() * doiTraChiTiet.getSoLuong();
                    tongTienTra += giaTriSanPham;
                } else if (doiTraChiTiet.getHoaDonChiTiet().getDonGiaKhiGiam() != null || doiTraChiTiet.getHoaDonChiTiet().getDonGiaKhiGiam() != 0) {
                    productTable1.addCell(createTableCell(String.valueOf(doiTraChiTiet.getHoaDonChiTiet().getDonGiaKhiGiam()), titleFont1));
                    productTable1.addCell(createTableCell(String.valueOf(doiTraChiTiet.getHoaDonChiTiet().getDonGiaKhiGiam() * doiTraChiTiet.getSoLuong()), titleFont1));
                    double giaTriSanPham = doiTraChiTiet.getHoaDonChiTiet().getDonGiaKhiGiam() * doiTraChiTiet.getSoLuong();
                    tongTienTra += giaTriSanPham;
                }

                productTable1.addCell(createTableCell(doiTraChiTiet.getHoaDonChiTiet().getHoaDon().getNhanVien().getHo() + " " + doiTraChiTiet.getHoaDonChiTiet().getHoaDon().getNhanVien().getTenDem() + " " + doiTraChiTiet.getHoaDonChiTiet().getHoaDon().getNhanVien().getTen(), titleFont1));
                productTable1.addCell(createTableCell(doiTraChiTiet.getLyDoDoiTra(), titleFont1));

            }

            document.add(productTable1);
            Paragraph TongCong = new Paragraph("Số tiền hoàn       :    " + tongTienTra + "VNĐ", titleFont2);
            document.add(TongCong);
//

            document.close();
//
//

            byte[] pdfBytes = baos.toByteArray();
            response.setContentType("application/pdf");
            response.setContentLength(pdfBytes.length);

            // Thay đổi giá trị "inline" thành "attachment"
            // Thay đổi giá trị "inline" thành "attachment"
            response.setHeader("Content-Disposition", "attachment; filename=" + hoaDonDoiTra.getMaHoaDon() + ".pdf");


            response.getOutputStream().write(pdfBytes);
            response.getOutputStream().flush();
            response.getOutputStream().close();
//
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/bumblebee/doi-hang-doi-tra/print/{id}")
    public void printDoiHang(HttpServletResponse response, @PathVariable("id") UUID id, Model model) throws ParseException {

        HoaDon hoaDonDoiTra = hoaDonService.getOne(id);
        model.addAttribute("searchDT", new SearchDoiTra());

        List<HoaDonChiTiet> listHoaDon1 = hoaDonChiTietService.getHoaDonTheoHoaDonChiTiet(id);
        List<DoiTraChiTiet> lstSanPhamDoi = doiTraChiTietService.listSanPhamDoi(id);
        try {
            Document document = new Document();
            document.setPageSize(PageSize.A4);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document, baos);
            PdfWriter.getInstance(document, baos);
            ////
            document.open();
            String qrCodeData = hoaDonDoiTra.getMaHoaDon();
            BarcodeQRCode qrCode = new BarcodeQRCode(qrCodeData, 200, 250, null);
            com.itextpdf.text.Image qrCodeImage = qrCode.getImage();
//
            qrCodeImage.setAbsolutePosition(400, 80);
            document.add(qrCodeImage);
            Font largeFont = new Font(Font.FontFamily.TIMES_ROMAN, 25f, Font.BOLD);
            ////////////// hoá đơn
            Paragraph HoaDon = new Paragraph(" BUMBLEBEE SHOES" + "\n", largeFont);

            HoaDon.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(HoaDon);
//
            Paragraph maHoaDon = new Paragraph(hoaDonDoiTra.getMaHoaDon());
            maHoaDon.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(maHoaDon);
            Font titleFont = new Font(BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 18, Font.BOLD, BaseColor.BLACK);
            Paragraph tieuDeThongTin = new Paragraph("HOÁ ĐƠN BÁN HÀNG  ", titleFont);
            document.add(tieuDeThongTin);

//            Thông tin hoá dơn
            Font titleFont1 = new Font(BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 15, Font.NORMAL, BaseColor.BLACK);
            Paragraph nhanVien = new Paragraph("Nhân Viên bán hàng : " + hoaDonDoiTra.getNhanVien().getHo() + " " + hoaDonDoiTra.getNhanVien().getTenDem() + " " + hoaDonDoiTra.getNhanVien().getTen(), titleFont1);
            document.add(nhanVien);

            if (hoaDonDoiTra.getKhachHang() != null && hoaDonDoiTra.getKhachHang().getTen() != null) {
                Paragraph khachHang = new Paragraph("Khách hàng : " + hoaDonDoiTra.getKhachHang().getTen(), titleFont1);
                document.add(khachHang);
            } else {
                Paragraph khachHang = new Paragraph("Khách hàng : khách vãn lai", titleFont1);
                document.add(khachHang);
            }


            Paragraph sdt = new Paragraph("Số điện thoại : " + hoaDonDoiTra.getSdt(), titleFont1);
            document.add(sdt);
            Font titleFont2 = new Font(BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 18, Font.BOLD, BaseColor.BLACK);
            Paragraph danhSachMua = new Paragraph("DANH SÁCH SẢN PHẨM KHÁCH MUA", titleFont2);
            danhSachMua.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(danhSachMua);
            Paragraph KhoangTrang = new Paragraph("                                                         ");
            document.add(KhoangTrang);
            PdfPTable productTable = new PdfPTable(6);
            productTable.setWidthPercentage(100);
            float[] columnWidths = {3f, 3f, 2f, 2f, 2f, 2f};
            productTable.setWidths(columnWidths);

            productTable.addCell(createTableCell("Tên sản phẩm", titleFont1));
            productTable.addCell(createTableCell("Màu sắc", titleFont1));
            productTable.addCell(createTableCell("Kích cỡ", titleFont1));
            productTable.addCell(createTableCell("Số lượng", titleFont1));
            productTable.addCell(createTableCell("Giá tiền", titleFont1));
            productTable.addCell(createTableCell("Thành tiền", titleFont1));
            double tongTien = 0.0;
            List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonTheoHoaDonChiTiet(id);
            for (HoaDonChiTiet hoaDonChiTiet : listHoaDonChiTiet) {
                productTable.addCell(createTableCell(hoaDonChiTiet.getChiTietSanPham().getSanPham().getTenSanPham(), titleFont1));
                productTable.addCell(createTableCell(hoaDonChiTiet.getChiTietSanPham().getMauSac().getTen(), titleFont1));
                productTable.addCell(createTableCell(String.valueOf(hoaDonChiTiet.getChiTietSanPham().getKichCo().getSize()), titleFont1));
                productTable.addCell(createTableCell(String.valueOf(hoaDonChiTiet.getSoLuong()), titleFont1));
                productTable.addCell(createTableCell(String.valueOf(hoaDonChiTiet.getDonGia()), titleFont1));
                productTable.addCell(createTableCell(String.valueOf(hoaDonChiTiet.getDonGia() * hoaDonChiTiet.getSoLuong()), titleFont1));
                double giaTriSanPham = hoaDonChiTiet.getDonGia() * hoaDonChiTiet.getSoLuong();
                tongTien += giaTriSanPham;
            }
            document.add(productTable);

//
            Paragraph KhoangTrang1 = new Paragraph("                                                         ");
            document.add(KhoangTrang1);

//            Font titleFont2 = new Font(BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 18, Font.BOLD, BaseColor.BLACK);
            Paragraph danhSachTra = new Paragraph("DANH SÁCH SẢN PHẨM KHÁCH ĐỔI", titleFont2);
            danhSachTra.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(danhSachTra);
            Paragraph KhoangTrang2 = new Paragraph("                                                         ");
            document.add(KhoangTrang2);
            PdfPTable productTable1 = new PdfPTable(8);
            productTable1.setWidthPercentage(100);
            float[] columnWidths2 = {4f, 2f, 2f, 2f, 2f, 2f, 4f, 2f}; // Adjusted for 8 columns
            productTable1.setWidths(columnWidths2);
            productTable1.addCell(createTableCell("Tên sản phẩm", titleFont1));
            productTable1.addCell(createTableCell("Màu sắc", titleFont1));
            productTable1.addCell(createTableCell("Kích cỡ", titleFont1));
            productTable1.addCell(createTableCell("Số lượng", titleFont1));
            productTable1.addCell(createTableCell(" Đơn giá ", titleFont1));
            productTable1.addCell(createTableCell("Thành tiền", titleFont1));
            productTable1.addCell(createTableCell("Nhân viên thực hiện", titleFont1));
            productTable1.addCell(createTableCell("Lí do", titleFont1));
            double tongTienTra = 0.0;

            for (DoiTraChiTiet doiTraChiTiet : lstSanPhamDoi) {

                productTable1.addCell(createTableCell(doiTraChiTiet.getChiTietSanPham().getSanPham().getTenSanPham(), titleFont1));
                productTable1.addCell(createTableCell(doiTraChiTiet.getChiTietSanPham().getMauSac().getTen(), titleFont1));
                productTable1.addCell(createTableCell(String.valueOf(doiTraChiTiet.getChiTietSanPham().getKichCo().getSize()), titleFont1));
                productTable1.addCell(createTableCell(String.valueOf(doiTraChiTiet.getSoLuong()), titleFont1));
                if (doiTraChiTiet.getHoaDonChiTiet().getDonGiaKhiGiam() == null || doiTraChiTiet.getHoaDonChiTiet().getDonGiaKhiGiam() == 0) {
                    productTable1.addCell(createTableCell(String.valueOf(doiTraChiTiet.getDonGia()), titleFont1));
                    productTable1.addCell(createTableCell(String.valueOf(doiTraChiTiet.getDonGia() * doiTraChiTiet.getSoLuong()), titleFont1));
                    double giaTriSanPham = doiTraChiTiet.getDonGia() * doiTraChiTiet.getSoLuong();
                    tongTienTra += giaTriSanPham;
                } else if (doiTraChiTiet.getHoaDonChiTiet().getDonGiaKhiGiam() != null || doiTraChiTiet.getHoaDonChiTiet().getDonGiaKhiGiam() != 0) {
                    productTable1.addCell(createTableCell(String.valueOf(doiTraChiTiet.getHoaDonChiTiet().getDonGiaKhiGiam()), titleFont1));
                    productTable1.addCell(createTableCell(String.valueOf(doiTraChiTiet.getHoaDonChiTiet().getDonGiaKhiGiam() * doiTraChiTiet.getSoLuong()), titleFont1));
                    double giaTriSanPham = doiTraChiTiet.getHoaDonChiTiet().getDonGiaKhiGiam() * doiTraChiTiet.getSoLuong();
                    tongTienTra += giaTriSanPham;
                }

                productTable1.addCell(createTableCell(doiTraChiTiet.getHoaDonChiTiet().getHoaDon().getNhanVien().getHo() + " " + doiTraChiTiet.getHoaDonChiTiet().getHoaDon().getNhanVien().getTenDem() + " " + doiTraChiTiet.getHoaDonChiTiet().getHoaDon().getNhanVien().getTen(), titleFont1));
                productTable1.addCell(createTableCell(doiTraChiTiet.getLyDoDoiTra(), titleFont1));

            }

            document.add(productTable1);
//            Paragraph TongCong = new Paragraph("Số tiền hoàn       :    " + tongTienTra + "VNĐ", titleFont2);
//            document.add(TongCong);
//

            document.close();
//
//

            byte[] pdfBytes = baos.toByteArray();
            response.setContentType("application/pdf");
            response.setContentLength(pdfBytes.length);

            // Thay đổi giá trị "inline" thành "attachment"
            // Thay đổi giá trị "inline" thành "attachment"
            response.setHeader("Content-Disposition", "attachment; filename=" + hoaDonDoiTra.getMaHoaDon() + ".pdf");


            response.getOutputStream().write(pdfBytes);
            response.getOutputStream().flush();
            response.getOutputStream().close();
//
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PdfPCell createTableCell(String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }

    @GetMapping("/bumblebee/don-hang/list-sp-km/{id}")
    public ResponseEntity<?> getListByMaSPKM(Model model, @PathVariable("id") UUID id) {
        ChiTietSanPham ctsp = chiTietSanPhamService.getOne(id);
        model.addAttribute("searchDT", new SearchDoiTra());
        this.idCTSP = id;
        List<ChiTietSanPhamCustom> lstSanPham = chiTietSanPhamService.listCTSPKhuyenMai(ctsp.getSanPham().getId());
        return ResponseEntity.ok(lstSanPham);
    }
}