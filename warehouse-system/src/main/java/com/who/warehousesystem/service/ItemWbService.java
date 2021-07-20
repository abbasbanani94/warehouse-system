package com.who.warehousesystem.service;

import com.who.warehousesystem.model.*;
import com.who.warehousesystem.repository.ItemWbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemWbService {

    @Autowired
    ItemWbRepository itemWbRepository;

    public ItemWb findItemWbByItemDp(Integer itemDpId) {
        return itemWbRepository.findItemWbByItemDp(itemDpId);
    }

    public List<ItemWb> findItemWbByWb(Integer wbId) {
        return itemWbRepository.findAllItemWbByWb(wbId).orElse(new ArrayList<>());
    }

    public boolean deleteItemWbByWb(Integer wbId, User user) {
        List<ItemWb> itemWbs = itemWbRepository.findAllItemWbByWb(wbId).orElse(new ArrayList<>());
        for(ItemWb itemWb : itemWbs) {
            itemWb.setActive(false);
            itemWb.setUpdatedBy(user);
        }
        itemWbRepository.saveAll(itemWbs);
        return true;
    }

    public void saveItemWbs(Waybill waybill, List<ItemDp> itemDps, User user) throws Exception {
        List<ItemWb> itemWbs = new ArrayList<>();
        for (ItemDp itemDp : itemDps) {
            ItemWb itemWb = new ItemWb(waybill,itemDp,user);
            itemWbs.add(itemWb);
        }
        itemWbRepository.saveAll(itemWbs);
    }
}
