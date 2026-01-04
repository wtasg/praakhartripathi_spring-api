package com.Whether_Info_api.service;

import com.Whether_Info_api.dto.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherResponse getWeatherCity (String city) {
        String url = apiUrl + "?q=" + city + "&appid=" + apiKey + "&units=metric";
        Map response = restTemplate.getForObject(url, Map.class);
        Map main = (Map) response.get("main");
        List weatherList = (List) response.get("weather");
        Map weather = (Map) weatherList.get(0);

        return new WeatherResponse(
                city,
                Double.parseDouble(main.get("temp").toString()),
                Integer.parseInt(main.get("humidity").toString()),
                weather.get("description").toString()
        );
    }
}
