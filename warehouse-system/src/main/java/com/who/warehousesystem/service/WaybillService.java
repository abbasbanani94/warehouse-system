package com.who.warehousesystem.service;

import com.who.warehousesystem.dto.WbDetailsSaveDto;
import com.who.warehousesystem.dto.WbSaveDto;
import com.who.warehousesystem.model.*;
import com.who.warehousesystem.repository.WaybillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class WaybillService {

    @Autowired
    WaybillRepository waybillRepository;

    @Autowired
    HealthCenterService healthCenterService;

    @Autowired
    ItemDpService itemDpService;

    @Autowired
    KitDpService kitDpService;

    @Autowired
    UserService userService;

    @Autowired
    ItemWbService itemWbService;

    @Autowired
    KitWbService kitWbService;

    public boolean findWaybillByHealthCenter(Integer healthCenterId) {
        List<Waybill> waybills = waybillRepository.findWaybillByHealthCenter(healthCenterId);
        if(waybills.size() >= 1)
            return true;
        else
            return false;
    }

    private List<String> addTwoLists(List<String> items, List<String> kits) {
        List<String> listBox = new ArrayList<>();
        for(String item : items) {
            listBox.add(item);
        }
        for(String kit : kits) {
            listBox.add(kit);
        }
        return listBox;
    }

    public List<Waybill> findAllWaybillsDgv() {
        return waybillRepository.findAllWaybillsDgv().orElse(new ArrayList<>());
    }

    public Waybill saveWaybill(WbSaveDto dto, Integer userId) throws Exception {
        checkWaybillNoDuplicate(dto.getWbNo());
        User user = userService.findUserById(userId);
        HealthCenter healthCenter = healthCenterService.findHealthCenterById(dto.getCenterId());
        Waybill waybill = new Waybill(dto,healthCenter,user);
        return waybillRepository.save(waybill);
    }

    private void checkWaybillNoDuplicate(Integer wbNo) throws Exception {
        if(waybillRepository.findWaybillByNo(wbNo) != null)
            throw new Exception("WB NO entered is already taken, please check and retry again");
    }

    private void checkWaybillNoDuplicate(Integer id, Integer wbNo) throws Exception {
        Waybill waybill = waybillRepository.findWaybillByNo(wbNo);
        if(waybill != null && waybill.getId() != id)
            throw new Exception("WB NO entered is already taken, please check and retry again");
    }

    public Waybill editWaybill(Integer id, WbSaveDto dto, Integer userId) throws Exception {
        checkWaybillNoDuplicate(id,dto.getWbNo());
        User user = userService.findUserById(userId);
        Waybill waybill = findWaybillById(id);
        HealthCenter healthCenter = healthCenterService.findHealthCenterById(dto.getCenterId());
        waybill.setExportDate(dto.getWbDate());
        waybill.setFormNo(dto.getWbNo());
        waybill.setHealthCenter(healthCenter);
        waybill.setTotalBoxes(dto.getBoxes());
        waybill.setTotalPallets(dto.getPallets());
        waybill.setUpdatedBy(user);
        return waybillRepository.save(waybill);
    }

    public Waybill findWaybillById(Integer id) throws Exception {
        return waybillRepository.findWaybillById(id).orElseThrow(() ->
                new Exception("No Waybill for ID : " + id));
    }

    public void deleteWaybill(Integer id, Integer userId) throws Exception {
        checkWaybillExistence(id);
        User user = userService.findUserById(userId);
        Waybill waybill = findWaybillById(id);
        waybill.setUpdatedBy(user);
        waybill.setActive(false);
        waybillRepository.save(waybill);
    }

    private void checkWaybillExistence(Integer id) throws Exception {
        if(itemWbService.findItemWbByWb(id).size() > 0 || kitWbService.findKitWbByWb(id).size() > 0)
            throw new Exception("Cannot delete this Waybill because it's included in another tables");
    }

    @Autowired
    EntityManager entityManager;

    public List<Waybill> searchWaybill(String wbNo, String wbDate, String boxes, String pallets, String cityId,
                                       String districtId, String centerId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Waybill> cq = cb.createQuery(Waybill.class);
        Root<Waybill> root = cq.from(Waybill.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("active"), true));

        if(!wbNo.isBlank() && !wbNo.isEmpty())
            predicates.add(cb.equal(root.get("formNo"), wbNo));
        if(!wbDate.isBlank() && !wbDate.isEmpty())
            predicates.add(cb.equal(root.get("exportDate"),wbDate));
        if(!boxes.isBlank() && !boxes.isEmpty())
            predicates.add(cb.equal(root.get("totalBoxes"), boxes));
        if(!pallets.isEmpty() && !pallets.isBlank())
            predicates.add(cb.equal(root.get("totalPallets"), pallets));
        if(!cityId.isBlank() && !cityId.isEmpty())
            predicates.add(cb.equal(root.join("healthCenter").join("district").join("city").get("id"),cityId));
        if(!districtId.isBlank() && !districtId.isEmpty())
            predicates.add(cb.equal(root.join("healthCenter").join("district").get("id"),districtId));
        if(!centerId.isEmpty() && !centerId.isBlank())
            predicates.add(cb.equal(root.join("healthCenter").get("id"),districtId));

        cq.select(root).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<String> findDpItemsByWb(Integer wbId) throws Exception {
        HealthCenter healthCenter = healthCenterService.findHealthCenterByWb(wbId);
        List<String> items = itemDpService.findDpItemsByCenterNoWb(healthCenter.getId());
        List<String> kits = kitDpService.findDpKitsByCenterNoWb(healthCenter.getId());

        return addTwoLists(items,kits);
    }

    public List<String> findWbItemsByWb(Integer wbId) throws Exception {
        HealthCenter healthCenter = healthCenterService.findHealthCenterByWb(wbId);
        List<String> items = itemDpService.findDpItemsByCenterWb(healthCenter.getId());
        List<String> kits = kitDpService.findDpKitsByCenterWb(healthCenter.getId());

        return addTwoLists(items,kits);
    }

    public void saveWbDetails(Integer id, WbDetailsSaveDto dto, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        Waybill waybill = findWaybillById(id);
        if(itemWbService.deleteItemWbByWb(id,user) && kitWbService.deleteItemWbByWb(id,user)) {
            List<ItemDp> itemDps = itemDpService.extractItemDpsFromWbStringList(dto.getWbList());
            List<KitDp> kitDps = kitDpService.extractKitsDpsFromWbStringList(dto.getWbList());

            itemWbService.saveItemWbs(waybill,itemDps,user);
            kitWbService.saveKitWbs(waybill,kitDps,user);
        }
    }
}
