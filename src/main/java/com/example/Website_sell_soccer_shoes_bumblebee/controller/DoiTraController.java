package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.dto.ChiTietSanPhamCustom;
import com.example.Website_sell_soccer_shoes_bumblebee.dto.ChiTietSanPhamDto;
import com.example.Website_sell_soccer_shoes_bumblebee.dto.DoiTraChiTietCustom;
import com.example.Website_sell_soccer_shoes_bumblebee.dto.HoaDonChiTietCustom;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.*;
import com.example.Website_sell_soccer_shoes_bumblebee.service.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/bumblebee/doi-hang/list-san-pham-loi")
    public String danhSachSanPhamLoi(Model model) {
        model.addAttribute("view", "../doi-tra/danh-sach-doi-tra.jsp");
        model.addAttribute("listDoiTra", doiTraChiTietService.findSanPhamLoi());
        return "/admin/index";
    }

    @GetMapping("/bumblebee/doi-hang/list-doi-hang")
    public String danhSachHuy(Model model) {
        model.addAttribute("view", "../doi-tra/danh-sach-doi-tra.jsp");
//        model.addAttribute("listDuDK", hoaDonService.danhSachHDDuDK());
        model.addAttribute("listDoiTra", doiTraService.listHuyDoiTra());
        return "/admin/index";
    }

    @GetMapping("/bumblebee/doi-hang/list-tra-hang")
    public String danhSachThanhCong(Model model) {
        model.addAttribute("view", "../doi-tra/danh-sach-doi-tra.jsp");
        model.addAttribute("listDoiTra", doiTraService.listDoiTraThanhCong());
        return "/admin/index";
    }

