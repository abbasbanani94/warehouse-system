package com.who.warehousesystem.service;

import com.who.warehousesystem.dto.KitDisposalSaveDto;
import com.who.warehousesystem.model.*;
import com.who.warehousesystem.repository.KitDisposalRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class KitDisposalService {

    @Autowired
    KitDisposalRepository kitDisposalRepository;

    @Autowired
    UserService userService;

    @Autowired
    DisposalService disposalService;

    @Autowired
    KitPoService kitPoService;

    @Autowired
    InventoryTypeService inventoryTypeService;

    @Autowired
    KitInventoryService kitInventoryService;

    public KitDisposal findKitDetailByKitPo(Integer kitPoId) {
        return kitDisposalRepository.findKitDisposalByKitPo(kitPoId);
    }

    public List<KitDisposal> findAllKitDisposalsByDisposal(Integer disposalId) {
        return kitDisposalRepository.findKitDisposalsByDisposal(disposalId).orElse(new ArrayList<>());
    }

    public KitDisposal saveKitDisposal(Integer disposalId, KitDisposalSaveDto dto, Integer userId) throws Exception {
        checkDuplicate(disposalId,dto.getKitPoId());
        User user = userService.findUserById(userId);
        Disposal disposal = disposalService.findDisposalById(disposalId);
        KitPo kitPo = kitPoService.findKitPoById(dto.getKitPoId());
        KitDisposal kitDisposal = new KitDisposal(disposal,kitPo,dto.getQty(),user);

        kitDisposal = kitDisposalRepository.save(kitDisposal);
        kitPoService.subInventoryByKitDisposal(kitDisposal,user);
        InventoryType inventoryType = inventoryTypeService.findTypeById(2); //Out

        KitInventory kitInventory = new KitInventory(kitDisposal,inventoryType,user);
        kitInventoryService.saveKitInventory(kitInventory);
        return kitDisposal;
    }

    private void checkDuplicate(Integer disposalId, Integer kitPoId) throws Exception {
        if(kitDisposalRepository.findKitDisposalByDisposalAndKitPo(disposalId,kitPoId) != null)
            throwDuplicateException();
    }

    private void throwDuplicateException() throws Exception {
        throw new Exception("the same Kit was already inserted to this Disposal");
    }

    public KitDisposal editKitDisposal(Integer id, Integer disposalId, KitDisposalSaveDto dto, Integer userId)
            throws Exception {
        checkDuplicate(disposalId,dto.getKitPoId(),id);
        User user = userService.findUserById(userId);
        KitDisposal kitDisposal = findKitDisposalById(id);
        kitPoService.addInventoryByKitDisposal(kitDisposal,user);
        InventoryType inventoryType = inventoryTypeService.findTypeById(2); //Out
        KitInventory kitInventory =kitInventoryService.findKitInventoryByTypeAndKitDisposal(2,id);
        Disposal disposal = disposalService.findDisposalById(disposalId);
        KitPo kitPo = kitPoService.findKitPoById(dto.getKitPoId());
        kitDisposal.setDisposal(disposal);
        kitDisposal.setKitPo(kitPo);
        kitDisposal.setQty(dto.getQty());
        kitDisposal.setUpdatedBy(user);
        kitDisposal = kitDisposalRepository.save(kitDisposal);

        kitInventory.setKitDisposal(kitDisposal);
        kitInventory.setKitPo(kitPo);
        kitInventory.setOutQty(dto.getQty());
        kitInventory.setUpdatedBy(user);
        kitInventory.setNote("KitDisposal ID: " + id + ", Disposal Reason: " + kitDisposal.getDisposal().getReason() +
                ", Kit: " + kitPo.getKit().getName() + ", Out Qty: " + kitDisposal.getQty());
        kitInventoryService.saveKitInventory(kitInventory);
        kitPoService.subInventoryByKitDisposal(kitDisposal,user);
        return kitDisposal;
    }

    private KitDisposal findKitDisposalById(Integer id) throws Exception {
        return kitDisposalRepository.findKitDisposalById(id).orElseThrow(() ->
                new Exception("No Kit Disposal for ID : " + id));
    }

    private void checkDuplicate(Integer disposalId, Integer kitPoId, Integer id) throws Exception {
        KitDisposal kitDisposal = kitDisposalRepository.findKitDisposalByDisposalAndKitPo(disposalId,kitPoId);
        if(kitDisposal != null && kitDisposal.getId() != id)
            throwDuplicateException();
    }

    public void deleteKitDisposal(Integer id, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        KitDisposal kitDisposal = findKitDisposalById(id);
        kitPoService.addInventoryByKitDisposal(kitDisposal,user);
        KitInventory kitInventory = kitInventoryService.findKitInventoryByTypeAndKitDisposal(2,id);
        kitDisposal.setActive(false);
        kitDisposal.setUpdatedBy(user);
        kitDisposalRepository.save(kitDisposal);
        kitInventory.setActive(false);
        kitInventory.setUpdatedBy(user);
        kitInventoryService.saveKitInventory(kitInventory);
    }

    @Autowired
    EntityManager entityManager;

    public List<KitDisposal> searchKitDisposals(Integer disposalId, String poId, String kitPoId, String qty) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<KitDisposal> cq = cb.createQuery(KitDisposal.class);
        Root<KitDisposal> root = cq.from(KitDisposal.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("active"),true));
        predicates.add(cb.equal(root.join("disposal").get("id"),disposalId));

        if(!poId.isBlank() && !poId.isEmpty())
            predicates.add(cb.equal(root.join("kitPo").join("order").get("id"),poId));
        if(!kitPoId.isBlank() && !kitPoId.isEmpty())
            predicates.add(cb.equal(root.join("kitPo").get("id"), kitPoId));
        if(!qty.isEmpty() && !qty.isBlank())
            predicates.add(cb.equal(root.get("qty"),qty));

        cq.select(root).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(cq).getResultList();
    }
}
