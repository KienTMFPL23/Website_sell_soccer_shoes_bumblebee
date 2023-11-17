package com.example.Website_sell_soccer_shoes_bumblebee.service;

import jakarta.servlet.http.HttpServletRequest;

public interface VnPayService {

    public String createOrder(Integer total, String orderInfor, String urlReturn);

    public int orderReturn(HttpServletRequest request);
}
