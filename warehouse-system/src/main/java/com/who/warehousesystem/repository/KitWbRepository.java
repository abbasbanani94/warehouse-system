package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.KitWb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KitWbRepository extends JpaRepository<KitWb,Integer> {

    @Query(value = "select * from kit_wb where active = 1 and kit_dp_id = :kitDpId", nativeQuery = true)
    KitWb findKitWbByKitDp(@Param(value = "kitDpId") Integer kitDpId);

    @Query(value = "select * from kit_wb where active = 1 and waybill_id = :wbId", nativeQuery = true)
    Optional<List<KitWb>> findAllKitWbByWb(@Param(value = "wbId") Integer wbId);
}
