package com.conexia.saludcoop.common.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.security.MenuItem;
import com.conexia.saludcoop.common.exception.NotExistenItemsDeMenuException;

public interface MenuRepository extends CrudRepository<MenuItem, Long>{

	public List<MenuItem> findByAuthorityIn(Set<Integer> authoritiesId) throws NotExistenItemsDeMenuException;

}