//    @GetMapping("/bumblebee/don-hang/{maHD}")
//    public String chiTietDonHang(Model model, @PathVariable("maHD") String maHoaDon) {
//        model.addAttribute("view", "../doi-tra/doi-hang.jsp");
//        List<HoaDonChiTiet> listSPMuonDoi = hoaDonChiTietService.listHDCTByMaHD(maHoaDon);
//        model.addAttribute("listHDCT", listSPMuonDoi);
//        model.addAttribute("listDoiTraCT",doiTraChiTietService.listDoiTraCTByHoaDon(this.maHoaDon));
//
//        getTaiKhoan(model);
//        return "/admin/index";
//    }

    @GetMapping("/bumblebee/don-hang/{maHD}")
    public String searchHoaDon(Model model, @PathVariable("maHD") String maHD) {
        model.addAttribute("view", "../doi-tra/tra-hang.jsp");
        getTaiKhoan(model);
        List<HoaDon> listHoaDonDuDK = hoaDonService.danhSachHDDuDK();
        if (listHoaDonDuDK != null){
            for (HoaDon hd : listHoaDonDuDK){
                if (hd.getMaHoaDon().equals(maHD)){
                    HoaDon hoaDon  = hoaDonService.searchHoaDon(maHD);
                    model.addAttribute("hoaDonMua",hoaDon);
                    List<HoaDonChiTiet> lstHoaDonCT = hoaDonChiTietService.listHDCTByMaHD(maHD);
                    model.addAttribute("listHDCT", lstHoaDonCT);
                    Double sumMoney = hoaDonChiTietService.getTotalMoney(lstHoaDonCT);
                    model.addAttribute("sumMoney",sumMoney);
                    this.maHoaDon = maHD;
                    return "/admin/index";
                }
            }
            model.addAttribute("notFound","Khong tim thay");
            return "redirect:/bumblebee/doi-hang/list-tra-hang";
        }else {
            model.addAttribute("notFound","Khong tim thay");
            return "redirect:/bumblebee/doi-hang/list-tra-hang";
        }
    }

    @GetMapping("/bumblebee/don-hang/list-sp/{id}")
    public ResponseEntity<?> getListByMa(Model model, @PathVariable("id") UUID id) {
        ChiTietSanPham ctsp = chiTietSanPhamService.getOne(id);
        List<ChiTietSanPhamCustom> lstSanPham = chiTietSanPhamService.listSPCungLoai(ctsp.getGiaBan());
        return ResponseEntity.ok(lstSanPham);
    }

    @GetMapping("/bumblebee/don-hang/list-hoa-don-ct/{id}")
    public ResponseEntity<?> listHoaDonCTCustom(Model model, @PathVariable("id") UUID id) {
        List<HoaDonChiTietCustom> lstHoaDonCT = hoaDonChiTietService.listHoaDonCTCustom(id);
        return ResponseEntity.ok(lstHoaDonCT);
    }

    @GetMapping("/bumblebee/don-hang/list-doi-tra-ct/{id}")
    public ResponseEntity<?> listDoiTraCTCustom(Model model, @PathVariable("id") UUID id) {
        List<DoiTraChiTietCustom> lstDoiTraCT = doiTraChiTietService.listDoiTraCTCustom(id);
        return ResponseEntity.ok(lstDoiTraCT);
    }

    @RequestMapping("/bumblebee/don-hang/tao-doi-tra/{maHD}")
    public String taoDoiTra(Model model, @PathVariable("maHD") String maHD) throws ParseException {
        model.addAttribute("view", "../doi-tra/doi-hang.jsp");
        this.maHoaDon = maHD;
        return "redirect:/bumblebee/don-hang/" + maHD;
    }

    private void createDoiTraCT(DoiTra doiTra, String lydo, Integer soLuong) {
        for (ChiTietSanPham ctsp : listCTSP) {
            DoiTraChiTiet doiTraChiTiet = new DoiTraChiTiet();
            HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getHDCTDoiTra(this.idHoaDon, ctsp.getId());
            doiTraChiTiet.setChiTietSanPham(ctsp);
            doiTraChiTiet.setHoaDonChiTiet(hoaDonChiTiet);
            doiTraChiTiet.setSoLuong(soLuong);
            doiTraChiTiet.setDoiTra(doiTra);
            doiTraChiTiet.setLyDoDoiTra(lydo);
            doiTraChiTiet.setDonGia(ctsp.getGiaBan());
            doiTraChiTiet.setTrangThai(2);
            doiTraChiTietService.saveDoiTraCT(doiTraChiTiet);
        }
        this.idDoiTra = doiTra.getId();
    }

    private static int maDoiTra = 1;

    private void createMaDoiTraAuto(DoiTra doiTra, HoaDon hoaDon) throws ParseException {
        String formatDoiTra = "DT" + String.format("%07d", maDoiTra);
        DoiTra checkMaDoiTra = doiTraService.getDoiTraByMa(formatDoiTra);
        if (checkMaDoiTra != null) {
            String maDoiTraMax = doiTraService.maxMaDoiTra();
            maDoiTra = Integer.valueOf(maDoiTraMax.substring(2));
            maDoiTra++;
            String formatSoMa = "DT" + String.format("%07d", maDoiTra);
            doiTra.setMaDoiTra(formatSoMa);
        } else {
            doiTra.setMaDoiTra(formatDoiTra);
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String format = sdf.format(date);
        doiTra.setNgayDoiTra(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(format));
        doiTra.setHoaDon(hoaDon);
        doiTra.setNhanVien(nhanVien);
        doiTraService.saveDoiTra(doiTra);
        this.idHoaDon = hoaDon.getId();
    }

    private void createDoiSanPham(UUID idSanPham, DoiTra doiTra) {
        DoiTraChiTiet doiTraChiTiet = new DoiTraChiTiet();
//        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getHDCTDoiTra(this.idHoaDon, idCTSP);
        ChiTietSanPham ctsp = chiTietSanPhamService.getOne(idSanPham);
        doiTraChiTiet.setChiTietSanPham(ctsp);
//        doiTraChiTiet.setHoaDonChiTiet(hoaDonChiTiet);
        doiTraChiTiet.setSoLuong(1);
        doiTraChiTiet.setDoiTra(doiTra);
        doiTraChiTiet.setDonGia(ctsp.getGiaBan());
        doiTraChiTiet.setTrangThai(1);
        doiTraChiTietService.saveDoiTraCT(doiTraChiTiet);
        this.idDoiTra = doiTra.getId();
    }

    @RequestMapping("/bumblebee/don-hang/create-doi-tra/{idSanPham}")
    public String createDoiTra(Model model, @PathVariable("idSanPham") UUID idSanPham
    ) throws ParseException {
        model.addAttribute("view", "../doi-tra/doi-hang.jsp");
        DoiTra doiTra = new DoiTra();
        HoaDon hoaDon = hoaDonService.searchHoaDon(this.maHoaDon);
        createMaDoiTraAuto(doiTra,hoaDon);
        idCTSP = idSanPham;
        createDoiSanPham(idSanPham, doiTra);
        return "redirect:/bumblebee/don-hang/" + this.maHoaDon;
    }

    @RequestMapping("/bumblebee/don-hang/update-so-Luong")
    public String updateSoLuong(@RequestParam("soLuong") Integer soLuong){
        DoiTraChiTiet doiTraChiTiet = doiTraChiTietService.getDoiTraCT(this.idDoiTra, idCTSP);
        doiTraChiTiet.setSoLuong(soLuong);
        return "redirect:/bumblebee/don-hang/" + this.maHoaDon;
    }
    @RequestMapping("/bumblebee/don-hang/xac-nhan-doi")
    public String doiHang(@RequestParam("lyDoDoiTra") String lyDoDoiTra,
                          @RequestParam("soLuong") Integer soLuong) throws ParseException {
        DoiTra doiTra = doiTraService.getOneDoiTra(this.maHoaDon);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String format = sdf.format(date);
        doiTra.setNgayDoiTra(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(format));
        doiTra.setTrangThai(1);
        doiTraService.saveDoiTra(doiTra);
        HoaDon hoaDon = hoaDonService.searchHoaDon(this.maHoaDon);
        hoaDon.setTrangThai(7);
        hoaDonService.saveHoaDon(hoaDon);
        List<DoiTraChiTiet> lstDoiTraCT = doiTraChiTietService.listDoiTraCTById(this.idDoiTra);
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getHDCTDoiTra(hoaDon.getId(), idCTSP);
        hoaDonChiTiet.setTrangThai(0);
        DoiTraChiTiet doiTraChiTiet = doiTraChiTietService.getDoiTraCT(this.idDoiTra, idCTSP);
        doiTraChiTiet.setHoaDonChiTiet(hoaDonChiTiet);
        doiTraChiTiet.setLyDoDoiTra(lyDoDoiTra);
        doiTraChiTiet.setSoLuong(soLuong);
        doiTraChiTietService.saveDoiTraCT(doiTraChiTiet);
        hoaDonChiTietService.saveHoaDonCT(hoaDonChiTiet);
        if (lyDoDoiTra.equals("Sản phẩm lỗi")) {

        } else {
            ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getOne(idCTSP);
            chiTietSanPhamService.updateDelete(chiTietSanPham.getId(), soLuong);
        }
        for (DoiTraChiTiet dtct : lstDoiTraCT) {
        }
        return "redirect:/bumblebee/doi-hang/list-doi-hang";
    }

    @RequestMapping("/bumblebee/don-hang/create-tra-hang")
    public String createTraHang(Model model,
                                @RequestParam(name = "idListCartDetail", required = false) String idListCartDetail,
                                @RequestParam(name = "lyDoDoiTra") String lyDoDoiTra,
// <<<<<<< vinhdq123
//                                 @RequestParam(name = "soLuongTra") Integer soLuong) {
// =======
                                @RequestParam(name = "soLuong") Integer soLuong) throws ParseException {
// >>>>>>> master
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
        for (UUID id : idCartUUIDList) {
            ChiTietSanPham ctkm = chiTietSanPhamService.getOne(id);
            listCTSP.add(ctkm);
        }
        DoiTra checkDoiTra = doiTraService.getOneDoiTra(this.maHoaDon);
//        if (checkDoiTra != null){
        checkDoiTra.setTrangThai(2);
        doiTraService.saveDoiTra(checkDoiTra);
        createDoiTraCT(checkDoiTra, lyDoDoiTra, soLuong);
//        HoaDon hoaDon = hoaDonService.searchHoaDon(this.maHoaDon);
        hoaDon.setTrangThai(7);
        hoaDonService.saveHoaDon(hoaDon);
        for (ChiTietSanPham ctsp : listCTSP) {
            HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getHDCTDoiTra(this.idHoaDon, ctsp.getId());
            hoaDonChiTiet.setTrangThai(0);
            hoaDonChiTietService.saveHoaDonCT(hoaDonChiTiet);
            if (lyDoDoiTra.equals("Sản phẩm lỗi")) {

            } else {
                ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getOne(ctsp.getId());
                chiTietSanPhamService.updateDelete(chiTietSanPham.getId(), soLuong);
            }
        }

//        }else {
//            DoiTra doiTra = new DoiTra();
//            HoaDon hoaDon = hoaDonService.searchHoaDon(this.maHoaDon);
//            checkDoiTra.setTrangThai(2);
//            doiTraService.saveDoiTra(checkDoiTra);
//            createDoiTraCT(doiTra,lyDoDoiTra,soLuong);
//            hoaDon.setTrangThai(7);
//            hoaDonService.saveHoaDon(hoaDon);
//            for (ChiTietSanPham ctsp : listCTSP){
//                HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getHDCTDoiTra(this.idHoaDon,ctsp.getId());
//                hoaDonChiTiet.setTrangThai(0);
//                hoaDonChiTietService.saveHoaDonCT(hoaDonChiTiet);
//            }
//        }
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
    public String removeDoiTraCT(@PathVariable("id") UUID id) {
        doiTraChiTietService.removeDoiTraCT(id);
        return "redirect:/bumblebee/don-hang/" + this.maHoaDon;
    }

    @RequestMapping("/bumblebee/doi-hang/list-tra-hang")
    public String huyDoiTra() {
        doiTraService.huyDoiTra(this.idDoiTra);
        return "redirect:/bumblebee/doi-hang/list";
    }

    @RequestMapping("/bumblebee/doi-hang/chi-tiet/{id}")
    public String chiTietDoitra(Model model,@PathVariable("id")UUID id) {
        model.addAttribute("view", "../doi-tra/detail-doi-tra.jsp");
        List<HoaDonChiTiet> lstHoaDonCT = hoaDonChiTietService.getListHoaDonCTByIdHoaDon(id);
        List<DoiTraChiTiet> lstDoiTraCT = doiTraChiTietService.listDoiTraCTByIdHoaDon(id);
        HoaDon hoaDon = hoaDonService.getOne(id);
        model.addAttribute("hoaDon",hoaDon);
        model.addAttribute("listHoaDonCT",lstHoaDonCT);
        model.addAttribute("listDoiTraCT",lstDoiTraCT);
        return "admin/index";
    }
    @RequestMapping("/bumblebee/doi-hang/print/{id}")
    public void print(HttpServletResponse response,@PathVariable("id") UUID id)throws ParseException{
        HoaDon hoaDonDoiTra = hoaDonService.getOne(id);
        List<HoaDonChiTiet> listHoaDon1 = hoaDonChiTietService.getHoaDonTheoHoaDonChiTiet(id);
        List<DoiTraChiTiet> listDoiTra = doiTraChiTietService.listDoiTraCTByIdHoaDon(id);
        try{
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
            Font titleFont = new Font(BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 20, Font.BOLD, BaseColor.BLACK);
            Paragraph tieuDeThongTin = new Paragraph("HOÁ ĐƠN BÁN HÀNG  ", titleFont);
            document.add(tieuDeThongTin);
            Paragraph maHoaDon = new Paragraph(hoaDonDoiTra.getMaHoaDon());
            maHoaDon.setAlignment(Paragraph.ALIGN_CENTER);
//            Thông tin hoá dơn
            Font titleFont1 = new Font(BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 15, Font.NORMAL, BaseColor.BLACK);
            Paragraph nhanVien = new Paragraph("Nhân Viên bán hàng : "+hoaDonDoiTra.getNhanVien().getHo()+" "+hoaDonDoiTra.getNhanVien().getTenDem()+" "+hoaDonDoiTra.getNhanVien().getTen(),titleFont1);
            document.add(nhanVien);
            Paragraph khachHang = new Paragraph("Khách hàng : "+hoaDonDoiTra.getKhachHang().getTen(),titleFont1);
            document.add(khachHang);
            Paragraph sdt = new Paragraph("Số điện thoại : "+hoaDonDoiTra.getSdt(),titleFont1);
            document.add(sdt);
            Font titleFont2 = new Font(BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 18, Font.BOLD, BaseColor.BLACK);
            Paragraph danhSachMua = new Paragraph("DANH SÁCH SẢN PHẨM KHÁCH MUA",titleFont2);
            danhSachMua.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(danhSachMua);
            Paragraph KhoangTrang = new Paragraph("                                                         ");
            document.add(KhoangTrang);
            PdfPTable productTable = new PdfPTable(6);
            productTable.setWidthPercentage(100);
            float[] columnWidths = {3f, 3f, 2f, 2f, 2f,2f};
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
            Paragraph TongCong = new Paragraph("Tổng Hoá Đơn       :    " + tongTien + "VNĐ", titleFont2);
            document.add(TongCong);
//
            Paragraph KhoangTrang1 = new Paragraph("                                                         ");
            document.add(KhoangTrang1);
//            Font titleFont2 = new Font(BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 18, Font.BOLD, BaseColor.BLACK);
            Paragraph danhSachTra = new Paragraph("DANH SÁCH SẢN PHẨM KHÁCH TRẢ",titleFont2);
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
//            double tongTienTra = 0.0;
            for(DoiTraChiTiet doiTraChiTiet: listDoiTra){
               productTable1.addCell(createTableCell(doiTraChiTiet.getChiTietSanPham().getSanPham().getTenSanPham(), titleFont1));
                productTable1.addCell(createTableCell(doiTraChiTiet.getChiTietSanPham().getMauSac().getTen(), titleFont1));
                productTable1.addCell(createTableCell(String.valueOf(doiTraChiTiet.getChiTietSanPham().getKichCo().getSize()), titleFont1));
                productTable1.addCell(createTableCell(String.valueOf(doiTraChiTiet.getSoLuong()), titleFont1));
                productTable1.addCell(createTableCell(String.valueOf(doiTraChiTiet.getDonGia()), titleFont1));
                productTable1.addCell(createTableCell(String.valueOf(doiTraChiTiet.getDonGia()*doiTraChiTiet.getSoLuong()), titleFont1));
                productTable1.addCell(createTableCell(doiTraChiTiet.getHoaDonChiTiet().getHoaDon().getNhanVien().getHo()+" "+doiTraChiTiet.getHoaDonChiTiet().getHoaDon().getNhanVien().getTenDem()+" "+doiTraChiTiet.getHoaDonChiTiet().getHoaDon().getNhanVien().getTen(), titleFont1));
                productTable1.addCell(createTableCell(doiTraChiTiet.getLyDoDoiTra(), titleFont1));

            }
            document.add(productTable1);
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
    }

