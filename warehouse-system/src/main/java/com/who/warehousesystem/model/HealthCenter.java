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
@Table(name = "health_centers")
public class HealthCenter extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_center_id")
    private Integer id;

    @Column(name = "ar_name",unique = true,columnDefinition = "NVARCHAR(MAX)")
    private String arName;

    @Column(name = "en_name",unique = true)
    private String enName;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    public HealthCenter(District district, String enName, String arName, User user) {
        this.district = district;
        this.enName = enName;
        this.arName = arName;
        this.setCreatedBy(user);
    }
}
