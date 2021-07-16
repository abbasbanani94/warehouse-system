package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.CheckType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckTypeRepository extends JpaRepository<CheckType,Integer> {
}
