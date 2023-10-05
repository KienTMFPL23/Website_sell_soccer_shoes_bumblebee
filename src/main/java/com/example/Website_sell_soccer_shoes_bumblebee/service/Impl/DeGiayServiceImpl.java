package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.DeGiay;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.DeGiayRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.DeGiayService;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DeGiayServiceImpl implements DeGiayService {

    @Autowired
    private DeGiayRepository deGiayRepository;



    @Override
    public List<DeGiay> getList() {
        return deGiayRepository.findAll();
    }

    @Override
    public Page<DeGiay> getListPage(Pageable pageable) {
        return deGiayRepository.getPage(pageable);
    }

    @Override
    public DeGiay add(DeGiay deGiay) {
        return deGiayRepository.save(deGiay);
    }

    @Override
    public DeGiay findById(UUID id) {
        return deGiayRepository.findById(id).orElse(null);
    }

    @Override
    public DeGiay findByMa(String ma) {
        return deGiayRepository.findByMa(ma);
    }

    @Override
    public List<DeGiay> search(String keyword) {
        return deGiayRepository.search(keyword);
    }

    @Override
    public Page<DeGiay> sort(Pageable pageable) {
        return deGiayRepository.sort(pageable);
    }

    Workbook workbook;

    @Value("${app.upload.file:${user.home}}")
    public String EXCEL_FILE_PATH;

    @Override
    public List<DeGiay> getExcelDataAsList() {
        List<String> list = new ArrayList<String>();

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        // Create the Workbook
        try {
            workbook = WorkbookFactory.create(new File(EXCEL_FILE_PATH));
        } catch (EncryptedDocumentException | IOException e) {
            e.printStackTrace();
        }

        // Retrieving the number of sheets in the Workbook
        System.out.println("-------Workbook has '" + workbook.getNumberOfSheets() + "' Sheets-----");

        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        // Getting number of columns in the Sheet
        int noOfColumns = sheet.getRow(0).getLastCellNum();
        System.out.println("-------Sheet has '" + noOfColumns + "' columns------");

        // Using for-each loop to iterate over the rows and columns
        for (Row row : sheet) {
            for (Cell cell : row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                list.add(cellValue);
            }
        }

        // filling excel data and creating list as List<Invoice>
        List<DeGiay> deGiay = createList(list, noOfColumns);

        // Closing the workbook
        try {
            workbook.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return deGiay;
    }

    private List<DeGiay> createList(List<String> excelData, int noOfColumns) {

        ArrayList<DeGiay> listDeGiay = new ArrayList<DeGiay>();

        int i = noOfColumns;
        do {
            DeGiay deGiay = new DeGiay();

            deGiay.setMa(excelData.get(i));
            deGiay.setLoaiDe(excelData.get(i + 1));
            deGiay.setTrangThai(Integer.valueOf(excelData.get(i + 2)));

            listDeGiay.add(deGiay);
            i = i + (noOfColumns);

        } while (i < excelData.size());
        return listDeGiay;
    }


    @Override
    public int saveExcelData(List<DeGiay> deGiay) {
        deGiay = deGiayRepository.saveAll(deGiay);
        return deGiay.size();
    }

    public List<DeGiay> invoiceExcelReaderService() {
        return null;
    }

    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    @Override
    public void uploadFile(MultipartFile file) {

        try {
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!");
        }
    }


}
