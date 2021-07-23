package com.who.warehousesystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PoDgvDto {

    @JsonProperty(value = "PO ID")
    private Integer id;
    @JsonProperty(value = "PO NO")
    private String no;
    @JsonProperty(value = "Items Count")
    private Integer items;
    @JsonProperty(value = "Kits Count")
    private Integer kits;
}
