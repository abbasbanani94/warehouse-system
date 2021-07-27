package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.CheckType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckTypeRepository extends JpaRepository<CheckType,Integer> {

    @Query(value = "select * from check_types where active = 1", nativeQuery = true)
    Optional<List<CheckType>> findAllCheckTypes();

    @Query(value = "select * from check_types where active = 1 and check_type_id = :typeId",nativeQuery = true)
    Optional<CheckType> findCheckTypeById(@Param(value = "typeId") Integer typeId);
}
