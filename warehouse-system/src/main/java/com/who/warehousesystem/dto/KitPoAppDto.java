package com.who.warehousesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class KitPoAppDto {

    private Integer id;
    private String kitName;
    private String orderNo;
    private String batchNo;
    private LocalDate recDate;
    private LocalDate manDate;
    private LocalDate expDate;
    private String countryName;
    private String location;
    private Integer boxesPerPallet;
    private Integer kitsPerPallet;
}
