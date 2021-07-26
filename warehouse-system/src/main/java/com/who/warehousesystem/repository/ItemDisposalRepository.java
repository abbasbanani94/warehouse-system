package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.ItemDisposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemDisposalRepository extends JpaRepository<ItemDisposal,Integer> {

    @Query(value = "select * from item_disposals where item_po_id = :itemPoId and active = 1", nativeQuery = true)
    Optional<List<ItemDisposal>> findItemDisposalsByItemPo(@Param(value = "itemPoId") Integer itemPoId);

    @Query(value = "select * from item_disposals where active = 1 and disposal_id = :disposal",nativeQuery = true)
    Optional<List<ItemDisposal>> findItemDisposalsByDisposal(@Param(value = "disposal") Integer disposalId);

    @Query(value = "select * from item_disposals where active = 1 and disposal_id = :disposal and " +
            "item_po_id = :itemPo", nativeQuery = true)
    ItemDisposal findItemDisposalByDisposalAndItemPo(@Param(value = "disposal") Integer disposalId,
                                                     @Param(value = "itemPo") Integer itemPoId);

    @Query(value = "select * from item_disposals where active = 1 and item_disposal_id = :id",nativeQuery = true)
    Optional<ItemDisposal> findItemDisposalById(@Param(value = "id") Integer id);
}