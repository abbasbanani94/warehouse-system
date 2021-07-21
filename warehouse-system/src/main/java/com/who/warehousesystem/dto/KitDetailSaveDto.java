package com.who.warehousesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class KitDetailSaveDto {

    private Integer boxNo;
    private String itemName;
    private BigDecimal minTemp;
    private BigDecimal maxTemp;
    private String description;
    private String packaging;
    private Integer packsPerBox;
    private Integer piecesPerPack;
    private LocalDate expDate;
}
