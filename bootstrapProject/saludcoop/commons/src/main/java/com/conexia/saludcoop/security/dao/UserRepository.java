package com.conexia.saludcoop.security.dao;

import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.security.User;



public interface UserRepository extends CrudRepository<User, Long> {
	
	User findOneByUsername(String username);
	
}
