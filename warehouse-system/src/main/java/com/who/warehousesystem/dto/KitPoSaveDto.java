package com.who.warehousesystem.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data

public class KitPoSaveDto {

    private Integer poId;
    private String poNo;
    private LocalDate dateReceived;
    private String kitName;
    private BigDecimal minTemp;
    private BigDecimal maxTemp;
    private String kitDescription;
    private LocalDate manDate;
    private LocalDate expDate;
    private String country;
    private String batchNo;
    private Integer palletsQty;
    private Integer boxesPerPallet;
    private Integer kitsPerPallet;
    private Integer totalQty;
    private String location;
    private Integer kitPoId;
    private String kitType;
}
