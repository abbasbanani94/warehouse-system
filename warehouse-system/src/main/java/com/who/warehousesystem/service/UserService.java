package com.who.warehousesystem.service;

import com.who.warehousesystem.dto.UserSaveDto;
import com.who.warehousesystem.model.Role;
import com.who.warehousesystem.model.User;
import com.who.warehousesystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    public User findUserById(Integer id) throws Exception {
        return userRepository.findUserById(id)
                .orElseThrow(() -> new Exception("User not found for Id : " + id));
    }

    public List<User> findAllUsers() {
        return userRepository.findAllUsers().orElse(new ArrayList<>());
    }

    public User saveUser(UserSaveDto dto) throws Exception {
        checkDuplicate(dto.getName(),dto.getUsername());
        checkPassword(dto.getPassword(),dto.getConfirm());

        Role role = roleService.findRoleById(dto.getRoleId());
        User user = new User(dto.getName(),dto.getUsername(),dto.getPassword(),role);
        return userRepository.save(user);
    }

    private void checkDuplicate(String name, String username) throws Exception {
        if(userRepository.findUserByNameOrUsername(name,username) != null)
            throwDuplicateException();
    }

    private void throwDuplicateException() throws Exception {
        throw new Exception("Name or Username are already taken");
    }

    public User editUser(Integer id,UserSaveDto dto) throws Exception {
        checkDuplicate(dto.getName(),dto.getUsername(),id);
        checkPassword(dto.getPassword(),dto.getConfirm());

        Role role = roleService.findRoleById(dto.getRoleId());
        User user = findUserById(id);
        user.setRole(role);
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());

        return userRepository.save(user);
    }

    private void checkPassword(String password, String confirm) throws Exception {
        if(!password.equals(confirm))
            throw new Exception("Passwords doesn't match");
    }

    private void checkDuplicate(String name, String username, Integer id) throws Exception {
        User user = userRepository.findUserByNameOrUsername(name,username);
        if(user != null && user.getId() != id)
            throwDuplicateException();
    }

    public User activateUser(Integer id) throws Exception {
        User user = findUserById(id);
        if(user.getActive())
            throw new Exception("This user is already activated");
        user.setActive(true);
        return userRepository.save(user);
    }

    public User deactivateUser(Integer id) throws Exception {
        User user = findUserById(id);
        if(!user.getActive())
            throw new Exception("This user is already deactivated");
        user.setActive(false);
        return userRepository.save(user);
    }
}
