package com.who.warehousesystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CheckDgvDto {

    @JsonProperty(value = "Check ID")
    private Integer id;
    @JsonProperty(value = "Type")
    private String type;
    @JsonProperty(value = "Date")
    private Date date;
    @JsonProperty(value = "Notes")
    private String notes;
    @JsonProperty(value = "Workers")
    private Integer workers;
    @JsonProperty(value = "Items And Kits")
    private Integer items;
}
