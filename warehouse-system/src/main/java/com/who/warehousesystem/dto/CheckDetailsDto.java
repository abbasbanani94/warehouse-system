package com.who.warehousesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor

public class CheckDetailsDto {

    private Integer id;
    private String type;
    private Date date;
    private String notes;
}
