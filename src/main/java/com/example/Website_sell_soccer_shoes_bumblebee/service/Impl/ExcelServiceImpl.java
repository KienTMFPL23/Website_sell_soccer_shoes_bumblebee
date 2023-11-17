package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.dto.ProductDetailDTO;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.*;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.*;
import com.example.Website_sell_soccer_shoes_bumblebee.service.ExcelService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.Impl.LoaiGiayServiceImpl;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
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
//            Tìm tên Sản Phẩm dựa trên tên
//            UUID studentUuid = UUID.fromString(row.getCell(1).getStringCellValue());
//            Student student = studentRepository.findByUuid(studentUuid);

//            UUID spuuid = UUID.fromString(row.getCell(0).getStringCellValue());
//            SanPham sp = spr.findById(spuuid);
            Row headerRow = sheet.getRow(0);
            int expectedColumnCount = 10; // Số lượng cột mong đợi
            if (headerRow.getPhysicalNumberOfCells() != expectedColumnCount) {
                throw new IOException("Cấu trúc dữ liệu trong tệp Excel không đúng. Yêu cầu " + expectedColumnCount + " cột.");
            }
            String spName = row.getCell(0).getStringCellValue();
            if (spName.trim().isEmpty()) {
                throw new IOException("Dữ liệu tên sản phẩm  nhập vào không hợp lệ");
            }
            SanPham sp = spr.findByTenSp(spName);
            if (sp == null) {
                throw new IOException("sản phẩm chưa tồn tại  ");
//                return;

            }
//            else if(sp !=null) {
//                throw new IOException("chưa dc nhập đủ trường dữ liệu   ");
//            }
//            Tìm tên màu sắc dựa trên tên
            String msName = row.getCell(1).getStringCellValue();
            if(msName.trim().isEmpty()){
                throw new IOException("Dữ liệu màu sắc nhập vào không hợp lệ");
            }
            MauSac ms = msr.findByTen(msName);
            if (ms == null) {
                throw new IOException("màu sắc chưa tồn tại  ");
//                return;
            }
//            else if(ms != null){
//                throw new IOException("màu sắc  chưa dc nhập  ");
//            }
//            Tìm tên thể loại dựa trên tên
            String tlName = row.getCell(2).getStringCellValue();
            if(tlName.trim().isEmpty()){
                throw new IOException("Dữ liệu thể loại nhập vào không hợp lệ");
            }
            LoaiGiay lg = lgr.findbyten(tlName);
            if (lg == null) {
                throw new IOException("Loại giày  chưa tồn tại  ");
//                return;
            }
//            else if(lg != null){
//                throw  new IOException("Loài giày để trống ");
//            }
//            Tìm tên kích cỡ dựa trên tên
            int kcName = (int) row.getCell(3).getNumericCellValue();
            if(kcName <0){
                throw new IOException("Dữ liệu kichs cõw nhập vào không hợp lệ");
            }
            KichCo kc = kcr.findBySize(kcName);
            if (kc == null) {
                throw new IOException("size chưa tồn tại  ");
//                return;
            }
//            else if(kc != null){
//                throw new IOException("size chưa dc nhập   ");
//            }
//           Tìm tên chất liệu dựa trên tên
            String clName = row.getCell(4).getStringCellValue();
            ChatLieu cl = clr.findByTen(clName);
            if (cl == null) {
                throw new IOException("chất liệu chưa tồn tại  ");
            }
//            else if(cl != null ){
//                throw new IOException("Chất liệu ch được nhập ");
//            }
//            Tìm đế giày dựa trên tên

            String dgName = row.getCell(5).getStringCellValue();
            if(dgName.trim().isEmpty()){
                throw new IOException("Dữ liệu đế giày nhập vào không hợp lệ");
            }
            DeGiay dg = dgr.findByLoaiDe(dgName);
            if (dg == null) {
                throw new IOException("đế giày  chưa tồn tại  ");
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
            Double giaBan =row.getCell(6).getNumericCellValue();
            int SoLuong =(int) row.getCell(7).getNumericCellValue();
            String mota = row.getCell(8).getStringCellValue();
            int tt = (int) row.getCell(9).getNumericCellValue();
            if(mota.trim().isEmpty()){
                throw new IOException("Mô tả chưa được nhập ");
            }
            ctsp.setGiaBan(giaBan);
            ctsp.setSoLuong(SoLuong);
            ctsp.setMoTaCT(mota);
            ctsp.setTrangThai(tt);
            ctspr.save(ctsp);
        }
        workbook.close();
    }

