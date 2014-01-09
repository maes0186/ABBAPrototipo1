package com.conexia.saludcoop.listener;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.security.SaludCoopUserDetailsService;


@Component
public class AuthenticationSuccessApplicationListener implements ApplicationListener<AuthenticationSuccessEvent> {
    private static final Logger LOG = Logger.getLogger(AuthenticationSuccessApplicationListener.class);

    @Autowired
    private SaludCoopUserDetailsService saludCoopUserDetailsManager;
    
    @Override
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
