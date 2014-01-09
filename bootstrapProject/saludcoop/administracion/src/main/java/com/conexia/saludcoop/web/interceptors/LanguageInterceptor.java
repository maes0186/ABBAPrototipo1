package com.conexia.saludcoop.web.interceptors;

import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.conexia.saludcoop.common.util.I18NUtils;

@Component
public class LanguageInterceptor extends LocaleChangeInterceptor {
	@Autowired
	private MessageSource messages;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler)
			throws ServletException {
		// TODO Auto-generated method stub
		boolean b = super.preHandle(request, response, handler);
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		Locale.setDefault(localeResolver.resolveLocale(request));
		I18NUtils.getInstance().setMessageSource(messages);
				
		return b;
	}

	

}
