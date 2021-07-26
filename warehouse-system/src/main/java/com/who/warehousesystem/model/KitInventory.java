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
@Table(name = "kit_inventory")
public class KitInventory extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kit_inventory_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "kit_po_id")
    private KitPo kitPo;

    @ManyToOne
    @JoinColumn(name = "kit_dp_id")
    private KitDp kitDp;

    @ManyToOne
    @JoinColumn(name = "kit_disposal_id")
    private KitDisposal kitDisposal;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private InventoryType inventoryType;

    @Column(name = "note")
    private String note;

    @Column(name = "in_qty")
    private Integer inQty;

    @Column(name = "out_qty")
    private Integer outQty;

    public KitInventory(KitPo kitPo, User user, InventoryType inventoryType) {
        this.kitPo = kitPo;
        this.inventoryType = inventoryType;
        this.setCreatedBy(user);
        this.inQty = kitPo.getTotalQty();
        this.outQty = 0;
        this.note = "KitPo Id:" + kitPo.getId() + ", PO No:" + kitPo.getOrder().getNo() + ", Kit:" +
                kitPo.getKit().getName() + ", Exp. Date:" + kitPo.getExpDate() + ", In Qty:" + kitPo.getTotalQty();
    }

    public KitInventory(KitDp kitDp, InventoryType inventoryType, User user) {
        this.kitPo = kitDp.getKitPo();
        this.kitDp = kitDp;
        this.inventoryType = inventoryType;
        this.inQty = 0;
        this.outQty = kitDp.getQty();
        this.setCreatedBy(user);
        this.note = "KitDp ID: " + kitDp.getId() + ", Plan Name: " + kitDp.getDistributionPlan().getEnName() + ", " +
                "Kit: " + kitDp.getKitPo().getKit().getName() + ", Out Qty: " + kitDp.getQty();
    }

    public KitInventory(KitDisposal kitDisposal, InventoryType inventoryType, User user) {
        this.kitDisposal = kitDisposal;
        this.kitPo = kitDisposal.getKitPo();
        this.inventoryType = inventoryType;
        this.inQty = 0;
        this.outQty = kitDisposal.getQty();
        this.setCreatedBy(user);
        this.note = "KitDisposal ID: " + kitDisposal.getId() + ", Disposal Reason: " + kitDisposal.getDisposal().getReason() +
                ", Kit: " + kitDisposal.getKitPo().getKit().getName() + ", Out Qty: " + kitDisposal.getQty();
    }
}
