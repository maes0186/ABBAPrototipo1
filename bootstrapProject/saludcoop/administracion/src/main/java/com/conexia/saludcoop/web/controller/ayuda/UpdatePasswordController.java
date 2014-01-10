/**
 * 
 */
package com.conexia.saludcoop.web.controller.ayuda;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.conexia.saludcoop.Globals;
import com.conexia.saludcoop.common.entity.security.User;
import com.conexia.saludcoop.common.mail.exception.CannotSendEmailException;
import com.conexia.saludcoop.common.mail.service.IEmailSenderService;
import com.conexia.saludcoop.security.SaludCoopUserDetails;
import com.conexia.saludcoop.security.dto.RecoveryTokenDto;
import com.conexia.saludcoop.web.BaseValidatingController;
import com.conexia.saludcoop.web.form.UpdatePasswordForm;
import com.conexia.saludcoop.web.manager.RecoveryTokenManager;
import com.conexia.saludcoop.web.manager.UsuarioManager;
import com.conexia.saludcoop.web.validator.UpdatePasswordValidator;

/**
 * @author nobregon
 *
 */
@Controller
@SessionAttributes({"userUpdate"})
public class UpdatePasswordController extends BaseValidatingController {
	
	@Autowired
	private UsuarioManager userManager;
			
	@Autowired
	private IEmailSenderService mailService;
		
	@Autowired
	private RecoveryTokenManager tokenService;

	@Autowired
	private UpdatePasswordValidator updatePasswordValidator;
	
	@Autowired
    protected AuthenticationManager authenticationManager;
	

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(getUpdatePasswordValidator());
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/expiredPassword")
	public String expiredPassword(HttpServletRequest request, HttpServletResponse response, Model model){
		String username = 	(String) request.getSession().getAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY);
		User user = getUserManager().loadUserByUsername(username);
		model.addAttribute("userUpdate", user);
		return "ayuda/updatePassword";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/updatePassword")
	public String updatePassword(
			HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute @Valid UpdatePasswordForm form, 
			BindingResult bindingResult,
			Model model){
		
		if (bindingResult.hasErrors()){
			model.addAttribute("errors", getMessage("validation.passwords.missmatch"));
			return "ayuda/updatePassword";
		}
		//En los casos en los que Spring no logueó al usuario
		User user = (User)model.asMap().get("userUpdate");
		if (user==null){
			SaludCoopUserDetails userDetails = (SaludCoopUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			user = getUserManager().loadUserByUsername(userDetails.getUsername());
		}
		getUserManager().updatePassword(user, form.getPassword());
		getUserManager().clearFailedLoginAttempts(user);
		
		String message = getMessage("mail.updatePassword.body");
		
		try {
			this.mailService.sendEmail(user.getEmail(), getMessage("mail.updatePassword.subject"), message);
		} catch (final CannotSendEmailException e) {
			/* TODO Definir manejo de la excepción */
			e.printStackTrace();
		}
		
		Object userDetails =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userDetails==null || !userDetails.getClass().equals(SaludCoopUserDetails.class)){
			authenticateUserAndSetSession(user, form.getPassword(), request);
		}
		model.addAttribute("passwordMessage", getMessage("message.updatePassword.passwordUpdated"));
		return "redirect:/main";
		
	}

	private void authenticateUserAndSetSession(User user, String newPassword, HttpServletRequest request)
	{
	    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
	            user.getUsername(), newPassword);

	    request.getSession();

	    token.setDetails(new WebAuthenticationDetails(request));
	    Authentication authenticatedUser = authenticationManager.authenticate(token);

	    SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
	}

	@RequestMapping(method=RequestMethod.GET, value = "/updatePassword/{usuario}/{token}")
	public String retrievePassword(@PathVariable("usuario") String username, @PathVariable("token") String token, Model model) throws Exception{
		
		String redirect = "redirect:/jsp/public/index.jsp?login_error=";

		User user = getUserManager().loadUserByUsername(username);
		if(user == null ){
			return redirect+"t";
		}
		if (user.getFailedLogins()>=Globals.getInstance().getMaxLoginAttempts()){
			return redirect+"l";
		}
		RecoveryTokenDto tokenDto = tokenService.getUserLastToken(user);
		GregorianCalendar yesterday = new GregorianCalendar();
		yesterday.add( Calendar.DATE, -1 );
		long yesterdayMilis = yesterday.getTime().getTime();  
		if (!StringUtils.equals(token, tokenDto.getToken())){
			return redirect+"noToken";
		}else if (tokenDto.getInserted().before(new Date(yesterdayMilis))){
			return redirect+"tokenExpired";
		}
		model.addAttribute("userUpdate", user);
		return "ayuda/updatePassword";
	}

	

	/**
	 * Devuelve el valor de updatePasswordValidator.
	 *
	 * @return El valor de updatePasswordValidator.
	 */
	public UpdatePasswordValidator getUpdatePasswordValidator() {
		return updatePasswordValidator;
	}

	/**
	 * Asigna un nuevo valor a updatePasswordValidator.
	 *
	 * @param updatePasswordValidator El valor a asignar a updatePasswordValidator.
	 */
	public void setUpdatePasswordValidator(
			UpdatePasswordValidator updatePasswordValidator) {
		this.updatePasswordValidator = updatePasswordValidator;
	}



	/**
	 * Devuelve el valor de tokenService.
	 *
	 * @return El valor de tokenService.
	 */
	public RecoveryTokenManager getTokenService() {
		return tokenService;
	}

	/**
	 * Asigna un nuevo valor a tokenService.
	 *
	 * @param tokenService El valor a asignar a tokenService.
	 */
	public void setTokenService(RecoveryTokenManager tokenService) {
		this.tokenService = tokenService;
	}


	/**
	 * Devuelve el valor de userManager.
	 *
	 * @return El valor de userManager.
	 */
	public UsuarioManager getUserManager() {
		return userManager;
	}

	/**
	 * Asigna un nuevo valor a userManager.
	 *
	 * @param userManager El valor a asignar a userManager.
	 */
	public void setUserManager(UsuarioManager userManager) {
		this.userManager = userManager;
	}
}
