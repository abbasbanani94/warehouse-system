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
@Table(name = "checks")
public class Check extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "check_type_id")
    private CheckType checkType;

    @Column(name = "notes")
    private String notes;

    @Column(name = "check_date")
    private LocalDate checkDate;

    public Check(CheckType checkType, String notes, LocalDate date, User user) {
        this.checkType = checkType;
        this.notes = notes;
        this.checkDate = date;
        this.setCreatedBy(user);
    }
}
