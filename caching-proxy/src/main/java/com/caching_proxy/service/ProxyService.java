package com.caching_proxy.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProxyService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Cacheable(value = "proxyCache", key = "#url")
    public String fetchFromExternalApi(String url) {
        System.out.println("Calling external API...");
        return restTemplate.getForObject(url, String.class);
    }
}
