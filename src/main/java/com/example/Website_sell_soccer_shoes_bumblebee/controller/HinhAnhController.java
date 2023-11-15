package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.*;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.ChiTietSanPhamRepo;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.HinhAnhRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.LoaiGiayRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.HinhAnhService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.LoaiGiayService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
public class HinhAnhController {
    @Autowired
    HinhAnhService service;

    @Autowired
    HinhAnhRepository repository;


    @GetMapping("/hinh-anh/hien-thi")
    public String hienThi(Model model, @RequestParam(defaultValue = "0") int p) {
        model.addAttribute("view", "../hinh-anh/list.jsp");
        if (p < 0) {
            p = 0;
        }
        Pageable pageable = PageRequest.of(p, 5);
        Page<HinhAnh> page = service.listHinhAnh(pageable);
        model.addAttribute("page", page);
        return "/admin/index";
    }

    @GetMapping("/hinh-anh/hien-thi/list")
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(service.getAll());
    }

//    @GetMapping("/hinh-anh/view-add")
//    public String viewAdd(Model model, @ModelAttribute("hinhAnh") HinhAnh hinhAnh) {
//
//        model.addAttribute("action4", "/hinh-anh/add");
//        model.addAttribute("view", "../hinh-anh/add_update.jsp");
//        return "/admin/index";
//    }


    //    @PostMapping("/hinh-anh/add")
