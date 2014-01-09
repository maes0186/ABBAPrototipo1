package com.conexia.saludcoop.web.controller.ayuda;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conexia.saludcoop.Globals;
import com.conexia.saludcoop.common.entity.security.User;
import com.conexia.saludcoop.mail.MailService;
import com.conexia.saludcoop.util.ValidatedResponse;
import com.conexia.saludcoop.web.BaseValidatingController;
import com.conexia.saludcoop.web.form.PasswordRecoveryForm;
import com.conexia.saludcoop.web.manager.RecoveryTokenManager;
import com.conexia.saludcoop.web.manager.UsuarioManager;
import com.conexia.saludcoop.web.validator.PasswordRecoveryValidator;
import com.conexia.saludcoop.web.vo.PasswordRecoveryVO;

@Controller
@RequestMapping("/ayuda/passwordRecovery")
public class PasswordRecoveryController extends BaseValidatingController {

	@Autowired
	private UsuarioManager usuarioManager;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private PasswordRecoveryValidator passwordRecoveryValidator;
	
	@Autowired
	private RecoveryTokenManager tokenManager;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(getPasswordRecoveryValidator());
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	protected String gotoMesaAyuda(Model model) throws Exception {
		return "ayuda/passwordRecovery";
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	protected ValidatedResponse<PasswordRecoveryVO> passwordRecovery(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute @Valid PasswordRecoveryForm form, 
			@ModelAttribute BindingResult bindingResult,
			Model model) throws Exception{

			//Valido que el captcha ingresado coincida con la imagen
			getPasswordRecoveryValidator().validateCaptcha(request, form, bindingResult);
		
		   	ValidatedResponse<PasswordRecoveryVO> validatedResponse = new ValidatedResponse<PasswordRecoveryVO>();
		   	PasswordRecoveryVO passwordRecoveryVo = new PasswordRecoveryVO();
			if(bindingResult.hasErrors()) {
				validatedResponse.setValidationResult(this.getFieldErrorsStringMap(bindingResult));
				passwordRecoveryVo.setStatus("NOTOK");
				validatedResponse.setContent(passwordRecoveryVo);
				return validatedResponse;
			}
			
			User user = getUsuarioManager().loadUserByUsername(form.getNombre());
			if(user == null ){
				validatedResponse.addGeneralErrors(this.getMessage("message.passwordRecovery.userMailMissmatch"));
				passwordRecoveryVo.setStatus("NOTOK");
				validatedResponse.setContent(passwordRecoveryVo);
				return validatedResponse;
			}else if(!StringUtils.equals(user.getEmail(), form.getMail())){
				validatedResponse.addGeneralErrors(this.getMessage("message.passwordRecovery.userMailMissmatch"));
				passwordRecoveryVo.setStatus("NOTOK");
				validatedResponse.setContent(passwordRecoveryVo);
				return validatedResponse;
			}else if(user.getFailedLogins() >= Globals.getInstance().getMaxLoginAttempts()){
				validatedResponse.addGeneralErrors(this.getMessage("message.login.noMoreAttemps"));
				passwordRecoveryVo.setStatus("NOTOK");
				validatedResponse.setContent(passwordRecoveryVo);
				return validatedResponse;
			}

			String token = tokenManager.generateTokenForUser(user);
			
			StringBuffer urlWeb 	= request.getRequestURL();
			String url	= urlWeb.substring(0, urlWeb.indexOf("web/")+4)+"updatePassword/"+user.getUsername()+"/"+token;
			
			String message = getMessage("mail.passwordRecovery.body", url);
			
			try {
				getMailService().sendMail(getMessage("mail.passwordRecovery.subject"),message, user.getEmail());
			} catch (EnviarMailException e) {
				validatedResponse.addGeneralErrors(this.getMessage("message.mailService.notSendMail"));
				passwordRecoveryVo.setStatus("NOTOK");
				validatedResponse.setContent(passwordRecoveryVo);
				return validatedResponse;
			}
			passwordRecoveryVo.setStatus("OK");
			validatedResponse.setContent(passwordRecoveryVo);

		return validatedResponse;
	}

	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}


	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}


	public MailService getMailService() {
		return mailService;
	}


	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}


	/**
	 * Devuelve el valor de passwordRecoveryValidator.
	 *
	 * @return El valor de passwordRecoveryValidator.
	 */
	public PasswordRecoveryValidator getPasswordRecoveryValidator() {
		return passwordRecoveryValidator;
	}


	/**
	 * Asigna un nuevo valor a passwordRecoveryValidator.
	 *
	 * @param passwordRecoveryValidator El valor a asignar a passwordRecoveryValidator.
	 */
	public void setPasswordRecoveryValidator(
			PasswordRecoveryValidator passwordRecoveryValidator) {
		this.passwordRecoveryValidator = passwordRecoveryValidator;
	}

}
