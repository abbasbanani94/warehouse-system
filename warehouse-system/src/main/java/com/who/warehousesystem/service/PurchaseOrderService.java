package com.who.warehousesystem.service;

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

    public List<PurchaseOrder> findAllPurchaseOrders () {
        return purchaseOrderRepository.findAllPurchaseOrders().orElse(new ArrayList<>());
    }

    public PurchaseOrder savePurchaseOrder(Integer poNo,Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        checkPoDuplicate(poNo);
        PurchaseOrder purchaseOrder = new PurchaseOrder(poNo, user);
        return purchaseOrderRepository.save(purchaseOrder);
    }

    private void checkPoDuplicate(Integer poNo) throws Exception {
        if(purchaseOrderRepository.findPurchaseOrderByNo(poNo) != null)
            throw new Exception("There is another purchase order with the same No entered");
    }

    public PurchaseOrder findPurchaseOrderByPoNo(Integer poId, String poNo, User user) throws Exception {
        if(poId != null)
            return purchaseOrderRepository.findPurchaseOrderById(poId)
                    .orElseThrow(() -> new Exception("PO not found for ID : " + poId));
        else {
            return purchaseOrderRepository.save(new PurchaseOrder(Integer.parseInt(poNo),user));
        }
    }

    public List<PoDgvDto> findAllPoDgv() {
        return purchaseOrderRepository.findAllPoDgv().orElse(new ArrayList<>()).stream().map(po -> {
            return new PoDgvDto(Integer.parseInt(po[0].toString()),po[1].toString(),
                    Integer.parseInt(po[2].toString()),Integer.parseInt(po[3].toString()));
        }).collect(Collectors.toList());
    }

    public PurchaseOrder editPurchaseOrder(Integer id, Integer poNo, Integer userId) throws Exception {
        PurchaseOrder po = checkPoDuplicate(poNo, id);
        User user = userService.findUserById(userId);
        po.setNo(poNo.toString());
        po.setUpdatedBy(user);
        return purchaseOrderRepository.save(po);
    }

    private PurchaseOrder checkPoDuplicate(Integer poNo, Integer id) throws Exception {
        PurchaseOrder po = purchaseOrderRepository.findPurchaseOrderByNo(poNo);
        if(po != null && po.getId() == id)
            throw new Exception("There is another purchase order with the same No entered");
        return po;
    }
}
