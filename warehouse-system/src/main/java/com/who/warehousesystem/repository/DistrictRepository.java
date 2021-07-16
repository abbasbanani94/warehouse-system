package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District,Integer> {

    @Query(value = "select * from districts where active = 1", nativeQuery = true)
    Optional<List<District>> findAllDistricts();

    @Query(value = "select * from districts where active = 1 and city_id = :cityId", nativeQuery = true)
    Optional<List<District>> findAllDistrictsByCity(@Param(value = "cityId") Integer cityId);

    @Query(value = "select * from districts where active = 1 and district_id = :districtId", nativeQuery = true)
    Optional<District> findDistrictById(@Param(value = "districtId") Integer districtId);

    @Query(value = "select * from districts where active = 1 and en_name = :en and ar_name = :ar",nativeQuery = true)
    Optional<District> findDistrictByNames(@Param(value = "en") String enName,
                                           @Param(value = "ar") String arName);
}
