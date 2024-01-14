package com.example.Website_sell_soccer_shoes_bumblebee.controller;


import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChiTietSanPham;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDon;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDonChiTiet;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.ChiTietKhuyenMaiRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.ChiTietSanPhamRepo;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.HoaDonChiTietRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.ChiTietSanPhamService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.HoaDonChiTietService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.HoaDonService;
import com.itextpdf.forms.xfdf.Mode;
import com.itextpdf.text.*;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.text.pdf.*;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.*;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.KhachHangRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.*;


import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@Controller
@EnableScheduling
@RequestMapping("/bumblebee/ban-hang-tai-quay")
public class BanHangTaiQuayController {

    @Autowired
    HoaDonService hoaDonService;

    @Autowired
    ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    HoaDonChiTietService hoaDonChiTietService;

    @Autowired

    ChiTietSanPhamRepo chiTietSanPhamRepo;

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    HttpSession session;

    @Autowired
    NhanVienService nhanVienService;

    @Autowired
    KhachHangService khachHangService;

    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    ChiTietKhuyenMaiRepository chiTietKhuyenMaiRepository;



    private NhanVien nhanVien = null;

    private UUID idHoaDon = null;
    private UUID idHoaDonCT = null;
    private Double sumMoney = 0.0;
    private String nameNhanVien = "";
    private static int maKhachHang = 1;
    private UUID idCTSP = null;
    //    private UUID idHoaDonCT = null;

    //khach hang
    @Autowired
    KhachHangRepository khachHangRepository;

    @ModelAttribute("listKhachHang")
    List<KhachHang> listSP() {
        return khachHangRepository.findAll();
    }

    //ban hang
    @GetMapping("/sell")
    public String banHang(Model model) {
        model.addAttribute("view", "../ban_hang_tai_quay/index.jsp");
        model.addAttribute("listHoaDonCho", hoaDonService.listHoaDonCho());
        this.idHoaDon = null;
        this.sumMoney = 0.0;
//        getTaiKhoan(model);
        model.addAttribute("idHoaDon", idHoaDon);
        model.addAttribute("sumMoney", sumMoney);
        model.addAttribute("khachHang", new KhachHang());
<<<<<<< HEAD
        model.addAttribute("soLuongHD", hoaDonService.listHoaDonCho().size());
=======

        Integer soLuongHDCho = hoaDonService.listHoaDonCho().size();
        model.addAttribute("soLuongHD", soLuongHDCho);


>>>>>>> e5051b62c7e149956cd7a359c00ad43acf60783c
        model.addAttribute("hoaDon", new HoaDon());
        return "ban_hang_tai_quay/ban-hang";
    }

    private void getTaiKhoan(Model model) {
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        nhanVien = nhanVienService.getOne(taiKhoan.getId());
        model.addAttribute("username", taiKhoan.getUsername());
        nameNhanVien = nhanVien.getHo() + " " + nhanVien.getTenDem() + " " + nhanVien.getTen();
        model.addAttribute("fullNameStaff", nameNhanVien);
    }

    @GetMapping("/so-luong-hoa-don-cho")
    public ResponseEntity<?> soLuongHoaDon() {
        Integer soLuongHDC = hoaDonService.listHoaDonCho().size();
        return ResponseEntity.ok(soLuongHDC);
    }

    @RequestMapping("/create-hoadon")
    public String createHoaDon(Model model) throws ParseException {
        model.addAttribute("view", "../ban_hang_tai_quay/index.jsp");
        Integer soLuongHDCho = hoaDonService.listHoaDonCho().size();
        if (soLuongHDCho == 5) {
            model.addAttribute("soLuongHD", soLuongHDCho);
            return "redirect:/bumblebee/ban-hang-tai-quay/sell";
        }
        hoaDonService.createHoaDon();
        return "redirect:/bumblebee/ban-hang-tai-quay/sell";
    }

