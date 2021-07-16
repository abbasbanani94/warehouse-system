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
@Table(name = "item_checking_workers")
public class ItemCheckingWorker extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_checking_worker_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "item_checking_id")
    private ItemPoChecking itemPoChecking;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;
}
