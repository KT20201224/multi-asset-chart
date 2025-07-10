package com.multiasset.backend.service;

import com.multiasset.backend.dto.*;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChartDataMapper {

    public AssetChartResponseDto toAssetChartDto(RawTimeSeriesResponseDto raw) {
        RawMetaDto rawMeta = raw.getMeta();

        List<ChartCandleDto> candles = raw.getValues().stream()
                .map(value -> new ChartCandleDto(
                        value.getDatetime(),
                        Double.parseDouble(value.getOpen()),
                        Double.parseDouble(value.getHigh()),
                        Double.parseDouble(value.getLow()),
                        Double.parseDouble(value.getClose()),
                        Long.parseLong(value.getVolume())
                ))
                .sorted(Comparator.comparing(ChartCandleDto::getDatetime)) // 시간 순 정렬
                .collect(Collectors.toList());

        return new AssetChartResponseDto(
                rawMeta.getSymbol(),
                null, // 이름은 symbol_search로부터 별도 주입 예정
                "stock", // 지금은 고정, 이후 확장 가능
                rawMeta.getExchange(),
                rawMeta.getCurrency(),
                rawMeta.getInterval(),
                new AssetMetaDto(candles.get(candles.size() - 1).getDatetime(), "Asia/Seoul"), // 임시
                candles
        );
    }
}
