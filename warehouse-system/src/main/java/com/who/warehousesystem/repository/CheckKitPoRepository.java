package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.CheckKitPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckKitPoRepository extends JpaRepository<CheckKitPo,Integer> {
}
