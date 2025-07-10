package com.multiasset.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RawMetaDto {

    private String symbol;
    private String interval;
    private String currency;
    private String exchange;
    private String type;
}
