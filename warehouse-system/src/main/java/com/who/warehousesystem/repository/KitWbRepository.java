package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.KitWb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KitWbRepository extends JpaRepository<KitWb,Integer> {

    @Query(value = "select * from kit_wb where active = 1 and kit_dp_id = :kitDpId", nativeQuery = true)
    KitWb findKitWbByKitDp(@Param(value = "kitDpId") Integer kitDpId);
}