    @RequestMapping("/delete-hoadon/{id}")
    public String deleteHoaDon(@PathVariable("id") UUID id) {
        List<HoaDonChiTiet> listHoaDonCT = hoaDonChiTietService.getListHoaDonCTByIdHoaDon(id);
        if (listHoaDonCT.size() != 0) {
            for (HoaDonChiTiet hdct : listHoaDonCT) {
                hoaDonChiTietService.deleteHoaDonCT(hdct.getId());
                chiTietSanPhamService.updateDelete(hdct.getChiTietSanPham().getId(), hdct.getSoLuong());
            }
        }
        hoaDonService.deleteHoaDon(id);
        return "redirect:/bumblebee/ban-hang-tai-quay/sell";
    }

<<<<<<< HEAD
//    @GetMapping("/searchSanPham")
//    public String searchSanPham(Model model, @ModelAttribute("searchForm") SearchForm searchForm) {
//        model.addAttribute("view", "../ban_hang_tai_quay/ban-hang.jsp");
//        List<SanPham> danhSachSPSearch = sanPhamService.searchSanPham("" + searchForm.keyword + "");
//        model.addAttribute("listSearch", danhSachSPSearch);
//        return "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDon;
//    }
=======
>>>>>>> e5051b62c7e149956cd7a359c00ad43acf60783c

    @GetMapping("/hoa-don-chi-tiet/{id}")
    public String hoaDonChiTiet(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("view", "../ban_hang_tai_quay/index.jsp");
        model.addAttribute("khachHang", new KhachHang());
        idHoaDon = id;
        model.addAttribute("idHDCT", id);
        model.addAttribute("listHoaDonCho", hoaDonService.listHoaDonCho());
        model.addAttribute("listHDCT", hoaDonChiTietService.getListHoaDonCTByIdHoaDon(id));
        model.addAttribute("idHoaDon", this.idHoaDon);
        model.addAttribute("listKhachHang", khachHangService.getAllKHOderBy());
        getTaiKhoan(model);
        model.addAttribute("hoaDon", hoaDonService.getOne(id));

        List<HoaDonChiTiet> list = hoaDonChiTietService.getListHoaDonCTByIdHoaDon(id);
        sumMoney = hoaDonChiTietService.getTotalMoney(list);
        if (sumMoney < 0) {
            sumMoney = 0.0;
            model.addAttribute("sumMoney", sumMoney);
        } else {
            model.addAttribute("sumMoney", sumMoney);
        }

        model.addAttribute("listMauSac", chiTietSanPhamRepo.listMauSac());
        model.addAttribute("listKC", chiTietSanPhamRepo.listKC());
        model.addAttribute("listLoaiGiay", chiTietSanPhamRepo.listLoaiGiay());
        model.addAttribute("listDeGiay", chiTietSanPhamRepo.listDeGiay());
        model.addAttribute("listChatLieu", chiTietSanPhamRepo.lítChatLieu());
        model.addAttribute("listCTSPByTrangThaiAndSoLuong", chiTietSanPhamRepo.getListCTSPByTrangThaiAndSoLuong());
        idHoaDonCT = id;
        return "ban_hang_tai_quay/ban-hang";
    }