//    public String save(Model model,
//                       @RequestParam(name = "tenanh") MultipartFile tenanh,
//                       @RequestParam(name = "duongdan1") MultipartFile duongdan1,
//                       @RequestParam(name = "duongdan2") MultipartFile duongdan2,
//                       @RequestParam(name = "duongdan3") MultipartFile duongdan3,
//                       @RequestParam(name = "duongdan4") MultipartFile duongdan4,
//                       @RequestParam(name = "duongdan5") MultipartFile duongdan5,
//                       @RequestParam(name = "ctsp") ChiTietSanPham ctsp
//    ) {
//        HinhAnh hinhAnh = new HinhAnh();
//        hinhAnh.setCtsp(ctsp);
//
//        try {
//            // Lấy đường dẫn tới thư mục lưu trữ tệp tin ảnh từ cấu hình
//            String uploadPath = service.getImageUploadPath();
//
//            // Tạo thư mục lưu trữ nếu chưa tồn tại
//            Path uploadDir = Paths.get(uploadPath);
//            if (!Files.exists(uploadDir)) {
//                Files.createDirectories(uploadDir);
//            }
//
//            // Lưu trữ các tệp tin ảnh và sử dụng tên tệp tin làm đường dẫn
//            MultipartFile[] imageFiles = {tenanh, duongdan1, duongdan2, duongdan3, duongdan4, duongdan5};
//            for (int i = 0; i < imageFiles.length; i++) {
//                MultipartFile file = imageFiles[i];
//                if (file != null && !file.isEmpty()) {
//                    String fileName = file.getOriginalFilename().toLowerCase(); // Sử dụng tên tệp tin làm đường dẫn
//                    Path filePath = uploadDir.resolve(fileName);
//                    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//
//                    // Gán tên tệp tin ảnh và đường dẫn tới các thuộc tính tương ứng của đối tượng HinhAnh
//                    switch (i) {
//                        case 0:
//                            hinhAnh.setTenanh(fileName);
//                            break;
//                        case 1:
//                            hinhAnh.setDuongdan1(fileName);
//                            break;
//                        case 2:
//                            hinhAnh.setDuongdan2(fileName);
//                            break;
//                        case 3:
//                            hinhAnh.setDuongdan3(fileName);
//                            break;
//                        case 4:
//                            hinhAnh.setDuongdan4(fileName);
//                            break;
//                        case 5:
//                            hinhAnh.setDuongdan5(fileName);
//                            break;
//                        default:
//                            break;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        repository.save(hinhAnh);
//        return "redirect:/hinh-anh/hien-thi";
//    }
//
    @GetMapping("/hinh-anh/view-update/{id}")
    public String viewUpdate(Model model, @ModelAttribute("hinhAnh") HinhAnh hinhAnh, @PathVariable(name = "id") UUID id) {
        HinhAnh hinhAnh2 = service.findById(id);
        UUID idctsp = repository.getIdCTSP(id);
        model.addAttribute("idctsp", idctsp);
        model.addAttribute("listHinhAnh", hinhAnh2);
        model.addAttribute("action4", "/hinh-anh/update/" + hinhAnh2.getId());
        model.addAttribute("view", "../hinh-anh/add_update.jsp");
        return "/admin/index";
    }

    @RequestMapping("/hinh-anh/update/{id}")
    public String update(@PathVariable(name = "id") UUID id,
                         @RequestParam(name = "tenanh") MultipartFile tenanh,
                         @RequestParam(name = "duongdan1") MultipartFile duongdan1,
                         @RequestParam(name = "duongdan2") MultipartFile duongdan2,
                         @RequestParam(name = "duongdan3") MultipartFile duongdan3,
//                         @RequestParam(name = "duongdan4") MultipartFile duongdan4,
//                         @RequestParam(name = "duongdan5") MultipartFile duongdan5,
                         @RequestParam(name = "ctsp") ChiTietSanPham ctsp, Model model) {
        HinhAnh hinhAnh = service.findById(id);
        model.addAttribute("hinhANh", hinhAnh);
        UUID idctsp = repository.getIdCTSP(id);
        model.addAttribute("idctsp", idctsp);
        // Cập nhật ảnh "tenanh" nếu người dùng đã chọn
        if (!tenanh.isEmpty()) {
            String newTenAnh = saveImage(tenanh);
            hinhAnh.setTenanh(newTenAnh);
        }
        if (!duongdan1.isEmpty()) {
            String newDuongdan1 = saveImage(duongdan1);
            hinhAnh.setDuongdan1(newDuongdan1);
        }
        if (!duongdan2.isEmpty()) {
            String newDuongdan2 = saveImage(duongdan2);
            hinhAnh.setDuongdan2(newDuongdan2);
        }
        if (!duongdan3.isEmpty()) {
            String newDuongdan3 = saveImage(duongdan3);
            hinhAnh.setDuongdan3(newDuongdan3);
        }
//        if (!duongdan4.isEmpty()) {
//            String newDuongdan4 = saveImage(duongdan4);
//            hinhAnh.setDuongdan4(newDuongdan4);
//        }
//        if (!duongdan5.isEmpty()) {
//            String newDuongdan5 = saveImage(duongdan5);
//            hinhAnh.setDuongdan5(newDuongdan5);
//        }


        repository.save(hinhAnh);

        return "redirect:/hinh-anh/hien-thi";
    }

    //
    @RequestMapping("/hinh-anh-ctsp/update/{id}")
    public String updateHinhAnhCTSP(@PathVariable(name = "id") UUID id,
                                    @RequestParam(name = "tenanh") MultipartFile tenanh,
                                    @RequestParam(name = "duongdan1") MultipartFile duongdan1,
                                    @RequestParam(name = "duongdan2") MultipartFile duongdan2,
                                    @RequestParam(name = "duongdan3") MultipartFile duongdan3,
//                                    @RequestParam(name = "duongdan4") MultipartFile duongdan4,
//                                    @RequestParam(name = "duongdan5") MultipartFile duongdan5,
                                    @RequestParam(name = "ctsp") ChiTietSanPham ctsp, Model model) {
        HinhAnh hinhAnh = service.findById(id);
        model.addAttribute("hinhANh", hinhAnh);
        UUID idctsp = repository.getIdCTSP(id);
        SanPham sp = service.getSanPhamByIDCTSP(idctsp);
        model.addAttribute("idctsp", idctsp);
        // Cập nhật ảnh "tenanh" nếu người dùng đã chọn
        if (!tenanh.isEmpty()) {
            String newTenAnh = saveImage(tenanh);
            hinhAnh.setTenanh(newTenAnh);
        }
        if (!duongdan1.isEmpty()) {
            String newDuongdan1 = saveImage(duongdan1);
            hinhAnh.setDuongdan1(newDuongdan1);
        }
        if (!duongdan2.isEmpty()) {
            String newDuongdan2 = saveImage(duongdan2);
            hinhAnh.setDuongdan2(newDuongdan2);
        }
        if (!duongdan3.isEmpty()) {
            String newDuongdan3 = saveImage(duongdan3);
            hinhAnh.setDuongdan3(newDuongdan3);
        }
//        if (!duongdan4.isEmpty()) {
//            String newDuongdan4 = saveImage(duongdan4);
//            hinhAnh.setDuongdan4(newDuongdan4);
//        }
//        if (!duongdan5.isEmpty()) {
//            String newDuongdan5 = saveImage(duongdan5);
//            hinhAnh.setDuongdan5(newDuongdan5);
//        }


        repository.save(hinhAnh);

        return "redirect:/chi-tiet-san-pham/list-san-pham/" + sp.getId();
    }

    //
    @RequestMapping("/hinh-anh-spct/update/{id}")
    public String updateHinhAnh(@PathVariable(name = "id") UUID id,
                                @RequestParam(name = "tenanh") MultipartFile tenanh,
                                @RequestParam(name = "duongdan1") MultipartFile duongdan1,
                                @RequestParam(name = "duongdan2") MultipartFile duongdan2,
                                @RequestParam(name = "duongdan3") MultipartFile duongdan3,
//                                @RequestParam(name = "duongdan4") MultipartFile duongdan4,
//                                @RequestParam(name = "duongdan5") MultipartFile duongdan5,
                                @RequestParam(name = "ctsp") ChiTietSanPham ctsp, Model model) {
        HinhAnh hinhAnh = service.findById(id);
        model.addAttribute("hinhANh", hinhAnh);
        UUID idctsp = repository.getIdCTSP(id);
        model.addAttribute("idctsp", idctsp);
        // Cập nhật ảnh "tenanh" nếu người dùng đã chọn
        if (!tenanh.isEmpty()) {
            String newTenAnh = saveImage(tenanh);
            hinhAnh.setTenanh(newTenAnh);
        }
        if (!duongdan1.isEmpty()) {
            String newDuongdan1 = saveImage(duongdan1);
            hinhAnh.setDuongdan1(newDuongdan1);
        }
        if (!duongdan2.isEmpty()) {
            String newDuongdan2 = saveImage(duongdan2);
            hinhAnh.setDuongdan2(newDuongdan2);
        }
        if (!duongdan3.isEmpty()) {
            String newDuongdan3 = saveImage(duongdan3);
            hinhAnh.setDuongdan3(newDuongdan3);
        }
//        if (!duongdan4.isEmpty()) {
//            String newDuongdan4 = saveImage(duongdan4);
//            hinhAnh.setDuongdan4(newDuongdan4);
//        }
//        if (!duongdan5.isEmpty()) {
//            String newDuongdan5 = saveImage(duongdan5);
//            hinhAnh.setDuongdan5(newDuongdan5);
//        }


        repository.save(hinhAnh);

        return "redirect:/chi-tiet-san-pham/hien-thi";
    }

    //
    private String saveImage(MultipartFile file) {

        String newImageFileName = UUID.randomUUID().toString() + ".jpg";
        String imagePath = service.getImageUploadPath() + File.separator + newImageFileName;

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(imagePath);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newImageFileName;
    }
}
