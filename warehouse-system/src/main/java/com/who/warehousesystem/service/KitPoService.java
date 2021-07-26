package com.who.warehousesystem.service;

import com.who.warehousesystem.dto.KitPoSaveDto;
import com.who.warehousesystem.dto.KitPoSearchDto;
import com.who.warehousesystem.model.*;
import com.who.warehousesystem.repository.KitPoRepository;
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
public class KitPoService {

    @Autowired
    KitPoRepository kitPoRepository;

    @Autowired
    UserService userService;

    @Autowired
    PurchaseOrderService purchaseOrderService;

    @Autowired
    KitService kitService;

    @Autowired
    CountryService countryService;

    @Autowired
    InventoryTypeService inventoryTypeService;

    @Autowired
    KitInventoryService kitInventoryService;

    @Autowired
    KitTypeService kitTypeService;

    @Autowired
    KitDetailService kitDetailService;

    @Autowired
    KitDisposalService kitDisposalService;

    @Autowired
    KitDpService kitDpService;

    @Autowired
    KitPoCheckingService kitPoCheckingService;

    @Autowired
    EntityManager entityManager;

    public List<KitPo> findKitPoDgv() {
        return kitPoRepository.findAllKitPoDgv().orElse(new ArrayList<>());
    }

    private KitPo initiateObject(KitPoSaveDto dto, User user) throws Exception {
        PurchaseOrder po = purchaseOrderService.findPurchaseOrderByPoNo(dto.getPoId(),dto.getPoNo(),user);
        KitType kitType = kitTypeService.findKitTypeByName(dto.getKitType());
        Kit kit = kitService.findKitByKitPo(dto, user, kitType);
        Country country = countryService.findCountryByName(dto.getCountry(),user);
        return new KitPo(kit,po,dto.getDateReceived(),dto.getManDate(),dto.getExpDate(),country,
                dto.getBatchNo(),dto.getPalletsQty(),dto.getBoxesPerPallet(),dto.getKitsPerPallet(),
                dto.getTotalQty(),dto.getLocation(), user);
    }

    public KitPo saveKitPo(KitPoSaveDto dto, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        KitPo kitPo = initiateObject(dto, user);
        kitPo = kitPoRepository.save(kitPo);
        InventoryType inventoryType = inventoryTypeService.findTypeById(1); //In
        KitInventory kitInventory = new KitInventory(kitPo, user, inventoryType);
        kitInventoryService.saveKitInventory(kitInventory);
        return kitPo;
    }

    public KitPo editKitPo(Integer id, KitPoSaveDto dto, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        KitPo kitPo = initiateObject(dto, user);
        kitPo.setId(id);
        kitPo.setUpdatedBy(user);
        kitPo = kitPoRepository.save(kitPo);
        InventoryType inventoryType = inventoryTypeService.findTypeById(1); //In
        KitInventory kitInventory = kitInventoryService.findKitInventoryByTypeAndKitPo
                (inventoryType.getId(),kitPo.getId());
        kitInventory.setInQty(kitPo.getTotalQty());
        kitInventory.setNote("KitPo Id: " + kitPo.getId() + ", PO No: " + kitPo.getOrder().getNo() +
                ", Kit: " + kitPo.getKit().getName() + ", Exp. Date: " + kitPo.getExpDate() +
                ", InQty: " + kitPo.getTotalQty());
        kitInventory.setUpdatedBy(user);
        kitInventoryService.saveKitInventory(kitInventory);
        return kitPo;
    }

    public void deleteKitPo(Integer id, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        KitPo kitPo = kitPoRepository.findKitPoById(id).orElseThrow(() ->
                new Exception("Kit PO not found for ID : " + id));
        checkKitPoExistence(id);
        kitPo.setActive(false);
        kitPo.setUpdatedBy(user);
        kitPoRepository.save(kitPo);
        InventoryType inventoryType = inventoryTypeService.findTypeById(1);//In
        KitInventory kitInventory = kitInventoryService.findKitInventoryByTypeAndKitPo
                (inventoryType.getId(),kitPo.getId());
        kitInventory.setActive(false);
        kitInventory.setUpdatedBy(user);
        kitInventoryService.saveKitInventory(kitInventory);
    }

    private void checkKitPoExistence(Integer kitPoId) throws Exception {
        if(kitDetailService.findKitDetailByKitPo(kitPoId) ||
           kitDisposalService.findKitDetailByKitPo(kitPoId) != null ||
           kitDpService.findKitDpByKitPo(kitPoId) != null ||
           kitPoCheckingService.findKitPoCheckingByKitPo (kitPoId) != null) {
            throw new Exception("Kit PO ID : " + kitPoId + " cannot be deleted because it's included with another tables");
        }
    }

