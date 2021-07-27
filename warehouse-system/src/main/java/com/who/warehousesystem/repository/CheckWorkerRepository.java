package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.CheckWorker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckWorkerRepository extends JpaRepository<CheckWorker,Integer> {

    @Query(value = "select * from check_workers where active = 1 and worker_id = :worker", nativeQuery = true)
    Optional<List<CheckWorker>> findCheckWorkersByWorker(@Param(value = "worker") Integer workerId);

    @Query(value = "select * from check_workers where active = 1 and check_id = :check", nativeQuery = true)
    Optional<List<CheckWorker>> findCheckWorkersByCheck(@Param(value = "check") Integer checkId);
}
