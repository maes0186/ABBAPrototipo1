/**
 * 
 */
package com.conexia.saludcoop.security.dto;

import java.util.Date;

import com.conexia.saludcoop.common.entity.security.RecoveryToken;
import com.conexia.saludcoop.common.entity.security.User;
import com.conexia.saludcoop.common.util.beanutils.BaseDTO;


/**
 * @author nobregon
 *
 */
public class RecoveryTokenDto extends BaseDTO{
	private Long id;
	private String token;
	private Date inserted;
	private boolean deleted;
	private User user;
	
	/**
	 * 
	 */
	public RecoveryTokenDto() {
	}
	
	/**
	 * 
	 */
	public RecoveryTokenDto(Long id, String token, Date inserted, boolean deleted, User user){
		this.id = id;
		this.token = token;
		this.inserted = inserted;
		this.deleted=deleted;
		this.user = user;
	} 
	
	
	
	/**
	 * Devuelve el valor de id.
	 *
	 * @return El valor de id.
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Asigna un nuevo valor a id.
	 *
	 * @param id El valor a asignar a id.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * Devuelve el valor de token.
	 *
	 * @return El valor de token.
	 */
	public String getToken() {
		return token;
	}
	/**
	 * Asigna un nuevo valor a token.
	 *
	 * @param token El valor a asignar a token.
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * Devuelve el valor de inserted.
	 *
	 * @return El valor de inserted.
	 */
	public Date getInserted() {
		return inserted;
	}
	/**
	 * Asigna un nuevo valor a inserted.
	 *
	 * @param inserted El valor a asignar a inserted.
	 */
	public void setInserted(Date inserted) {
		this.inserted = inserted;
	}
	/**
	 * Devuelve el valor de deleted.
	 *
	 * @return El valor de deleted.
	 */
	public boolean getDeleted() {
		return deleted;
	}
	/**
	 * Asigna un nuevo valor a deleted.
	 *
	 * @param deleted El valor a asignar a deleted.
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	/**
	 * Devuelve el valor de user.
	 *
	 * @return El valor de user.
	 */
	public User getUser() {
		return user;
	}
	/**
	 * Asigna un nuevo valor a user.
	 *
	 * @param user El valor a asignar a user.
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	protected Class obtenerTipoEntity() {
		// TODO Auto-generated method stub
		return RecoveryToken.class;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		RecoveryTokenDto tokenDto = (RecoveryTokenDto) obj;
		return this.token.equals(tokenDto.token);
	}
}
