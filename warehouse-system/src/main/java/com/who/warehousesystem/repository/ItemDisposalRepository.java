package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.ItemDisposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDisposalRepository extends JpaRepository<ItemDisposal,Integer> {

    @Query(value = "select * from item_disposals where item_po_id = :itemPoId and active = 1", nativeQuery = true)
    ItemDisposal findItemDisposalByItemPo(@Param(value = "itemPoId") Integer itemPoId);
}
