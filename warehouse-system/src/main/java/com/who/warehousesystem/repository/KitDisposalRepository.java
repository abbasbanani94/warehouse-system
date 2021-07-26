package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.KitDisposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KitDisposalRepository extends JpaRepository<KitDisposal,Integer> {

    @Query(value = "select * from kit_disposals where active = 1 and kit_po_id = :kitPoId", nativeQuery = true)
    KitDisposal findKitDisposalByKitPo(@Param(value = "kitPoId") Integer kitPoId);

    @Query(value = "select * from kit_disposals where active = 1 and disposal_id = :disposal", nativeQuery = true)
    Optional<List<KitDisposal>> findKitDisposalsByDisposal(@Param(value = "disposal") Integer disposalId);

    @Query(value = "select * from kit_disposals where active = 1 and disposal_id = :disposal and " +
            "kit_po_id = :kitPo", nativeQuery = true)
    KitDisposal findKitDisposalByDisposalAndKitPo(@Param(value = "disposal") Integer disposalId,
                                                  @Param(value = "kitPo") Integer kitPoId);

    @Query(value = "select * from kit_disposals where active = 1 and kit_disposal_id = :id", nativeQuery = true)
    Optional<KitDisposal> findKitDisposalById(@Param(value = "id") Integer id);
}
