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

    @Query(value = "select top 50 * from item_po where active = 1 order by item_po_id desc", nativeQuery = true)
    Optional<List<ItemPo>> findItemPoDgv();

    @Query(value = "select * from item_po where active = 1 and item_po_id = :id", nativeQuery = true)
    Optional<ItemPo> findItemPoById(@Param(value = "id") Integer id);
}
