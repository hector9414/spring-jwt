package com.example.sprinsecurityjwt.dao;

import com.example.sprinsecurityjwt.models.Roles;
import org.springframework.data.repository.CrudRepository;

public interface RoleDao extends CrudRepository<Roles, Long> {
}
