package com.who.warehousesystem.service;

import com.who.warehousesystem.model.Country;
import com.who.warehousesystem.model.User;
import com.who.warehousesystem.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public List<Country> findAllCountries() {
        return countryRepository.findAllCountries().orElse(new ArrayList<>());
    }

    public Country findCountryByName(String name, User user) {
        Country country = countryRepository.findCountryByName(name);
        if(country != null)
            return country;
        else {
            country = new Country(name,user);
            return countryRepository.save(country);
        }
    }
}
