package com.who.warehousesystem.model;

import com.who.warehousesystem.audit.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "item_po")
public class ItemPo extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_po_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrder purchaseOrder;

    @Column(name = "rec_date")
    private LocalDate recDate;

    @Column(name = "exp_date")
    private LocalDate expDate;

    @Column(name = "man_date")
    private LocalDate manDate;

    @Column(name = "batch_no")
    private String batchNo;

    @Column(name = "inventory")
    private Integer inventory;

    @Column(name = "packaging")
    private String packaging;

    @Column(name = "pallets_qty")
    private Integer palletsQty;

    @Column(name = "boxes_per_pallet")
    private Integer boxesPerPallet;

    @Column(name = "packs_per_box")
    private Integer packsPerBox;

    @Column(name = "pieces_per_pack")
    private Integer piecesPerPack;

    @Column(name = "total_qty")
    private Integer totalQty;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "location")
    private String location;

    public ItemPo(Item item, PurchaseOrder po, LocalDate dateReceived, LocalDate manDate, LocalDate expDate,
                  Country country, String batchNo, String packaging, Integer palletsQty, Integer boxesPerPallet,
                  Integer packsPerBox, Integer piecesPerPack, Integer totalQty, User user, String location) {
        this.item = item;
        this.purchaseOrder = po;
        this.recDate = dateReceived;
        this.manDate = manDate;
        this.expDate = expDate;
        this.country = country;
        this.batchNo = batchNo;
        this.packaging = packaging;
        this.palletsQty = palletsQty;
        this.boxesPerPallet = boxesPerPallet;
        this.packsPerBox = packsPerBox;
        this.piecesPerPack = piecesPerPack;
        this.totalQty = totalQty;
        this.setCreatedBy(user);
        this.inventory = totalQty;
        this.location = location;
    }
}
