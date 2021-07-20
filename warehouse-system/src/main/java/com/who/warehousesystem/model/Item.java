package com.who.warehousesystem.model;

import com.who.warehousesystem.audit.DateAudit;
import com.who.warehousesystem.dto.ItemSaveDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "items")
public class Item extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "min_temp")
    private BigDecimal minTemp;

    @Column(name = "max_temp")
    private BigDecimal maxTemp;

    @Column(name = "description")
    private String description;

    public Item(ItemSaveDto dto, User user) {
        this.name = dto.getName();
        this.minTemp = dto.getMinTemp();
        this.maxTemp = dto.getMaxTemp();
        this.description = dto.getDescription();
        this.setCreatedBy(user);
    }

    public Item(String itemName, BigDecimal minTemp, BigDecimal maxTemp, String itemDescription, User user) {
        this.name = itemName;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.description = itemDescription;
        this.setCreatedBy(user);
    }
}
