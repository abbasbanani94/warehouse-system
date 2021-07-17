package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.KitPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface KitPoRepository extends JpaRepository<KitPo,Integer> {

    @Query(value = "select * from kit_po where active = 1 order by kit_po_id desc",nativeQuery = true)
    Optional<List<KitPo>> findAllKitPo();

    @Query(value = "select * from kit_po where active = 1 and kit_po_id = :kitPoId", nativeQuery = true)
    Optional<KitPo> findKitPoById(@Param(value = "kitPoId") Integer id);

    @Query(value = "select top 20 * from kit_po where active = 1 order by kit_po_id desc", nativeQuery = true)
    Optional<List<KitPo>> findAllKitPoDgv();
}
