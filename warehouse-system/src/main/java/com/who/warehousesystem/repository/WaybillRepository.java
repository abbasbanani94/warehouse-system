package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.Waybill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WaybillRepository extends JpaRepository<Waybill,Integer> {

    @Query(value = "select * from waybills where active = 1 and health_center_id = :healthCenterId", nativeQuery = true)
    List<Waybill> findWaybillByHealthCenter(@Param(value = "healthCenterId") Integer healthCenterId);

    @Query(value = "select * from waybills where active = 1 order by waybill_id desc", nativeQuery = true)
    Optional<List<Waybill>> findAllWaybills();
}
