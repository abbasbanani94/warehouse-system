package com.who.warehousesystem.model;

import com.who.warehousesystem.audit.DateAudit;
import com.who.warehousesystem.dto.ItemDpSaveDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "distribution_plans")
public class DistributionPlan extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dp_id")
    private Integer id;

    @Column(name = "ar_name", columnDefinition = "NVARCHAR(MAX)")
    private String arName;

    @Column(name = "en_name")
    private String enName;

    @Column(name = "d_date")
    private LocalDate dDate;

    public DistributionPlan(String arName,String enName,LocalDate planDate, User user) {
        this.arName = arName;
        this.enName = enName;
        this.dDate = planDate;
        this.setCreatedBy(user);
    }
}
