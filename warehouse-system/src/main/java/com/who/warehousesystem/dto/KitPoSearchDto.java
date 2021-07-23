package com.who.warehousesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor

public class KitPoSearchDto {

    private String poId;
    private String dateReceived;
    private String kitId;
    private String minTemp;
    private String maxTemp;
    private String description;
    private String manDate;
    private String expDate;
    private String country;
    private String batchNo;
    private String location;
    private String palletsQty;
    private String boxesPallet;
    private String kitsPallet;
    private String totalQty;
    private String kitType;
    private boolean rec;
    private boolean man;
    private boolean exp;
}
