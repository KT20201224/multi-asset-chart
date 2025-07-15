package com.multiasset.backend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.multiasset.backend.client.TwelveDataClient;
import com.multiasset.backend.dto.CandleDto;
import com.multiasset.backend.dto.ChartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CryptoChartService {

    private final TwelveDataClient client;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ChartResponse getChart(String symbol){
        try {
            String json = client.getTimeSeries(symbol);
            System.out.println(json);
            JsonNode root = objectMapper.readTree(json);

            JsonNode meta = root.path("meta");
            JsonNode values = root.path("values");

            List<CandleDto> candles = new ArrayList<>();
            for (JsonNode item : values) {
                candles.add(new CandleDto(
                        item.path("datetime").asText(),
                        new BigDecimal(item.path("open").asText()),
                        new BigDecimal(item.path("high").asText()),
                        new BigDecimal(item.path("low").asText()),
                        new BigDecimal(item.path("close").asText())
                ));
            }

            List<CandleDto> sortedCandles = candles.stream()
                    .sorted(Comparator.comparing(CandleDto::getDate))
                    .collect(Collectors.toList());

            ChartResponse response = new ChartResponse();
            response.setSymbol(meta.path("symbol").asText());
            response.setType("crypto");
            response.setCurrency(meta.path("currency").asText());
            response.setCandles(sortedCandles);

            return response;

        } catch (Exception e) {
            throw new RuntimeException("Crypto 차트 데이터를 불러올 수 없습니다.", e);
        }
    }

}
