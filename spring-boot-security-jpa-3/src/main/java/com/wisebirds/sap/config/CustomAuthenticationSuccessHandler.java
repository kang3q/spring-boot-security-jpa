package com.wisebirds.sap.config;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import com.wisebirds.sap.util.AppContext;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private LocaleResolver localeResolver;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		Locale locale = new Locale(AppContext.getLocale(request, authentication));
		localeResolver.setLocale(request, response, locale);
		SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
		
		if(savedRequest == null){
			response.sendRedirect("/main");
		}else{
			String redirectUrl = savedRequest.getRedirectUrl();
			if (StringUtils.isEmpty(redirectUrl)) {
				response.sendRedirect("/main");
			}
			response.sendRedirect(redirectUrl);
		}

	}

}
