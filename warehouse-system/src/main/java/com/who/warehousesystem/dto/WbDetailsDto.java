package com.who.warehousesystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class WbDetailsDto {

    @JsonProperty(value = "wbNo")
    private Integer formNo;
    @JsonProperty(value = "wbDate")
    private LocalDate exportDate;
    @JsonProperty(value = "boxes")
    private Integer totalBoxes;
    @JsonProperty(value = "pallets")
    private Integer totalPallets;
    @JsonProperty(value = "city")
    private String healthCenterDistrictCityArName;
    @JsonProperty(value = "district")
    private String healthCenterDistrictArName;
    @JsonProperty(value = "center")
    private String healthCenterArName;
}
