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
@Table(name = "item_inventory")
public class ItemInventory extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_inventory_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "item_po_id")
    private ItemPo itemPo;

    @ManyToOne
    @JoinColumn(name = "item_dp_id")
    private ItemDp itemDp;

    @ManyToOne
    @JoinColumn(name = "item_disposal_id")
    private ItemDisposal itemDisposal;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private InventoryType inventoryType;

    @Column(name = "note")
    private String note;

    @Column(name = "in_qty")
    private Integer inQty;

    @Column(name = "out_qty")
    private Integer outQty;

    public ItemInventory(ItemPo itemPo, User user, InventoryType inventoryType) {
        this.itemPo = itemPo;
        this.setCreatedBy(user);
        this.inventoryType = inventoryType;
        this.inQty = itemPo.getTotalQty();
        this.outQty = 0;
        this.note = "ItemPo Id: " + itemPo.getId() + ", PO No: " + itemPo.getPurchaseOrder().getNo() + "," +
                " Item: " + itemPo.getItem().getName() + ", Exp. Date: " + itemPo.getExpDate() +
                ", In Qty: " + itemPo.getTotalQty();
    }
}
