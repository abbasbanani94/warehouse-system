package com.who.warehousesystem.model;

import com.who.warehousesystem.audit.DateAudit;
import com.who.warehousesystem.dto.DisposalSaveDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "disposals")
public class Disposal extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disposal_id")
    private Integer id;

    @Column(name = "reason")
    private String reason;

    @Column(name = "disposal_date")
    private LocalDate disposalDate;

    public Disposal(DisposalSaveDto dto, User user) {
        this.reason = dto.getReason();
        this.disposalDate = dto.getDate();
        this.setCreatedBy(user);
    }
}
