package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder,Integer> {

    @Query(value = "select * from purchase_orders where active = 1 order by purchase_order_id desc", nativeQuery = true)
    Optional<List<PurchaseOrder>> findAllPurchaseOrders();

    @Query(value = "select * from purchase_orders where purchase_order_id = :id", nativeQuery = true)
    Optional<PurchaseOrder> findPurchaseOrderById(@Param(value = "id") Integer poId);

    @Query(value = "select p.purchase_order_id,p.purchase_order_no,(select count(item_po_id) from item_po " +
            "where active = 1 and purchase_order_id = p.purchase_order_id) as items,(select count(kit_po_id) from " +
            "kit_po where active = 1 and purchase_order_id = p.purchase_order_id) as kits from purchase_orders p where " +
            "p.active = 1 order by p.purchase_order_id desc", nativeQuery = true)
    Optional<List<Object[]>> findAllPoDgv();

    @Query(value = "select * from purchase_orders where active = 1 and purchase_order_no = :no", nativeQuery = true)
    Optional<PurchaseOrder> findPurchaseOrderByNo(@Param(value = "no") String poNo);

    @Query(value = "select p.purchase_order_id,p.purchase_order_no,(select count(item_po_id) from item_po " +
            "where active = 1 and purchase_order_id = p.purchase_order_id) as items,(select count(kit_po_id) from " +
            "kit_po where active = 1 and purchase_order_id = p.purchase_order_id) as kits from purchase_orders p where " +
            "p.active = 1 and p.purchase_order_no = :no", nativeQuery = true)
    Optional<List<Object[]>> searchAllPoDgv(@Param(value = "no") Integer no);
}
