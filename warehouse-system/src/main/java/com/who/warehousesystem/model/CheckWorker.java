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
@Table(name = "check_workers")
public class CheckWorker extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_worker_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "check_id")
    private Check check;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;
}