//    @Override
//    @Transactional
//    public void processExcelFile(MultipartFile file) throws IOException {
//        if (file.isEmpty()) {
//            throw new IllegalArgumentException("Vui lòng chọn 1 tệp Excel");
//        }
//        List<ChiTietSanPham> list =readExcel(file);
//        for(ChiTietSanPham ctsp : list){
//            ctspr.save(ctsp);
//        }
//    }
//
//
//
//    //    public void insertDataFromExcel(MultipartFile file) throws IOException {
////        List<ProductDetailDTO> productDetailDTOS = readDataFromExcel(file);
////
////    }
////
////    private List<ProductDetailDTO> readDataFromExcel(MultipartFile file) throws IOException {
////        List<ProductDetailDTO> productDetailDTOS = new ArrayList<>();
////        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
////            Sheet sheet = workbook.getSheetAt(0);
////            for (Row row : sheet) {
////                if (row.getRowNum() == 0) {
////                    continue;
////                }
////                ProductDetailDTO pro = new ProductDetailDTO();
////
////                pro.setMauSac(row.getCell(1).getStringCellValue());
////                pro.setTheLoai(row.getCell(2).getStringCellValue());
////                pro.setKichCo(row.getCell(3).getStringCellValue());
////                pro.setChatLieu(row.getCell(4).getStringCellValue());
////                pro.setDeGiay(row.getCell(5).getStringCellValue());
////                pro.setGia(row.getCell(6).getNumericCellValue());
////                pro.setSoLuong(row.getCell(7).getColumnIndex());
////                pro.setMota(row.getCell(8).getStringCellValue());
////                pro.setTrangthai(row.getCell(9).getColumnIndex());
////                productDetailDTOS.add(pro);
////            }
////        }
////        return productDetailDTOS;
////    }
////private void saveSPCT (ProductDetailDTO dto){
////        ChiTietSanPham ctsp = new ChiTietSanPham();
////        SanPham sp = this.spr.findById();
////}
//    private List<ChiTietSanPham> readExcel(MultipartFile file) throws IOException {
//        List<ChiTietSanPham> listsp = new ArrayList<>();
//
//        try (InputStream inputStream = file.getInputStream()) {
//            Workbook workbook = WorkbookFactory.create(inputStream);
//            Sheet sheet = workbook.getSheetAt(0); // Giả sử dữ liệu ở sheet đầu tiên
//
//            Iterator<Row> rowIterator = sheet.iterator();
//            rowIterator.next(); // Bỏ qua dòng tiêu đề
//
//            while (rowIterator.hasNext()) {
//                Row row = rowIterator.next();
//                ChiTietSanPham ctsp = new ChiTietSanPham();
//                SanPham sp = new SanPham();
//                MauSac ms = new MauSac();
//                LoaiGiay lg = new LoaiGiay();
//                KichCo kc = new KichCo();
//                ChatLieu cl = new ChatLieu();
//                DeGiay dg = new DeGiay();
//                sp.setTenSanPham(row.getCell(0).getStringCellValue());
//                ms.setTen(row.getCell(1).getStringCellValue());
//                lg.setTentheloai(row.getCell(2).getStringCellValue());
//                kc.setSize(row.getCell(3).getColumnIndex());
//                cl.setTen(row.getCell(4).getStringCellValue());
//                dg.setLoaiDe(row.getCell(5).getStringCellValue());
//                ctsp.setGiaBan(row.getCell(6).getNumericCellValue());
//                ctsp.setMoTaCT(row.getCell(7).getStringCellValue());
//                ctsp.setTrangThai(row.getCell(8).getColumnIndex());
//                // Assume your Excel columns are in order: Name, Age, FullAddress
////            person.setName(row.getCell(0).getStringCellValue());
////            person.setAge((int) row.getCell(1).getNumericCellValue());
////            address.setFullAddress(row.getCell(2).getStringCellValue());
////
////            person.setAddress(address);
//                ctsp.setSanPham(sp);
//                ctsp.setMauSac(ms);
//                ctsp.setLoaiGiay(lg);
//                ctsp.setKichCo(kc);
//                ctsp.setChatLieu(cl);
//                ctsp.setDeGiay(dg);
//                listsp.add(ctsp);
//            }
//        } catch (Exception e) {
//            throw new IOException("Lỗi khi đọc tệp Excel: " + e.getMessage(), e);
//        }
//
//        return listsp;
//    }
}

