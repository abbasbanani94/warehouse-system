package com.who.warehousesystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DisposalDgvDto {

    @JsonProperty(value = "Disposal ID")
    private Integer id;
    @JsonProperty(value = "Reason")
    private String reason;
    @JsonProperty(value = "Date")
    private LocalDate disposalDate;
}
