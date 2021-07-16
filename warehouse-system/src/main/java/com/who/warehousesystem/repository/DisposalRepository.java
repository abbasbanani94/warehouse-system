package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.Disposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisposalRepository extends JpaRepository<Disposal,Integer> {
}
