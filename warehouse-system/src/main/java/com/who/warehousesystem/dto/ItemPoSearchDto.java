package com.who.warehousesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ItemPoSearchDto {

    private String poId;
    private String dateReceived;
    private String itemId;
    private String minTemp;
    private String maxTemp;
    private String description;
    private String manDate;
    private String expDate;
    private String country;
    private String batch;
    private String packaging;
    private String pallets;
    private String boxes;
    private String packs;
    private String totalQty;
    private String location;
}
