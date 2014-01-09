package com.conexia.saludcoop.common.entity.security;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import ar.com.conexia.common.persistence.entity.Identifiable;

@Entity
@Table(schema = "security", name = "authority", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "id"})})
public class Authority implements Identifiable<Integer> {

	private Integer id;
	private String authority;
	private Date inserted;
	private boolean deleted;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "inserted", nullable = false, length = 29)
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

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
