package com.conexia.saludcoop.listener;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.Globals;
import com.conexia.saludcoop.security.SaludCoopUserDetails;
import com.conexia.saludcoop.security.SaludCoopUserDetailsService;

@Component
public class BadCredentialsApplicationListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    private static final Logger LOG = Logger.getLogger(BadCredentialsApplicationListener.class);

    @Autowired
    private SaludCoopUserDetailsService saludCoopUserDetailsManager;
    
    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        String userName = (String) event.getAuthentication().getPrincipal();
        Object credentials = event.getAuthentication().getCredentials();
        
        SaludCoopUserDetails loadedUser;

        try {
            loadedUser = (SaludCoopUserDetails) this.getUserDetailsManager().loadUserByUsername(userName);
        }
        catch (DataAccessException repositoryProblem) {
            throw new AuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }
        
        saludCoopUserDetailsManager.incrementarIntentosFallidos(loadedUser);
        if(loadedUser.getLoginsFallidos()+1>=Globals.getInstance().getMaxLoginAttempts()){
        	// Si se supero la cantidad de intentos fallidos, avisarle al usuario que su usuario ha sido bloqueado.
        	throw new LockedException(userName + " is locked");
        }
        
    }

	public SaludCoopUserDetailsService getUserDetailsManager() {
		return saludCoopUserDetailsManager;
	}

	public void setUserDetailsManager(SaludCoopUserDetailsService saludCoopUserDetailsManager) {
		this.saludCoopUserDetailsManager = saludCoopUserDetailsManager;
	}
    
    
    
//    public void onApplicationEvent ( ApplicationEvent event )
//	{
//		if ( event instanceof AuthorizedEvent )
//		{
//			AuthorizedEvent authorizedEvent = ( AuthorizedEvent ) event;
//			System.out.println ( "authorized:" + authorizedEvent );
//		}
//		else if ( event instanceof AuthorizationFailureEvent )
//		{
//			AuthorizationFailureEvent authorizationFailureEvent = ( AuthorizationFailureEvent ) event;
//			System.out.println ( "not authorized:" + authorizationFailureEvent );
//		}
//		else if ( event instanceof AuthenticationFailureBadCredentialsEvent )
//		{
//			AuthenticationFailureBadCredentialsEvent badCredentialsEvent = ( AuthenticationFailureBadCredentialsEvent ) event;
//			System.out.println ( "badCredentials:" + badCredentialsEvent );
//		}
//		else if ( event instanceof AuthenticationSuccessEvent )
//		{
//			AuthenticationSuccessEvent authenticationSuccessEvent = ( AuthenticationSuccessEvent ) event;
//			System.out.println ( "authSuccess:" + authenticationSuccessEvent );
//		}
//		else
//		{
//			System.out.println ( "undefined: " + event.getClass ().getName () );
//		}
//	}
}

