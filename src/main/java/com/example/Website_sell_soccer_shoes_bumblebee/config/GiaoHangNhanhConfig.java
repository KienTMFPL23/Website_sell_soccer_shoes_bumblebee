package com.example.Website_sell_soccer_shoes_bumblebee.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GiaoHangNhanhConfig {

    @Value("${ghn.api.base-url}")
    private String baseUrl;

    @Value("${ghn.api.key}")
    private String apiKey;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public String getApiUrl() {
        return baseUrl;
    }

    public String getApiKey() {
        return apiKey;
    }
}
