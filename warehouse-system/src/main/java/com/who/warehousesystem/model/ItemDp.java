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
@Table(name = "item_dp")
public class ItemDp extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_dp_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "dp_id")
    private DistributionPlan distributionPlan;

    @ManyToOne
    @JoinColumn(name = "health_center_id")
    private HealthCenter healthCenter;

    @ManyToOne
    @JoinColumn(name = "item_po_id")
    private ItemPo itemPo;

    @Column(name = "qty")
    private Integer qty;

    public ItemDp(DistributionPlan dp, HealthCenter center, ItemPo itemPo, Integer qty, User user) {
        this.distributionPlan = dp;
        this.healthCenter = center;
        this.itemPo = itemPo;
        this.qty = qty;
        this.setCreatedBy(user);
    }
}
