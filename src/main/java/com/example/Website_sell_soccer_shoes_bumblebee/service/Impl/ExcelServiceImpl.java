package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.*;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            rows.next(); // Bỏ qua dòng tiêu đề

            int expectedColumnCount = 11; // Thay đổi theo số lượng cột trong file Excel của bạn

            while (rows.hasNext()) {
                Row row = rows.next();

                // Kiểm tra xem số lượng cột có đúng không
                if (row.getLastCellNum() != expectedColumnCount) {
                    throw new IOException("Lỗi: File Excel này còn thiếu 1 số trường nhớ bổ xung");
                }

                ChiTietSanPham ctsp = createChiTietSanPhamFromRow(row);
                saveOrUpdateData(ctsp);
            }
        }
    }

    private ChiTietSanPham createChiTietSanPhamFromRow(Row row) throws IOException {
        String spName = getCellValueAsString(row.getCell(0));
        String msName = getCellValueAsString(row.getCell(1));
        String tlName = getCellValueAsString(row.getCell(2));
        int kcName = getCellValueAsInt(row.getCell(3));
        String clName = getCellValueAsString(row.getCell(4));
        String dgName = getCellValueAsString(row.getCell(5));
        Double giaBan = getCellValueAsDouble(row.getCell(6));
        int soLuong = getCellValueAsInt(row.getCell(7));
        String mota = getCellValueAsString(row.getCell(8));
        Date ngayTao = row.getCell(9).getDateCellValue();
        int tt = getCellValueAsInt(row.getCell(10));

        // Kiểm tra và báo lỗi nếu có trường dữ liệu trống
        if (StringUtils.isAnyBlank(spName, msName, tlName, clName, dgName, mota)) {
            throw new IOException("Lỗi: Dữ liệu không được để trống.");
        }

        ChiTietSanPham ctsp = new ChiTietSanPham();

        // Lấy hoặc tạo các đối tượng liên qua
        SanPham sp = spr.findByTenSp(spName);
        MauSac ms = msr.findByTen(msName);
        LoaiGiay lg = lgr.findbyten(tlName);
        KichCo kc = kcr.findBySize(kcName);
        ChatLieu cl = clr.findByTen(clName);
        DeGiay dg = dgr.findByLoaiDe(dgName);

        ctsp.setSanPham(sp);
        ctsp.setMauSac(ms);
        ctsp.setLoaiGiay(lg);
        ctsp.setKichCo(kc);
        ctsp.setChatLieu(cl);
        ctsp.setDeGiay(dg);
        ctsp.setGiaBan(giaBan);
        ctsp.setSoLuong(soLuong);
        ctsp.setMoTaCT(mota);
        ctsp.setNgayTao(ngayTao);
        ctsp.setTrangThai(tt);

        return ctsp;
    }

    private void saveOrUpdateData(ChiTietSanPham ctsp) throws IOException {
        // Kiểm tra và báo lỗi nếu dữ liệu đã tồn tại trong database
        if (ctspr.existsBySanPhamAndMauSacAndLoaiGiayAndKichCoAndChatLieuAndDeGiay(
                ctsp.getSanPham(), ctsp.getMauSac(), ctsp.getLoaiGiay(), ctsp.getKichCo(),
                ctsp.getChatLieu(), ctsp.getDeGiay())) {
            throw new IOException("Lỗi: Dữ liệu đã tồn tại.");
        }

        ctspr.save(ctsp);
    }

    private String getCellValueAsString(Cell cell) {
        return (cell != null && cell.getCellType() == CellType.STRING) ? cell.getStringCellValue() : "";
    }

    private int getCellValueAsInt(Cell cell) {
        return (cell != null && cell.getCellType() == CellType.NUMERIC) ? (int) cell.getNumericCellValue() : 0;
    }

    private Double getCellValueAsDouble(Cell cell) {
        return (cell != null && cell.getCellType() == CellType.NUMERIC) ? cell.getNumericCellValue() : 0.0;
    }
}
