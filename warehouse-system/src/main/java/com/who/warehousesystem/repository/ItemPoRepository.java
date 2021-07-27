package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.ItemPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemPoRepository extends JpaRepository<ItemPo,Integer> {

    @Query(value = "select top 20 * from item_po where active = 1 order by item_po_id desc", nativeQuery = true)
    Optional<List<ItemPo>> findItemPoDgv();

    @Query(value = "select * from item_po where active = 1 and item_po_id = :id", nativeQuery = true)
    Optional<ItemPo> findItemPoById(@Param(value = "id") Integer id);

    @Query(value = "select * from item_po where active = 1 and purchase_order_id = :poId", nativeQuery = true)
    Optional<List<ItemPo>> findItemsPoByPo(@Param(value = "poId") Integer poId);

    @Query(value = "select count(item_po_id) from item_po where active = 1 and purchase_order_id = :poId",
        nativeQuery = true)
    int findItemsCountById(@Param(value = "poId") Integer poId);

    @Query(value = "select * from item_po where active = 1 and item_po_id not in (select item_po_id from " +
            "check_item_po where active = 1 and check_id = :check) order by item_po_id desc", nativeQuery = true)
    Optional<List<ItemPo>> findItemsByNoCheck(@Param(value = "check") Integer checkId);

    @Query(value = "select * from item_po where active = 1 and item_po_id in (select item_po_id from " +
            "check_item_po where active = 1 and check_id = :check) order by item_po_id desc", nativeQuery = true)
    Optional<List<ItemPo>> findItemsByCheck(@Param(value = "check") Integer checkId);
}
