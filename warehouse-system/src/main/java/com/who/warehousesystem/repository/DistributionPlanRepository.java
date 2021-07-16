package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.DistributionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistributionPlanRepository extends JpaRepository<DistributionPlan,Integer> {
}
