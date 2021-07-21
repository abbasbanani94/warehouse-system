package com.who.warehousesystem.model;

import com.who.warehousesystem.audit.DateAudit;
import com.who.warehousesystem.dto.KitDetailSaveDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "kit_details")
public class KitDetail extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kit_detail_id")
    private Integer id;

    @Column(name = "box_no")
    private Integer boxNo;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "kit_po_id")
    private KitPo kitPo;

    @Column(name = "packaging")
    private String packaging;

    @Column(name = "pack_per_box")
    private Integer packsPerBox;

    @Column(name = "pieces_per_pack")
    private Integer piecesPerPack;

    @Column(name = "exp_date")
    private LocalDate expDate;

    public KitDetail(KitPo kitPo, Item item, KitDetailSaveDto dto, User user) {
        this.kitPo = kitPo;
        this.item = item;
        this.boxNo = dto.getBoxNo();
        this.expDate = dto.getExpDate();
        this.packaging = dto.getPackaging();
        this.packsPerBox = dto.getPacksPerBox();
        this.piecesPerPack = dto.getPiecesPerPack();
        this.setCreatedBy(user);
    }
}
