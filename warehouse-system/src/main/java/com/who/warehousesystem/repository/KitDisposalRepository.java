package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.KitDisposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KitDisposalRepository extends JpaRepository<KitDisposal,Integer> {

    @Query(value = "select * from kit_disposals where active = 1 and kit_po_id = :kitPoId", nativeQuery = true)
    KitDisposal findKitDisposalByKitPo(@Param(value = "kitPoId") Integer kitPoId);
}
