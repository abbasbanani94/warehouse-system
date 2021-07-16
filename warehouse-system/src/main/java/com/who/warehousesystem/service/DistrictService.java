package com.who.warehousesystem.service;

import com.who.warehousesystem.model.District;
import com.who.warehousesystem.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.BitSet;
import java.util.List;

@Service
public class DistrictService {

    @Autowired
    DistrictRepository districtRepository;

    public List<District> findAllDistricts() throws Exception {
        return districtRepository.findAllDistricts().orElseThrow(() ->
                new Exception("No districts added to the system, please contact the system admin"));
    }

    public List<District> findAllDistrictsByCity(Integer cityId) throws Exception {
        return districtRepository.findAllDistrictsByCity(cityId).orElseThrow(() ->
                new Exception("No districts added to the system, please contact the system admin"));
    }

    public District findDistrictById(Integer districtId) throws Exception {
        return districtRepository.findDistrictById(districtId).orElseThrow(() ->
                new Exception("No District found for ID : " + districtId));
    }

    public District findDistrictByNames(String enName, String arName) throws Exception {
        return districtRepository.findDistrictByNames(enName,arName).orElseThrow(() ->
                new Exception("No District found for Name : " + enName + " and arName : " + arName));
    }
}
