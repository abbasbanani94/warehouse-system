package com.who.warehousesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CheckSaveDto {

    private Integer typeId;
    private String notes;
    private LocalDate date;
}
