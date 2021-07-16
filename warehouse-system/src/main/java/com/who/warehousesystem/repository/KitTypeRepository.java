package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.KitType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KitTypeRepository extends JpaRepository<KitType,Integer> {

    @Query(value = "select * from kit_types where active = 1 and name = :name", nativeQuery = true)
    Optional<KitType> findKitTypeByName(@Param(value = "name") String kitType);
}
