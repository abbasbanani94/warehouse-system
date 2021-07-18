package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.ItemDp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemDpRepository extends JpaRepository<ItemDp,Integer> {

    @Query(value = "select * from item_dp where active = 1 and item_po_id = :itemPoId", nativeQuery = true)
    ItemDp findItemDpByItemPo(@Param(value = "itemPoId") Integer itemPoId);

    @Query(value = "select * from item_dp where active = 1 and health_center_id = :healthCenterId", nativeQuery = true)
    List<ItemDp> findItemDpByHealthCenter(@Param(value = "healthCenterId") Integer healthCenterId);

    @Query(value = "select * from item_dp where active = 1 order by item_dp_id desc", nativeQuery = true)
    Optional<List<ItemDp>> findAllItemDp();

    @Query(value = "select top 20 * from item_dp where active = 1 order by item_dp_id desc", nativeQuery = true)
    Optional<List<ItemDp>> findAllItemDpDgv();

    @Query(value = "select * from item_dp where active = 1 and item_dp_id = :id", nativeQuery = true)
    Optional<ItemDp> findItemDpById(@Param(value = "id") Integer id);
}
