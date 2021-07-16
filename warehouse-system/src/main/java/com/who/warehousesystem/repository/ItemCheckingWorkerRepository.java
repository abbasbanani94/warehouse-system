package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.ItemCheckingWorker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCheckingWorkerRepository extends JpaRepository<ItemCheckingWorker, Integer> {
}
