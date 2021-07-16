package com.who.warehousesystem.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data

//Text boxes dto
public class ItemTbDto {

    private Integer id;
    private String name;
    private BigDecimal minTemp;
    private BigDecimal maxTemp;
    private String description;
}
