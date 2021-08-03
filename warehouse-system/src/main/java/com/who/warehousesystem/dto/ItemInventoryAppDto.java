package com.who.warehousesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ItemInventoryAppDto {

    private String note;
    private LocalDate date;
    private Integer inQty;
    private Integer outQty;
    private Integer stock;
}
