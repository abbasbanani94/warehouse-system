package com.who.warehousesystem.model;

import com.who.warehousesystem.audit.DateAudit;
import com.who.warehousesystem.dto.WorkerSaveDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "workers")
public class Worker extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "worker_id")
    private Integer id;

    @Column(name = "ar_name",columnDefinition = "NVARCHAR(MAX)")
    private String arName;

    @Column(name = "en_name")
    private String enName;

    @Column(name = "mobile")
    private String mobile;

    public Worker(WorkerSaveDto dto, User user) {
        this.enName = dto.getEnName();
        this.arName = dto.getArName();
        this.mobile = dto.getMobile();
        this.setCreatedBy(user);
    }
}
