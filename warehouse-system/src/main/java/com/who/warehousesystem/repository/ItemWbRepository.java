package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.ItemWb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemWbRepository extends JpaRepository<ItemWb,Integer> {

    @Query(value = "select * from item_wb where active = 1 and item_dp_id = :itemDpId", nativeQuery = true)
    ItemWb findItemWbByItemDp(@Param(value = "itemDpId") Integer itemDpId);

    @Query(value = "select * from item_wb where active = 1 and waybill_id = :wbId", nativeQuery = true)
    Optional<List<ItemWb>> findAllItemWbByWb(@Param(value = "wbId") Integer wbId);
}
