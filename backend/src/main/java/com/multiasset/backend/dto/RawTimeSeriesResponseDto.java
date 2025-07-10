package com.multiasset.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RawTimeSeriesResponseDto {

    private RawMetaDto meta;
    private List<RawCandleDto> values;
    private String status;

}
