package com.who.warehousesystem.service;

import com.who.warehousesystem.dto.CenterEditDto;
import com.who.warehousesystem.dto.CenterSaveDto;
import com.who.warehousesystem.model.District;
import com.who.warehousesystem.model.HealthCenter;
import com.who.warehousesystem.model.KitDp;
import com.who.warehousesystem.model.User;
import com.who.warehousesystem.repository.HealthCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class HealthCenterService {

    @Autowired
    HealthCenterRepository healthCenterRepository;

    @Autowired
    UserService userService;

    @Autowired
    DistrictService districtService;

    @Autowired
    ItemDpService itemDpService;

    @Autowired
    KitDpService kitDpService;

    @Autowired
    WaybillService waybillService;

    @Autowired
    EntityManager entityManager;

    public List<HealthCenter> findAllHealthCenters () {
        return healthCenterRepository.findAllHealthCenters().orElse(new ArrayList<>());
    }

    public HealthCenter saveHealthCenter(CenterSaveDto dto,Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        District district = districtService.findDistrictById(dto.getDistrictId());
        HealthCenter healthCenter = new HealthCenter(district, dto.getEnName(), dto.getArName(), user);
        return healthCenterRepository.save(healthCenter);
    }

    public HealthCenter editHealthCenter(Integer id, CenterEditDto dto, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        HealthCenter healthCenter = healthCenterRepository.findHealthCenterById(id)
                .orElseThrow(() -> new Exception("No Health Center found for ID : " + id));
        healthCenter.setDistrict(districtService.findDistrictByNames(dto.getDistrictEn(),dto.getDistrictAr()));
        healthCenter.setArName(dto.getArName());
        healthCenter.setEnName(dto.getEnName());
        healthCenter.setUpdatedBy(user);
        return healthCenterRepository.save(healthCenter);
    }

    public void deleteHealthCenter(Integer id, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        HealthCenter healthCenter = healthCenterRepository.findHealthCenterById(id).orElseThrow(() ->
                new Exception("No Health Center found for ID : " + id));
        checkHealthCenters(id);
        healthCenter.setUpdatedBy(user);
        healthCenter.setActive(false);
        healthCenterRepository.save(healthCenter);
    }

    private void checkHealthCenters(Integer id) throws Exception {
        if(itemDpService.findItemDpByHealthCenter(id) ||
           kitDpService.findKitDpByHealthCenter(id) ||
           waybillService.findWaybillByHealthCenter(id))
            throw new Exception("Cannot delete this health center because it's included with another tables");
    }

    public List<HealthCenter> searchHealthCenter(String cityName, String districtName, String enName, String arName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<HealthCenter> cq = cb.createQuery(HealthCenter.class);
        Root<HealthCenter> root = cq.from(HealthCenter.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("active"), true));

        if(!cityName.isBlank() && !cityName.isEmpty())
            predicates.add(cb.like(root.join("district").join("city").get("arName"),"%" + cityName + "%"));
        if(!districtName.isBlank() && !districtName.isEmpty())
            predicates.add(cb.like(root.join("district").get("arName"), "%" + districtName + "%"));
        if(!enName.isBlank() && !enName.isEmpty())
            predicates.add(cb.like(root.get("enName"), "%" + enName + "%"));
        if(!arName.isBlank() && !arName.isEmpty())
            predicates.add(cb.like(root.get("arName"), "%" + arName + "%"));

        cq.select(root).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(cq).getResultList();
    }
}
