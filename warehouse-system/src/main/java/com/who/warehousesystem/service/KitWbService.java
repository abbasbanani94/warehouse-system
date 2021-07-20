package com.who.warehousesystem.service;

import com.who.warehousesystem.model.KitDp;
import com.who.warehousesystem.model.KitWb;
import com.who.warehousesystem.model.User;
import com.who.warehousesystem.model.Waybill;
import com.who.warehousesystem.repository.KitWbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KitWbService {

    @Autowired
    KitWbRepository kitWbRepository;

    public KitWb findKitWbByKitDp(Integer kitDpId) {
        return kitWbRepository.findKitWbByKitDp(kitDpId);
    }

    public List<KitWb> findKitWbByWb(Integer wbId) {
        return kitWbRepository.findAllKitWbByWb(wbId).orElse(new ArrayList<>());
    }

    public boolean deleteItemWbByWb(Integer wbId, User user) {
        List<KitWb> kitWbs = kitWbRepository.findAllKitWbByWb(wbId).orElse(new ArrayList<>());
        for(KitWb kitWb : kitWbs) {
            kitWb.setActive(false);
            kitWb.setUpdatedBy(user);
        }
        kitWbRepository.saveAll(kitWbs);
        return true;
    }

    public void saveKitWbs(Waybill waybill, List<KitDp> kitDps, User user) {
        List<KitWb> kitWbs = new ArrayList<>();
        for(KitDp kitDp : kitDps) {
            KitWb kitWb = new KitWb(waybill,kitDp,user);
            kitWbs.add(kitWb);
        }
        kitWbRepository.saveAll(kitWbs);
    }
}
