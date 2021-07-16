package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.DpChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DpCheckingRepository extends JpaRepository<DpChecking,Integer> {
}
