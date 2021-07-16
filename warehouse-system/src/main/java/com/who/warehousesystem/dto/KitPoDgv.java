package com.who.warehousesystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class KitPoDgv {

    @JsonProperty(value = "Kit PO. ID")
    private Integer id;

    @JsonProperty(value = "PO. NO.")
    private String purchaseOrderNo;

    @JsonProperty(value = "Kit Name")
    private String kitName;

    @JsonProperty(value = "Kit Type")
    private String kitKitTypeName;

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

    @JsonProperty(value = "Kits/Pallet")
    private Integer kitsPerPallet;

    @JsonProperty(value = "Total Qty")
    private Integer totalQty;

    @JsonProperty(value = "Min. Temp.")
    private Integer kitMinTemp;

    @JsonProperty(value = "Max. Temp.")
    private Integer kitMaxTemp;

    @JsonProperty(value = "Man. Country")
    private String countryName;

    @JsonProperty(value = "Batch NO.")
    private String batchNo;

    @JsonProperty(value = "Desc.")
    private String kitDescription;

    @JsonProperty(value = "Location")
    private String location;

    @JsonProperty(value = "PO. ID")
    private Integer purchaseOrderId;

    @JsonProperty(value = "kit ID")
    private Integer kitId;
}
