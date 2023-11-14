package com.example.Website_sell_soccer_shoes_bumblebee.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ExcelService {
    void processExcelFile(MultipartFile file) throws IOException;

}
