package com.who.warehousesystem.service;

import com.who.warehousesystem.dto.PoComboDto;
import com.who.warehousesystem.dto.PoDgvDto;
import com.who.warehousesystem.model.PurchaseOrder;
import com.who.warehousesystem.model.User;
import com.who.warehousesystem.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderService {

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    UserService userService;

    private List<PoComboDto> findAllPurchaseOrders (Integer index) {
        List<PoComboDto> list = new ArrayList<>();
        List<Object[]> objects = purchaseOrderRepository.findAllPoDgv().orElse(new ArrayList<>());
        for(Object[] po : objects) {
            if (Integer.parseInt(po[index].toString()) != 0) {
                list.add(new PoComboDto(Integer.parseInt(po[0].toString()), po[1].toString()));
            }
        }
        return list;
    }

    public List<PoComboDto> findAllPurchaseOrdersItems() {
        return findAllPurchaseOrders(2); // 2 for items
    }

    public List<PoComboDto> findAllPurchaseOrdersKits() {
        return findAllPurchaseOrders(3); // 3 for kits
    }

    public PurchaseOrder savePurchaseOrder(String poNo,Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        checkPoDuplicate(poNo);
        PurchaseOrder purchaseOrder = new PurchaseOrder(poNo, user);
        return purchaseOrderRepository.save(purchaseOrder);
    }

    private void checkPoDuplicate(String poNo) throws Exception {
        if(purchaseOrderRepository.findPurchaseOrderByNo(poNo).isPresent())
            throw new Exception("There is another purchase order with the same No entered");
    }

    public PurchaseOrder findPurchaseOrderByPoNo(Integer poId, String poNo, User user) throws Exception {
        if(poId != null)
            return purchaseOrderRepository.findPurchaseOrderById(poId)
                    .orElseThrow(() -> new Exception("PO not found for ID : " + poId));
        else {
            if(purchaseOrderRepository.findPurchaseOrderByNo(poNo).isPresent())
                return purchaseOrderRepository.findPurchaseOrderByNo(poNo).get();
            return purchaseOrderRepository.save(new PurchaseOrder(poNo,user));
        }
     }

    public List<PoDgvDto> findAllPoDgv() {
        return purchaseOrderRepository.findAllPoDgv().orElse(new ArrayList<>()).stream().map(po -> {
            return new PoDgvDto(Integer.parseInt(po[0].toString()),po[1].toString(),
                    Integer.parseInt(po[2].toString()),Integer.parseInt(po[3].toString()));
        }).collect(Collectors.toList());
    }

    public PurchaseOrder editPurchaseOrder(Integer id, String poNo, Integer userId) throws Exception {
        PurchaseOrder po = checkPoDuplicate(poNo, id);
        User user = userService.findUserById(userId);
        po.setNo(poNo);
        po.setUpdatedBy(user);
        return purchaseOrderRepository.save(po);
    }

    private PurchaseOrder checkPoDuplicate(String poNo, Integer id) throws Exception {
        PurchaseOrder po = purchaseOrderRepository.findPurchaseOrderByNo(poNo).orElse(null);
        if(po != null && po.getId() != id)
            throw new Exception("There is another purchase order with the same No entered");
        return po;
    }

    public void deletePurchaseOrder(Integer id, Integer userId) throws Exception {
        checkPoRelatedData(id);
        User user = userService.findUserById(userId);
        PurchaseOrder po = findPurchaseOrderById(id);
        po.setActive(false);
        po.setUpdatedBy(user);
        purchaseOrderRepository.save(po);
    }

    private PurchaseOrder findPurchaseOrderById(Integer id) throws Exception {
        return purchaseOrderRepository.findPurchaseOrderById(id).orElseThrow(() ->
                new Exception("No Purchase Order for ID : " + id));
    }

    @Autowired
    ItemPoService itemPoService;

    @Autowired
    KitPoService kitPoService;

    private void checkPoRelatedData(Integer id) throws Exception {
        if(itemPoService.findItemsCountByPo(id) != 0 || kitPoService.findKitsCountByPo(id) != 0)
            throw new Exception("This PO cannot be deleted because it's included with Items or Kits");
    }

    public List<PoDgvDto> searchAllPoDgv(Integer no) {
        return purchaseOrderRepository.searchAllPoDgv(no).orElse(new ArrayList<>()).stream().map(po -> {
            return new PoDgvDto(Integer.parseInt(po[0].toString()),po[1].toString(),
                    Integer.parseInt(po[2].toString()),Integer.parseInt(po[3].toString()));
        }).collect(Collectors.toList());
    }

    public List<PurchaseOrder> findAllPurchaseOrders() {
        return purchaseOrderRepository.findAllPurchaseOrders().orElse(new ArrayList<>());
    }
}
