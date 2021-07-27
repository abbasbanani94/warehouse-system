package com.who.warehousesystem.service;

import com.who.warehousesystem.dto.CheckDgvDto;
import com.who.warehousesystem.dto.CheckSaveDto;
import com.who.warehousesystem.model.Check;
import com.who.warehousesystem.model.CheckType;
import com.who.warehousesystem.model.User;
import com.who.warehousesystem.repository.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckService {

    @Autowired
    CheckRepository checkRepository;

    @Autowired
    UserService userService;

    @Autowired
    CheckTypeService checkTypeService;

    public List<CheckDgvDto> findAllChecksDgv() {
        return checkRepository.findAllChecksDgv().orElse(new ArrayList<>()).stream().map(check -> {
            return new CheckDgvDto((Integer)check[0],check[1].toString(),(Date)check[2],
                    check[3].toString(),(Integer)check[6],((Integer)check[4] + (Integer)check[5]));
        }).collect(Collectors.toList());
    }

    public Check saveCheck(CheckSaveDto dto, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        CheckType checkType = checkTypeService.findCheckTypeById(dto.getTypeId());
        Check check = new Check(checkType,dto.getNotes(),dto.getDate(),user);
        return checkRepository.save(check);
    }

    public Check editCheck(Integer id, CheckSaveDto dto, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        CheckType checkType = checkTypeService.findCheckTypeById(dto.getTypeId());
        Check check = findCheckById(id);
        check.setCheckType(checkType);
        check.setCheckDate(dto.getDate());
        check.setNotes(dto.getNotes());
        check.setUpdatedBy(user);
        return checkRepository.save(check);
    }

    public Check findCheckById(Integer id) throws Exception {
        return checkRepository.findCheckById(id).orElseThrow(() ->
                new Exception("No Check for ID : " + id));
    }

    public void deleteCheck(Integer id, Integer userId) throws Exception {
        checkRelatedData(id);
        User user = userService.findUserById(userId);
        Check check = findCheckById(id);
        check.setActive(false);
        check.setUpdatedBy(user);
        checkRepository.save(check);
    }

    @Autowired
    CheckItemPoService checkItemPoService;

    @Autowired
    CheckKitPoService checkKitPoService;

    @Autowired
    CheckWorkerService checkWorkerService;

    private void checkRelatedData(Integer checkId) throws Exception {
        if(checkItemPoService.findCheckItemsByCheck(checkId).size() > 0 ||
           checkKitPoService.findCheckKitsByCheck(checkId).size() > 0 ||
           checkWorkerService.findCheckWorkersByCheck(checkId).size() > 0)
            throw new Exception("Cannot delete this check record because it's included in other tables");
    }

    @Autowired
    EntityManager entityManager;

    public List<CheckDgvDto> searchChecks(String type, String notes, String date, boolean d) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Check> cq = cb.createQuery(Check.class);
        Root<Check> root = cq.from(Check.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("active"), true));

        if(!type.isEmpty() && !type.isBlank())
            predicates.add(cb.equal(root.join("checkType").get("enName"),type));
        if(!notes.isEmpty() && !notes.isBlank())
            predicates.add(cb.like(root.get("notes"),"%" + notes + "%"));
        if(d)
            predicates.add(cb.equal(root.get("checkDate"),LocalDate.parse(date)));

        cq.select(root).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        List<Check> checks = entityManager.createQuery(cq).getResultList();

        List<Integer> ids = checks.stream().map(check -> {
            return check.getId();
        }).collect(Collectors.toList());

        return checkRepository.searchAllChecksDgv(ids).orElse(new ArrayList<>()).stream().map(check -> {
            return new CheckDgvDto((Integer)check[0],check[1].toString(),(Date)check[2],
                    check[3].toString(),(Integer)check[6],((Integer)check[4] + (Integer)check[5]));
        }).collect(Collectors.toList());
    }
}
