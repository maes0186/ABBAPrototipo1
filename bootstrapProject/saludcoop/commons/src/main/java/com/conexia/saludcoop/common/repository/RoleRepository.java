package com.conexia.saludcoop.common.repository;

import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.security.Role;

public interface RoleRepository extends CrudRepository<Role, Integer>{

}
