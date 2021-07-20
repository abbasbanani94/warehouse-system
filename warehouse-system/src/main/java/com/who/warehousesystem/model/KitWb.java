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
@Table(name = "kit_wb")
public class KitWb extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kit_wb_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "waybill_id")
    private Waybill waybill;

    @OneToOne
    @JoinColumn(name = "kit_dp_id")
    private KitDp kitDp;

    public KitWb(Waybill waybill, KitDp kitDp, User user) {
        this.waybill = waybill;
        this.kitDp = kitDp;
        this.setCreatedBy(user);
    }
}
