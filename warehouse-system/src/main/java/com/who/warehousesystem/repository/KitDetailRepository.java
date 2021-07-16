package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.KitDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KitDetailRepository extends JpaRepository<KitDetail,Integer> {

    @Query(value = "select * from kit_details where active = 1 and kit_po_id = :kitPoId", nativeQuery = true)
    List<KitDetail> findKitDetailsByKitPo(@Param(value = "kitPoId") Integer kitPoId);
}
