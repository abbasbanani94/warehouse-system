package com.who.warehousesystem.service;

import com.who.warehousesystem.dto.WbDetailsSaveDto;
import com.who.warehousesystem.model.User;
import com.who.warehousesystem.model.Waybill;
import com.who.warehousesystem.repository.WaybillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WaybillService {

    @Autowired
    WaybillRepository waybillRepository;

    @Autowired
    ItemDpService itemDpService;

    @Autowired
    KitDpService kitDpService;

    @Autowired
    UserService userService;

    public boolean findWaybillByHealthCenter(Integer healthCenterId) {
        List<Waybill> waybills = waybillRepository.findWaybillByHealthCenter(healthCenterId);
        if(waybills.size() >= 1)
            return true;
        else
            return false;
    }

    public List<Waybill> findAllWaybills() {
        return waybillRepository.findAllWaybills().orElse(new ArrayList<>());
    }

    public List<String> findItemsKitsListNoWb(Integer dpId, Integer centerId) throws Exception {
        List<String> items = itemDpService.findItemsDpListByDpAndCenterNoWb(dpId,centerId);
        List<String> kits = kitDpService.findKitsDpListByDpAndCenterNoWb(dpId,centerId);
        List<String> listBox = addTwoLists(items,kits);
        return listBox;
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

    public List<String> findItemsKitsListWb(Integer dpId, Integer centerId) {
        List<String> items = itemDpService.findItemsDpListByDpAndCenterWb(dpId, centerId);
        List<String> kits = kitDpService.findKitsDpListByDpAndCenterWb(dpId, centerId);
        List<String> listBox = addTwoLists(items,kits);
        return listBox;
    }

    public void saveWaybillDetails(WbDetailsSaveDto dto, Integer userId) throws Exception {
        User user = userService.findUserById(userId);

    }
}
