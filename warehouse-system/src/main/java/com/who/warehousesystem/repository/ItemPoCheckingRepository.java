package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.ItemPoChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPoCheckingRepository extends JpaRepository<ItemPoChecking,Integer> {

    @Query(value = "select * from item_po_checking where active = 1 and item_po_id = :itemPoId", nativeQuery = true)
    ItemPoChecking findItemPoCheckingByItemPo(@Param(value = "itemPoId") Integer itemPoId);
}
