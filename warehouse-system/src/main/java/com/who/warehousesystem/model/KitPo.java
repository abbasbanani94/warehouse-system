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
@Table(name = "kit_po")
public class KitPo extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kit_po_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "kit_id")
    private Kit kit;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrder order;

    @Column(name = "rec_date")
    private LocalDate recDate;

    @Column(name = "exp_date")
    private LocalDate expDate;

    @Column(name = "man_date")
    private LocalDate manDate;

    @Column(name = "batch_no")
    private String batchNo;

    @Column(name = "boxes_per_pallet")
    private Integer boxesPerPallet;

    @Column(name = "inventory")
    private Integer inventory;

    @Column(name = "pallets_qty")
    private Integer palletsQty;

    @Column(name = "kits_per_pallet")
    private Integer kitsPerPallet;

    @Column(name = "total_qty")
    private Integer totalQty;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "location")
    private String location;

    public KitPo(Kit kit, PurchaseOrder po, LocalDate dateReceived, LocalDate manDate, LocalDate expDate,
                 Country country, String batchNo, Integer palletsQty, Integer boxesPerPallet, Integer kitsPerPallet,
                 Integer totalQty, String location, User user) {
        this.kit = kit;
        this.order = po;
        this.recDate = dateReceived;
        this.manDate = manDate;
        this.expDate = expDate;
        this.country = country;
        this.batchNo = batchNo;
        this.palletsQty = palletsQty;
        this.boxesPerPallet = boxesPerPallet;
        this.kitsPerPallet = kitsPerPallet;
        this.totalQty = totalQty;
        this.location = location;
        this.setCreatedBy(user);
        this.setActive(true);
    }
}
