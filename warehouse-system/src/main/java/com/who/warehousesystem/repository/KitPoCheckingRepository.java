package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.KitPoChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KitPoCheckingRepository extends JpaRepository<KitPoChecking,Integer> {

    @Query(value = "select * from kit_po_checking where active = 1 and kit_po_id = :kitPoId", nativeQuery = true)
    KitPoChecking findKitPoCheckingByKitPo(@Param(value = "kitPoId") Integer kitPoId);
}
