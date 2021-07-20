package com.who.warehousesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class WbSaveDto {

    private Integer wbNo;
    private LocalDate wbDate;
    private Integer boxes;
    private Integer pallets;
    private Integer centerId;
}
