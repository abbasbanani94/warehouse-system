package com.who.warehousesystem.service;

import com.who.warehousesystem.dto.ItemDisposalSaveDto;
import com.who.warehousesystem.model.*;
import com.who.warehousesystem.repository.ItemDisposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ItemDisposalService {

    @Autowired
    ItemDisposalRepository itemDisposalRepository;

    @Autowired
    UserService userService;

    @Autowired
    ItemPoService itemPoService;

    @Autowired
    ItemInventoryService itemInventoryService;

    @Autowired
    InventoryTypeService inventoryTypeService;

    @Autowired
    DisposalService disposalService;

    public ItemDisposal findItemDisposalByItemPo(Integer itemPoId) {
        return itemDisposalRepository.findItemDisposalByItemPo(itemPoId);
    }

    public List<ItemDisposal> findAllItemDisposalsByDisposal(Integer disposalId) {
        return itemDisposalRepository.findItemDisposalsByDisposal(disposalId).orElse(new ArrayList<>());
    }

    public ItemDisposal saveItemDisposal(Integer disposalId, ItemDisposalSaveDto dto, Integer userId) throws Exception {
        checkDuplicate(disposalId,dto.getItemPoId());
        User user = userService.findUserById(userId);
        Disposal disposal = disposalService.findDisposalById(disposalId);
        ItemPo itemPo = itemPoService.findItemPoById(dto.getItemPoId());
        ItemDisposal itemDisposal = new ItemDisposal(disposal,itemPo,dto.getQty(),user);

        itemDisposal = itemDisposalRepository.save(itemDisposal);
        itemPoService.subInventoryByItemDisposal(itemDisposal,user);
        InventoryType inventoryType = inventoryTypeService.findTypeById(2);//Out

        ItemInventory itemInventory = new ItemInventory(itemDisposal,inventoryType,user);
        itemInventoryService.saveItemInventory(itemInventory);
        return itemDisposal;
    }

    private void checkDuplicate(Integer disposalId, Integer itemPoId) throws Exception {
        if(itemDisposalRepository.findItemDisposalByDisposalAndItemPo(disposalId,itemPoId) != null)
            throwDuplicateException();
    }

    private void throwDuplicateException() throws Exception {
        throw new Exception("the same item was already inserted to this Disposal");
    }

    public ItemDisposal editItemDisposal(Integer id, Integer disposalId, ItemDisposalSaveDto dto, Integer userId)
            throws Exception {
        checkDuplicate(disposalId,dto.getItemPoId(),id);
        User user = userService.findUserById(userId);
        ItemDisposal itemDisposal = findItemDisposalById(id);
    }

    private ItemDisposal findItemDisposalById(Integer id) throws Exception {
        return itemDisposalRepository.findItemDisposalById(id).orElseThrow(() ->
                new Exception("No Item Disposal for ID : " + id));
    }

    private void checkDuplicate(Integer disposalId, Integer itemPoId, Integer id) throws Exception {
        ItemDisposal itemDisposal = itemDisposalRepository.findItemDisposalByDisposalAndItemPo(disposalId,itemPoId);
        if(itemDisposal != null && itemDisposal.getId() != id)
            throwDuplicateException();
    }
}
