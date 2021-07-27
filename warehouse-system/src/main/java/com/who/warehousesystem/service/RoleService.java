package com.who.warehousesystem.service;

import com.who.warehousesystem.model.Role;
import com.who.warehousesystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public List<Role> findAllRoles() {
        return roleRepository.findAllRoles().orElse(new ArrayList<>());
    }

    public Role findRoleById(Integer roleId) throws Exception {
        return roleRepository.findRoleById(roleId).orElseThrow(() ->
                new Exception("No Role for ID : " + roleId));
    }
}
