package com.who.warehousesystem.service;

import com.who.warehousesystem.dto.PlanSaveDto;
import com.who.warehousesystem.model.DistributionPlan;
import com.who.warehousesystem.model.User;
import com.who.warehousesystem.repository.DistributionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DistributionPlanService {

    @Autowired
    DistributionPlanRepository distributionPlanRepository;

    @Autowired
    UserService userService;

    public List<DistributionPlan> findDpCombo() {
        return distributionPlanRepository.findAllDps().orElse(new ArrayList<>());
    }

    public DistributionPlan findDpByDetails (Integer planId, String enName, String arName, LocalDate planDate, User user)
            throws Exception {
        if(planId != null && planId != 0)
            return distributionPlanRepository.findDpById(planId).orElseThrow(() ->
                    new Exception("No Plan for ID : " + planId));
        else {
            DistributionPlan dp = distributionPlanRepository.findDpByNamesAndDate(enName,arName,planDate);
            if(dp != null)
                return dp;
            else {
                dp = new DistributionPlan(arName,enName,planDate,user);
                return distributionPlanRepository.save(dp);
            }
        }
    }

    public DistributionPlan findDpById(Integer id) throws Exception {
        return distributionPlanRepository.findDpById(id).orElseThrow(() ->
                new Exception("No DP for ID : " + id));
    }

    public List<DistributionPlan> findAllDpDgv() {
        return distributionPlanRepository.findAllDpDgv().orElse(new ArrayList<>());
    }

    public DistributionPlan saveDistributionPlan(PlanSaveDto dto, Integer userId) throws Exception {
        checkDpDuplicates(dto);
        User user = userService.findUserById(userId);
        DistributionPlan dp = new DistributionPlan(dto.getArName(),dto.getEnName(),dto.getDate(),user);
        return distributionPlanRepository.save(dp);
    }

    private void checkDpDuplicates(PlanSaveDto dto) throws Exception {
        if(distributionPlanRepository.findDpByNamesAndDate(dto.getEnName(),dto.getArName(),dto.getDate()) != null)
            throwDuplicateException();
    }

    private void throwDuplicateException() throws Exception {
        throw new Exception("There is another DP with the same names and date");
    }

    public DistributionPlan editDistributionPlan(Integer id, PlanSaveDto dto, Integer userId) throws Exception {
        checkDpDuplicates(dto,id);
        User user = userService.findUserById(userId);
        DistributionPlan dp = findDpById(id);
        dp.setArName(dto.getArName());
        dp.setEnName(dto.getEnName());
        dp.setDDate(dto.getDate());
        dp.setUpdatedBy(user);
        return distributionPlanRepository.save(dp);
    }

    private void checkDpDuplicates(PlanSaveDto dto, Integer id) throws Exception {
        DistributionPlan dp = distributionPlanRepository.findDpByNamesAndDate(dto.getEnName(),dto.getArName(),
                dto.getDate());
        if(dp != null && dp.getId() != id)
            throwDuplicateException();
    }

    @Autowired
    ItemDpService itemDpService;

    @Autowired
    KitDpService kitDpService;

    public void deleteDistributionPlan(Integer id, Integer userId) throws Exception {
        checkDpRelatedData(id);
        User user = userService.findUserById(userId);
        DistributionPlan dp = findDpById(id);
        dp.setActive(false);
        dp.setUpdatedBy(user);
        distributionPlanRepository.save(dp);
    }

    private void checkDpRelatedData(Integer dpId) throws Exception {
        if(itemDpService.findItemDpByDp(dpId).size() > 0 ||
           kitDpService.findKitDpByDp(dpId).size() > 0)
            throw new Exception("This DP cannot be deleted because it's included in another tables");
    }

    @Autowired
    EntityManager entityManager;

    public List<DistributionPlan> searchDistributionPlan(String enName, String arName, String date, boolean d) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<DistributionPlan> cq = cb.createQuery(DistributionPlan.class);
        Root<DistributionPlan> root = cq.from(DistributionPlan.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("active"),true));

        if(!enName.isBlank() && !enName.isEmpty())
            predicates.add(cb.like(root.get("enName"),"%" + enName + "%"));
        if(!arName.isEmpty() && !arName.isBlank())
            predicates.add(cb.like(root.get("arName"), "%" + arName + "%"));
        if(d)
            predicates.add(cb.equal(root.get("dDate"),LocalDate.parse(date)));

        cq.select(root).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(cq).getResultList();
    }
}
