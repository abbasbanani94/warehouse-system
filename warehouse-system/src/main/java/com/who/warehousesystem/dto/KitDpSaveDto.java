package com.who.warehousesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class KitDpSaveDto {

    private Integer planId;
    private String enName;
    private String arName;
    private LocalDate planDate;
    private Integer kitPoId;
    private Integer centerId;
    private Integer qty;
}
