package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;


import com.example.Website_sell_soccer_shoes_bumblebee.entity.*;

import com.example.Website_sell_soccer_shoes_bumblebee.repository.HoaDonChiTietRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.HoaDonRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.NhanVienRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.HoaDonService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

import jakarta.servlet.http.HttpSession;
import org.apache.commons.math3.analysis.function.Identity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import java.time.LocalDateTime;

import java.util.*;

@Service
@Transactional
public class HoaDonServiceImpl implements HoaDonService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    NhanVienRepository nhanVienRepository;

    @Autowired
    HttpSession session;


    @Override
    public Page<HoaDon> search(String key, Pageable pageable) {
        if (key != null) {
            return hoaDonRepository.search(key, pageable);
        }
        return hoaDonRepository.findAll(pageable);
    }

    @Override
    public Page<HoaDon> searchALlBetweenDates(Date fromDate, Date toDate, Pageable pageable) {
        return hoaDonRepository.searchALlBetweenDates(fromDate, toDate, pageable);
    }

    @Override
    public HoaDon searchHoaDon(String hoaDon) {
        return hoaDonRepository.searchHoaDon(hoaDon);
    }

    @Override
    public List<HoaDon> findAll() {
        return hoaDonRepository.findAll();
    }

    @Override
    public List<HoaDon> listHoaDonCho() {
        return hoaDonRepository.getListByTrangThai();
    }

    private static int maHoaDon = 1;

    @Override
    public HoaDon createHoaDon() throws ParseException {
        HoaDon hoaDon = new HoaDon();
        String formatHoaDon = "HD" + String.format("%08d", maHoaDon);
        hoaDon.setMaHoaDon(formatHoaDon);
        maHoaDon++;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String format = sdf.format(date);
        hoaDon.setNgayTao(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(format));
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("userLogged");
        NhanVien nhanVien = nhanVienRepository.findByIdTaiKhoan(taiKhoan.getId());
        hoaDon.setNhanVien(nhanVien);
        hoaDon.setTrangThai(0);
        return hoaDonRepository.save(hoaDon);
    }

    @Override
    public Page<HoaDon> searchByStatusBills(int status, Pageable pageable) {
        return hoaDonRepository.searchByStatusBills(status,pageable);
    }

    @Override
    public HoaDon saveHoaDon(HoaDon hoaDon) {
        return hoaDonRepository.save(hoaDon);
    }
