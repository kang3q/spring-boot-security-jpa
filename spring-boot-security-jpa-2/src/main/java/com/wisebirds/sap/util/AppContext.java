package com.wisebirds.sap.util;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

public class AppContext {
	public static final String SECRET_KEY = "w!i@s#e$b%i^r&d*s";
	public static Gson GSON = new Gson();

	public static long getAccountId(HttpServletRequest request) {
		return SessionUtils.getLoginedAccount(request).getId();
	}
	
	public static long getAdAccountId(HttpServletRequest request) {
		return SessionUtils.getLoginedAccount(request).getAdAccountList().get(0).getAdAccountId();
	}

}
