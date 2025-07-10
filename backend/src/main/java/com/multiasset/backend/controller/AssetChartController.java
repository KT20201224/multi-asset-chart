package com.multiasset.backend.controller;

import com.multiasset.backend.dto.AssetChartResponseDto;
import com.multiasset.backend.dto.RawTimeSeriesResponseDto;
import com.multiasset.backend.service.ChartApiClient;
import com.multiasset.backend.service.ChartDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assets")
@RequiredArgsConstructor
public class AssetChartController {

    private final ChartApiClient apiClient;
    private final ChartDataMapper mapper;

    @GetMapping("/stock/{symbol}")
    public AssetChartResponseDto getStockChart(@PathVariable String symbol) {
        RawTimeSeriesResponseDto raw = apiClient.fetchDailyChart(symbol);
        return mapper.toAssetChartDto(raw);
    }
}
