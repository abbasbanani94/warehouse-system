package com.who.warehousesystem.service;

import com.who.warehousesystem.model.User;
import com.who.warehousesystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findUserById(Integer id) throws Exception {
        return userRepository.findUserById(id)
                .orElseThrow(() -> new Exception("User not found for Id : " + id));
    }
}
