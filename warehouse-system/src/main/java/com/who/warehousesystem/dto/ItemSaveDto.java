package com.who.warehousesystem.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data

public class ItemSaveDto {

    private String name;
    private BigDecimal minTemp;
    private BigDecimal maxTemp;
    private String description;
}
