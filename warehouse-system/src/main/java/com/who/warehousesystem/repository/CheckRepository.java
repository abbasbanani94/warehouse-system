package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.Check;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckRepository extends JpaRepository<Check,Integer> {

    @Query(value = "select top 20 c.check_id,ct.en_name,c.check_date,c.notes,(select count(check_item_po_id) from " +
            "check_item_po where active = 1 and check_id = c.check_id) as items,(select count(check_kit_po_id) from " +
            "check_kit_po where active = 1 and check_id = c.check_id) as kits,(select count(check_worker_id) from " +
            "check_workers where active = 1 and check_id = c.check_id) as workers from checks c,check_types ct where " +
            "c.active = 1 and c.check_type_id = ct.check_type_id order by c.check_id desc", nativeQuery = true)
    Optional<List<Object[]>> findAllChecksDgv();

    @Query(value = "select * from checks where active = 1 and check_id = :id", nativeQuery = true)
    Optional<Check> findCheckById(@Param(value = "id") Integer id);

    @Query(value = "select c.check_id,ct.en_name,c.check_date,c.notes,(select count(check_item_po_id) from " +
            "check_item_po where active = 1 and check_id = c.check_id) as items,(select count(check_kit_po_id) from " +
            "check_kit_po where active = 1 and check_id = c.check_id) as kits,(select count(check_worker_id) from " +
            "check_workers where active = 1 and check_id = c.check_id) as workers from checks c,check_types ct where " +
            "c.active = 1 and c.check_type_id = ct.check_type_id and c.check_id in (:ids)", nativeQuery = true)
    Optional<List<Object[]>> searchAllChecksDgv(@Param(value = "ids") List<Integer> ids);
}
