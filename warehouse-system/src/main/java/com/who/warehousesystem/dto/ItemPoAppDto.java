package com.who.warehousesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ItemPoAppDto {

    private Integer id;
    private String itemName;
    private String purchaseOrderNo;
    private String batchNo;
    private String packaging;
    private LocalDate recDate;
    private LocalDate manDate;
    private LocalDate expDate;
    private String countryName;
}
