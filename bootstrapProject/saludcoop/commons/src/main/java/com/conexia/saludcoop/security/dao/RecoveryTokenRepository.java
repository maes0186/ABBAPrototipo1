/**
 * 
 */
package com.conexia.saludcoop.security.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ar.com.conexia.common.persistence.exception.DaoException;

import com.conexia.saludcoop.common.entity.security.RecoveryToken;
import com.conexia.saludcoop.common.entity.security.User;


/**
 * @author nobregon
 *
 */
public interface RecoveryTokenRepository extends CrudRepository<RecoveryToken, Integer>{
	
//	public Long saveNewToken(RecoveryToken token) throws DaoException;
	@Query
	public List<RecoveryToken> findByUserOrderByInsertedDesc(User user) throws DaoException;
	

}
