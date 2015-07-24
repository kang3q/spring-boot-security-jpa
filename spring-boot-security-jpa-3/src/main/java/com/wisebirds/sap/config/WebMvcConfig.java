package com.wisebirds.sap.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.wisebirds.sap.dao.AdCampaign.AdCampaignDao;
import com.wisebirds.sap.interceptor.CampaignInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
	AdCampaignDao adCampaignDao;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// registry.addInterceptor(new TestInterceptor());
		// registry.addInterceptor(new TestInterceptor()).addPathPatterns("/campaign_list/**");
		// registry.addInterceptor(new TestInterceptor2()).addPathPatterns("/admin/**");
		registry.addInterceptor(new CampaignInterceptor(adCampaignDao)).addPathPatterns("/campaign_list/**");
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		// request로 넘어오는 language parameter를 받아서 locale로 설정 한다.
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}

	@Bean(name = "localeResolver")
	public LocaleResolver sessionLocaleResolver() {
		// 세션 기준으로 로케일을 설정 한다.
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		// 쿠키 기준(세션이 끊겨도 브라우져에 설정된 쿠키 기준으로)
		// CookieLocaleResolver localeResolver = new CookieLocaleResolver();

		// 최초 기본 로케일을 강제로 설정이 가능 하다.
		localeResolver.setDefaultLocale(new Locale("ko_KR"));
		return localeResolver;
	}



}
