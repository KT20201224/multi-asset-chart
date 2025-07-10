package com.multiasset.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AssetChartResponseDto {

    private String symbol;
    private String name;
    private String type;
    private String exchange;
    private String currency;
    private String interval;
    private AssetMetaDto meta;
    private List<ChartCandleDto> chartData;

}
