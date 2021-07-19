package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.KitInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KitInventoryRepository extends JpaRepository<KitInventory,Integer> {

    @Query(value = "select * from kit_inventory where active = 1 and kit_po_id = :kitPoId and type_id = :typeId",
        nativeQuery = true)
    Optional<KitInventory> findKitInventoryByTypeAndKitPo(Integer typeId, Integer kitPoId);

    @Query(value = "select * from kit_inventory where active = 1 and kit_dp_id = :kitDpId and type_id = :typeId",
    nativeQuery = true)
    Optional<KitInventory> findKitInventoryByTypeAndKitDp(@Param(value = "typeId") Integer typeId,
                                                          @Param(value = "kitDpId") Integer kitDpId);
}
