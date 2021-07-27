package com.who.warehousesystem.service;

import com.who.warehousesystem.dto.KitDpSaveDto;
import com.who.warehousesystem.model.*;
import com.who.warehousesystem.repository.KitDpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KitDpService {

    @Autowired
    KitDpRepository kitDpRepository;

    @Autowired
    UserService userService;

    @Autowired
    DistributionPlanService dpService;

    @Autowired
    HealthCenterService healthCenterService;

    @Autowired
    KitPoService kitPoService;

    @Autowired
    InventoryTypeService inventoryTypeService;

    @Autowired
    KitInventoryService kitInventoryService;

    @Autowired
    KitWbService kitWbService;

    public List<KitDp> findKitDpByKitPo(Integer kitPoId) {
        return kitDpRepository.findKitDpByKitPo(kitPoId).orElse(new ArrayList<>());
    }

    public boolean findKitDpByHealthCenter(Integer healthCenterId) {
        List<KitDp> kitDps = kitDpRepository.findKitDpByHealthCenter(healthCenterId);
        if(kitDps.size() >= 1)
            return true;
        else
            return false;
    }

    public List<KitDp> findAllKitDpDgv() {
        return kitDpRepository.findAllKitDpDgv().orElse(new ArrayList<>());
    }

    public KitDp saveKitDp(KitDpSaveDto dto, Integer userId) throws Exception {
        checkKitDpDuplicate(dto);
        User user = userService.findUserById(userId);
        DistributionPlan dp = dpService.findDpByDetails(dto.getPlanId(),dto.getEnName(),dto.getArName(),dto.getPlanDate(),user);
        HealthCenter center = healthCenterService.findHealthCenterById(dto.getCenterId());
        KitPo kitPo = kitPoService.findKitPoById(dto.getKitPoId());
        KitDp kitDp = new KitDp(dp,center,kitPo,dto.getQty(),user);

        kitDp = kitDpRepository.save(kitDp);

        InventoryType inventoryType = inventoryTypeService.findTypeById(2); //Out
        KitInventory kitInventory = new KitInventory(kitDp, inventoryType, user);

        kitInventoryService.saveKitInventory(kitInventory);
        kitPoService.subInventoryByKitDp(kitDp, user);

        return kitDp;
    }

    private void checkKitDpDuplicate(KitDpSaveDto dto) throws Exception {
        if(kitDpRepository.findKitDpByDpAndCenterAndKitPo(dto.getPlanId(),dto.getCenterId(),dto.getKitPoId()) != null)
            throw new Exception("This kitPo already saved for this center and DP");
    }

    private void checkKitDpDuplicate(Integer id,KitDpSaveDto dto) throws Exception {
        KitDp kitDp =kitDpRepository.findKitDpByDpAndCenterAndKitPo(dto.getPlanId(),dto.getCenterId(),dto.getKitPoId());
        if(kitDp != null && kitDp.getId() != id)
            throw new Exception("This kitPo already saved for this center and DP");
    }

    public KitDp editKitDp(Integer id, KitDpSaveDto dto, Integer userId) throws Exception {
        checkKitDpDuplicate(id, dto);
        User user = userService.findUserById(userId);
        KitDp kitDp = findKitDpById(id);
        kitPoService.addInventoryByKitDp(kitDp, user);
        InventoryType inventoryType = inventoryTypeService.findTypeById(2); //Out
        KitInventory kitInventory = kitInventoryService.findKitInventoryByTypeAndKitDp
                (inventoryType.getId(), id);
        KitPo kitPo = kitPoService.findKitPoById(dto.getKitPoId());
        DistributionPlan dp = dpService.findDpByDetails(dto.getPlanId(),dto.getEnName(),dto.getArName(),dto.getPlanDate(),user);
        HealthCenter center = healthCenterService.findHealthCenterById(dto.getCenterId());
        kitDp.setDistributionPlan(dp);
        kitDp.setHealthCenter(center);
        kitDp.setKitPo(kitPo);
        kitDp.setQty(dto.getQty());
        kitDp.setUpdatedBy(user);
        kitDp = kitDpRepository.save(kitDp);

        kitInventory.setKitPo(kitPo);
        kitInventory.setKitDp(kitDp);
        kitInventory.setOutQty(kitDp.getQty());
        kitInventory.setUpdatedBy(user);
        kitInventory.setNote("KitDp ID: " + id + ", Plan Name: " + kitDp.getDistributionPlan().getEnName() + ", " +
                "Kit: " + kitDp.getKitPo().getKit().getName() + ", Out Qty: " + kitDp.getQty());

        kitInventoryService.saveKitInventory(kitInventory);
        kitPoService.subInventoryByKitDp(kitDp, user);
        return kitDp;
    }

    private KitDp findKitDpById(Integer id) throws Exception {
        return kitDpRepository.findKitDpById(id).orElseThrow(() ->
                new Exception("No Kit DP found for ID : " + id));
    }

    public void deleteKitDp(Integer id, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        KitDp kitDp = findKitDpById(id);
        checkKitDpExistence(id);
        kitDp.setUpdatedBy(user);
        kitDp.setActive(false);
        kitPoService.addInventoryByKitDp(kitDp, user);
        kitDpRepository.save(kitDp);
        KitInventory kitInventory = kitInventoryService.findKitInventoryByTypeAndKitDp(2, id);
        kitInventory.setActive(false);
        kitInventory.setUpdatedBy(user);
        kitInventoryService.saveKitInventory(kitInventory);
    }

    private void checkKitDpExistence(Integer id) throws Exception {
        if(kitWbService.findKitWbByKitDp(id) != null)
            throw new Exception("Kit DP cannot be deleted because it's included in other tables");
    }

    @Autowired
    EntityManager entityManager;

    public List<KitDp> searchKitDp(String planId, String date, String poId, String kitPoId, String cityId,
                                   String districtId, String centerId, String qty,boolean d) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<KitDp> cq = cb.createQuery(KitDp.class);
        Root<KitDp> root = cq.from(KitDp.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("active"), true));

        if(!districtId.isBlank() && !districtId.isEmpty())
            predicates.add(cb.equal(root.join("healthCenter").join("district").get("id"), districtId));
        if(!centerId.isBlank() && !centerId.isEmpty())
            predicates.add(cb.equal(root.join("healthCenter").get("id"), centerId));
        if(!poId.isBlank() && !poId.isEmpty())
            predicates.add(cb.equal(root.join("kitPo").join("purchaseOrder").get("id"), poId));
        if(!kitPoId.isBlank() && !kitPoId.isEmpty())
            predicates.add(cb.equal(root.join("kitPo").get("id"), kitPoId));
        if(!cityId.isBlank() && !cityId.isEmpty())
            predicates.add(cb.equal(root.join("healthCenter").join("district").join("city").get("id"), cityId));
        if(!qty.isBlank() && !qty.isEmpty())
            predicates.add(cb.equal(root.get("qty"), qty));
        if(!planId.isBlank() && !planId.isEmpty())
            predicates.add(cb.equal(root.join("distributionPlan").get("id"), planId));
        if(d)
            predicates.add(cb.equal(root.join("distributionPlan").get("dDate"), LocalDate.parse(date)));

        cq.select(root).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<String> findDpKitsByCenterNoWb(Integer centerId) {
        return kitDpRepository.findKitDpByCenterNoWb(centerId).orElse(new ArrayList<>()).stream().map(kitDp -> {
            return "K" + kitDp.getId() + " - " + kitDp.getKitPo().getKit().getName() + " - PO#" +
                    kitDp.getKitPo().getOrder().getNo() + " - Qty : " + kitDp.getQty();
        }).collect(Collectors.toList());
    }

    public List<String> findDpKitsByCenterWb(Integer centerId) {
        return kitDpRepository.findKitDpByCenterWb(centerId).orElse(new ArrayList<>()).stream().map(kitDp -> {
            return "K" + kitDp.getId() + " - " + kitDp.getKitPo().getKit().getName() + " - Qty : " + kitDp.getQty();
        }).collect(Collectors.toList());
    }

    public List<KitDp> extractKitsDpsFromWbStringList(List<String> wbList) throws Exception {
        List<KitDp> kitDps = new ArrayList<>();
        for(String kit : wbList) {
            if(kit.startsWith("K")) {
                Integer id = Integer.parseInt(kit.substring(1,kit.indexOf(' ')));
                KitDp kitDp = findKitDpById(id);
                kitDps.add(kitDp);
            }
        }
        return kitDps;
    }

    public List<KitDp> findKitDpByDp(Integer dpId) {
        return kitDpRepository.findKitDpByDp(dpId).orElse(new ArrayList<>());
    }
}
