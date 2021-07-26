package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.CheckKitPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckKitPoRepository extends JpaRepository<CheckKitPo,Integer> {

    @Query(value = "select * from check_kit_po where active = 1 and kit_po_id = :kitPo", nativeQuery = true)
    Optional<List<CheckKitPo>> findCheckKitsByKitPo(@Param(value = "kitPo") Integer kitPoId);
}
