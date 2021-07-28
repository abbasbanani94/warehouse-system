package com.who.warehousesystem.repository;

import com.who.warehousesystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(value = "select * from users where user_id = :id", nativeQuery = true)
    Optional<User> findUserById(@Param(value = "id") int id);

    @Query(value = "select * from users order by user_id desc",nativeQuery = true)
    Optional<List<User>> findAllUsers();

    @Query(value = "select * from users where name = :name or username = :username",nativeQuery = true)
    User findUserByNameOrUsername(@Param(value = "name") String name,
                                  @Param(value = "username") String username);

    @Query(value = "select * from users where active = 1 and username = :username",nativeQuery = true)
    Optional<User> findUserByUsername(@Param(value = "username") String username);
}
