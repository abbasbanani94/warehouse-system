package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.CheckWorker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckWorkerRepository extends JpaRepository<CheckWorker,Integer> {
}
