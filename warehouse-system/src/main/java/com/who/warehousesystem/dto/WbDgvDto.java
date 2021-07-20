package com.who.warehousesystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class WbDgvDto {

    @JsonProperty(value = "WB ID")
    private Integer id;
    @JsonProperty(value = "WB NO")
    private Integer formNo;
    @JsonProperty(value = "WB Date")
    private LocalDate exportDate;
    @JsonProperty(value = "Boxes")
    private Integer totalBoxes;
    @JsonProperty(value = "Pallets")
    private Integer totalPallets;
    @JsonProperty(value = "City")
    private String healthCenterDistrictCityArName;
    @JsonProperty(value = "District")
    private String healthCenterDistrictArName;
    @JsonProperty(value = "Center")
    private String healthCenterArName;
    @JsonProperty(value = "Center ID")
    private Integer healthCenterId;
}
