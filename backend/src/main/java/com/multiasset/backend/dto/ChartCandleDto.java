package com.multiasset.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChartCandleDto {

    private String datetime;
    private double open;
    private double high;
    private double low;
    private double close;
    private long volume;

}
