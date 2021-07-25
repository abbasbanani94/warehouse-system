package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.ItemCheckingWorker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemCheckingWorkerRepository extends JpaRepository<ItemCheckingWorker, Integer> {

    @Query(value = "select * from item_checking_workers where active = 1 and worker_id = :workerId",nativeQuery = true)
    Optional<List<ItemCheckingWorker>> findAllItemsCheckingByWorker(@Param(value = "workerId") Integer workerId);
}
