package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.DpCheckingWorker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DpCheckingWorkerRepository extends JpaRepository<DpCheckingWorker,Integer> {
}
