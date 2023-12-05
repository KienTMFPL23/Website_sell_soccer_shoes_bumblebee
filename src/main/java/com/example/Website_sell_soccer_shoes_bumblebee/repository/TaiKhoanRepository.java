
package com.example.Website_sell_soccer_shoes_bumblebee.repository;

import com.example.Website_sell_soccer_shoes_bumblebee.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, UUID> {

    @Query("select tk from TaiKhoan tk where tk.username LIKE :username and tk.password LIKE :password ")
    TaiKhoan findUsernameAndPassword (String username, String password);

    @Query("select tk from TaiKhoan tk where tk.role = 2")
    List<TaiKhoan> listByRole2();

    @Query("select tk from TaiKhoan tk where tk.username =?1")
    TaiKhoan getByUsername(String username);

    @Query("select tk from TaiKhoan tk where tk.id =?1")
    TaiKhoan getById(UUID id);

}
