package com.conexia.saludcoop.web.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.conexia.saludcoop.Globals;
import com.conexia.saludcoop.common.entity.security.Authority;
import com.conexia.saludcoop.common.entity.security.Role;
import com.conexia.saludcoop.common.entity.security.User;
import com.conexia.saludcoop.security.SaludCoopUserDetails;
import com.conexia.saludcoop.security.dto.MenuTree;
import com.conexia.saludcoop.web.BaseValidatingController;
import com.conexia.saludcoop.web.manager.MenuManager;
import com.conexia.saludcoop.web.manager.PropertiesManager;
import com.conexia.saludcoop.web.manager.UsuarioManager;

@Controller
@RequestMapping("/main")
@SessionAttributes({"menu","userName","esLDF","cacheControl","menuActivo"})
public class MainController extends BaseValidatingController {
	
	@Autowired
	private UsuarioManager userManager;	
	
	@Autowired
	private MenuManager menuManager;
	
	@Autowired
	private PropertiesManager propertiesManager;
	
	
	public MainController() {
	}
	
	@RequestMapping(method=RequestMethod.GET)
	protected String showLandingPage(ModelMap model, HttpSession session) throws Exception {
		
		removeFromSession(model, session, "menuActivo");
		
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
		model.addAttribute("userName", user.getName());
		
		boolean esLDF = false;
		for (Role r : user.getRoles())
			if (r.getRole().equals("ROLE_LINEA_FRENTE"))
				esLDF = true;
		model.addAttribute("esLDF",esLDF);
		
		model.addAttribute("cacheControl", propertiesManager.getCacheControl());
		
        if (!model.containsAttribute("menu") || model.get("menu")==null || (CollectionUtils.isEmpty(((MenuTree)model.get("menu")).getRootNodes()))){
        	Set<Integer> authIds = new HashSet<Integer>();
            for (Role role : user.getRoles()){
                    for (Authority auth : role.getAuthorities()){
                            authIds.add(auth.getId());
                    }
            }
            MenuTree menu = menuManager.getMenuTree(authIds);
            if (menu== null || CollectionUtils.isEmpty(menu.getRootNodes())){
            	return "/logout";
            }
            model.addAttribute("menu", menu);	
        }
        		
		return "common/loggedIn";
	}

    @RequestMapping(value = "setMenuId")
	public void setMenuActivo(String menuId, ModelMap map) {
        map.put("menuActivo", menuId);
    }
	
}
