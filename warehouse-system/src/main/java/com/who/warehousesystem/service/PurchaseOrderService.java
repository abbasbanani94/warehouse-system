package com.who.warehousesystem.service;

import com.who.warehousesystem.model.PurchaseOrder;
import com.who.warehousesystem.model.User;
import com.who.warehousesystem.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseOrderService {

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    UserService userService;

    public List<PurchaseOrder> findAllPurchaseOrders () {
        return purchaseOrderRepository.findAllPurchaseOrders().orElse(new ArrayList<>());
    }

    public PurchaseOrder savePurchaseOrder(String poNo,Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        PurchaseOrder purchaseOrder = new PurchaseOrder(poNo, user);
        return purchaseOrderRepository.save(purchaseOrder);
    }

    public PurchaseOrder findPurchaseOrderByPoNo(Integer poId, String poNo, User user) throws Exception {
        if(poId != null)
            return purchaseOrderRepository.findPurchaseOrderById(poId)
                    .orElseThrow(() -> new Exception("PO not found for ID : " + poId));
        else {
            return purchaseOrderRepository.save(new PurchaseOrder(poNo,user));
        }
    }
}
