package com.conexia.saludcoop.common.entity.security;

// Generated 11/05/2012 09:26:18 by Hibernate Tools 3.6.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(schema = "security", name = "[user]", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User {

	private Long id;
	private String username;
	private String password;
	private String name;
	private String email;
	private boolean enabled;
	private Date inserted;
	private boolean deleted;
	
	private Set<Role> roles = new HashSet<Role>(0);
	
	private Date credentialsExpirationDate;
	
	private Set<RecoveryToken> recoveryTokens = new HashSet<RecoveryToken>(
			0);
	private Integer failedLogins;

	public User() {
	}


	@Column(name = "credentials_expiration_date", nullable = false)
	public Date getCredentialsExpirationDate() {
		return credentialsExpirationDate;
	}

	public void setCredentialsExpirationDate(Date credentialsExpirationDate) {
		this.credentialsExpirationDate = credentialsExpirationDate;
	}

	public User(Long id, String username, String password, String name,
			String email, boolean enabled,
			Date inserted, boolean deleted,
			Set<Role> roles, Set<RecoveryToken> recoveryTokens, Integer failedLogins) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.recoveryTokens = recoveryTokens;
		this.failedLogins = failedLogins;
		this.enabled = enabled;
		this.inserted = inserted;
		this.deleted = deleted;
		this.roles = roles;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Column(name = "username", unique = true, nullable = false, length = 25)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 32)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "email", nullable = false, length = 80)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Column(name = "enabled", nullable = false)
	public boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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

	@ManyToMany(fetch= FetchType.EAGER)
	@JoinTable(name = "user_role", schema = "security", 
			joinColumns = { @JoinColumn(name = "user_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "role_id") })
	
	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(
			Set<Role> roles) {
		this.roles = roles;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	public Set<RecoveryToken> getRecoveryTokens() {
		return this.recoveryTokens;
	}

	public void setRecoveryTokens(
			Set<RecoveryToken> recoveryTokens) {
		this.recoveryTokens = recoveryTokens;
	}
	
	

    @Column(name = "failed_logins", nullable = false)
    public Integer getFailedLogins() {
		return failedLogins;
	}

	public void setFailedLogins(Integer failedLogins) {
		this.failedLogins = failedLogins;
	}
	
	@Transient
	public boolean containsRole(String role){
		for (Role r : this.getRoles()){
			if (r.getRole().equals("ROLE_LINEA_FRENTE"));
			return true;
		}
		return false;
		
	}

	@Transient
	public boolean containsAuthority(String authority){
		for (Authority r : this.getAuthorities()){
			if (r.getAuthority().equals(authority))
				return true;
		}
		return false;
	}
	
	@Transient
	public Set<Authority> getAuthorities(){
		Set<Authority> set = new HashSet<>();
		for (Role r : this.getRoles()){
			for (Authority authority : r.getAuthorities()) {
				if(!set.contains(authority)){
					set.add(authority);
				}
			}
		}
		return set;
	}
	

}
