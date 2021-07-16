package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.InventoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryTypeRepository extends JpaRepository<InventoryType,Integer> {

    @Query(value = "select * from inventory_types where type_id = :id", nativeQuery = true)
    Optional<InventoryType> findTypeById(@Param(value = "id") Integer id);
}
