package com.example.Website_sell_soccer_shoes_bumblebee.utils;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChiTietSanPham;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.Map;

public class QRCodeGenerator {
    public static void generatorQRCode(ChiTietSanPham sp, String qrCodeFolderPath) throws WriterException, IOException {
        // gen qr code
        File existingQRCodeFile = new File(qrCodeFolderPath + File.separator + sp.getId() + ".png");
        if (existingQRCodeFile.exists()) {
            // Mã QR code đã tồn tại, tiến hành cập nhật
            String qrCodeData = String.valueOf(sp.getId());

            // Cấu hình mã QR code
            Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8.name());

            QRCodeWriter qrCodeWriter = new QRCodeWriter();

            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, 400, 400, hints);
            Path path = FileSystems.getDefault().getPath(existingQRCodeFile.getAbsolutePath());
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        } else {
//            String qrCodeData = String.valueOf(sp.getId()+ sp.getSanPham().getTenSanPham());

            String qrCodeData = String.valueOf(sp.getId());            // Cấu hình mã QR code
            Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8.name());

            QRCodeWriter qrCodeWriter = new QRCodeWriter();

            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, 400, 400, hints);

            // Tạo đường dẫn và tên tệp mã QR code
            String qrCodeFileName = sp.getId() + ".png";
            String qrCodeFilePath = qrCodeFolderPath + File.separator + qrCodeFileName;

            // Lưu mã QR code vào thư mục đích
            Path path = FileSystems.getDefault().getPath(qrCodeFilePath);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        }
    }
}
