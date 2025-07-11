package com.multiasset.backend.controller;

import com.multiasset.backend.dto.ChartResponse;
import com.multiasset.backend.service.CryptoChartService;
import com.multiasset.backend.service.ForexChartService;
import com.multiasset.backend.service.StockChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chart")
@RequiredArgsConstructor
public class ChartController {

    private final CryptoChartService cryptoService;
    private final StockChartService stockService;
    private final ForexChartService forexService;

    @GetMapping("/candle")
    public ChartResponse getCandleChart(
            @RequestParam String symbol,
            @RequestParam String type
    ) {
        switch (type.toLowerCase()) {
            case "crypto": return cryptoService.getChart(symbol);
            case "stock": return stockService.getChart(symbol);
            case "forex": return forexService.getChart(symbol);
            default: throw new IllegalArgumentException("지원하지 않는 타입입니다: " + type);
        }
    }
}