    public List<KitPo> searchKitPo(KitPoSearchDto dto) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<KitPo> cq = cb.createQuery(KitPo.class);
        Root<KitPo> root = cq.from(KitPo.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("active"), true));

        if(!dto.getBatchNo().isBlank() && !dto.getBatchNo().isEmpty())
            predicates.add(cb.like(root.get("batchNo"), "%" + dto.getBatchNo() + "%"));
        if(!dto.getBoxesPallet().isBlank() && !dto.getBoxesPallet().isEmpty())
            predicates.add(cb.equal(root.get("boxesPerPallet"), dto.getBoxesPallet()));
        if(!dto.getCountry().isBlank() && !dto.getCountry().isEmpty())
            predicates.add(cb.equal(root.join("country").get("name"), dto.getCountry()));
        if(dto.isRec())
            predicates.add(cb.equal(root.get("recDate"), LocalDate.parse(dto.getDateReceived())));
        if(!dto.getDescription().isBlank() && !dto.getDescription().isEmpty())
            predicates.add(cb.like(root.join("kit").get("description"), "%" + dto.getDescription() + "%"));
        if(dto.isExp())
            predicates.add(cb.equal(root.get("expDate"), LocalDate.parse(dto.getExpDate())));
        if(dto.isMan())
            predicates.add(cb.equal(root.get("manDate"), LocalDate.parse(dto.getManDate())));
        if(!dto.getKitId().isBlank() && !dto.getKitId().isEmpty())
            predicates.add(cb.equal(root.join("kit").get("id"), dto.getKitId()));
        if(!dto.getKitsPallet().isBlank() && !dto.getKitsPallet().isEmpty())
            predicates.add(cb.equal(root.get("kitsPerPallet"), dto.getKitsPallet()));
        if(!dto.getKitType().isBlank() && !dto.getKitType().isEmpty())
            predicates.add(cb.equal(root.join("kit").join("kitType").get("name"), dto.getKitType()));
        if(!dto.getLocation().isBlank() && !dto.getLocation().isEmpty())
            predicates.add(cb.like(root.get("location"), "%" + dto.getLocation() + "%"));
        if(!dto.getPalletsQty().isBlank() && !dto.getPalletsQty().isEmpty())
            predicates.add(cb.equal(root.get("palletsQty"), dto.getPalletsQty()));
        if(!dto.getPoId().isBlank() && !dto.getPoId().isEmpty())
            predicates.add(cb.equal(root.join("order").get("id"), dto.getPoId()));
        if(!dto.getTotalQty().isBlank() && !dto.getTotalQty().isEmpty())
            predicates.add(cb.equal(root.get("totalQty"), dto.getTotalQty()));

        cq.select(root).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<KitPo> findKitsPoComboByPo(Integer poId) throws Exception {
        return kitPoRepository.findKitsPoByPo(poId).orElseThrow(() ->
                new Exception("No Kits were added using this PO ID : " + poId));
    }

    public KitPo findKitPoById(Integer id) throws Exception {
        return kitPoRepository.findKitPoById(id).orElseThrow(() ->
                new Exception("No Kit PO with ID : " + id));
    }

    private void editInventoryByKitPo(KitPo kitPo, Integer qty, User user, String operation) {
        if(operation.equalsIgnoreCase("add"))
            kitPo.setInventory(kitPo.getInventory() + qty);
        else
            kitPo.setInventory(kitPo.getInventory() - qty);
        kitPo.setUpdatedBy(user);
        kitPoRepository.save(kitPo);
    }

    public void subInventoryByKitDp(KitDp kitDp, User user) {
        editInventoryByKitPo(kitDp.getKitPo(), kitDp.getQty(),user, "sub");
    }

    public void addInventoryByKitDp(KitDp kitDp, User user) {
        editInventoryByKitPo(kitDp.getKitPo(), kitDp.getQty(), user, "add");
    }

    public String findKitNameByKitPo(Integer id) throws Exception {
        return findKitPoById(id).getKit().getName();
    }

    public int findKitsCountByPo(Integer poId) {
        return kitPoRepository.findKitsCountByPo(poId);
    }

    public void subInventoryByKitDisposal(KitDisposal kitDisposal, User user) {
        editInventoryByKitPo(kitDisposal.getKitPo(),kitDisposal.getQty(),user,"sub");
    }

    public void addInventoryByKitDisposal(KitDisposal kitDisposal, User user) {
        editInventoryByKitPo(kitDisposal.getKitPo(),kitDisposal.getQty(),user,"add");
    }
}
