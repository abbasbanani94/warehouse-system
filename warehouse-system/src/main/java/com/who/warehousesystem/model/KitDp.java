package com.who.warehousesystem.model;

import com.who.warehousesystem.audit.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "kit_dp")
public class KitDp extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kit_dp_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "dp_id")
    private DistributionPlan distributionPlan;

    @ManyToOne
    @JoinColumn(name = "health_center_id")
    private HealthCenter healthCenter;

    @ManyToOne
    @JoinColumn(name = "kit_po_id")
    private KitPo kitPo;

    @Column(name = "qty")
    private Integer qty;
}
