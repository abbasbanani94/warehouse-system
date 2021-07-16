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
@Table(name = "waybills")
public class Waybill extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "waybill_id")
    private Integer id;

    @Column(name = "form_no",unique = true)
    private Integer formNo;

    @ManyToOne
    @JoinColumn(name = "health_center_id")
    private HealthCenter healthCenter;

    @Column(name = "export_date")
    private LocalDate exportDate;

    @Column(name = "total_boxes")
    private Integer totalBoxes;

    @Column(name = "total_pallets")
    private Integer totalPallets;
}
