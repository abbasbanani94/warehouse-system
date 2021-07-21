package com.who.warehousesystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class KitDetailDgvDto {

    @JsonProperty(value = "Detail ID")
    private Integer id;
    @JsonProperty(value = "Box")
    private Integer boxNo;
    @JsonProperty(value = "Item")
    private String itemName;
    @JsonProperty(value = "Packaging")
    private String packaging;
    @JsonProperty(value = "Packs")
    private Integer packsPerBox;
    @JsonProperty(value = "Pieces")
    private Integer piecesPerPack;
    @JsonProperty(value = "Exp. Date")
    private LocalDate expDate;
    @JsonProperty(value = "Item ID")
    private Integer itemId;
    @JsonProperty(value = "Kit PO ID")
    private Integer kitPoId;
}
