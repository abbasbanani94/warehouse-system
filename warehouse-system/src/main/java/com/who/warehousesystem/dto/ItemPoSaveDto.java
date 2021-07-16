package com.who.warehousesystem.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data

public class ItemPoSaveDto {

    private Integer poId;
    private String poNo;
    private LocalDate dateReceived;
    private String itemName;
    private BigDecimal minTemp;
    private BigDecimal maxTemp;
    private String itemDescription;
    private LocalDate manDate;
    private LocalDate expDate;
    private String country;
    private String batchNo;
    private String packaging;
    private Integer palletsQty;
    private Integer boxesPerPallet;
    private Integer packsPerBox;
    private Integer piecesPerPack;
    private Integer totalQty;
    private String location;
    private Integer itemPoId;
}
