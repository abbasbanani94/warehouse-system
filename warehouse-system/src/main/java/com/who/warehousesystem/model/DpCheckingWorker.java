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
@Table(name = "dp_checking_workers")
public class DpCheckingWorker extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dp_checking_worker_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "dp_checking_id")
    private DpChecking dpChecking;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;
}
