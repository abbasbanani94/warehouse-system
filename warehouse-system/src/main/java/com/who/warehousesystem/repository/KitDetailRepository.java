package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.KitDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KitDetailRepository extends JpaRepository<KitDetail,Integer> {

    @Query(value = "select * from kit_details where active = 1 and kit_po_id = :kitPoId", nativeQuery = true)
    Optional<List<KitDetail>> findKitDetailsByKitPo(@Param(value = "kitPoId") Integer kitPoId);

    @Query(value = "select * from kit_details where active = 1 and kit_po_id = :kitPoId order by kit_detail_id desc",
        nativeQuery = true)
    Optional<List<KitDetail>> findAllKitDetailsByKitPo(@Param(value = "kitPoId") Integer kitPoId);

    @Query(value = "select * from kit_details where active = 1 and kit_po_id = :kitPoId and item_id = :itemId and " +
            "box_no = :boxNo", nativeQuery = true)
    KitDetail findKitDetailByKitPoAndItemAndBox(@Param(value = "kitPoId") Integer kitPoId,
                                                @Param(value = "itemId") Integer itemId,
                                                @Param(value = "boxNo") Integer boxNo);

    @Query(value = "select * from kit_details where active = 1 and kit_detail_id = :id", nativeQuery = true)
    Optional<KitDetail> findKitDetailById(@Param(value = "id") Integer id);
}
