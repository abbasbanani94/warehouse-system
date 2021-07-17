package com.who.warehousesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DpDetailsDto {

    private Integer id;
    private String enName;
    private String arName;
    private LocalDate dDate;
}
