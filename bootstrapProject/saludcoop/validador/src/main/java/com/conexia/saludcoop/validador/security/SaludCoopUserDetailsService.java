package com.conexia.saludcoop.validador.security;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface SaludCoopUserDetailsService extends UserDetailsService {
	void incrementarIntentosFallidos(SaludCoopUserDetails userDetails);
	void reiniciarIntentosFallidos(String userName);

}
