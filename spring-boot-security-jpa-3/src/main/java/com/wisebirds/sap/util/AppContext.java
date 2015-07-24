package com.wisebirds.sap.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import com.google.gson.Gson;
import com.wisebirds.sap.domain.account.Account;

public class AppContext {
	public static final String SECRET_KEY = "w!i@s#e$b%i^r&d*s";
	public static final String PARAM_USER_ACCOUNT = "userAccountId";
	public static Gson GSON = new Gson();

	/**
	 * session에 로그인한 정보를 저장(업데이트는 안함) 후 Account반환 한다
	 * 
	 * @param request
	 * @param authentication
	 * @return Account
	 */
	public static Account getAccount(HttpServletRequest request, Authentication authentication) {
		return SessionUtils.getLoginedAccount(request, authentication);
	}

	/**
	 * session에 로그인한 정보를 저장(업데이트는 안함) 후 Account반환 한다
	 * 
	 * @param request
	 * @return Account
	 */
	public static Account getAccount(HttpServletRequest request) {
		Authentication auth = (Authentication) request.getUserPrincipal();
		return getAccount(request, auth);
	}

	/**
	 * session에 로그인한 정보를 저장(업데이트는 안함) 후 accountId반환 한다
	 * 
	 * @param request
	 * @return accountId
	 */
	public static long getAccountId(HttpServletRequest request) {
		return getAccount(request).getId();
	}

	/**
	 * session에 로그인한 정보를 저장(업데이트는 안함) 후 adAccountId반환 한다
	 * 
	 * @param request
	 * @return adAccountId
	 */
	public static long getAdAccountId(HttpServletRequest request) {
		return getAccount(request).getAdAccountList().get(0).getAdAccountId();
	}

	/**
	 * session에 로그인한 정보를 저장(업데이트는 안함) 후 role을 반환 한다
	 * 
	 * @param request
	 * @return role
	 */
	public static String getRole(HttpServletRequest request) {
		return getAccount(request).getRole();
	}

	/**
	 * session에 로그인한 정보를 저장(업데이트는 안함) 후 locale을 반환 한다
	 * 
	 * @param request
	 * @return locale
	 */
	public static String getLocale(HttpServletRequest request) {
		return getAccount(request).getLocale();
	}

	/**
	 * session에 로그인한 정보를 저장(업데이트는 안함) 후 locale을 반환 한다
	 * 
	 * @param request
	 * @param authentication
	 * @return locale
	 */
	public static String getLocale(HttpServletRequest request, Authentication authentication) {
		return getAccount(request, authentication).getLocale();
	}

}