//    @Override
//    public SanPham udpateSanPham(SanPham sanPham, UUID id) {
//        Optional<SanPham> exitingSanPham = sanPhamRepository.findById(id);
//        if (exitingSanPham.isPresent()) {
//            SanPham spToUpdate = exitingSanPham.get();
//            spToUpdate.setLastModifiedDate(LocalDateTime.now());
//            spToUpdate.setTenSanPham(sanPham.getTenSanPham());
//            spToUpdate.setTrangThai(sanPham.getTrangThai());
//            sanPhamRepository.save(spToUpdate);
//            return spToUpdate;
//        } else {
//            return null;
//        }
//    }
    @Override
    public HoaDon updateHoaDon(UUID id,Integer trangThai,HoaDon hoaDon) {
        Optional<HoaDon> exitingHoaDon = hoaDonRepository.findById(id);
        if (exitingHoaDon.isPresent()) {
            HoaDon hdToUpdate = exitingHoaDon.get();
            hdToUpdate.setSdt(hoaDon.getSdt());
            hdToUpdate.setMaHoaDon(hoaDon.getMaHoaDon());
            hdToUpdate.setGhiChu(hoaDon.getGhiChu());
            hdToUpdate.setDiaChiShip(hoaDon.getDiaChiShip());
            hdToUpdate.setHinhthucThanhToan(hoaDon.getHinhthucThanhToan());
//            hoaDon.set
            hdToUpdate.setTrangThai(trangThai);
            hoaDonRepository.save(hdToUpdate);
            return hdToUpdate;
        } else {
            return null;
        }
    }

    @Override
    public HoaDon deleteHoaDon(UUID id) {
        HoaDon hoaDon = hoaDonRepository.findById(id).orElse(null);
//        hoaDonChiTietRepository.deleteHDCTById(id);
        if (hoaDon != null) {
            hoaDonRepository.delete(hoaDon);
        }
        return hoaDon;
    }

    @Override
    public HoaDon getOne(UUID id) {
        return hoaDonRepository.findById(id).orElse(null);
    }

    @Override
    public List<HoaDon> getId(UUID id) {
        return hoaDonRepository.findId(id);
    }



    public void exportExcel(HttpServletResponse response) throws Exception {
        List<HoaDon> hoaDon = hoaDonRepository.findAll();


        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("hoaDon");
//        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        style.setFont(font);


        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("ID");
        row.getCell(0).setCellStyle(style);

        row.createCell(1).setCellValue("Mã HD");
        row.getCell(1).setCellStyle(style);

        row.createCell(2).setCellValue("Nhân Viên");
        row.getCell(2).setCellStyle(style);

        row.createCell(3).setCellValue("Ngày Tạo");
        row.getCell(3).setCellStyle(style);

        row.createCell(4).setCellValue("Ngày Thanh Toán");
        row.getCell(4).setCellStyle(style);

        row.createCell(5).setCellValue("Người Nhận");
        row.getCell(5).setCellStyle(style);

        row.createCell(6).setCellValue("Khách Hàng");
        row.getCell(6).setCellStyle(style);

        row.createCell(7).setCellValue("Địa Chỉ ship");
        row.getCell(7).setCellStyle(style);

        row.createCell(8).setCellValue("SĐT");
        row.getCell(8).setCellStyle(style);

        row.createCell(9).setCellValue("Ghi Chú");
        row.getCell(9).setCellStyle(style);

        row.createCell(10).setCellValue("Trạng Thái");
        row.getCell(10).setCellStyle(style);
        sheet.autoSizeColumn(1 - 10);


        int dataRowIndex = 1;
        for (HoaDon hd : hoaDon) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            DateFormat dateFormat = (DateFormat) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            dataRow.createCell(0).setCellValue(String.valueOf(hd.getId()));
            dataRow.createCell(1).setCellValue(String.valueOf(hd.getMaHoaDon()));
            dataRow.createCell(2).setCellValue(String.valueOf(hd.getNhanVien().getTen()));
            dataRow.createCell(3).setCellValue(dateFormat.format(hd.getNgayTao()));
            dataRow.createCell(4).setCellValue(dateFormat.format(hd.getNgayThanhToan()));
            dataRow.createCell(5).setCellValue(String.valueOf(hd.getTenNguoiNhan()));
//            dataRow.createCell(6).setCellValue(String.valueOf(hd.getKhachHang().getTen()));
            dataRow.createCell(7).setCellValue(String.valueOf(hd.getDiaChiShip()));
            dataRow.createCell(8).setCellValue(String.valueOf(hd.getSdt()));
            dataRow.createCell(9).setCellValue(String.valueOf(hd.getGhiChu()));
            dataRow.createCell(10).setCellValue(hd.getTrangThai());
            sheet.setDefaultColumnWidth(20);
            dataRowIndex++;

        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        ops.close();
    }

    @Override
    public List<HoaDon> listHoaDonMua(UUID idKH) {
        return hoaDonRepository.listHoaDonMua( idKH);
    }

    @Override
    public List<HoaDon> listHoaDonChoThanhToan(UUID idKH) {
        return hoaDonRepository.listHoaDonChoThanhToan(idKH);
    }

    @Override
    public List<HoaDon> listHoaDonDangChuanBi(UUID idKH) {
        return hoaDonRepository.listHoaDonDangChuanBi(idKH);
    }

    @Override
    public List<HoaDon> listHoaDonDangGiao(UUID idKH) {
        return hoaDonRepository.listHoaDonDangGiao(idKH);
    }

    @Override
    public List<HoaDon> listHoaDonHoanThanh(UUID idKH) {
        return hoaDonRepository.listHoaDonHoanThanh(idKH);
    }

    @Override
    public List<HoaDon> listHoaDonDaHuy(UUID idKH) {
        return hoaDonRepository.listHoaDonDaHuy(idKH);
    }

    @Override
    public List<HoaDon> listHoaDonTraHang(UUID idKH) {
        return hoaDonRepository.listHoaDonTraHang(idKH);
    }

    @Override
    public List<HoaDon> listHoaDonDaHoanTra(UUID idKH) {
        return hoaDonRepository.listHoaDonDaHoanTra(idKH);
    }

    @Override
    public HoaDon hoaDonFindId(UUID id) {
        return hoaDonRepository.hoaDonFindId(id);
    }

}