    @RequestMapping("/add-gio-hang/{id}")
    public String themSanPham(Model model, @PathVariable("id") UUID id) {
        try {
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        HoaDon hoaDon = hoaDonService.getOne(idHoaDon);
        this.idCTSP = id;
        ChiTietSanPham sp = chiTietSanPhamService.getOne(id);
        if (sp == null) {
            model.addAttribute("erorSP", "Không tồn tại sản phẩm!");
            System.out.println("Không có sản phẩm nào!!!");
            return "forward:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDonCT;
        }
        HoaDonChiTiet sanPhamInHDCT = hoaDonChiTietService.getAndUpdateSanPhamInHDCT(this.idHoaDon, id);

        if (sanPhamInHDCT != null) {
            Integer soLuong = sp.getSoLuong() - 1;
            chiTietSanPhamService.updateSoLuongTon(id, soLuong);
            hoaDonChiTietService.saveHoaDonCT(hoaDonChiTiet);
            return "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDonCT;
        } else {
            hoaDonChiTiet.setHoaDon(hoaDon);
            hoaDonChiTiet.setChiTietSanPham(sp);
            hoaDonChiTiet.setSoLuong(1);
            hoaDonChiTiet.setDonGia(sp.getGiaBan());
            hoaDonChiTiet.setTrangThai(1);

            if (sp.getCtkm().isEmpty()) {
                hoaDonChiTiet.setDonGiaKhiGiam(0.0);
            } else {
                ChiTietKhuyenMai ctkmNull = null;
                for (ChiTietKhuyenMai ctkm : sp.getCtkm()) {
                    System.out.println("CTKM: " + ctkm.getId());
                    if (ctkm.getTrangThai() == 0) {
                        ctkmNull = ctkm;
                    }
                }
                if (ctkmNull != null) {
                    hoaDonChiTiet.setDonGiaKhiGiam(ctkmNull.getGiaKhuyenMai());
                } else {
                    hoaDonChiTiet.setDonGiaKhiGiam(0.0);
                }
            }

            Integer soLuong = sp.getSoLuong() - 1;
            chiTietSanPhamService.updateSoLuongTon(id, soLuong);
            hoaDonChiTietService.saveHoaDonCT(hoaDonChiTiet);

        }
        return "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDonCT;

        } catch (IllegalArgumentException e) {
            model.addAttribute("erorSP", "Không tồn tại sản phẩm");
            return "forward:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDonCT;
        }
//        return "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDonCT;
    }

    @RequestMapping("/delete-hdct/{id}")
    public String deleteHoaDonCT(Model model, @PathVariable("id") UUID id) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getOneHoaDon(id);
        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getOne(hoaDonChiTiet.getChiTietSanPham().getId());
        chiTietSanPhamService.updateDelete(chiTietSanPham.getId(), hoaDonChiTiet.getSoLuong());
        hoaDonChiTietService.deleteHoaDonCT(id);
        return "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDon;
    }

    @RequestMapping("/update-cart/{id}")
    public String updateSLGioHang(@PathVariable("id") UUID id, int soLuong) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getOneHoaDon(id);
        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getOne(hoaDonChiTiet.getChiTietSanPham().getId());
        Integer soLuongTon = chiTietSanPham.getSoLuong() + hoaDonChiTiet.getSoLuong();
        if (soLuong > soLuongTon) {
            hoaDonChiTiet.setSoLuong(soLuongTon);
            chiTietSanPhamService.updateSoLuongTon(chiTietSanPham.getId(), 0);
            hoaDonChiTietService.saveHoaDonCT(hoaDonChiTiet);
            return "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDon;
        }
        if (soLuong <= 0) {
            hoaDonChiTiet.setSoLuong(1);
            chiTietSanPhamService.updateSoLuongTon(chiTietSanPham.getId(), soLuongTon - 1);
            hoaDonChiTietService.saveHoaDonCT(hoaDonChiTiet);
            return "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDon;
        }
        if (hoaDonChiTiet != null) {
            hoaDonChiTiet.setSoLuong(soLuong);
            chiTietSanPhamService.updateSoLuongTon(chiTietSanPham.getId(), soLuongTon - soLuong);
            hoaDonChiTietService.saveHoaDonCT(hoaDonChiTiet);
        }
        return "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDon;
    }

    @RequestMapping("/thanhtoan/{idHoaDon}")
    public String thanhToan(Model model, HttpServletResponse response, @PathVariable("idHoaDon") UUID id,
                            @Valid @ModelAttribute("hoaDon") HoaDon hoaDon,
                            BindingResult result) throws ParseException, DocumentException, IOException {
        HoaDon hoaDonThanhToan = hoaDonService.getOne(idHoaDon);
        KhachHang khachHang = null;
        String sdt = hoaDon.getSdt();
        if (sdt != "") {
            khachHang = khachHangRepository.findKHBySDT(hoaDon.getSdt());
        }

        if (result.hasErrors()) {
            return "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDon;
        }
//        if (! hoaDon.getSdt().startsWith("0")){
//            model.addAttribute("errorSDT","Số điện thoại bắt đầu bằng 0");
//            return  "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDon;
//        }
//        if (Integer.parseInt(hoaDon.getSdt()) <= 0){
//            model.addAttribute("errorSDT","Số điện thoại lớn hơn 0");
//            return  "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDon;
//        }
        if (hoaDonThanhToan != null) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String format = sdf.format(date);
            hoaDonThanhToan.setNgayThanhToan(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(format));
            hoaDonThanhToan.setNhanVien(nhanVien);
            hoaDonThanhToan.setTrangThai(5);
            hoaDonThanhToan.setSdt(hoaDon.getSdt());

            if (khachHang != null) {
                hoaDonThanhToan.setKhachHang(khachHang);
                hoaDonThanhToan.setTenNguoiNhan(khachHang.getTen());
            } else {
                hoaDonThanhToan.setTenNguoiNhan("Khách vãng lai");
            }
            hoaDonThanhToan.setPhuongThucThanhToan(1);
            hoaDonThanhToan.setGhiChu(hoaDon.getGhiChu());
            hoaDonService.saveHoaDon(hoaDonThanhToan);
            this.sumMoney = 0.0;
            this.idHoaDon = id;

            model.addAttribute("successThanhToan", "Thanh toán thất bại");
//            System.out.println("thất bại");
        } else {
            model.addAttribute("errorThanhToan", "Thanh toan thanh  cong");
            return "redirect:/bumblebee/ban-hang-tai-quay/sell";
        }
        return "redirect:/bumblebee/ban-hang-tai-quay/sell";
    }

    @RequestMapping("/download-pdf/{idHoaDon}")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable("idHoaDon") UUID idHoaDon) throws IOException, DocumentException {

        // Tạo và lưu file PDF trong bộ nhớ
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);
        document.open();
        // Sử dụng font mặc định của iText với bảng mã Unicode
        Font titleFont = new Font(BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 16, Font.BOLD, BaseColor.BLACK);


        HoaDon hoaDonThanhToan = hoaDonService.getOne(idHoaDon);
        List<HoaDonChiTiet> listHoaDon1 = hoaDonChiTietService.getHoaDonTheoHoaDonChiTiet(idHoaDon);


        String qrCodeData = hoaDonThanhToan.getMaHoaDon();
        BarcodeQRCode qrCode = new BarcodeQRCode(qrCodeData, 200, 250, null);
        com.itextpdf.text.Image qrCodeImage = qrCode.getImage();
