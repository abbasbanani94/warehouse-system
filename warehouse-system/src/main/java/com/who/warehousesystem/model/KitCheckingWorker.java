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
@Table(name = "kit_checking_workers")
public class KitCheckingWorker extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kit_checking_worker_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "kit_checking_id")
    private KitPoChecking kitPoChecking;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;
}
