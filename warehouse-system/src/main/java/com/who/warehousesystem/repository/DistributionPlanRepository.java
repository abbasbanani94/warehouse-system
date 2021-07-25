package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.DistributionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DistributionPlanRepository extends JpaRepository<DistributionPlan,Integer> {

    @Query(value = "select * from distribution_plans where active = 1", nativeQuery = true)
    Optional<List<DistributionPlan>> findAllDps();

    @Query(value = "select * from distribution_plans where active = 1 and dp_id = :dpId",nativeQuery = true)
    Optional<DistributionPlan> findDpById(@Param(value = "dpId") Integer planId);

    @Query(value = "select * from distribution_plans where active = 1 and en_name = :en and ar_name = :ar and " +
            "d_date = :planDate", nativeQuery = true)
    DistributionPlan findDpByNamesAndDate(@Param(value = "en") String enName,
                                          @Param(value = "ar") String arName,
                                          @Param(value = "planDate") LocalDate planDate);

    @Query(value = "select top 20 * from distribution_plans where active = 1 order by dp_id desc", nativeQuery = true)
    Optional<List<DistributionPlan>> findAllDpDgv();
}
