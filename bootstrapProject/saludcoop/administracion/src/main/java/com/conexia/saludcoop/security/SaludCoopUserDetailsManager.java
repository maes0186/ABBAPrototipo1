package com.conexia.saludcoop.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.Globals;
import com.conexia.saludcoop.common.entity.security.Authority;
import com.conexia.saludcoop.common.entity.security.Role;
import com.conexia.saludcoop.common.entity.security.User;
import com.conexia.saludcoop.security.dao.UserRepository;

/**
 * Clase que trae un usuario de la bbdd según el modelo Salud Coop y lo hace accesible al contexto de
 * seguridad de spring implementando la interfaz UserDetailsServices. Para ayudar a esto, se declara
 * como @Service, y se le pone un nombre (arbitrario) y asi queda visible para la configuración en
 * xml (conexia-security.xml). Para mantener el esquema de servicios de Salud Coop, está como @Transactional
 * y extiende de BaseSErvice.
 */
@Service("saludCoopUserDetailsManager")
@Transactional
public class SaludCoopUserDetailsManager implements SaludCoopUserDetailsService {
 
	private static Logger LOGGER = LoggerFactory.getLogger(SaludCoopUserDetailsManager.class);
	
	@Autowired
	private UserRepository userDao;
	
	public SaludCoopUserDetailsManager() {

	}

	/**
	 * Devuelve un {@link UserDetails} que es el que usa Spring. En realidad puedo ir a buscar
	 * cualquier objeto que quiera, en este caso, el Usuario logueado en SaludCoop con toda su data
	 * asociada siempre y cuando ese objeto implemente la interfaz UserDetails. Ver
	 * {@link SaludCoopUserDetails}.
	 * 
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;
		try {
			user = userDao.findOneByUsername(username);
		}catch(NoResultException nre){
			/* si no encuentra el usuario que le pasan, es un problema de autenticacion
			 * asi que lanzo una UsernameNotFoundException, que entra en el circuito de
			 * SpringSecurity y es transparente a la pantalla, entonces se puede usar
			 * el token de error standard de SpringSecurity. 
			 */
			throw new UsernameNotFoundException("Usuario no encontrado");
		} catch (Exception e) {
			/* cualquier problema con los dao es un problema interno, asi que
			 * se lanza un  runtime excepcion 
			 */
			LOGGER.error(e.toString());
			throw new RuntimeException(e);
		}


		SaludCoopUserDetails saludCoopUser = new SaludCoopUserDetails();

		saludCoopUser.setAccountNonExpired(true);
		saludCoopUser.setAccountNonLocked(true);
		saludCoopUser.setCredentialsNonExpired(true);
		saludCoopUser.setEnabled(user.getEnabled());
		saludCoopUser.setUsername(user.getUsername());
		saludCoopUser.setPassword(user.getPassword());
		saludCoopUser.setAuthorities(getGrantedAuthorities(user.getRoles()));
		saludCoopUser.setEmail(user.getEmail());
		saludCoopUser.setNombre(user.getName());
		saludCoopUser.setCredentialsExpirationDate(user.getCredentialsExpirationDate());
		saludCoopUser.setLoginsFallidos(user.getFailedLogins());
		
		Date today = new Date();
		if(user.getCredentialsExpirationDate()!=null && today.after(user.getCredentialsExpirationDate()) || today.equals(user.getCredentialsExpirationDate())){
			saludCoopUser.setCredentialsNonExpired(false);
		}
		else {
			saludCoopUser.setCredentialsNonExpired(true);
		}
		
		if(saludCoopUser.getLoginsFallidos()>=Globals.getInstance().getMaxLoginAttempts()){
        	// Si se supero la cantidad de intentos, bloquear el acceso del usuario a la aplicacion.
        	saludCoopUser.setAccountNonLocked(false);
        }
		
		return saludCoopUser;

	}
	
	public void incrementarIntentosFallidos(SaludCoopUserDetails userDetails){
		User user = null;
		try {
			user = userDao.findOneByUsername(userDetails.getUsername());
		}catch(NoResultException nre){
			/* si no encuentra el usuario que le pasan, es un problema de autenticacion
			 * asi que lanzo una UsernameNotFoundException, que entra en el circuito de
			 * SpringSecurity y es transparente a la pantalla, entonces se puede usar
			 * el token de error standard de SpringSecurity. 
			 */
			throw new UsernameNotFoundException("Usuario no encontrado");
		} catch (Exception e) {
			/* cualquier problema con los dao es un problema interno, asi que
			 * se lanza un  runtime excepcion 
			 */
			LOGGER.error(e.toString());
			throw new RuntimeException(e);
		}

		user.setFailedLogins(user.getFailedLogins()+1);
		
		userDao.save(user);

	}
	
	public void reiniciarIntentosFallidos(String userName){
		User user = null;
		try {
			user = userDao.findOneByUsername(userName);
		}catch(NoResultException nre){
			/* si no encuentra el usuario que le pasan, es un problema de autenticacion
			 * asi que lanzo una UsernameNotFoundException, que entra en el circuito de
			 * SpringSecurity y es transparente a la pantalla, entonces se puede usar
			 * el token de error standard de SpringSecurity. 
			 */
			throw new UsernameNotFoundException("Usuario no encontrado");
		} catch (Exception e) {
			/* cualquier problema con los dao es un problema interno, asi que
			 * se lanza un  runtime excepcion 
			 */
			LOGGER.error(e.toString());
			throw new RuntimeException(e);
		}

		user.setFailedLogins(0);
		
		userDao.save(user);
	}


	/**
	 * Pasa los roles de un usuario según se el modelo SaludCoop, a los roles que entiende Spring (que
	 * se llaman GrantedAuthorities). Cada GrantedAuthority, en realidad, puede ser cualquier objeto
	 * que represente para mi un rol, siempre y cuando este objeto implemente la interfaz
	 * GrantedAuthority. Ver {@link SaludCoopGrantedAuthority}.
	 * 
	 * @param usuarioRoles
	 * @return
	 */
	private Collection<GrantedAuthority> getGrantedAuthorities(Collection<Role> roles) {

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role role : roles) {
			for (Authority authority : role.getAuthorities()) {
				SaludCoopGrantedAuthority grantedAuthority =
				        new SaludCoopGrantedAuthority(authority.getAuthority());
			}
			
			SaludCoopGrantedAuthority grantedAuthority =
			        new SaludCoopGrantedAuthority(role.getRole());
			authorities.add(grantedAuthority);
		}
		return authorities;
	}

}
