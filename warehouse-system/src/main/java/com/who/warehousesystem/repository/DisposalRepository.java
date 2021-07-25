package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.Disposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DisposalRepository extends JpaRepository<Disposal,Integer> {

    @Query(value = "select top 20 * from disposals where active = 1 order by disposal_id desc",nativeQuery = true)
    Optional<List<Disposal>> findAllDisposalsDgv();

    @Query(value = "select * from disposals where active = 1 and reason = :reason and disposal_date = :date",
            nativeQuery = true)
    Disposal findDisposalByReasonAndDate(@Param(value = "reason") String reason,
                                         @Param(value = "date") LocalDate date);

    @Query(value = "select * from disposals where active = 1 and disposal_id = :id", nativeQuery = true)
    Optional<Disposal> findDisposalById(@Param(value = "id") Integer id);
}
