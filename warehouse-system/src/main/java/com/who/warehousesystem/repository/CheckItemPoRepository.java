package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.CheckItemPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckItemPoRepository extends JpaRepository<CheckItemPo,Integer> {
}
