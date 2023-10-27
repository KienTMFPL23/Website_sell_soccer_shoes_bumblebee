package com.example.Website_sell_soccer_shoes_bumblebee.service.Impl;

import com.example.Website_sell_soccer_shoes_bumblebee.config.GiaoHangNhanhConfig;
import com.example.Website_sell_soccer_shoes_bumblebee.service.GiaoHangNhanhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service

public class GiaoHangNhanhServiceImpl implements GiaoHangNhanhService {

    @Autowired
    private GiaoHangNhanhConfig ghnConfiguration;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<String> callGhnApi() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Token", ghnConfiguration.getApiKey());

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                ghnConfiguration.getApiUrl() + "/master-data/province", HttpMethod.GET, entity, String.class);
        return response;
    }
}
