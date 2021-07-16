package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.KitCheckingWorker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitCheckingWorkerRepository extends JpaRepository<KitCheckingWorker,Integer> {
}
