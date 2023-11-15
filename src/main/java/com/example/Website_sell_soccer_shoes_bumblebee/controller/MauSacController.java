package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.ChatLieu;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.LoaiGiay;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.MauSac;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.MauSacReponsitory;
import com.example.Website_sell_soccer_shoes_bumblebee.service.Impl.MauSacServiceImpl;
import com.example.Website_sell_soccer_shoes_bumblebee.service.MauSacService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller

public class MauSacController {
    @Autowired
    MauSacReponsitory msr;
    @Autowired
    MauSacServiceImpl mss;
    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(0, "K còn hoạt động");
        dsTrangThai.put(1, "Hoạt động");
        return dsTrangThai;
    }

    @GetMapping("mau-sac/hien-thi")
    public String hien(@Param("key") String key, Model model, @ModelAttribute("ms") MauSac mauSac, @RequestParam(defaultValue = "1") int page) {


//        if (page < 1) {
//            page = 1;
//        }
//        Pageable pageable = PageRequest.of(page - 1, 6);
//        Page<MauSac> pgg = this.mss.search(key, pageable);
//        model.addAttribute("list", pgg);
////        model.addAttribute("ms", mauSac);
        model.addAttribute("view", "../mau_sac/index.jsp");
        return "/admin/index";
    }

    //    @GetMapping("delete/{id}")
//    public String xoa(@PathVariable("id") MauSac ms) {
//        this.msr.delete(ms);
//        return "redirect:/mau-sac/hien-thi";
//
//    }
     @GetMapping("/mau-sac/hien-thi/list")
     public ResponseEntity<?> index(@RequestParam(defaultValue = "0",name = "page")Integer page,Model model){
        return ResponseEntity.ok(msr.findAll());
     }

    @RequestMapping("mau-sac/update/{id}")
    public String vupdate(@PathVariable("id") UUID id, Model model) {
        MauSac ms = this.msr.findById(id).orElse(null);
        model.addAttribute("ms", ms);
        model.addAttribute("view", "../mau_sac/update.jsp");
        return "/admin/index";
    }

    @GetMapping("/hien-thi-add")
    public String vthem(Model model, MauSac ms) {
        model.addAttribute("ms", ms);
        model.addAttribute("view", "../mau_sac/add.jsp");
        return "/admin/index";
    }

    @RequestMapping("mau-sac/update")
    public String update( Model model,
                         @Valid @ModelAttribute("ms") MauSac cl,
                         BindingResult result
    ) {
        Boolean hasError = result.hasErrors();
        if (hasError) {
            // Báo lỗi
            model.addAttribute("view", "../chat_lieu/add_update.jsp");
            return "/admin/index";
        }

        this.msr.save(cl);
        model.addAttribute("view", "../mau_sac/index.jsp");
        return "redirect:/mau-sac/hien-thi";
    }

    @PostMapping("mau-sac/add")
    public String add(@Valid @ModelAttribute("ms") MauSac ms, BindingResult result, Model model) {
        Boolean hasE = result.hasErrors();
        List<MauSac> list = msr.findAll();
        for(int i = 0;i<list.size();i++){
            if (result.hasErrors()) {
                return "mau_sac/update";
            }else if(ms.getMa().length()==0){
                model.addAttribute("errorMa", "Ma màu khong duoc bo trong");
                hasE = true;
            }
            else if(list.get(i).getMa().equals(ms.getMa())){
                model.addAttribute("errorMa", "Ma màu khong duoc trung");
                hasE = true;
            }else if(list.get(i).getTen().equals(ms.getTen())){
                model.addAttribute("errorMa", "tên màu khong duoc trung");
                hasE = true;
            }
            if(hasE){
                model.addAttribute("view", "../mau_sac/update.jsp");
                return "redirect:/mau-sac/hien-thi";
            } else{
                this.msr.save(ms);
            }
        }
        model.addAttribute("view", "../mau_sac/index.jsp");
        return "redirect:/mau-sac/hien-thi";
    }
    @RequestMapping("/mau-sac/sort")
    public String Sort(@RequestParam(defaultValue = "0") int pageNum, Model model) {
        if (pageNum < 0){
            pageNum = 0;
        }
        Pageable pageable = PageRequest.of(pageNum, 5);
        Page<MauSac> page = msr.sort(pageable);
        model.addAttribute("list", page);
        model.addAttribute("view", "../mau_sac/list.jsp");
        return "/admin/index";
    }
    @PostMapping("/mau-sac/import")
    public String uploadExcelFile(@RequestParam("file") MultipartFile file, Model model) {
        try {
            mss.saveDataFromExcel(file);
//        return ResponseEntity.ok("File uploaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while uploading the file.");
        }
        model.addAttribute("view", "../mau_sac/index.jsp");
        return "redirect:/mau-sac/hien-thi";
    }
}
