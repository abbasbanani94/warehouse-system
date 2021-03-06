package com.who.warehousesystem.service;

import com.who.warehousesystem.dto.ItemPoSaveDto;
import com.who.warehousesystem.dto.ItemSaveDto;
import com.who.warehousesystem.model.Item;
import com.who.warehousesystem.model.PurchaseOrder;
import com.who.warehousesystem.model.User;
import com.who.warehousesystem.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserService userService;

    public Item saveItem(ItemSaveDto dto, Integer userId) throws Exception {
        if(itemRepository.checkItemExistence(dto.getName(),dto.getMinTemp(),dto.getMaxTemp(),dto.getDescription())
            .isEmpty()) {
            User user = userService.findUserById(userId);
            Item item = new Item(dto,user);
            return itemRepository.save(item);
        }
        else {
            throw new Exception("Cannot save new item, another item has the same data");
        }
    }

    public List<Item> findItemsCombo() {
        return itemRepository.findAllItems().orElse(new ArrayList<>());
    }

    public Item findItemById(Integer id) throws Exception {
        return itemRepository.findItemById(id).orElseThrow(() -> new Exception("No Item for ID : " + id));
    }

    public Item findItemByDetails (String name,BigDecimal minTemp,BigDecimal maxTemp,String description,User user) {
        Item item = itemRepository.findItemByDetails(name,minTemp,maxTemp,description);
        if(item != null)
            return item;
        else {
            item = new Item(name, minTemp, maxTemp,description,user);
            return itemRepository.save(item);
        }
    }

    public Item findItemByItemPo(ItemPoSaveDto dto, User user) {
        return findItemByDetails(dto.getItemName(),dto.getMinTemp(),dto.getMaxTemp(),dto.getItemDescription(),user);
    }
}
