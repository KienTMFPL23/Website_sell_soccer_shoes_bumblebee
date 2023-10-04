
package com.example.Website_sell_soccer_shoes_bumblebee.service;


import com.example.Website_sell_soccer_shoes_bumblebee.entity.TaiKhoan;

public interface TaiKhoanService {

    TaiKhoan findByUsernameAndPassword(String username, String password);

    TaiKhoan dangKy(TaiKhoan taiKhoan);
}

