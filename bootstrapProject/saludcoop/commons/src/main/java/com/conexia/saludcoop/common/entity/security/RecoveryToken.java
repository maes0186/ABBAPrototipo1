package com.conexia.saludcoop.common.entity.security;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.com.conexia.common.persistence.entity.Identifiable;

import com.conexia.saludcoop.common.util.beanutils.BaseEntity;
import com.conexia.saludcoop.security.dto.RecoveryTokenDto;

@Entity
@Table(schema = "security", name = "recovery_token")
public class RecoveryToken extends BaseEntity implements Identifiable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String token;
	private Date inserted;
	private boolean deleted;
	private User user;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return this.id;
	}

	/**
	 * Asigna un nuevo valor a id.
	 * 
	 * @param id El valor a asignar a id.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "token", nullable = false)
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "inserted", nullable = false, length = 29, columnDefinition="timestamp default now()")
	public Date getInserted() {
		return this.inserted;
	}

	public void setInserted(Date inserted) {
		this.inserted = inserted;
	}

	@Column(name = "deleted", nullable = false)
	public boolean getDeleted() {
		return this.deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	protected Class<RecoveryTokenDto> obtenerTipoDTO() {
		// TODO Auto-generated method stub
		return RecoveryTokenDto.class;
	}
	
	
}
