package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.Kit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface KitRepository extends JpaRepository<Kit,Integer> {

    @Query(value = "select * from kits where active = 1 order by kit_id desc", nativeQuery = true)
    Optional<List<Kit>> findAllKits();

    @Query(value = "select * from kits where active = 1 and kit_id = :id", nativeQuery = true)
    Optional<Kit> findKitById(@Param(value = "id") Integer id);

    @Query(value = "select * from kits where name = :kitName and min_temp = :minTemp and max_temp = :maxTemp and " +
            "description = :description and kit_type_id = :kitType and active = 1", nativeQuery = true)
    Kit findKitByDetails(@Param(value = "kitName") String kitName,
                         @Param(value = "minTemp") BigDecimal minTemp,
                         @Param(value = "maxTemp") BigDecimal maxTemp,
                         @Param(value = "description") String kitDescription,
                         @Param(value = "kitType") Integer kitType);
}
