package com.who.warehousesystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ItemPoDgv {

    @JsonProperty(value = "Item PO. ID")
    private Integer id;

    @JsonProperty(value = "PO. NO.")
    private String purchaseOrderNo;

    @JsonProperty(value = "Item Name")
    private String itemName;

    @JsonProperty(value = "Date Received")
    private LocalDate recDate;

    @JsonProperty(value = "Man. Date")
    private LocalDate manDate;

    @JsonProperty(value = "Exp. Date")
    private LocalDate expDate;

    @JsonProperty(value = "Pallets")
    private Integer palletsQty;

    @JsonProperty(value = "Boxes/Pallet")
    private Integer boxesPerPallet;

    @JsonProperty(value = "Packs/Box")
    private Integer packsPerBox;

    @JsonProperty(value = "Pieces/Pack")
    private Integer piecesPerPack;

    @JsonProperty(value = "Total Qty")
    private Integer totalQty;

    @JsonProperty(value = "Min. Temp.")
    private Integer itemMinTemp;

    @JsonProperty(value = "Max. Temp.")
    private Integer itemMaxTemp;

    @JsonProperty(value = "Packaging")
    private String packaging;

    @JsonProperty(value = "Man. Country")
    private String countryName;

    @JsonProperty(value = "Batch NO.")
    private String batchNo;

    @JsonProperty(value = "Desc.")
    private String itemDescription;

    @JsonProperty(value = "Location")
    private String location;

    @JsonProperty(value = "PO. ID")
    private Integer purchaseOrderId;

    @JsonProperty(value = "Item ID")
    private Integer itemId;
}
