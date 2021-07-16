package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.ItemWb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemWbRepository extends JpaRepository<ItemWb,Integer> {
}
