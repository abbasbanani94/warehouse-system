package com.who.warehousesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class KitPoDpDto {

    private LocalDate dateReceived;
    private String batchNo;
    private String description;
    private LocalDate manDate;
    private LocalDate expDate;
    private String kitType;
    private Integer totalQty;
    private Integer inventory;
}
