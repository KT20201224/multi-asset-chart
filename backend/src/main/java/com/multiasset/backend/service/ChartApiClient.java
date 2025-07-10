package com.multiasset.backend.service;

import com.multiasset.backend.dto.RawTimeSeriesResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChartApiClient {

    private final String API_KEY = "a92402914b8440c197a4efff05e6ef80"; // Twelve Data API Key
    private final String BASE_URL = "https://api.twelvedata.com/time_series";

    public RawTimeSeriesResponseDto fetchDailyChart(String symbol) {
        String url = BASE_URL + "?symbol=" + symbol + "&interval=1day&outputsize=30&apikey=" + API_KEY;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, RawTimeSeriesResponseDto.class);
    }
}
