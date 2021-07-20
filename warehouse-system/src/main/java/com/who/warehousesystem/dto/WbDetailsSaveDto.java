package com.who.warehousesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class WbDetailsSaveDto {

    private Integer wbNo;
    private LocalDate wbDate;
    private Integer totalBoxes;
    private Integer totalPallets;
    private Integer centerId;
    private Integer dpId;
    private List<String> dpList;
    private List<String> wbList;
}
