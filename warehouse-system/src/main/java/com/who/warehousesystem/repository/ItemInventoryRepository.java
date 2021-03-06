package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.ItemInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemInventoryRepository extends JpaRepository<ItemInventory,Integer> {

    @Query(value = "select * from item_inventory where active = 1 and item_po_id = :itemPoId and type_id = :typeId",
        nativeQuery = true)
    Optional<ItemInventory> findItemInventoryByTypeAndItemPo(@Param(value = "typeId") Integer typeId,
                                                             @Param(value = "itemPoId") Integer itemPoId);

    @Query(value = "select * from item_inventory where active = 1 and item_dp_id = :itemDpId and type_id = :typeId",
            nativeQuery = true)
    Optional<ItemInventory> findItemInventoryByTypeAndItemDp(@Param(value = "typeId") Integer typeId,
                                                             @Param(value = "itemDpId") Integer itemDpId);

    @Query(value = "select * from item_inventory where active = 1 and item_disposal_id = :itemDisposal and " +
            "type_id = :typeId", nativeQuery = true)
    Optional<ItemInventory> findItemInventoryByTypeAndItemDisposal(@Param(value = "typeId") Integer typeId,
                                                                   @Param(value = "itemDisposal") Integer itemDisposalId);

    @Query(value = "select * from item_inventory where active = 1 and item_po_id = :itemPo", nativeQuery = true)
    Optional<List<ItemInventory>> findItemInventoriesByItemPo(@Param(value = "itemPo") Integer itemPoId);
}
