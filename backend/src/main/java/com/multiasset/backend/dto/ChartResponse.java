package com.multiasset.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChartResponse {

    private String symbol;
    private String type;
    private String currency;
    private List<CandleDto> candles;

}
