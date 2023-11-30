package com.example.Website_sell_soccer_shoes_bumblebee.controller;


import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChiTietSanPham;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDon;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HoaDonChiTiet;
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
import lombok.Getter;
import lombok.Setter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
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


    @Getter
    @Setter
    public static class SearchForm {
        String keyword = "";
    }

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
        getTaiKhoan(model);
        model.addAttribute("idHoaDon", idHoaDon);
        model.addAttribute("sumMoney", sumMoney);
        model.addAttribute("khachHang", new KhachHang());

        Integer soLuongHDCho = hoaDonService.listHoaDonCho().size();
        model.addAttribute("soLuongHD", soLuongHDCho);

        model.addAttribute("searchForm", new SearchForm());
//        model.addAttribute("sumMoney", sumMoney);

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
            for(HoaDonChiTiet hdct : listHoaDonCT){
                hoaDonChiTietService.deleteHoaDonCT(hdct.getId());
                chiTietSanPhamService.updateDelete(hdct.getChiTietSanPham().getId(),hdct.getSoLuong());
            }
        }
        hoaDonService.deleteHoaDon(id);
        return "redirect:/bumblebee/ban-hang-tai-quay/sell";
    }

    @GetMapping("/searchSanPham")
    public String searchSanPham(Model model, @ModelAttribute("searchForm") SearchForm searchForm) {
        model.addAttribute("view", "../ban_hang_tai_quay/ban-hang.jsp");
        List<SanPham> danhSachSPSearch = sanPhamService.searchSanPham("" + searchForm.keyword + "");
        model.addAttribute("listSearch", danhSachSPSearch);
        return "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDon;
    }

    @GetMapping("/hoa-don-chi-tiet/{id}")
    public String hoaDonChiTiet(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("view", "../ban_hang_tai_quay/index.jsp");
        model.addAttribute("khachHang", new KhachHang());
        idHoaDon = id;
        model.addAttribute("searchForm", new SearchForm());
        model.addAttribute("listHoaDonCho", hoaDonService.listHoaDonCho());
        model.addAttribute("listSanPham", chiTietSanPhamService.listCTSPSuDung());
        model.addAttribute("listSanPham", chiTietSanPhamService.getList());
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

        idHoaDonCT = id;
        return "ban_hang_tai_quay/ban-hang";
    }

    @RequestMapping("/add-gio-hang/{id}")
    public String themSanPham(Model model, @PathVariable("id") UUID id) {
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        HoaDon hoaDon = hoaDonService.getOne(idHoaDon);
//        if (idHoaDon == null) {
//            return "redirect:/bumblebee/ban-hang-tai-quay/sell";
//        }
        this.idCTSP = id;
        ChiTietSanPham sp = chiTietSanPhamService.getOne(id);
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
            if (sp.getCtkm() != null) {
                Double donGiaKhiGiam = hoaDonChiTietService.getDonGiaKhiGiam(sp.getCtkm());
                hoaDonChiTiet.setDonGiaKhiGiam(donGiaKhiGiam);
            } else {
                hoaDonChiTiet.setDonGiaKhiGiam(0.0);
            }
            Integer soLuong = sp.getSoLuong() - 1;
            chiTietSanPhamService.updateSoLuongTon(id, soLuong);
            hoaDonChiTietService.saveHoaDonCT(hoaDonChiTiet);
        }
        return "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDonCT;
    }

    @RequestMapping("/delete-hdct/{id}")
    public String deleteHoaDonCT(Model model, @PathVariable("id") UUID id) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getOneHoaDon(id);
//        Integer soLuong = hoaDonChiTiet.getSoLuong();
//        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getOne(hoaDonChiTiet.getChiTietSanPham().getId());
//        Integer soLuongTon = chiTietSanPham.getSoLuong();
//        chiTietSanPhamService.updateSoLuongTon(chiTietSanPham.getId(), soLuongTon + soLuong);
//        hoaDonChiTietService.deleteHoaDonCT(id);
//        Integer soLuong = hoaDonChiTiet.getSoLuong();
        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getOne(hoaDonChiTiet.getChiTietSanPham().getId());
