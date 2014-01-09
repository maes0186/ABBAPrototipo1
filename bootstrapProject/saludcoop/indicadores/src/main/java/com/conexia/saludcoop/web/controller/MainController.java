package com.conexia.saludcoop.web.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.conexia.saludcoop.Globals;
import com.conexia.saludcoop.common.entity.security.Authority;
import com.conexia.saludcoop.common.entity.security.Role;
import com.conexia.saludcoop.common.entity.security.User;
import com.conexia.saludcoop.security.SaludCoopUserDetails;
import com.conexia.saludcoop.web.BaseValidatingController;
import com.conexia.saludcoop.web.manager.MenuManager;
import com.conexia.saludcoop.web.manager.UsuarioManager;

@Controller
@RequestMapping("/main")
@SessionAttributes({"menu"})
public class MainController extends BaseValidatingController {
	
	@Autowired
	private UsuarioManager userManager;	
	
	@Autowired
	private MenuManager menuManager;
	
	
	public MainController() {
	}
	
	@RequestMapping(method=RequestMethod.GET)
	protected String showLandingPage(Model model) throws Exception {
		
		SaludCoopUserDetails userDetails = (SaludCoopUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		User user = userManager.loadUserByUsername(userDetails.getUsername());
		userManager.clearFailedLoginAttempts(user);
		
		// calcular la diferencia en dias entra hoy y la fecha de expiracion
        long todayMillis = new Date().getTime();
        long expirationMillis = userDetails.getCredentialsExpirationDate().getTime();

        long diff = expirationMillis - todayMillis;

		long diffDays = diff / (24 * 60 * 60 * 1000);

		String mensaje = "";
		if (diffDays <= Globals.getInstance().getWarningDaysForExpirationDate()){
			mensaje = this.getMessage("message.login.updatePasswordAlert",diffDays);
		}
		model.addAttribute("passwordMessage", mensaje);
		
		
		
		Set<Integer> authIds = new HashSet<Integer>();
        for (Role role : user.getRoles()){
                for (Authority auth : role.getAuthorities()){
                        authIds.add(auth.getId());
                }
        }
        
        ;
		
		model.addAttribute("menu", menuManager.getMenuTree(authIds));
		
		return "indicadores/main";
	}

	
}
