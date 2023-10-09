package com.example.Website_sell_soccer_shoes_bumblebee.controller;

import com.example.Website_sell_soccer_shoes_bumblebee.dto.ChiTietSanPhamDto;
import com.example.Website_sell_soccer_shoes_bumblebee.entity.*;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.ChiTietSanPhamRepo;
import com.example.Website_sell_soccer_shoes_bumblebee.repository.HinhAnhRepository;
import com.example.Website_sell_soccer_shoes_bumblebee.service.ChiTietSanPhamService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.KichCoService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.LoaiGiayService;
import com.example.Website_sell_soccer_shoes_bumblebee.service.MauSacService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.AbstractPersistable_;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    LoaiGiayService loaiGiayService;

    @Autowired
    KichCoService kichCoService;

    @Autowired
    MauSacService mauSacService;

    @Autowired
    ChiTietSanPhamRepo chiTietSanPhamRepo;

    @Data
    public static class SortForm {
        String key;
    }

    @Autowired
    HinhAnhRepository hinhAnhRepository;

    @Data
    public static class SearchFormByKichCo {
        Double key;
    }

    @Data
    public static class SearchFormByGiaBan {
        Double minPrice;
        Double maxPrice;
    }


    @RequestMapping("/bumblebee/home")
    public String index(Model model, @RequestParam(defaultValue = "0") int p) {
        Pageable pageable = PageRequest.of(p, 8);
        Page<ChiTietSanPham> listSP = chiTietSanPhamService.getListSP(pageable);
        model.addAttribute("listSP", listSP);
        model.addAttribute("view", "../template_home/home.jsp");
        return "template_home/index";
    }

    @RequestMapping("/bumblebee/detail/{id}")
    public String detail(Model model, @PathVariable UUID id) {
        System.out.println("-----------------------------" + id);
        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getOne(id);
        List<KichCo> listKC = kichCoService.getList();
        HinhAnh hinhAnh = chiTietSanPhamRepo.getHADetail(id);
        model.addAttribute("hinhAnh",hinhAnh);
        List<ChiTietSanPham> listSP = chiTietSanPhamService.getList();
        List<MauSac> listMS = mauSacService.getAll();
        model.addAttribute("listMS", listMS);
        model.addAttribute("listSP",listSP);
        model.addAttribute("listKC", listKC);
        model.addAttribute("ctsp", chiTietSanPham);
        model.addAttribute("view", "../template_home/product_detail.jsp");
        return "template_home/index";
    }

    @RequestMapping("/bumblebee/cart")
    public String cart(Model model) {
        model.addAttribute("view", "../template_home/cart.jsp");
        return "template_home/index";
    }

    @RequestMapping("/bumblebee/product_list/sort")
    public String sort(Model model, @RequestParam(defaultValue = "0") int p,
                       @ModelAttribute("sortForm") SortForm sortForm,
                       @ModelAttribute("searchFormByGiaban") SearchFormByGiaBan searchFormByGiaBan) {
        Sort sort = Sort.by(Sort.Direction.ASC, sortForm.key);
        Pageable pageable = PageRequest.of(p, 12, sort);
        Page<ChiTietSanPham> pageCTSP = chiTietSanPhamService.getListSP(pageable);
        List<ChiTietSanPham> listSP = chiTietSanPhamService.getList();
        List<LoaiGiay> listLG = loaiGiayService.findAll();
        List<KichCo> listKC = kichCoService.getList();
        List<MauSac> listMS = mauSacService.getAll();
        model.addAttribute("listSP", listSP);
        model.addAttribute("pageSP", pageCTSP);
        model.addAttribute("listLG", listLG);
        model.addAttribute("listKC", listKC);
        model.addAttribute("listMS", listMS);
        model.addAttribute("view", "../template_home/product_listing.jsp");
        return "template_home/index";
    }

    @RequestMapping("/bumblebee/product_list")
    public String product_list(Model model, @RequestParam(defaultValue = "0") int p,
                               @ModelAttribute("sortForm") SortForm sortForm,
                               @ModelAttribute("searchFormByGiaban") SearchFormByGiaBan searchFormByGiaBan) {
        Pageable pageable = PageRequest.of(p, 12);
        Page<ChiTietSanPham> pageCTSP = chiTietSanPhamService.getListSP(pageable);
    //    List<ChiTietSanPhamDto> pageCTSP = chiTietSanPhamService.getListChiTietSanPhamHinhAnh();
        List<ChiTietSanPham> listSP = chiTietSanPhamService.getList();
        List<LoaiGiay> listLG = loaiGiayService.findAll();
        List<KichCo> listKC = kichCoService.getList();
        List<MauSac> listMS = mauSacService.getAll();
        model.addAttribute("listSP", listSP);
        model.addAttribute("pageSP", pageCTSP);
        model.addAttribute("listLG", listLG);
        model.addAttribute("listKC", listKC);
        model.addAttribute("listMS", listMS);
        model.addAttribute("view", "../template_home/product_listing.jsp");
        return "template_home/index";
    }

    @RequestMapping("/bumblebee/product_list/searchbygiaban")
    public String getListByGiaBan(Model model, @RequestParam(defaultValue = "0") int p,
                                  @ModelAttribute("sortForm") SortForm sortForm,
                                  @ModelAttribute("searchFormByGiaban") SearchFormByGiaBan searchFormByGiaBan) {
        System.out.println(searchFormByGiaBan.minPrice + "-" + searchFormByGiaBan.maxPrice);
        Pageable pageable = PageRequest.of(p, 12);
        Page<ChiTietSanPham> pageCTSPByGia = chiTietSanPhamService.getCTSPByGiaBan(searchFormByGiaBan.minPrice, searchFormByGiaBan.maxPrice, pageable);
        List<ChiTietSanPham> listSP = chiTietSanPhamService.getList();
        List<LoaiGiay> listLG = loaiGiayService.findAll();
        List<KichCo> listKC = kichCoService.getList();
        List<MauSac> listMS = mauSacService.getAll();
        model.addAttribute("listSP", listSP);
        model.addAttribute("pageSP", pageCTSPByGia);
        model.addAttribute("listLG", listLG);
        model.addAttribute("listKC", listKC);
        model.addAttribute("listMS", listMS);
        model.addAttribute("view", "../template_home/product_listing.jsp");
        return "template_home/index";
    }

    @RequestMapping("/bumblebee/product_list/searchbykichco/{id}")
    public String getListByKichCo(Model model, @RequestParam(defaultValue = "0") int p,
                                  @ModelAttribute("sortForm") SortForm sortForm,
                                  @ModelAttribute("searchFormByGiaban") SearchFormByGiaBan searchFormByGiaBan,
                                  @PathVariable UUID id) {
        KichCo kichCo = kichCoService.getOne(id);
        System.out.println(kichCo);
        System.out.println("----------" + id);
        Pageable pageable = PageRequest.of(p, 12);
        Page<ChiTietSanPham> pageCTSPByKichCo = chiTietSanPhamService.getCTSPByKC(kichCo.getId(), pageable);

        System.out.println("aa");
        List<LoaiGiay> listLG = loaiGiayService.findAll();
        List<KichCo> listKC = kichCoService.getList();
        List<MauSac> listMS = mauSacService.getAll();
        model.addAttribute("pageSP", pageCTSPByKichCo);
        model.addAttribute("listLG", listLG);
        model.addAttribute("listKC", listKC);
        model.addAttribute("listMS", listMS);
        model.addAttribute("view", "../template_home/product_listing.jsp");
        return "template_home/index";
    }

    @RequestMapping("/bumblebee/product_list/searchbymausac")
    public String getListByMauSac(Model model, @RequestParam(defaultValue = "0") int p,
                                  @ModelAttribute("sortForm") SortForm sortForm,
                                  @ModelAttribute("searchFormByGiaban") SearchFormByGiaBan searchFormByGiaBan,
                                  @RequestParam UUID idMS
    ) {
        Pageable pageable = PageRequest.of(p, 12);
        Page<ChiTietSanPham> listCTSPBYMS = chiTietSanPhamService.getCTSPByMS(idMS, pageable);
        List<LoaiGiay> listLG = loaiGiayService.findAll();
        List<KichCo> listKC = kichCoService.getList();
        List<MauSac> listMS = mauSacService.getAll();
        model.addAttribute("pageSP", listCTSPBYMS);
        model.addAttribute("listLG", listLG);
        model.addAttribute("listKC", listKC);
        model.addAttribute("listMS", listMS);
        model.addAttribute("view", "../template_home/product_listing.jsp");
        return "template_home/index";
    }

    @RequestMapping("/bumblebee/product_list/searchbyloaigiay")
    public String getListByLoaiGiay(Model model,
                                    @RequestParam(defaultValue = "0") int p,
                                    @ModelAttribute("sortForm") SortForm sortForm,
                                    @ModelAttribute("searchFormByGiaban") SearchFormByGiaBan searchFormByGiaBan,
                                    @RequestParam(name = "idLoaiGiayList", required = false) String idLoaiGiayList
    ) {
        if ("all".equals(idLoaiGiayList)) {
            Pageable pageable = PageRequest.of(p, 12);
            Page<ChiTietSanPham> pageSP = chiTietSanPhamService.getListSP(pageable);
            List<LoaiGiay> listLG = loaiGiayService.findAll();
            List<KichCo> listKC = kichCoService.getList();
            List<MauSac> listMS = mauSacService.getAll();

            model.addAttribute("pageSP", pageSP);
            model.addAttribute("listLG", listLG);
            model.addAttribute("listKC", listKC);
            model.addAttribute("listMS", listMS);
            model.addAttribute("view", "../template_home/product_listing.jsp");
        } else {
            // Xử lý với các UUID bình thường
            List<UUID> idLoaiGiayUUIDList = Arrays.asList(idLoaiGiayList.split(","))
                    .stream()
                    .map(UUID::fromString)
                    .collect(Collectors.toList());

            Pageable pageable = PageRequest.of(p, 12);
            Page<ChiTietSanPham> listCTSPByLoaiGiay = chiTietSanPhamService.searchCTSPByLoaiGiayList(idLoaiGiayUUIDList, pageable);
            List<LoaiGiay> listLG = loaiGiayService.findAll();
            List<KichCo> listKC = kichCoService.getList();
            List<MauSac> listMS = mauSacService.getAll();

            model.addAttribute("pageSP", listCTSPByLoaiGiay);
            model.addAttribute("listLG", listLG);
            model.addAttribute("listKC", listKC);
            model.addAttribute("listMS", listMS);
            model.addAttribute("view", "../template_home/product_listing.jsp");
        }

        return "template_home/index";
    }
}
