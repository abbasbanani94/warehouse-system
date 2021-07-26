package com.who.warehousesystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class KitDisposalDgvDto {

    @JsonProperty(value = "Kit Disposal ID")
    private Integer id;
    @JsonProperty(value = "PO NO")
    private String kitPoPurchaseOrderNo;
    @JsonProperty(value = "Kit")
    private String kitPoKitName;
    @JsonProperty(value = "Qty")
    private Integer qty;
    @JsonProperty(value = "Kit PO ID")
    private Integer kitPoId;
    @JsonProperty(value = "Kit ID")
    private Integer kitPoKitId;
    @JsonProperty(value = "PO ID")
    private Integer kitPoPurchaseOrderId;
}
