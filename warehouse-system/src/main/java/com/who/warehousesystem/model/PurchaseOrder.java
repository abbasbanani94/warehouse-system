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
@Table(name = "purchase_orders")
public class PurchaseOrder extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_order_id")
    private Integer id;

    @Column(name = "purchase_order_no",unique = true)
    private String no;

    public PurchaseOrder(String poNo, User user) {
        this.no = poNo;
        this.setCreatedBy(user);
    }
}
