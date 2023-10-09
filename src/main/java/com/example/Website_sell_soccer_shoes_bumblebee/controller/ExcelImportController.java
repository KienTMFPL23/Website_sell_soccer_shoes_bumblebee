//package com.example.Website_sell_soccer_shoes_bumblebee.controller;
//
//import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChiTietSanPham;
//import com.example.Website_sell_soccer_shoes_bumblebee.entity.KichCo;
//import com.example.Website_sell_soccer_shoes_bumblebee.entity.SanPham;
//import org.apache.poi.ss.usermodel.*;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//@Controller
//
//public class ExcelImportController {
//    @GetMapping("/import")
//    public String importPage() {
//        return "import"; // Trả về trang JSP để tải tệp Excel
//    }
//
//    @PostMapping("/import")
//    public String importExcel(@RequestParam("file") MultipartFile file) {
//        int sanPhamColumnIndex = -1; // Khởi tạo giá trị mặc định
//        Row headerRow = sheet.getRow(0); // Hàng đầu tiên là hàng chứa tiêu đề
//        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
//            Cell cell = headerRow.getCell(i);
//            if (cell != null && "SanPhamColumnHeader".equals(cell.getStringCellValue())) {
//                sanPhamColumnIndex = i;
//                break;
//            }
//        }
//        if (!file.isEmpty()) {
//            try {
//                InputStream inputStream = file.getInputStream();
//                Workbook workbook = WorkbookFactory.create(inputStream);
//                Sheet sheet = workbook.getSheetAt(0);
//
//                List<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();
//
//                Iterator<Row> rowIterator = sheet.iterator();
//
//                while (rowIterator.hasNext()) {
//                    Row row = rowIterator.next();
//                    ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
//
//                    // Ánh xạ dữ liệu từ các ô trong dòng vào các thuộc tính của ChiTietSanPham
//                    chiTietSanPham.setGiaBan(row.getCell(0).getNumericCellValue());
//                    chiTietSanPham.setSoLuong((int) row.getCell(1).getNumericCellValue());
//                    chiTietSanPham.setMoTaCT(row.getCell(2).getStringCellValue());
//
//                    // Ánh xạ dữ liệu cho thuộc tính sanPham
//                    String sanPhamId = row.getCell(sanPhamColumnIndex).getStringCellValue();
//                    SanPham sanPham = sanPhamRepository.findById(UUID.fromString(sanPhamId)).orElse(null);
//                    chiTietSanPham.setSanPham(sanPham);
//
//                    // Ánh xạ dữ liệu cho thuộc tính kichCo
//                    String kichCoId = row.getCell(kichCoColumnIndex).getStringCellValue();
//                    KichCo kichCo = kichCoRepository.findById(UUID.fromString(kichCoId)).orElse(null);
//                    chiTietSanPham.setKichCo(kichCo);
//
//                    // Ánh xạ dữ liệu cho các thuộc tính khác tương tự
//
//                    chiTietSanPhams.add(chiTietSanPham);
//                }
//
//                // Lưu danh sách chiTietSanPhams vào cơ sở dữ liệu
//                chiTietSanPhamRepository.saveAll(chiTietSanPhams);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//                // Xử lý lỗi nếu có
//            }
//        }
//
//        return "redirect:/import";
//    }
//}
