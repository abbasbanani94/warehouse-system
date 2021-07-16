package com.who.warehousesystem.audit;

import com.who.warehousesystem.model.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data

@MappedSuperclass
public abstract class DateAudit {

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "created_by",referencedColumnName = "user_id")
    private User createdBy;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by",referencedColumnName = "user_id")
    private User updatedBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "active")
    private Boolean active;

    @PrePersist
    private void createEntity () {
        this.active = true;
        this.createdAt = LocalDateTime.now();
        this.updateEntity();
    }

    @PreUpdate
    private void updateEntity() {
        this.updatedAt = LocalDateTime.now();
    }
}
