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
@Table(name = "check_types")
public class CheckType extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_type_id")
    private Integer id;

    @Column(name = "ar_name", columnDefinition = "NVARCHAR(MAX)")
    private String arName;

    @Column(name = "en_name")
    private String enName;
}
