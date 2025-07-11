package com.multiasset.backend.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class TwelveDataClient {

    @Value("${twelve.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getTimeSeries(String symbol){
        String url = UriComponentsBuilder.fromHttpUrl("https://api.twelvedata.com/time_series")
                .queryParam("symbol", symbol)
                .queryParam("interval", "1day")
                .queryParam("outputsize", "30")
                .queryParam("apiKey", apiKey)
                .toUriString();

        return restTemplate.getForObject(url, String.class);
    }

}
