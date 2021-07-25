package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.DpCheckingWorker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DpCheckingWorkerRepository extends JpaRepository<DpCheckingWorker,Integer> {

    @Query(value = "select * from dp_checking_workers where active = 1 and worker_id = :worker", nativeQuery = true)
    Optional<List<DpCheckingWorker>> findAllDpCheckingWorkersByWorker(@Param(value = "worker") Integer workerId);
}
