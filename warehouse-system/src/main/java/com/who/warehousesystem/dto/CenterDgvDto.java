package com.who.warehousesystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CenterDgvDto {

    @JsonProperty(value = "Center ID")
    private Integer id;
    @JsonProperty(value = "City")
    private String city;
    @JsonProperty(value = "District")
    private String district;
    @JsonProperty(value = "Center EN")
    private String centerEn;
    @JsonProperty(value = "Center AR")
    private String centerAr;
}
