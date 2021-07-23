package com.who.warehousesystem.service;

import com.who.warehousesystem.dto.KitDetailSaveDto;
import com.who.warehousesystem.model.Item;
import com.who.warehousesystem.model.KitDetail;
import com.who.warehousesystem.model.KitPo;
import com.who.warehousesystem.model.User;
import com.who.warehousesystem.repository.KitDetailRepository;
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
public class KitDetailService {

    @Autowired
    KitDetailRepository kitDetailRepository;

    @Autowired
    UserService userService;

    @Autowired
    KitPoService kitPoService;

    @Autowired
    ItemService itemService;

    public boolean findKitDetailByKitPo(Integer kitPoId) {
        List<KitDetail> kitDetails = kitDetailRepository.findKitDetailsByKitPo(kitPoId);
        if(kitDetails.size() >= 1)
            return true;
        else
            return false;
    }

    public List<KitDetail> findAllKitDetailsByKitPoDgv(Integer kitPoId) {
        return kitDetailRepository.findAllKitDetailsByKitPo(kitPoId).orElse(new ArrayList<>());
    }

    public KitDetail saveKitDetail(Integer kitPoId, KitDetailSaveDto dto, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        KitPo kitPo = kitPoService.findKitPoById(kitPoId);
        Item item = itemService.findItemByDetails(dto.getItemName(),dto.getMinTemp(),dto.getMaxTemp(),
                dto.getDescription(),user);
        checkItemDuplicate(kitPoId,item.getId(),dto.getBoxNo());
        KitDetail kitDetail = new KitDetail(kitPo,item,dto,user);
        return kitDetailRepository.save(kitDetail);
    }

    private void checkItemDuplicate(Integer kitPoId, Integer itemId, Integer boxNo) throws Exception {
        if(kitDetailRepository.findKitDetailByKitPoAndItemAndBox(kitPoId,itemId,boxNo) != null)
            throw new Exception("Cannot add the same item to the same box twice");
    }

    public KitDetail editKitDetail(Integer kitPoId, Integer detailId, KitDetailSaveDto dto, Integer userId)
            throws Exception {
        User user = userService.findUserById(userId);
        KitPo kitPo = kitPoService.findKitPoById(kitPoId);
        Item item = itemService.findItemByDetails(dto.getItemName(), dto.getMinTemp(), dto.getMaxTemp(),
                dto.getDescription(),user);
        checkItemDuplicate(kitPoId,item.getId(),dto.getBoxNo(),detailId);
        KitDetail kitDetail = findKitDetailById(detailId);
        kitDetail.setBoxNo(dto.getBoxNo());
        kitDetail.setKitPo(kitPo);
        kitDetail.setItem(item);
        kitDetail.setExpDate(dto.getExpDate());
        kitDetail.setPackaging(dto.getPackaging());
        kitDetail.setPacksPerBox(dto.getPacksPerBox());
        kitDetail.setPiecesPerPack(dto.getPiecesPerPack());
        kitDetail.setUpdatedBy(user);
        return kitDetailRepository.save(kitDetail);
    }

    private KitDetail findKitDetailById(Integer id) throws Exception {
        return kitDetailRepository.findKitDetailById(id).orElseThrow(() ->
                new Exception("No Kit detail found for ID : " + id));
    }

    private void checkItemDuplicate(Integer kitPoId, Integer itemId, Integer boxNo, Integer detailId) throws Exception {
        KitDetail kitDetail = kitDetailRepository.findKitDetailByKitPoAndItemAndBox(kitPoId,itemId,boxNo);
        if(kitDetail != null && kitDetail.getId() != detailId)
            throw new Exception("Cannot add the same item to the same box twice");
    }

    public void deleteKitDetail(Integer detailId, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        KitDetail kitDetail = findKitDetailById(detailId);
        kitDetail.setActive(false);
        kitDetail.setUpdatedBy(user);
        kitDetailRepository.save(kitDetail);
    }

    @Autowired
    EntityManager entityManager;

    public List<KitDetail> searchKitDetails(String kitPoId, String boxNo, String minTemp, String maxTemp,
                                            String description, String packaging, String packs, String pieces,
                                            String expDate, String itemId,boolean exp) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<KitDetail> cq = cb.createQuery(KitDetail.class);
        Root<KitDetail> root = cq.from(KitDetail.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("active"), true));
        predicates.add(cb.equal(root.join("kitPo").get("id"), kitPoId));

        if(!boxNo.isEmpty() && !boxNo.isBlank())
            predicates.add(cb.equal(root.get("boxNo"), boxNo));
        if(!minTemp.isEmpty() && !minTemp.isBlank())
            predicates.add(cb.equal(root.join("item").get("minTemp"),minTemp));
        if(!maxTemp.isEmpty() && !maxTemp.isBlank())
            predicates.add(cb.equal(root.join("item").get("maxTemp"),maxTemp));
        if(!description.isEmpty() && !description.isBlank())
            predicates.add(cb.equal(root.join("item").get("description"),description));
        if(!packaging.isEmpty() && !packaging.isBlank())
            predicates.add(cb.equal(root.get("packaging"),packaging));
        if(!packs.isEmpty() && !packs.isBlank())
            predicates.add(cb.equal(root.get("packsPerBox"),packs));
        if(!pieces.isEmpty() && !pieces.isBlank())
            predicates.add(cb.equal(root.get("piecesPerPack"),pieces));
        if(exp)
            predicates.add(cb.equal(root.get("expDate"), LocalDate.parse(expDate)));
        if(!itemId.isEmpty() && !itemId.isBlank())
            predicates.add(cb.equal(root.join("item").get("id"),itemId));

        cq.select(root).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(cq).getResultList();
    }
}
