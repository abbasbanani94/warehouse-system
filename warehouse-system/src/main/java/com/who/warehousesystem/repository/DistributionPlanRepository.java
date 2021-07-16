package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.DistributionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistributionPlanRepository extends JpaRepository<DistributionPlan,Integer> {

    @Query(value = "select * from distribution_plans where active = 1", nativeQuery = true)
    Optional<List<DistributionPlan>> findAllDistributionPlans();
}
