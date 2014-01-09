/**
 * 
 */
package com.conexia.saludcoop.validador.security;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



/**
 * @author gchiesa
 *
 */
public class SaludCoopUserDetails implements UserDetails {

	/**
	 * Roles del usuario, requerido por spring. 
	 */
	private Collection<GrantedAuthority> grantedAuthorities;
	
	/**
	 * Password, requerido por spring
	 */
	private String password;
	
	/**
	 * Nombre de usuario, requerido por spring 
	 */
	private String username;
	
	/**
	 * Inidica si la cuenta está expirada o no, requerida por spring
	 */
	private boolean accountNonExpired;
	
	/**
	 * Indica si la cuenta no esta bloqueeada, requerido por spring
	 */
	private boolean accountNonLocked;
	
	/**
	 * Indica si la password no esta expirada, requerida por spring
	 */
	private boolean credentialsNonExpired;
	
	/**
	 * Indica si el usuario se encuentra habilitado. Esta requeridi por spring, pero coincide con el 
	 * modelo conexia.
	 */
	private boolean enabled;

	/**
	 * Indica la fecha de expiracion del password del usuario. 
	 */
	private Date credentialsExpirationDate;
	
	/**
	 * LoginsFallidos.
	 */
	private Integer loginsFallidos;

	/**
	 * Contiene el email del prestador.
	 */
	private String email;
	/**
	 * Contiene el nombre del prestador.
	 */
	private String nombre;
	
	/**
	 * Contiene el Tipo de Usuario en cuestion. Puede Ser un Prestador, un Asegurador, etc.
	 */
	private TipoUsuarioDto tipoUsuario;
	
	
	public Date getCredentialsExpirationDate() {
		return credentialsExpirationDate;
	}

	public void setCredentialsExpirationDate(Date credentialsExpirationDate) {
		this.credentialsExpirationDate = credentialsExpirationDate;
	}
	
	/** 
	 * Devuelve los roles aosciados a este usuario.
	 * 
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
    public Collection<GrantedAuthority> getAuthorities() {
    	return this.grantedAuthorities;
    }
    
    /**
     * Setea los roles asociados a este usuario.
     * @param grantedAuthorities
     */
    public void setAuthorities(Collection<GrantedAuthority> grantedAuthorities) {
    	this.grantedAuthorities = grantedAuthorities;
    }

	/**
	 * Obtiene el password del usuario. 
     * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
     */
    public String getPassword() {
	    return this.password;
    }
    
    /**
     * Setea el password del usuario.
     * @param password
     */
    public void setPassword(String password) {
	    this.password = password;
    }

	/**
	 * Obtiene el nombre de usuario
     * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
     */
    public String getUsername() {
	    return this.username;
    }
    
    /**
     * Setea el nombre de usuario.
     * @param username
     */
    public void setUsername(String username) {
	    this.username = username;
    }

    /**
     * Indica si la cuenta está expirada o no.
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
     */
    public boolean isAccountNonExpired() {
	    return this.accountNonExpired;
    }
    
    /**
     * Setea si la cuenta esta expirada o no.
     * @param accountNonExpired
     */
    public void setAccountNonExpired(Boolean accountNonExpired) {
	    this.accountNonExpired = accountNonExpired;
    }

	/**
	 * Indica si la cuenta esta bloqueada o no.
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
     */
    public boolean isAccountNonLocked() {
    	return this.accountNonLocked;
    }
    
    /**
     * Setea si la cuenta esta bloqueada o no.
     * @param accountNonLocked
     */
    public void setAccountNonLocked(Boolean accountNonLocked) {
	    this.accountNonLocked = accountNonLocked;
    }

	/**
	 * Indica si la contraseña esta bloqueada o no.
     * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
     */
    public boolean isCredentialsNonExpired() {
    	return this.credentialsNonExpired;
    }
    
    /**
     * Setea si la contraseña esta bloqueada o no.
     * @param credentialsNonExpired
     */
    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
    	this.credentialsNonExpired = credentialsNonExpired;
    }

	/**
	 * Indica si el usuario esta bloqueado o no.
     * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
     */
    public boolean isEnabled() {
	    return this.enabled;
    }
    
    /**
     * Setea si el usuario esta bloqueado o no.
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
	    this.enabled = enabled;
    }
    

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
    /**
     * Devuelve el valor de tipoUsuario.
     *
     * @return El valor de tipoUsuario.
     */
    public TipoUsuarioDto getTipoUsuario() {
    
    	return this.tipoUsuario;
    }

	
    /**
     * Asigna un nuevo valor a tipoUsuario.
     *
     * @param tipoUsuario El valor a asignar a tipoUsuario.
     */
    public void setTipoUsuario(TipoUsuarioDto tipoUsuario) {
    
    	this.tipoUsuario = tipoUsuario;
    }

	/* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	    return "SaludCoopUserDetails [grantedAuthorities=" + grantedAuthorities + ", username=" + username + ", accountNonExpired=" + accountNonExpired + ", accountNonLocked=" + accountNonLocked
	            + ", credentialsNonExpired=" + credentialsNonExpired + ", enabled=" + enabled + ", tipoPrestador=" + "]";
    }

	public Integer getLoginsFallidos() {
		return loginsFallidos;
	}

	public void setLoginsFallidos(Integer loginsFallidos) {
		this.loginsFallidos = loginsFallidos;
	}

    

}