//        Integer soLuongTon = chiTietSanPham.getSoLuong();
        chiTietSanPhamService.updateDelete(chiTietSanPham.getId(), hoaDonChiTiet.getSoLuong());
        hoaDonChiTietService.deleteHoaDonCT(id);
        return "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDon;
    }

    @RequestMapping("/update-cart/{id}")
    public String updateSLGioHang(@PathVariable("id") UUID id, int soLuong) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getOneHoaDon(id);
        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getOne(hoaDonChiTiet.getChiTietSanPham().getId());
        Integer soLuongTon = chiTietSanPham.getSoLuong() + hoaDonChiTiet.getSoLuong();
        if (soLuong > 5) {
            hoaDonChiTiet.setSoLuong(5);
            chiTietSanPhamService.updateSoLuongTon(chiTietSanPham.getId(), soLuongTon - 5);
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
                            @RequestParam("soDienThoai") String soDienThoai,
                            @RequestParam("ghiChu") String ghiChu) throws ParseException {
        HoaDon hoaDonThanhToan = hoaDonService.getOne(idHoaDon);


        if (hoaDonThanhToan != null) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String format = sdf.format(date);
            hoaDonThanhToan.setNgayThanhToan(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(format));
            hoaDonThanhToan.setNhanVien(nhanVien);
            hoaDonThanhToan.setTrangThai(5);
            hoaDonThanhToan.setSdt(soDienThoai);
            KhachHang khachHang = khachHangRepository.findKHBySDT(soDienThoai);

            hoaDonThanhToan.setKhachHang(khachHang);
            hoaDonThanhToan.setTenNguoiNhan(khachHang.getTen());
            hoaDonThanhToan.setPhuongThucThanhToan(1);

            hoaDonThanhToan.setGhiChu(ghiChu);
            hoaDonService.saveHoaDon(hoaDonThanhToan);
            this.sumMoney = 0.0;
            this.idHoaDon = null;

            model.addAttribute("successThanhToan", "Thanh toán thất bại");
        } else {
            model.addAttribute("errorThanhToan", "Thanh toan that bai");
            return "redirect:/bumblebee/ban-hang-tai-quay/sell";
        }
        return "redirect:/bumblebee/ban-hang-tai-quay/sell";
    }

    @RequestMapping("/them-khach-hang")
    public String themKhachHang(Model model, @ModelAttribute("khachHang") KhachHang khachHang) {
        KhachHang addKhachHang = new KhachHang();

        String formatKH = "KH" + String.format("%07d", maKhachHang);
        KhachHang checkMa = khachHangService.searchKhachHang(formatKH);
        if (checkMa != null) {
            String maKHMax = khachHangService.searchMaxKH();
            maKhachHang = Integer.valueOf(maKHMax.substring(2));
            maKhachHang++;
            String formatSoMa = "KH" + String.format("%07d", maKhachHang);
            addKhachHang.setMa(formatSoMa);
        } else {
            addKhachHang.setMa(formatKH);
        }
        addKhachHang.setTen(khachHang.getTen());
        addKhachHang.setSoDienThoai(khachHang.getSoDienThoai());
        addKhachHang.setTrangThai(5);
        khachHangService.saveKhachHang(addKhachHang);
        return "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDon;
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
//    @RequestMapping("/print/{id}")
//    public void xuatFilePdf(HttpServletResponse response, Model model, @PathVariable("id") UUID id, @ModelAttribute("hoaDon") HoaDon hoaDon) throws ParseException {
//
//        HoaDon hoaDonThanhToan = hoaDonService.getOne(idHoaDon);
//
//        List<HoaDonChiTiet> listHoaDon1 = hoaDonChiTietService.getHoaDonTheoHoaDonChiTiet(idHoaDon);
//        try {
//            Document document = new Document();
//            document.setPageSize(PageSize.A4);
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            PdfWriter.getInstance(document, baos);
//
//
//            ////
//            document.open();
//
//            String qrCodeData = hoaDonThanhToan.getMaHoaDon();
//            BarcodeQRCode qrCode = new BarcodeQRCode(qrCodeData, 200, 250, null);
//            com.itextpdf.text.Image qrCodeImage = qrCode.getImage();
////
//            qrCodeImage.setAbsolutePosition(400, 400);
//
//            document.add(qrCodeImage);
//
//            Font largeFont = new Font(Font.FontFamily.TIMES_ROMAN, 25f, Font.BOLD);
//            ////////////// hoá đơn
//            Paragraph HoaDon = new Paragraph(" BUMBLEBEE SHOES" + "\n", largeFont);
//
//            HoaDon.setAlignment(Paragraph.ALIGN_CENTER);
//            document.add(HoaDon);
//            Paragraph khoangTrang = new Paragraph("-");
//
//            khoangTrang.setAlignment(Paragraph.ALIGN_CENTER);
//            document.add(khoangTrang);
//            Font chutable = new Font(Font.FontFamily.TIMES_ROMAN, 18f);
//            //// Table
//            Paragraph MaHoaDon = new Paragraph("Ma Hoa Don     :    " + hoaDonThanhToan.getMaHoaDon());
//            Paragraph ma = new Paragraph();

//            Paragraph Ngay = new Paragraph("Ngay Mua    :    " + hoaDonThanhToan.getNgayTao());
//
//            document.add(MaHoaDon);
//            document.add(Ngay);
//
//
//            Paragraph tennhanvien = new Paragraph("Nhan vien    :    " + nameNhanVien);
//            document.add(tennhanvien);
//            Paragraph tenKhac = new Paragraph("Khach hang    :    " + hoaDonThanhToan.getTenNguoiNhan());
//            document.add(tenKhac);
//
////            table.addCell(MaHoaDon);
////            table.addCell(Ma);
////            table.addCell(Ngay);
////            table.addCell(ngay);
////            document.add(table);
//            Paragraph khoangtrang2 = new Paragraph("✿✿✿");
//            khoangtrang2.setAlignment(Paragraph.ALIGN_CENTER);
//            document.add(khoangtrang2);
//            ////
//            PdfPTable productTable1 = new PdfPTable(4);
//            productTable1.setWidthPercentage(100);
//            productTable1.addCell(new Paragraph("Ten san pham"));
//            productTable1.addCell(new Paragraph("So luong"));
//            productTable1.addCell(new Paragraph("Gia tien"));
//            productTable1.addCell(new Paragraph("Thanh tien"));
//            document.add(productTable1);
//            for (HoaDonChiTiet hoaDon1 : listHoaDon1) {
//                PdfPTable productTable = new PdfPTable(4);
//                productTable.setWidthPercentage(100);
//                productTable.addCell(hoaDon1.getChiTietSanPham().getSanPham().getTenSanPham());
//                productTable.addCell(String.valueOf(hoaDon1.getSoLuong()));
//                productTable.addCell(String.valueOf(hoaDon1.getDonGia()));
//                productTable.addCell(String.valueOf(hoaDon1.getDonGia() * hoaDon1.getSoLuong()));
////    Paragraph Sl = new Paragraph("So luong");
////    Sl.setAlignment(Element.ALIGN_LEFT);
////    Sl.setFont(chutable);
////    Paragraph SoLuong = new Paragraph(""+hoaDon1.getSoLuong());
////    SoLuong.setAlignment(Element.ALIGN_RIGHT);
////    SoLuong.setFont(chutable);
////    Paragraph TenSanPham = new Paragraph("Ten san pham");
////    TenSanPham.setAlignment(Element.ALIGN_LEFT);
////    TenSanPham.setFont(chutable);
////    Paragraph tsp = new Paragraph(hoaDon1.getChiTietSanPham().getSanPham().getTenSanPham());
////    tsp.setAlignment(Element.ALIGN_RIGHT);
////    tsp.setFont(chutable);
////    Paragraph Tien = new Paragraph("Don gia ");
////    Tien.setAlignment(Element.ALIGN_LEFT);
////    Tien.setFont(chutable);
////    Paragraph tien = new Paragraph("" + hoaDon1.getDonGia());
////    tien.setAlignment(Element.ALIGN_RIGHT);
////    tien.setFont(chutable);
////    table.addCell(Sl);
////    table.addCell(SoLuong);
////    table.addCell(TenSanPham);
////    table.addCell(tsp);
////    table.addCell(Tien);
////    table.addCell(tien);
//
//
//                document.add(productTable);
//
//            }
//
//
//            Paragraph dong = new Paragraph("==========================================================================");
//
//            document.add(dong);
//            List<HoaDonChiTiet> list = hoaDonChiTietService.getListHoaDonCTByIdHoaDon(id);
//            sumMoney = hoaDonChiTietService.getTotalMoney(list);
//            Double TinhTong = 0.0;
////            for(HoaDonChiTiet hoadon1 : listHoaDon1){
////
////                TinhTong += hoadon1.getSoLuong()* hoadon1.getDonGia();
////
////
////            }
//            Paragraph TongCong = new Paragraph("Tong cong       :    " + sumMoney);
//
//            document.add(TongCong);
//
//            Paragraph DongGanCuoi = new Paragraph("CHI XUAT HOA DON TRONG NGAY");
//            DongGanCuoi.setAlignment(Paragraph.ALIGN_CENTER);
//            Paragraph camOn = new Paragraph("CAM ON QUY KHACH DA SU DUNG DICH VU");
//            camOn.setAlignment(Paragraph.ALIGN_CENTER);
//            ////// insert document
//
//
////            document.add(SoLuong);
////            document.add(TenSanPham);
////            document.add(Tien);
//
//
//            document.add(DongGanCuoi);
//            document.add(camOn);
//            document.close();
//            /// add vào table
//
//
////            Paragraph MaHoaDon = new Paragraph("Ma Hoa Don     :    "+hoaDonThanhToan.getMaHoaDon());
////            Paragraph ma = new Paragraph();
////            Paragraph Ngay = new Paragraph("Ngay                   ");
////            Paragraph SoLuong = new Paragraph("So Luong       :     "+hoaDon1.getSoLuong());
////            Paragraph sl = new Paragraph();
////            Paragraph TenSanPham = new Paragraph("Ten San Pham   :  "+hoaDon1.getChiTietSanPham().getSanPham().getTenSanPham());
////            Paragraph tsp = new Paragraph();
////            Paragraph Tien = new Paragraph("Don gia             :     "+hoaDon1.getDonGia());
//
//
//            ///////// Tính tổng hoá đơn và càm ơn
//
////            BaseFont baseFont = BaseFont.createFont("path_to_vietnamese_font.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//
//            ///
//            byte[] pdfBytes = baos.toByteArray();
//            response.setContentType("application/pdf");
//            response.setContentLength(pdfBytes.length);
//            response.setHeader("Content-Disposition", "inline; filename=hoa_don.pdf");
//            response.getOutputStream().write(pdfBytes);
//            response.getOutputStream().flush();
//            response.getOutputStream().close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
////        return "redirect:/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/" + this.idHoaDon;
//
//    }

}
