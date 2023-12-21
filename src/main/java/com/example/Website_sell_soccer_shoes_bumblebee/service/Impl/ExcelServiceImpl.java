package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;


import com.example.Website_sell_soccer_shoes_bumblebee.entity.*;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.*;

@Service
public class ExcelServiceImpl {
    @Autowired
    SanPhamRepository spr;
    @Autowired
    MauSacReponsitory msr;
    @Autowired
    LoaiGiayRepository lgr;
    @Autowired
    KichCoRepository kcr;
    @Autowired
    ChatLieuRepository clr;
    @Autowired
    DeGiayRepository dgr;
    @Autowired
    ChiTietSanPhamRepo ctspr;

    public void saveDataFromExcel(MultipartFile file) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();
        rows.next();
        while (rows.hasNext()) {
            Row row = rows.next();
            ChiTietSanPham ctsp = new ChiTietSanPham();

            Row headerRow = sheet.getRow(0);
            int expectedColumnCount = 11; // Số lượng cột mong đợi
            if (headerRow.getPhysicalNumberOfCells() != expectedColumnCount) {
                throw new IOException("Cấu trúc dữ liệu trong tệp Excel không đúng. Yêu cầu " + expectedColumnCount + " cột.");
            }
            String spName = row.getCell(0).getStringCellValue();
            if (spName.trim().isEmpty()) {
                throw new IOException("Dữ liệu tên sản phẩm  nhập vào không hợp lệ");
            }
            SanPham sp = spr.findByTenSp(spName);
            if (sp == null) {
            sp = new SanPham();
            String MaSp = "MSP"+generateRandomCodeSp();
            sp.setMaSanPham(MaSp);
            sp.setTenSanPham(spName);
            sp.setTrangThai(1);
            spr.save(sp);

//                return;

            }
//            else if(sp !=null) {
//                throw new IOException("chưa dc nhập đủ trường dữ liệu   ");
//            }
//            Tìm tên màu sắc dựa trên tên
            String msName = row.getCell(1).getStringCellValue();
            if (msName.trim().isEmpty()) {
                throw new IOException("Dữ liệu màu sắc nhập vào không hợp lệ");
            }
            MauSac ms = msr.findByTen(msName);
            if (ms == null) {
                ms = new MauSac();
                String MaMs = "MSP"+generateRandomCodeMs();
                ms.setMa(MaMs);
                ms.setTen(msName);
                ms.setTt(1);
                msr.save(ms);
//                return;
            }
//            else if(ms != null){
//                throw new IOException("màu sắc  chưa dc nhập  ");
//            }
//            Tìm tên thể loại dựa trên tên
            String tlName = row.getCell(2).getStringCellValue();
            if (tlName.trim().isEmpty()) {
                throw new IOException("Dữ liệu thể loại nhập vào không hợp lệ");
            }
            LoaiGiay lg = lgr.findbyten(tlName);
            if (lg == null) {
                lg = new LoaiGiay();
                String MaLg = "MLG"+generateRandomCodeLg();
                lg.setMa(MaLg);
                lg.setTentheloai(tlName);
                lg.setTrangthai(1);
                lgr.save(lg);
//                return;
            }
//            else if(lg != null){
//                throw  new IOException("Loài giày để trống ");
//            }
//            Tìm tên kích cỡ dựa trên tên
            int kcName = (int) row.getCell(3).getNumericCellValue();
            if (kcName < 0) {
                throw new IOException("Dữ liệu kichs cõw nhập vào không hợp lệ");
            }
            KichCo kc = kcr.findBySize(kcName);
            if (kc == null) {
                kc = new KichCo();
                String MaKc = "MKC"+generateRandomCodeKc();
                kc.setMaKichCo(MaKc);;
                kc.setSize(kcName);
                kc.setTrangThai(1);
                kcr.save(kc);
//                return;
            }
//            else if(kc != null){
//                throw new IOException("size chưa dc nhập   ");
//            }
//           Tìm tên chất liệu dựa trên tên
            String clName = row.getCell(4).getStringCellValue();
            ChatLieu cl = clr.findByTen(clName);
            if (cl == null) {
                cl = new ChatLieu();
                String MaCL = "MCL"+generateRandomCodeCl();
                cl.setMa(MaCL);;
                cl.setTen(clName);
                cl.setTrangThai(1);
                clr.save(cl);
            }
//            else if(cl != null ){
//                throw new IOException("Chất liệu ch được nhập ");
//            }
//            Tìm đế giày dựa trên tên

            String dgName = row.getCell(5).getStringCellValue();
            if (dgName.trim().isEmpty()) {
                throw new IOException("Dữ liệu đế giày nhập vào không hợp lệ");
            }
            DeGiay dg = dgr.findByLoaiDe(dgName);
            if (dg == null) {
                dg = new DeGiay();
                String ma = "MDG"+generateRandomCodeDg();
                dg.setMa(ma);;
                dg.setLoaiDe(dgName);
                dg.setTrangThai(1);
                dgr.save(dg);
//                return;
            }

//            else if(dg != null){
//                throw  new IOException("đế giày chưa được nhập ");
//            }
////
            ctsp.setSanPham(sp);
            ctsp.setMauSac(ms);
            ctsp.setLoaiGiay(lg);
            ctsp.setKichCo(kc);
            ctsp.setChatLieu(cl);
            ctsp.setDeGiay(dg);
            Double giaBan = row.getCell(6).getNumericCellValue();
            int SoLuong = (int) row.getCell(7).getNumericCellValue();
            String mota = row.getCell(8).getStringCellValue();
            int tt = (int) row.getCell(9).getNumericCellValue();
            Date ngayTao = row.getCell(10).getDateCellValue();
            if (ngayTao.equals("")) {
                throw new IOException("Ngày Tạo chưa được nhập ");
            }
            if (mota.trim().isEmpty()) {
                throw new IOException("Mô tả chưa được nhập ");
            }
            ctsp.setGiaBan(giaBan);
            ctsp.setSoLuong(SoLuong);
            ctsp.setMoTaCT(mota);
            ctsp.setTrangThai(tt);


            if (isDuplicate(ctsp)) {
                throw new IOException("trùng dữ liệu");
            } else {
                // Nếu không trùng lặp, thêm mới dữ liệu vào cơ sở dữ liệu
                ctspr.save(ctsp);

            }


        }
        workbook.close();
    }

    private boolean isDuplicate(ChiTietSanPham ctsp) {
        // Kiểm tra sự trùng lặp dựa trên các tiêu chí, sử dụng phương thức từ repository
        List<ChiTietSanPham> existingCTSP = ctspr.findBySanPhamAndMauSacAndKichCoAndChatLieuAndDeGiayAndLoaiGiay(
                ctsp.getSanPham(), ctsp.getMauSac(), ctsp.getKichCo(), ctsp.getChatLieu(), ctsp.getDeGiay(), ctsp.getLoaiGiay()
        );
        return !existingCTSP.isEmpty();
    }
    private String generateRandomCodeSp() {
        // Logic để tạo mã ngẫu nhiên, bạn có thể sử dụng Random hoặc một cách khác
        return "MSP" + new Random().nextInt(10000);
    }
    private String generateRandomCodeMs() {
        // Logic để tạo mã ngẫu nhiên, bạn có thể sử dụng Random hoặc một cách khác
        return "MMS" + new Random().nextInt(10000);
    }
    private String generateRandomCodeKc() {
        // Logic để tạo mã ngẫu nhiên, bạn có thể sử dụng Random hoặc một cách khác
        return "MKC" + new Random().nextInt(10000);
    }
    private String generateRandomCodeCl() {
        // Logic để tạo mã ngẫu nhiên, bạn có thể sử dụng Random hoặc một cách khác
        return "MCL" + new Random().nextInt(10000);
    }
    private String generateRandomCodeDg() {
        // Logic để tạo mã ngẫu nhiên, bạn có thể sử dụng Random hoặc một cách khác
        return "MDG" + new Random().nextInt(10000);
    }
    private String generateRandomCodeLg() {
        // Logic để tạo mã ngẫu nhiên, bạn có thể sử dụng Random hoặc một cách khác
        return "MLG" + new Random().nextInt(10000);
    }
}
