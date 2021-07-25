package com.who.warehousesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ItemDisposalSaveDto {

    private Integer disposalId;
    private Integer itemPoId;
    private Integer qty;
}
