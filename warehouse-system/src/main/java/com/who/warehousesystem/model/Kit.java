package com.who.warehousesystem.model;

import com.who.warehousesystem.audit.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "kits")
public class Kit extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kit_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "min_temp")
    private BigDecimal minTemp;

    @Column(name = "max_temp")
    private BigDecimal maxTemp;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "kit_type_id")
    private KitType kitType;

    public Kit(String kitName, BigDecimal minTemp, BigDecimal maxTemp, String kitDescription, User user,
               KitType kitType) {
        this.name = kitName;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.description = kitDescription;
        this.setCreatedBy(user);
        this.kitType = kitType;
    }
}
