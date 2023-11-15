package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.MauSac;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.MauSacReponsitory;
import com.example.Website_sell_soccer_shoes_bumblebee.service.MauSacService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;


@Service
public class MauSacServiceImpl  implements MauSacService {
    @Autowired
    MauSacReponsitory msr;
    public Page<MauSac> search(String key, Pageable pageable){
        if(key != null){
            return msr.search(key,pageable);
        }
        return msr.findAll(pageable);
    }

    @Override
    public List<MauSac> getAll() {
        return msr.findAll();
    }
    public void saveDataFromExcel(MultipartFile file) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rows = sheet.iterator();
        rows.next(); // Skip header row

        while (rows.hasNext()) {
            Row row = rows.next();
            MauSac color = new MauSac();
            color.setMa(row.getCell(0).getStringCellValue());
            color.setTen(row.getCell(1).getStringCellValue());
            color.setTt((int)row.getCell(2).getNumericCellValue());

            // ... (Bạn có thể thêm các xử lý khác tùy thuộc vào yêu cầu của bạn)

            msr.save(color);
        }

        workbook.close();
    }
}
