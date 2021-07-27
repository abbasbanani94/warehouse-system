package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.CheckItemPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckItemPoRepository extends JpaRepository<CheckItemPo,Integer> {

    @Query(value = "select * from check_item_po where active = 1 and item_po_id = :itemPo", nativeQuery = true)
    Optional<List<CheckItemPo>> findCheckItemsByItemPo(@Param(value = "itemPo") Integer itemPoId);

    @Query(value = "select * from check_item_po where active = 1 and check_id = :check",nativeQuery = true)
    Optional<List<CheckItemPo>> findCheckItemsByCheck(@Param(value = "check") Integer checkId);
}
