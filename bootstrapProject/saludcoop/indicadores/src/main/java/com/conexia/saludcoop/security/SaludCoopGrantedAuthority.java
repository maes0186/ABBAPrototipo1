/**
 * 
 */
package com.conexia.saludcoop.security;

import org.springframework.security.core.GrantedAuthority;


/**
 * Clase que representa un ROL, segun lo entiende Spring (que los llama GrantedAuthorities).
 * Para efectos practicos, en el modelo SaludCoop un ROL es simplemente un String con la nombre del
 * rol, por ejemplo: ROL_ADMINISTRADOR.
 * Para facilitar las cosas, y mantener el principio de COC, el prefijo de cada ROL es 'ROLE_'
 *   																																																																																																																																																																																																																																																																																																																																																																																																																																																																							
 * @author gchiesa
 *
 */
public class SaludCoopGrantedAuthority implements GrantedAuthority {

	private String roleName;
	
	
    public SaludCoopGrantedAuthority() {
    }
    
    public SaludCoopGrantedAuthority(String role) {
	    this.roleName = role;
    }

	@Override
	public String getAuthority() {
	    return this.roleName;
	}
	
	public void setAuthority(String roleName) {
		this.roleName = roleName;
	}

	/* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

	    return "SaludCoopGrantedAuthority [roleName=" + roleName + "]";
    }
	
	
}
