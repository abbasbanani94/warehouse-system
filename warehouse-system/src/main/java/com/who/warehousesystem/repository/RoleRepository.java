package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    @Query(value = "select * from roles", nativeQuery = true)
    Optional<List<Role>> findAllRoles();

    @Query(value = "select * from roles where role_id = :id", nativeQuery = true)
    Optional<Role> findRoleById(@Param(value = "id") int id);
}
