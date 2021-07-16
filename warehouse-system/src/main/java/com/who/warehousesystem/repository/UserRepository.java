package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(value = "select * from users where user_id = :id", nativeQuery = true)
    Optional<User> findUserById(@Param(value = "id") int id);
}
