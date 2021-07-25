package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.KitCheckingWorker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KitCheckingWorkerRepository extends JpaRepository<KitCheckingWorker,Integer> {

    @Query(value = "select * from kit_checking_workers where active = 1 and worker_id = :worker",nativeQuery = true)
    Optional<List<KitCheckingWorker>> findAllKitsCheckingByWorker(@Param(value = "worker") Integer workerId);
}
