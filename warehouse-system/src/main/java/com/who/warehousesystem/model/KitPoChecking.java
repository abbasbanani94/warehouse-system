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
@Table(name = "kit_po_checking")
public class KitPoChecking extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kit_po_checking_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "check_type_id")
    private CheckType checkType;

    @ManyToOne
    @JoinColumn(name = "kit_po_id")
    private KitPo kitPo;
}
