package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.config.PaypalPaymentIntent;
import com.example.Website_sell_soccer_shoes_bumblebee.config.PaypalPaymentMethod;
import com.example.Website_sell_soccer_shoes_bumblebee.dto.ChiTietSanPhamDto;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.*;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.ChiTietSanPhamRepo;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.GioHangChiTietRepo;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.GioHangRepo;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.HinhAnhRepository;

import com.example.Website_sell_soccer_shoes_bumblebee.service.ChiTietSanPhamService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.KichCoService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.LoaiGiayService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.MauSacService;

import com.example.Website_sell_soccer_shoes_bumblebee.repository.KhachHangRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.*;
import com.itextpdf.forms.xfdf.Mode;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.AbstractPersistable_;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;


import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    LoaiGiayService loaiGiayService;

    @Autowired
    KichCoService kichCoService;

    @Autowired
    MauSacService mauSacService;

    @Autowired
    ChiTietSanPhamRepo chiTietSanPhamRepo;

    @Autowired
    GioHangRepo gioHangRepo;

    @Autowired
    GioHangChiTietRepo gioHangChiTietRepo;

    @Autowired
    GioHangChiTietService gioHangChiTietService;

    @Autowired
    KhachHangRepository khachHangRepository;

    @Autowired
    KhachHangService khachHangService;

    @Autowired
    HoaDonService hoaDonService;


    @Data
    public static class SortForm {
        String key;
    }

    @Autowired
    HinhAnhRepository hinhAnhRepository;

    @Autowired
    HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private PayPalService service;

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

    @Data
    public static class SearchFormByKichCo {
        Double key;
    }

    @Data
    public static class SearchFormByGiaBan {
        Double minPrice;
        Double maxPrice;
    }


    @RequestMapping("/bumblebee/select-slsp")
    public ResponseEntity<String> getSLBySize(@RequestParam UUID idSP, @RequestParam UUID idMS, @RequestParam String size) {
        String sl = chiTietSanPhamService.getSoLuongByKichCo(idMS, idSP, size);
        return ResponseEntity.ok(sl);
    }

    @GetMapping("/bumblebee/select-size")
    public ResponseEntity<List<Integer>> getKichCoByItemId(@RequestParam UUID idSP, @RequestParam UUID idMS, Model model) {
        List<Integer> kichCoList = chiTietSanPhamService.getKichCoByMauSacAndSanPham(idMS, idSP);
        return ResponseEntity.ok(kichCoList);
    }

    private List<GioHangChiTiet> listGHCT = null;
    private List<UUID> idCartUUIDList = null;
    //private Double totalPrice = 0.0;

    @RequestMapping("/bumblebee/home")
    public String home(Model model, @RequestParam(defaultValue = "0") int p, HttpSession session) {
        Pageable pageable = PageRequest.of(p, 8);
        Page<ChiTietSanPham> listSP = chiTietSanPhamRepo.get1CTSPByMauSac(pageable);
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        Integer slGioHang = chiTietSanPhamService.getSLGioHang(taiKhoan.getKhachHangKH().getId());
        model.addAttribute("slGioHang", slGioHang);
        model.addAttribute("listSP", listSP);
        model.addAttribute("view", "../template_home/home.jsp");
        return "template_home/index";
    }

    @PostMapping("/bumblebee/update-cart")
    public Map<String, Object> updateCart(@RequestBody Map<String, Object> requestData, @RequestParam UUID idGHCT) {
        int soLuongMoi = Integer.parseInt(requestData.get("soLuong").toString());
        GioHangChiTiet gioHangChiTiet = gioHangChiTietRepo.getOne(idGHCT);
        gioHangChiTiet.setSoLuong(soLuongMoi);
        gioHangChiTietRepo.save(gioHangChiTiet);
        double thanhTienMoi = soLuongMoi * gioHangChiTiet.getDonGia();
        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("soLuong", soLuongMoi);
        jsonResponse.put("thanhTien", thanhTienMoi);
        return jsonResponse;
    }

    @RequestMapping("/bumblebee/detail")
    public String detail(Model model, @RequestParam UUID idSP, @RequestParam UUID idCTSP, @RequestParam UUID idMS, HttpSession session) {
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        Integer slGioHang = chiTietSanPhamService.getSLGioHang(taiKhoan.getKhachHangKH().getId());
        model.addAttribute("slGioHang", slGioHang);
        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getOne(idCTSP);
        List<KichCo> listKC = kichCoService.getList();
        List<Integer> listKCBySP = chiTietSanPhamService.getKichCoByMauSacAndSanPham(idMS, idSP);
        HinhAnh hinhAnh = chiTietSanPhamRepo.getHADetail(idCTSP);
        model.addAttribute("hinhAnh", hinhAnh);
        List<ChiTietSanPham> listSP = chiTietSanPhamService.getList();
        List<MauSac> listMS = mauSacService.getAll();
        model.addAttribute("listMS", listMS);
        model.addAttribute("listSP", listSP);
        model.addAttribute("listKC", listKCBySP);
        model.addAttribute("ctsp", chiTietSanPham);
        model.addAttribute("view", "../template_home/product_detail.jsp");
        return "template_home/index";
    }

    @RequestMapping("/bumblebee/cart")
    public String cart(Model model, HttpSession session) {
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");

        Integer slGioHang = chiTietSanPhamService.getSLGioHang(taiKhoan.getKhachHangKH().getId());
        model.addAttribute("slGioHang", slGioHang);
        GioHang gioHang = gioHangRepo.getGioHang(taiKhoan.getKhachHangKH().getId());
        List<GioHangChiTiet> listGHCT = gioHangRepo.getGioHangChiTiet(gioHang.getId());
        model.addAttribute("listGHCT", listGHCT);
        model.addAttribute("view", "../template_home/cart.jsp");
        return "template_home/index";

        if (taiKhoan == null) {
            return "redirect:/bumblebee/login";
        } else {
            GioHang gioHang = gioHangRepo.getGioHang(taiKhoan.getKhachHangKH().getId());
            List<GioHangChiTiet> listGHCT = gioHangRepo.getGioHangChiTiet(gioHang.getId());
            model.addAttribute("listGHCT", listGHCT);
            model.addAttribute("totalPrice", gioHangChiTietService.getTotalMoney(listGHCT));
            model.addAttribute("view", "../template_home/cart.jsp");
            return "template_home/index";
        }

    }

    @RequestMapping("/bumblebee/remove-ghct/{id}")
    public String removeGHCT(@PathVariable UUID id) {
        gioHangChiTietRepo.deleteById(id);
        return "redirect:/bumblebee/cart";
    }


    @RequestMapping("/bumblebee/add-to-cart")
    public String addCart(Model model,
                          @RequestParam int kichCo,
                          @RequestParam UUID idMS,
                          @RequestParam UUID idSP,
                          @RequestParam String soLuong,
                          HttpSession session) {
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");

        GioHang gioHang = gioHangRepo.getGioHang(taiKhoan.getKhachHangKH().getId());
        List<GioHangChiTiet> listGHCT = gioHangRepo.getGioHangChiTiet(gioHang.getId());
        for (GioHangChiTiet gioHangChiTiet : listGHCT) {
            if (gioHangChiTiet.getCtsp().getId().equals(ctsp.getId())) {
                int soLuongHienTai = gioHangChiTiet.getSoLuong();
                int slThem = Integer.parseInt(soLuong);
                int slUpdate = soLuongHienTai + slThem;
                gioHangChiTiet.setSoLuong(slUpdate);
                gioHangChiTietRepo.save(gioHangChiTiet);
                return "redirect:/bumblebee/cart";
            }

        if (taiKhoan == null) {
            return "redirect:/bumblebee/login";
        } else {
            KichCo size = chiTietSanPhamRepo.getKichCoBySize(kichCo);
            ChiTietSanPham ctsp = chiTietSanPhamService.findCTSPAddCart(idSP, idMS, size.getId());
            GioHang gioHang = gioHangRepo.getGioHang(taiKhoan.getKhachHangKH().getId());
            List<GioHangChiTiet> listGHCT = gioHangRepo.getGioHangChiTiet(gioHang.getId());
            for (GioHangChiTiet gioHangChiTiet : listGHCT) {
                if (gioHangChiTiet.getCtsp().getId().equals(ctsp.getId())) {
                    int soLuongHienTai = gioHangChiTiet.getSoLuong();
                    int slThem = Integer.parseInt(soLuong);
                    int slUpdate = soLuongHienTai + slThem;
                    gioHangChiTiet.setSoLuong(slUpdate);
                    gioHangChiTietRepo.save(gioHangChiTiet);      
                    return "redirect:/bumblebee/cart";
                }
            }
            int sl = Integer.parseInt(soLuong);
            GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
            gioHangChiTiet.setCtsp(ctsp);
            gioHangChiTiet.setGioHang(gioHang);
            gioHangChiTiet.setDonGia(ctsp.getGiaBan());
            gioHangChiTiet.setSoLuong(sl);
            gioHangChiTietRepo.save(gioHangChiTiet);
            List<ChiTietSanPham> listCTSP = chiTietSanPhamService.getList();
            model.addAttribute("listGHCT", listGHCT);
            model.addAttribute("listCTSP", listCTSP);

            model.addAttribute("view", "../template_home/cart.jsp");
            return "redirect:/bumblebee/cart";
      
    }

    @RequestMapping("/bumblebee/thanh-toan")
    public String thanhToan(Model model, @RequestParam(name = "idListCartDetail", required = false) String idListCartDetail,
                            @ModelAttribute("hoadon") HoaDon hoadon, HttpSession session
    ) {
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        Integer slGioHang = chiTietSanPhamService.getSLGioHang(taiKhoan.getKhachHangKH().getId());
        model.addAttribute("slGioHang", slGioHang);
        if (taiKhoan == null) {
            return "redirect:/bumblebee/login";
        } else {
            model.addAttribute("listKH", taiKhoan.getKhachHangKH());

            if (idListCartDetail == null) {
                return "redirect:/bumblebee/cart";
            } else {

                // Lấy list idctsp
                idCartUUIDList = Arrays.asList(idListCartDetail.split(","))
                        .stream()
                        .map(UUID::fromString)
                        .collect(Collectors.toList());
                listGHCT = new ArrayList<>();
                for (UUID id : idCartUUIDList) {
                    GioHangChiTiet ghct = gioHangChiTietService.findId(id);
                    listGHCT.add(ghct);
                }
        model.addAttribute("listKH", taiKhoan.getKhachHangKH());

            //totalPrice = gioHangChiTietService.getTotalMoney(listGHCT);
            model.addAttribute("listGHCT", listGHCT);
            model.addAttribute("totalPrice", gioHangChiTietService.getTotalMoney(listGHCT));
            model.addAttribute("view", "../template_home/thanhtoan.jsp");

            return "template_home/index";
        }


    }

    private HoaDon hoaDon;

    @RequestMapping("/bumblebee/dat-hang")
    public String datHang(
            Model model,
            HttpSession session,
            //@ModelAttribute("hoadon") HoaDon hoaDon,
            @RequestParam(name = "tenNguoiNhan") String tenNguoiNhan,
            @RequestParam(name = "sdt") String sdt,
            @RequestParam(name = "diaChiShip") String diaChiShip,
            @RequestParam(name = "ghiChu") String ghiChu,
            @RequestParam(name = "payment") Integer payment
    ) throws ParseException {
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        Integer slGioHang = chiTietSanPhamService.getSLGioHang(taiKhoan.getKhachHangKH().getId());
        model.addAttribute("slGioHang", slGioHang);
        KhachHang kh = khachHangService.findId(taiKhoan.getKhachHangKH().getId());

        // Tạo hóa đơn
        hoaDon = new HoaDon();
        Random random = new Random();
        hoaDon.setMaHoaDon("HĐ-" + random.nextInt());
        hoaDon.setNgayTao(new Date());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String format = sdf.format(date);
        hoaDon.setKhachHang(kh);
        hoaDon.setNgayTao(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(format));
        hoaDon.setNgayThanhToan(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(format));
        hoaDon.setTenNguoiNhan(tenNguoiNhan);
        hoaDon.setSdt(sdt);
        hoaDon.setDiaChiShip(diaChiShip);
        hoaDon.setGhiChu(ghiChu);
        hoaDon.setLoaiHoaDon(1);
        if (payment.intValue() == 2) {
            return "redirect:/pay";
        } else {
            hoaDon.setPhuongThucThanhToan(1);
            hoaDon.setTrangThai(1);
            hoaDonService.saveHoaDon(hoaDon);
        }

        // Tạo hóa đơn chi tiết
        for (GioHangChiTiet ghct : listGHCT) {
            HoaDonChiTiet hdct = new HoaDonChiTiet();
            hdct.setHoaDon(hoaDon);
            hdct.setChiTietSanPham(ghct.getCtsp());
            hdct.setSoLuong(ghct.getSoLuong());
            hdct.setDonGia(ghct.getDonGia());
            hdct.setTrangThai(3);
            hoaDonChiTietService.save(hdct);

            ChiTietSanPham ctsp = chiTietSanPhamService.getOne(ghct.getCtsp().getId());
            ctsp.setSoLuong(ghct.getCtsp().getSoLuong() - ghct.getSoLuong());
            chiTietSanPhamRepo.save(ctsp);
            //gioHangChiTietService.deleteGHCT(ghct.getId());
        }

        return "redirect:/bumblebee/bill/" + hoaDon.getId();
//        return "redirect:/pay";
    }

//    @GetMapping("/pay")
//    public String home(Model model) {
//        Double price = gioHangChiTietService.getTotalMoney(listGHCT);
//        model.addAttribute("price", price);
//        return "/paypal/paypal";
//    }

    // THANH TOÁN PAYPAL
    @RequestMapping("/pay")
    public String pay(HttpServletRequest request
    ) {
        String cancelUrl = "http://localhost:8080/" + CANCEL_URL;
        String successUrl = "http://localhost:8080/" + SUCCESS_URL;
        Double price = gioHangChiTietService.getTotalMoney(listGHCT);
        try {
            Payment payment = service.createPayment(
                    price,
                    "USD",
                    PaypalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    "payment description",
                    cancelUrl,
                    successUrl);
            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    return "redirect:" + links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping(SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                hoaDon.setPhuongThucThanhToan(2);
                hoaDon.setTrangThai(1);
                hoaDonService.saveHoaDon(hoaDon);
                // Tạo hóa đơn chi tiết
                for (GioHangChiTiet ghct : listGHCT) {
                    HoaDonChiTiet hdct = new HoaDonChiTiet();
                    hdct.setHoaDon(hoaDon);
                    hdct.setChiTietSanPham(ghct.getCtsp());
                    hdct.setSoLuong(ghct.getSoLuong());
                    hdct.setDonGia(ghct.getDonGia());
                    hdct.setTrangThai(3);
                    hoaDonChiTietService.save(hdct);
                    //gioHangChiTietService.deleteGHCT(ghct.getId());
                }
                return "redirect:/bumblebee/bill/" + hoaDon.getId();
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }


    @RequestMapping("/bumblebee/bill/{id}")
    public String bill(Model model, @PathVariable(name = "id") UUID id) {
        model.addAttribute("listHD", hoaDonService.getId(id));
        model.addAttribute("totalPrice", gioHangChiTietService.getTotalMoney(listGHCT));
        model.addAttribute("view", "../template_home/bill.jsp");
        return "template_home/index";
    }

    @RequestMapping("/bumblebee/don-mua")
    public String donMua(Model model, HttpSession session) {

        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        model.addAttribute("listHoaDonMua", hoaDonService.listHoaDonMua(taiKhoan.getKhachHangKH().getId()));
        model.addAttribute("view", "../don_mua/don_mua.jsp");
        return "template_home/index";
    }

    @RequestMapping("/bumblebee/don-mua/{id}")
    public String donMuaChiTet(Model model, HttpSession session, @PathVariable(name = "id") UUID id) {

        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        List<HoaDonChiTiet> list = hoaDonChiTietService.getListHoaDonCTByIdHoaDon(id);
        Double sumMoney = hoaDonChiTietService.getTotalMoney(list);
        model.addAttribute("sumMoney", sumMoney);
        model.addAttribute("id", id);
        model.addAttribute("hoaDon", hoaDonService.getOne(id));
        model.addAttribute("listHoaDonChiTiet", hoaDonChiTietService.getHoaDonById(id));
        model.addAttribute("view", "../don_mua/don_mua_chi_tiet.jsp");
        return "template_home/index";
    }

    @RequestMapping("/bumblebee/don-mua/cho-xac-nhan")
    public String donChoThanhToan(Model model, HttpSession session) {

        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        model.addAttribute("listHoaDonMua", hoaDonService.listHoaDonChoThanhToan(taiKhoan.getKhachHangKH().getId()));
        model.addAttribute("view", "../don_mua/don_mua.jsp");
        return "template_home/index";
    }

    @RequestMapping("/bumblebee/don-mua/dang-chuan-bi")
    public String donDangChuanBi(Model model, HttpSession session) {

        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        model.addAttribute("listHoaDonMua", hoaDonService.listHoaDonDangChuanBi(taiKhoan.getKhachHangKH().getId()));
        model.addAttribute("view", "../don_mua/don_mua.jsp");
        return "template_home/index";
    }

    @RequestMapping("/bumblebee/don-mua/dang-giao")
    public String donDangGiao(Model model, HttpSession session) {

        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        model.addAttribute("listHoaDonMua", hoaDonService.listHoaDonDangGiao(taiKhoan.getKhachHangKH().getId()));
        model.addAttribute("view", "../don_mua/don_mua.jsp");
        return "template_home/index";
    }

    @RequestMapping("/bumblebee/don-mua/hoan-thanh")
    public String donHoanThanh(Model model, HttpSession session) {

        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        model.addAttribute("listHoaDonMua", hoaDonService.listHoaDonHoanThanh(taiKhoan.getKhachHangKH().getId()));
        model.addAttribute("view", "../don_mua/don_mua.jsp");
        return "template_home/index";
    }

    @RequestMapping("/bumblebee/don-mua/da-huy")
    public String donDaHuy(Model model, HttpSession session) {

        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        model.addAttribute("listHoaDonMua", hoaDonService.listHoaDonDaHuy(taiKhoan.getKhachHangKH().getId()));
        model.addAttribute("view", "../don_mua/don_mua.jsp");
        return "template_home/index";
    }

    @RequestMapping("/bumblebee/don-mua/tra-hang")
    public String donTraHang(Model model, HttpSession session) {

        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        model.addAttribute("listHoaDonMua", hoaDonService.listHoaDonTraHang(taiKhoan.getKhachHangKH().getId()));
        model.addAttribute("view", "../don_mua/don_mua.jsp");
        return "template_home/index";
    }

    @RequestMapping("/bumblebee/don-mua/da-hoan-tra")
    public String donDaHoanTra(Model model, HttpSession session) {

        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        model.addAttribute("listHoaDonMua", hoaDonService.listHoaDonDaHoanTra(taiKhoan.getKhachHangKH().getId()));
        model.addAttribute("view", "../don_mua/don_mua.jsp");
        return "template_home/index";
    }


    @RequestMapping("/bumblebee/product_list/sort")
    public String sort(Model model, @RequestParam(defaultValue = "0") int p,
                       @ModelAttribute("sortForm") SortForm sortForm,
                       @ModelAttribute("searchFormByGiaban") SearchFormByGiaBan searchFormByGiaBan,
                       HttpSession session) {
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        Integer slGioHang = chiTietSanPhamService.getSLGioHang(taiKhoan.getKhachHangKH().getId());
        model.addAttribute("slGioHang", slGioHang);
        Sort sort = Sort.by(Sort.Direction.ASC, sortForm.key);
        Pageable pageable = PageRequest.of(p, 12, sort);
        Page<ChiTietSanPham> pageCTSP = chiTietSanPhamService.getListSP(pageable);
        List<ChiTietSanPham> listSP = chiTietSanPhamService.getList();
        List<LoaiGiay> listLG = loaiGiayService.findAll();
        List<KichCo> listKC = kichCoService.getList();
        List<MauSac> listMS = mauSacService.getAll();
        model.addAttribute("listSP", listSP);
        model.addAttribute("pageSP", pageCTSP);
        model.addAttribute("listLG", listLG);
        model.addAttribute("listKC", listKC);
        model.addAttribute("listMS", listMS);
        model.addAttribute("view", "../template_home/product_listing.jsp");
        return "template_home/index";
    }

    @RequestMapping("/bumblebee/product_list")
    public String product_list(Model model, @RequestParam(defaultValue = "0") int p,
                               @ModelAttribute("sortForm") SortForm sortForm,
                               @ModelAttribute("searchFormByGiaban") SearchFormByGiaBan searchFormByGiaBan,
                               HttpSession session) {
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        Integer slGioHang = chiTietSanPhamService.getSLGioHang(taiKhoan.getKhachHangKH().getId());
        model.addAttribute("slGioHang", slGioHang);
        Pageable pageable = PageRequest.of(p, 12);
        Page<ChiTietSanPham> pageCTSP = chiTietSanPhamService.getListSP(pageable);
        Page<ChiTietSanPham> listSP = chiTietSanPhamRepo.get1CTSPByMauSac(pageable);
        //    List<ChiTietSanPhamDto> pageCTSP = chiTietSanPhamService.getListChiTietSanPhamHinhAnh();
        List<LoaiGiay> listLG = loaiGiayService.findAll();
        List<KichCo> listKC = kichCoService.getList();
        List<MauSac> listMS = mauSacService.getAll();
        model.addAttribute("pageSP", listSP);
        // model.addAttribute("pageSP", pageCTSP);
        model.addAttribute("listLG", listLG);
        model.addAttribute("listKC", listKC);
        model.addAttribute("listMS", listMS);
        model.addAttribute("view", "../template_home/product_listing.jsp");
        return "template_home/index";
    }

    @RequestMapping("/bumblebee/product_list/searchbygiaban")
    public String getListByGiaBan(Model model, @RequestParam(defaultValue = "0") int p,
                                  @ModelAttribute("sortForm") SortForm sortForm,
                                  @ModelAttribute("searchFormByGiaban") SearchFormByGiaBan searchFormByGiaBan) {
        System.out.println(searchFormByGiaBan.minPrice + "-" + searchFormByGiaBan.maxPrice);
        Pageable pageable = PageRequest.of(p, 12);
        Page<ChiTietSanPham> pageCTSPByGia = chiTietSanPhamService.getCTSPByGiaBan(searchFormByGiaBan.minPrice, searchFormByGiaBan.maxPrice, pageable);
        List<ChiTietSanPham> listSP = chiTietSanPhamService.getList();
        List<LoaiGiay> listLG = loaiGiayService.findAll();
        List<KichCo> listKC = kichCoService.getList();
        List<MauSac> listMS = mauSacService.getAll();
        model.addAttribute("listSP", listSP);
        model.addAttribute("pageSP", pageCTSPByGia);
        model.addAttribute("listLG", listLG);
        model.addAttribute("listKC", listKC);
        model.addAttribute("listMS", listMS);
        model.addAttribute("view", "../template_home/product_listing.jsp");
        return "template_home/index";
    }

    @RequestMapping("/bumblebee/product_list/searchbykichco/{id}")
    public String getListByKichCo(Model model, @RequestParam(defaultValue = "0") int p,
                                  @ModelAttribute("sortForm") SortForm sortForm,
                                  @ModelAttribute("searchFormByGiaban") SearchFormByGiaBan searchFormByGiaBan,
                                  @PathVariable UUID id,
                                  HttpSession session) {
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        Integer slGioHang = chiTietSanPhamService.getSLGioHang(taiKhoan.getKhachHangKH().getId());
        model.addAttribute("slGioHang", slGioHang);
        KichCo kichCo = kichCoService.getOne(id);
        System.out.println(kichCo);
        Pageable pageable = PageRequest.of(p, 12);
        Page<ChiTietSanPham> pageCTSPByKichCo = chiTietSanPhamService.getCTSPByKC(kichCo.getId(), pageable);

        System.out.println("aa");
        List<LoaiGiay> listLG = loaiGiayService.findAll();
        List<KichCo> listKC = kichCoService.getList();
        List<MauSac> listMS = mauSacService.getAll();
        model.addAttribute("pageSP", pageCTSPByKichCo);
        model.addAttribute("listLG", listLG);
        model.addAttribute("listKC", listKC);
        model.addAttribute("listMS", listMS);
        model.addAttribute("view", "../template_home/product_listing.jsp");
        return "template_home/index";
    }

    @RequestMapping("/bumblebee/product_list/searchbymausac")
    public String getListByMauSac(Model model, @RequestParam(defaultValue = "0") int p,
                                  @ModelAttribute("sortForm") SortForm sortForm,
                                  @ModelAttribute("searchFormByGiaban") SearchFormByGiaBan searchFormByGiaBan,
                                  @RequestParam UUID idMS,
                                  HttpSession session
    ) {
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        Integer slGioHang = chiTietSanPhamService.getSLGioHang(taiKhoan.getKhachHangKH().getId());
        model.addAttribute("slGioHang", slGioHang);
        Pageable pageable = PageRequest.of(p, 12);
        Page<ChiTietSanPham> listCTSPBYMS = chiTietSanPhamService.getCTSPByMS(idMS, pageable);
        List<LoaiGiay> listLG = loaiGiayService.findAll();
        List<KichCo> listKC = kichCoService.getList();
        List<MauSac> listMS = mauSacService.getAll();
        model.addAttribute("pageSP", listCTSPBYMS);
        model.addAttribute("listLG", listLG);
        model.addAttribute("listKC", listKC);
        model.addAttribute("listMS", listMS);
        model.addAttribute("view", "../template_home/product_listing.jsp");
        return "template_home/index";
    }

    @RequestMapping("/bumblebee/product_list/searchbyloaigiay")
    public String getListByLoaiGiay(Model model,
                                    @RequestParam(defaultValue = "0") int p,
                                    @ModelAttribute("sortForm") SortForm sortForm,
                                    @ModelAttribute("searchFormByGiaban") SearchFormByGiaBan searchFormByGiaBan,
                                    @RequestParam(name = "idLoaiGiayList", required = false) String idLoaiGiayList,
                                    HttpSession session
    ) {
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        Integer slGioHang = chiTietSanPhamService.getSLGioHang(taiKhoan.getKhachHangKH().getId());
        model.addAttribute("slGioHang", slGioHang);
        if ("all".equals(idLoaiGiayList)) {

            Pageable pageable = PageRequest.of(p, 12);
            Page<ChiTietSanPham> pageSP = chiTietSanPhamService.getListSP(pageable);
            List<LoaiGiay> listLG = loaiGiayService.findAll();
            List<KichCo> listKC = kichCoService.getList();
            List<MauSac> listMS = mauSacService.getAll();

            model.addAttribute("pageSP", pageSP);
            model.addAttribute("listLG", listLG);
            model.addAttribute("listKC", listKC);
            model.addAttribute("listMS", listMS);
            model.addAttribute("view", "../template_home/product_listing.jsp");
        } else {
            List<UUID> idLoaiGiayUUIDList = Arrays.asList(idLoaiGiayList.split(","))
                    .stream()
                    .map(UUID::fromString)
                    .collect(Collectors.toList());

            Pageable pageable = PageRequest.of(p, 12);
            Page<ChiTietSanPham> listCTSPByLoaiGiay = chiTietSanPhamService.searchCTSPByLoaiGiayList(idLoaiGiayUUIDList, pageable);
            List<LoaiGiay> listLG = loaiGiayService.findAll();
            List<KichCo> listKC = kichCoService.getList();
            List<MauSac> listMS = mauSacService.getAll();

            model.addAttribute("pageSP", listCTSPByLoaiGiay);
            model.addAttribute("listLG", listLG);
            model.addAttribute("listKC", listKC);
            model.addAttribute("listMS", listMS);
            model.addAttribute("view", "../template_home/product_listing.jsp");
        }

        return "template_home/index";
    }
}
