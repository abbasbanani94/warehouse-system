package com.who.warehousesystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ItemDisposalDgvDto {

    @JsonProperty(value = "Item Disposal ID")
    private Integer id;
    @JsonProperty(value = "PO NO")
    private String itemPoPurchaseOrderNo;
    @JsonProperty(value = "Item")
    private String itemPoItemName;
    @JsonProperty(value = "Qty")
    private Integer qty;
    @JsonProperty(value = "Item PO ID")
    private Integer itemPoId;
    @JsonProperty(value = "Item ID")
    private Integer itemPoItemId;
    @JsonProperty(value = "PO ID")
    private Integer itemPoPurchaseOrderId;
}
