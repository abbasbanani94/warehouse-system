package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country,Integer> {

    @Query(value = "select * from countries where active = 1 order by country_id desc", nativeQuery = true)
    Optional<List<Country>> findAllCountries();

    @Query(value = "select * from countries where active = 1 and name = :name", nativeQuery = true)
    Country findCountryByName(@Param(value = "name") String name);
}
