package com.multiasset.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RawCandleDto {

    private String datetime;
    private String open;
    private String high;
    private String low;
    private String close;
    private String volume;

}
