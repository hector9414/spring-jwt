package com.example.sprinsecurityjwt.dao;

import com.example.sprinsecurityjwt.models.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends CrudRepository<Users, Long> {

    Optional<Users> findByUsername(String username);



}
