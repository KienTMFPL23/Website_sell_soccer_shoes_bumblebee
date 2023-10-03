package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface TaiKhoanRepository {

    @Query( value = "select tk TaiKhoan form tk where tk.Username LIKE := username and tk.Password LIKE := password ", nativeQuery = true)
    TaiKhoan findUsernameAndPassword (@RequestParam(name = "username") String username, @RequestParam(name = "password") String password);
}
