package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.KitDp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface KitDpRepository extends JpaRepository<KitDp,Integer> {

    @Query(value = "select * from kit_dp where active = 1 and kit_po_id = :kitPoId", nativeQuery = true)
    KitDp findKitDpByKitPo(@Param(value = "kitPoId") Integer kitPoId);

    @Query(value = "select * from kit_dp where active = 1 and health_center_id = :healthCenterId", nativeQuery = true)
    List<KitDp> findKitDpByHealthCenter(@Param(value = "healthCenterId") Integer healthCenterId);

    @Query(value = "select top 20 * from kit_dp where active = 1 order by kit_dp_id desc", nativeQuery = true)
    Optional<List<KitDp>> findAllKitDpDgv();

    @Query(value = "select * from kit_dp where active = 1 and kit_dp_id = :id", nativeQuery = true)
    Optional<KitDp> findKitDpById(@Param(value = "id") Integer id);

    @Query(value = "select * from kit_dp where active = 1 and dp_id = :dpId and health_center_id = :centerId and " +
            "kit_po_id = :kitPoId", nativeQuery = true)
    KitDp findKitDpByDpAndCenterAndKitPo(@Param(value = "dpId") Integer planId,
                                         @Param(value = "centerId") Integer centerId,
                                         @Param(value = "kitPoId") Integer kitPoId);

    @Query(value = "select * from kit_dp where active = 1 and health_center_id = :centerId and kit_dp_id not " +
            "in (select kit_dp_id from kit_wb where active = 1)", nativeQuery = true)
    Optional<List<KitDp>> findKitDpByCenterNoWb(@Param(value = "centerId") Integer centerId);

    @Query(value = "select * from kit_dp where active = 1 and health_center_id = :centerId and kit_dp_id " +
            "in (select kit_dp_id from kit_wb where active = 1)", nativeQuery = true)
    Optional<List<KitDp>> findKitDpByCenterWb(@Param(value = "centerId") Integer centerId);
}