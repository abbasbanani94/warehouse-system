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
@Table(name = "kit_disposals")
public class KitDisposal extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kit_disposal_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "kit_po_id")
    private KitPo kitPo;

    @ManyToOne
    @JoinColumn(name = "disposal_id")
    private Disposal disposal;

    @Column(name = "qty")
    private Integer qty;

    public KitDisposal(Disposal disposal, KitPo kitPo, Integer qty, User user) {
        this.disposal = disposal;
        this.kitPo = kitPo;
        this.qty = qty;
        this.setCreatedBy(user);
    }
}
