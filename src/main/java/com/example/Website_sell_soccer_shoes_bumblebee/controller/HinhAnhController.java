package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChiTietSanPham;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.DeGiay;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.HinhAnh;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.LoaiGiay;
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
    public String index(Model model) {

        model.addAttribute("view", "../hinh-anh/list.jsp");
        return "/admin/index";
    }

    @GetMapping("/hinh-anh/hien-thi/list")
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/hinh-anh/view-add")
    public String viewAdd(Model model, @ModelAttribute("hinhAnh") HinhAnh hinhAnh) {
        model.addAttribute("action", "/hinh-anh/add");
        model.addAttribute("view", "../hinh-anh/add_update.jsp");
        return "/admin/index";
    }

    @PostMapping("/hinh-anh/add")
    public String save(Model model,
                       @RequestParam(name = "tenanh") MultipartFile tenanh,
                       @RequestParam(name = "duongdan1") MultipartFile duongdan1,
                       @RequestParam(name = "duongdan2") MultipartFile duongdan2,
                       @RequestParam(name = "duongdan3") MultipartFile duongdan3,
                       @RequestParam(name = "duongdan4") MultipartFile duongdan4,
                       @RequestParam(name = "duongdan5") MultipartFile duongdan5,
                       @RequestParam(name = "ctsp") ChiTietSanPham ctsp
    ) {
        HinhAnh hinhAnh = new HinhAnh();
        hinhAnh.setCtsp(ctsp);
        Path path = Paths.get("C:\\Users\\NamNguyenTien\\Desktop\\Website_sell_soccer_shoes_bumblebee\\src\\main\\webapp\\uploads");
        try {
            InputStream input1 = tenanh.getInputStream();
            Files.copy(input1, path.resolve(tenanh.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

            InputStream input2 = duongdan1.getInputStream();
            Files.copy(input2, path.resolve(duongdan1.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

            InputStream input3 = duongdan2.getInputStream();
            Files.copy(input3, path.resolve(duongdan2.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

            InputStream input4 = duongdan3.getInputStream();
            Files.copy(input4, path.resolve(duongdan3.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

            InputStream input5 = duongdan4.getInputStream();
            Files.copy(input5, path.resolve(duongdan4.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

            InputStream input6 = duongdan5.getInputStream();
            Files.copy(input6, path.resolve(duongdan5.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

            hinhAnh.setTenanh(tenanh.getOriginalFilename().toLowerCase());
            hinhAnh.setDuongdan1(duongdan1.getOriginalFilename().toLowerCase());
            hinhAnh.setDuongdan2(duongdan2.getOriginalFilename().toLowerCase());
            hinhAnh.setDuongdan3(duongdan3.getOriginalFilename().toLowerCase());
            hinhAnh.setDuongdan4(duongdan4.getOriginalFilename().toLowerCase());
            hinhAnh.setDuongdan5(duongdan5.getOriginalFilename().toLowerCase());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        repository.save(hinhAnh);
        return "redirect:/hinh-anh/hien-thi";
    }

    @GetMapping("/hinh-anh/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable(name = "id") UUID id){
        HinhAnh hinhAnh = service.findById(id);
        model.addAttribute("listHinhAnh", hinhAnh);
        model.addAttribute("action", "/hinh-anh/update/" + hinhAnh.getId());
        model.addAttribute("view", "../hinh-anh/add_update.jsp");
        return "/admin/index";
    }

    @PostMapping("/hinh-anh/update/{id}")
    public String update(Model model,
                       @RequestParam(name = "tenanh") MultipartFile tenanh,
                       @RequestParam(name = "duongdan1") MultipartFile duongdan1,
                       @RequestParam(name = "duongdan2") MultipartFile duongdan2,
                       @RequestParam(name = "duongdan3") MultipartFile duongdan3,
                       @RequestParam(name = "duongdan4") MultipartFile duongdan4,
                       @RequestParam(name = "duongdan5") MultipartFile duongdan5,
                       @RequestParam(name = "ctsp") ChiTietSanPham ctsp,
                         @PathVariable(name = "id") UUID id
    ) {
        HinhAnh hinhAnh = new HinhAnh();
        hinhAnh.setCtsp(ctsp);
        Path path = Paths.get("C:\\Users\\NamNguyenTien\\Desktop\\Website_sell_soccer_shoes_bumblebee\\src\\main\\webapp\\uploads");
        try {
            InputStream input1 = tenanh.getInputStream();
            Files.copy(input1, path.resolve(tenanh.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

            InputStream input2 = duongdan1.getInputStream();
            Files.copy(input2, path.resolve(duongdan1.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

            InputStream input3 = duongdan2.getInputStream();
            Files.copy(input3, path.resolve(duongdan2.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

            InputStream input4 = duongdan3.getInputStream();
            Files.copy(input4, path.resolve(duongdan3.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

            InputStream input5 = duongdan4.getInputStream();
            Files.copy(input5, path.resolve(duongdan4.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

            InputStream input6 = duongdan5.getInputStream();
            Files.copy(input6, path.resolve(duongdan5.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

            hinhAnh.setTenanh(tenanh.getOriginalFilename().toLowerCase());
            hinhAnh.setDuongdan1(duongdan1.getOriginalFilename().toLowerCase());
            hinhAnh.setDuongdan2(duongdan2.getOriginalFilename().toLowerCase());
            hinhAnh.setDuongdan3(duongdan3.getOriginalFilename().toLowerCase());
            hinhAnh.setDuongdan4(duongdan4.getOriginalFilename().toLowerCase());
            hinhAnh.setDuongdan5(duongdan5.getOriginalFilename().toLowerCase());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        HinhAnh findId = service.findById(id);
        hinhAnh.setId(findId.getId());
        BeanUtils.copyProperties(hinhAnh, findId);
        repository.save(findId);
        return "redirect:/hinh-anh/hien-thi";
    }


}