//
        qrCodeImage.setAbsolutePosition(400, 590);

        document.add(qrCodeImage);

        Font largeFont = new Font(Font.FontFamily.TIMES_ROMAN, 25f, Font.BOLD);
        ////////////// hoá đơn
        Paragraph HoaDon = new Paragraph(" BUMBLEBEE SHOES" + "\n", largeFont);

        HoaDon.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(HoaDon);
        Paragraph khoangTrang = new Paragraph("-");

        khoangTrang.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(khoangTrang);
        Font chutable = new Font(Font.FontFamily.TIMES_ROMAN, 18f);
        //// Table
        Paragraph MaHoaDon = new Paragraph("Mã hoá đơn      :    " + hoaDonThanhToan.getMaHoaDon(), titleFont);

        Paragraph Ngay = new Paragraph("Ngày Mua    :    " + hoaDonThanhToan.getNgayTao(), titleFont);




        Paragraph tennhanvien = new Paragraph("Nhân viên    :    " + nameNhanVien, titleFont);
        document.add(MaHoaDon);
        document.add(Ngay);
        document.add(tennhanvien);

        if (hoaDonThanhToan.getTenNguoiNhan()==null) {
            Paragraph tenKhach = new Paragraph("Khách hàng    :    Khách vãng lai", titleFont);
            document.add(tenKhach);
        } else {
            Paragraph tenKhach = new Paragraph("Khách hàng    :    "+ hoaDonThanhToan.getTenNguoiNhan(), titleFont);


            document.add(tenKhach);



        }





        Paragraph khoangtrang2 = new Paragraph("✿✿✿");
        khoangtrang2.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(khoangtrang2);
        ////
        PdfPTable productTable = new PdfPTable(6);
        productTable.setWidthPercentage(100);
        productTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        float[] columnWidths = {3f, 2f, 1f, 2f, 2f, 2f};
        productTable.setWidths(columnWidths);

        productTable.addCell(createTableCell("Tên sản phẩm", titleFont));
        productTable.addCell(createTableCell("Màu sắc", titleFont));
        productTable.addCell(createTableCell("Size", titleFont));
        productTable.addCell(createTableCell("Số lượng", titleFont));
        productTable.addCell(createTableCell("Giá tiền", titleFont));
        productTable.addCell(createTableCell("Thành tiền", titleFont));

        List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonTheoHoaDonChiTiet(idHoaDon);
        for (HoaDonChiTiet hoaDonChiTiet : listHoaDonChiTiet) {
            productTable.addCell(createTableCell(hoaDonChiTiet.getChiTietSanPham().getSanPham().getTenSanPham(), titleFont));
            productTable.addCell(createTableCell(hoaDonChiTiet.getChiTietSanPham().getMauSac().getTen(), titleFont));
            productTable.addCell(createTableCell(String.valueOf(hoaDonChiTiet.getChiTietSanPham().getKichCo().getSize()), titleFont));
            productTable.addCell(createTableCell(String.valueOf(hoaDonChiTiet.getSoLuong()), titleFont));

            String pattern = "###,###.##";
            DecimalFormat decimalFormat1 = new DecimalFormat(pattern);
            String formattedNumberDonGia = decimalFormat1.format(hoaDonChiTiet.getDonGia());
            String formattedNumberDonGiaKhiGiam = decimalFormat1.format(hoaDonChiTiet.getDonGiaKhiGiam());

            if (hoaDonChiTiet.getDonGiaKhiGiam() == 0 || hoaDonChiTiet.getDonGiaKhiGiam() == null){
                productTable.addCell(createTableCell(formattedNumberDonGia, titleFont));
            } else {
                productTable.addCell(createTableCell(formattedNumberDonGiaKhiGiam, titleFont));
            }



            if (hoaDonChiTiet.getDonGiaKhiGiam() == 0 || hoaDonChiTiet.getDonGiaKhiGiam() == null){
                DecimalFormat decimalFormat2 = new DecimalFormat(pattern);
                String formattedNumberThanhTien = decimalFormat2.format(hoaDonChiTiet.getDonGia() * hoaDonChiTiet.getSoLuong());
                productTable.addCell(createTableCell(formattedNumberThanhTien, titleFont));
            } else {
                DecimalFormat decimalFormat2 = new DecimalFormat(pattern);
                String formattedNumberThanhTien = decimalFormat2.format(hoaDonChiTiet.getDonGiaKhiGiam() * hoaDonChiTiet.getSoLuong());
                productTable.addCell(createTableCell(formattedNumberThanhTien, titleFont));
            }


        }

        document.add(productTable);


        Paragraph dong = new Paragraph("==========================================================================");

        document.add(dong);
        List<HoaDonChiTiet> list = hoaDonChiTietService.getListHoaDonCTByIdHoaDon(idHoaDon);
        sumMoney = hoaDonChiTietService.getTotalMoney(list);
        Double TinhTong = 0.0;

        String pattern = "###,###.##";
        DecimalFormat decimalFormat1 = new DecimalFormat(pattern);
        String formattedNumberSumMoney = decimalFormat1.format(sumMoney);
        Paragraph TongCong = new Paragraph("Tổng Cộng       :    " + formattedNumberSumMoney, titleFont);

        document.add(TongCong);


        Paragraph camOn = new Paragraph("CẢM ƠN QUÝ KHÁCH ĐÃ SỬ DỤNG DỊCH VỤ ", titleFont);
        camOn.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(camOn);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "hoadon_" + hoaDonThanhToan.getMaHoaDon() + ".pdf");

        // Trả về dữ liệu PDF
        document.close();
        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
    }

    private PdfPCell createTableCell(String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }


    //    @RequestMapping("/them-khach-hang")
