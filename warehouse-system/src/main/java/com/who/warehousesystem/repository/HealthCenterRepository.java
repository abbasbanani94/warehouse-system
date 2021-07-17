package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.HealthCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HealthCenterRepository extends JpaRepository<HealthCenter,Integer> {

    @Query(value = "select * from health_centers where active = 1 order by health_center_id desc", nativeQuery = true)
    Optional<List<HealthCenter>> findAllHealthCenters();

    @Query(value = "select * from health_centers where active = 1 and health_center_id = :id", nativeQuery = true)
    Optional<HealthCenter> findHealthCenterById(@Param(value = "id") Integer id);

    @Query(value = "select * from health_centers where active = 1 and district_id = :districtId", nativeQuery = true)
    Optional<List<HealthCenter>> findHealthCentersByDistrict(@Param(value = "districtId") Integer districtId);

    @Query(value = "select top 50 * from health_centers where active = 1 order by health_center_id desc", nativeQuery = true)
    Optional<List<HealthCenter>> findAllHealthCentersDgv();
}
