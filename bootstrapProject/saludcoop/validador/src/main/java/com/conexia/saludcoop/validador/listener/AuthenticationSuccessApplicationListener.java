package com.conexia.saludcoop.validador.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.validador.security.SaludCoopUserDetailsService;


@Component
public class AuthenticationSuccessApplicationListener implements ApplicationListener<AuthenticationSuccessEvent> {
    
	private static Logger LOG = LoggerFactory.getLogger(AuthenticationSuccessApplicationListener.class);
	
	
    @Autowired
    private SaludCoopUserDetailsService saludCoopUserDetailsManager;
    
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        
    	String userName = event.getAuthentication().getName();
        
        saludCoopUserDetailsManager.reiniciarIntentosFallidos(userName);
        
    }

	public SaludCoopUserDetailsService getUserDetailsManager() {
		return saludCoopUserDetailsManager;
	}

	public void setUserDetailsManager(SaludCoopUserDetailsService saludCoopUserDetailsManager) {
		this.saludCoopUserDetailsManager = saludCoopUserDetailsManager;
	}
}