//    public String themKhachHang(Model model, @Valid @ModelAttribute("khachHang") KhachHang khachHang, BindingResult result) {
//        KhachHang addKhachHang = new KhachHang();
//
//        String formatKH = "KH" + String.format("%07d", maKhachHang);
//        KhachHang checkMa = khachHangService.searchKhachHang(formatKH);
//        if (checkMa != null) {
//            String maKHMax = khachHangService.searchMaxKH();
//            maKhachHang = Integer.valueOf(maKHMax.substring(2));
//            maKhachHang++;
//            String formatSoMa = "KH" + String.format("%07d", maKhachHang);
//            addKhachHang.setMa(formatSoMa);
//        } else {
//            addKhachHang.setMa(formatKH);
//        }
//        addKhachHang.setTen(khachHang.getTen());
//        addKhachHang.setSoDienThoai(khachHang.getSoDienThoai());
//        addKhachHang.setTrangThai(5);
//        khachHangService.saveKhachHang(addKhachHang);
//        return "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDon;
//    }
    private List<String> getErrors(BindingResult result) {
        List<String> errors = new ArrayList<>();
        result.getFieldErrors().forEach(error -> errors.add(error.getField() + ": " + error.getDefaultMessage()));
        return errors;
    }

    @RequestMapping("/them-khach-hang")
    @ResponseBody
    public Map<String, Object> themKhachHang(Model model, @Validated @ModelAttribute("khachHang") KhachHang khachHang, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        // Kiểm tra lỗi validation
        if (result.hasErrors()) {
            response.put("status", "error");
            response.put("errors", getErrors(result));
            return response;
        }
//        String formatKH = "KH" + String.format("%07d", maKhachHang);
//        KhachHang checkMa = khachHangService.searchKhachHang(formatKH);
//        // Xử lý logic thêm khách hàng
//        if (checkMa != null) {
//            String maKHMax = khachHangService.searchMaxKH();
//            maKhachHang = Integer.valueOf(maKHMax.substring(2));
//            maKhachHang++;
//            String formatSoMa = "KH" + String.format("%07d", maKhachHang);
//            khachHang.setMa(formatSoMa);
//        } else {
//            khachHang.setMa(formatKH);
//        }
        String maKhachHang = khachHangService.generateMaKhachHang();
        khachHang.setMa(maKhachHang);
        // Kiểm tra trùng số điện thoại
        if (khachHangRepository.findKHBySDT(khachHang.getSoDienThoai()) != null) {
            result.rejectValue("soDienThoai", "duplicate", "Lỗi! Số điện thoại đã tồn tại!");
            response.put("status", "error");
            response.put("errors", getErrors(result));
            response.put("field", "soDienThoai");
            return response;
        }

        // Lưu thông tin khách hàng
        khachHang.setTrangThai(5);
        khachHangService.saveKhachHang(khachHang);
        model.addAttribute("idHoaDon", this.idHoaDon);
        response.put("status", "success");
        return response;

//     public String themKhachHang(Model model, @ModelAttribute("khachHang") KhachHang khachHang) {
//         KhachHang addKhachHang = new KhachHang();

//         Random  random = new Random();
//         addKhachHang.setMa("KH" + random.nextInt(999999999));
//         addKhachHang.setTen(khachHang.getTen());
//         addKhachHang.setSoDienThoai(khachHang.getSoDienThoai());
//         addKhachHang.setTrangThai(5);
//         khachHangService.saveKhachHang(addKhachHang);
//         return "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDon;

    }

    @RequestMapping("/print/{id}")
    public void xuatPDF() throws IOException, DocumentException, PrinterException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("example.pdf"));
        document.open();

        document.close();
        PDDocument documentPrint = PDDocument.load(new File("example.pdf"));
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(new PDFPageable(documentPrint));
        if (job.printDialog()) {
            job.print();
        }


    }
}
