package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.City;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City,Integer> {

    @Query(value = "select * from cities where active = 1", nativeQuery = true)
    Optional<List<City>> findAllCities();
}
