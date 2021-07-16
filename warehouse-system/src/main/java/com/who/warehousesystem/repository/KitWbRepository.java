package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.KitWb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitWbRepository extends JpaRepository<KitWb,Integer> {
}
