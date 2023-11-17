package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChiTietSanPham;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    public static List<ChiTietSanPham> readExcel(MultipartFile file) {
        List<ChiTietSanPham> list = new ArrayList<>();

        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                boolean isRowEmpty = true;
                ChiTietSanPham ctsp = new ChiTietSanPham();

                // Lặp qua từng ô trong mỗi dòng
                for (Cell cell : row) {
                    // Kiểm tra nếu ô không trống
                    if (cell.getCellType().equals(CellType.BLANK)) {
                        isRowEmpty = false;

                        switch (cell.getColumnIndex()) {
                            case 0:
                                ctsp.setGiaBan(cell.getNumericCellValue());
                                break;
                            case 1:
                                ctsp.setSoLuong((int)cell.getNumericCellValue());
                                break;
                            case 3 :
                                ctsp.setMoTaCT(cell.getStringCellValue());
                                break;
                            case 4 :
                                ctsp.setTrangThai((int)cell.getNumericCellValue());
                                // Thêm các trường khác nếu cần
                        }
                    }
                }

                // Kiểm tra dòng có trống không
                if (isRowEmpty) {
                    System.out.println("Error: Empty row detected at index " + row.getRowNum());
                    // Thông báo lỗi hoặc xử lý theo nhu cầu của bạn
                } else {
                    list.add(ctsp);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
