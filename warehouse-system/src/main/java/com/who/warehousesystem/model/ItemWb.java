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
@Table(name = "item_wb")
public class ItemWb extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kit_wb_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "waybill_id")
    private Waybill waybill;

    @OneToOne
    @JoinColumn(name = "item_dp_id")
    private ItemDp itemDp;

    public ItemWb(Waybill waybill, ItemDp itemDp, User user) {
        this.waybill = waybill;
        this.itemDp = itemDp;
        this.setCreatedBy(user);
    }
}
