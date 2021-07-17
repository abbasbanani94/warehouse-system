package com.who.warehousesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ItemDpSaveDto {

    private Integer planId;
    private String enName;
    private String arName;
    private LocalDate planDate;
    private Integer itemPoId;
    private Integer centerId;
    private Integer qty;
}
