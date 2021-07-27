package com.who.warehousesystem.model;

import com.who.warehousesystem.audit.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.yaml.snakeyaml.events.Event;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "check_item_po")
public class CheckItemPo extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_item_po_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "check_id")
    private Check check;

    @ManyToOne
    @JoinColumn(name = "item_po_id")
    private ItemPo itemPo;

    public CheckItemPo(Check check, ItemPo itemPo, User user) {
        this.check = check;
        this.itemPo = itemPo;
        this.setCreatedBy(user);
    }
}
