package com.who.warehousesystem.service;

import com.who.warehousesystem.model.City;
import com.who.warehousesystem.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

    @Autowired
    CityRepository cityRepository;

    public List<City> findAllCities() throws Exception {
        return cityRepository.findAllCities().orElseThrow(() ->
                new Exception("No Cities added to the system, please contact the system admin"));
    }
}
