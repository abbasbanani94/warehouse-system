package com.who.warehousesystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ItemDpDgv {

    @JsonProperty(value = "Item DP ID")
    private Integer id;
    @JsonProperty(value = "Plan")
    private String distributionPlanEnName;
    @JsonProperty(value = "Item")
    private String itemPoItemName;
    @JsonProperty(value = "PO NO")
    private String itemPoPurchaseOrderNo;
    @JsonProperty(value = "Qty")
    private Integer qty;
    @JsonProperty(value = "City")
    private String healthCenterDistrictCityArName;
    @JsonProperty(value = "District")
    private String healthCenterDistrictArName;
    @JsonProperty(value = "Center")
    private String healthCenterArName;
    @JsonProperty(value = "Center ID")
    private Integer healthCenterId;
    @JsonProperty(value = "DP ID")
    private Integer distributionPlanId;
    @JsonProperty(value = "Item PO ID")
    private Integer itemPoId;
    @JsonProperty(value = "PO ID")
    private Integer itemPoPurchaseOrderId;
}
