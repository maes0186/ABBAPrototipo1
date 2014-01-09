package com.conexia.saludcoop.web.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conexia.saludcoop.util.ValidatedResponse;

@Controller
public class LoginController {

	@RequestMapping("/loginCheck")
	public @ResponseBody ValidatedResponse<Boolean> loginCheck(Model model){
		
		ValidatedResponse<Boolean> vr = new ValidatedResponse<Boolean>();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			vr.setContent(true);	
		}
		
		return vr;
	}
	
}
