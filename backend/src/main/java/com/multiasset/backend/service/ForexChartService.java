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
import java.util.List;

@Service
@RequiredArgsConstructor
public class ForexChartService {

    private final TwelveDataClient client;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ChartResponse getChart(String symbol) {
        try {
            String json = client.getTimeSeries(symbol);
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

            ChartResponse response = new ChartResponse();
            response.setSymbol(meta.path("symbol").asText());
            response.setType("forex");
            response.setCurrency(meta.path("currency").asText());
            response.setCandles(candles);

            return response;

        } catch (Exception e) {
            throw new RuntimeException("Forex 차트 데이터를 불러올 수 없습니다.", e);
        }
    }
}
