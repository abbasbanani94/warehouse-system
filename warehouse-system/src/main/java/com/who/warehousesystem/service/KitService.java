package com.who.warehousesystem.service;

import com.who.warehousesystem.dto.KitPoSaveDto;
import com.who.warehousesystem.model.Kit;
import com.who.warehousesystem.model.KitType;
import com.who.warehousesystem.model.User;
import com.who.warehousesystem.repository.KitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KitService {

    @Autowired
    KitRepository kitRepository;

    public List<Kit> findKitsCombo() {
        return kitRepository.findAllKits().orElse(new ArrayList<>());
    }

    public Kit findKitById(Integer id) throws Exception {
        return kitRepository.findKitById(id).orElseThrow(() -> new Exception("No kit found for ID : " + id));
    }

    public Kit findKitByKitPo(KitPoSaveDto dto, User user, KitType kitType) {
        Kit kit = kitRepository.findKitByDetails(dto.getKitName(),dto.getMinTemp(),dto.getMaxTemp(),
                dto.getKitDescription(),kitType.getId());
        if(kit != null)
            return kit;
        else {
            kit = new Kit(dto.getKitName(),dto.getMinTemp(),dto.getMaxTemp(),
                    dto.getKitDescription(),user, kitType);
            return kitRepository.save(kit);
        }
    }
}
