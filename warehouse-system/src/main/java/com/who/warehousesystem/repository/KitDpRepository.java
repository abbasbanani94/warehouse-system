package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.KitDp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KitDpRepository extends JpaRepository<KitDp,Integer> {

    @Query(value = "select * from kit_dp where active = 1 and kit_po_id = :kitPoId", nativeQuery = true)
    KitDp findKitDpByKitPo(@Param(value = "kitPoId") Integer kitPoId);

    @Query(value = "select * from kit_dp where active = 1 and health_center_id = :healthCenterId", nativeQuery = true)
    List<KitDp> findKitDpByHealthCenter(@Param(value = "healthCenterId") Integer healthCenterId);
}
