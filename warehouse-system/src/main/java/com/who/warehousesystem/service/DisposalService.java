package com.who.warehousesystem.service;

import com.who.warehousesystem.dto.DisposalSaveDto;
import com.who.warehousesystem.model.Disposal;
import com.who.warehousesystem.model.User;
import com.who.warehousesystem.repository.DisposalRepository;
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
public class DisposalService {

    @Autowired
    DisposalRepository disposalRepository;

    @Autowired
    UserService userService;

    public List<Disposal> findAllDisposalDgv() {
        return disposalRepository.findAllDisposalsDgv().orElse(new ArrayList<>());
    }

    public Disposal saveDisposal(DisposalSaveDto dto, Integer userId) throws Exception {
        checkDuplicate(dto);
        User user = userService.findUserById(userId);
        Disposal disposal = new Disposal(dto,user);
        return disposalRepository.save(disposal);
    }

    private void checkDuplicate(DisposalSaveDto dto) throws Exception {
        if(disposalRepository.findDisposalByReasonAndDate(dto.getReason(),dto.getDate()) != null)
            throwDuplicateException();
    }

    private void throwDuplicateException() throws Exception {
        throw new Exception("There is another Disposal with the same reason and date");
    }

    public Disposal editDisposal(Integer id, DisposalSaveDto dto, Integer userId) throws Exception {
        checkDuplicate(id,dto);
        User user = userService.findUserById(userId);
        Disposal disposal = findDisposalById(id);
        disposal.setReason(dto.getReason());
        disposal.setDisposalDate(dto.getDate());
        disposal.setUpdatedBy(user);
        return disposalRepository.save(disposal);
    }

    private Disposal findDisposalById(Integer id) throws Exception {
        return disposalRepository.findDisposalById(id).orElseThrow(() ->
                new Exception("No Disposal found for ID : " + id));
    }

    private void checkDuplicate(Integer id, DisposalSaveDto dto) throws Exception {
        Disposal disposal = disposalRepository.findDisposalByReasonAndDate(dto.getReason(),dto.getDate());
        if(disposal != null && disposal.getId() != id)
            throwDuplicateException();
    }

    @Autowired
    ItemDisposalService itemDisposalService;

    @Autowired
    KitDisposalService kitDisposalService;

    public void deleteDisposal(Integer id, Integer userId) throws Exception {
        checkRelatedData(id);
        User user = userService.findUserById(userId);
        Disposal disposal = findDisposalById(id);
        disposal.setActive(false);
        disposal.setUpdatedBy(user);
        disposalRepository.save(disposal);
    }

    private void checkRelatedData(Integer disposalId) throws Exception {
        if(itemDisposalService.findAllItemDisposalsByDisposal(disposalId).size() > 0 ||
           kitDisposalService.findAllKitDisposalsByDisposal(disposalId).size() > 0)
            throw new Exception("Cannot delete this Disposal becaues it's included in another tables");
    }

    @Autowired
    EntityManager entityManager;

    public List<Disposal> searchDisposals(String reason, String date, boolean d) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Disposal> cq = cb.createQuery(Disposal.class);
        Root<Disposal> root = cq.from(Disposal.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("active"),true));

        if(!reason.isEmpty() && !reason.isBlank())
            predicates.add(cb.like(root.get("reason"), "%" + reason + "%"));
        if(d)
            predicates.add(cb.equal(root.get("disposalDate"), LocalDate.parse(date)));

        cq.select(root).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(cq).getResultList();
    }
}
