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
@Table(name = "item_disposals")
public class ItemDisposal extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_disposal_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "item_po_id")
    private ItemPo itemPo;

    @ManyToOne
    @JoinColumn(name = "disposal_id")
    private Disposal disposal;

    @Column(name = "qty")
    private Integer qty;
}
