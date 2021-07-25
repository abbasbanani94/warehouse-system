package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {

    @Query(value = "select top 20 * from workers where active = 1 order by worker_id desc", nativeQuery = true)
    Optional<List<Worker>> findAllWorkersDgv();

    @Query(value = "select * from workers where active = 1 and en_name = :en and ar_name = :ar and " +
            "mobile = :mobile", nativeQuery = true)
    Worker findWorkerByNamesAndMobile(@Param(value = "en") String enName,
                                                @Param(value = "ar") String arName,
                                                @Param(value = "mobile") String mobile);

    @Query(value = "select * from workers where active = 1 and worker_id = :id",nativeQuery = true)
    Optional<Worker> findWorkerById(@Param(value = "id") Integer id);
}
