package com.wisebirds.sap.util;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wisebirds.sap.domain.account.Account;

public class SessionUtils {

	public static final String LOGINED_ACCOUNT = "loginedAccount";

	static class SessionAccountToLoginedAccount {
		SPrincipal principal;
	}
	static class SPrincipal {
		Account wittAccount;
	}

	private static void putLoginedAccountInfo(HttpServletRequest request) {
		Principal userPrincipal = request.getUserPrincipal();
		SessionAccountToLoginedAccount satla = AppContext.GSON.fromJson(AppContext.GSON.toJson(userPrincipal), SessionAccountToLoginedAccount.class);
		Account loginedAccount = satla.principal.wittAccount;
		request.getSession().setAttribute(SessionUtils.LOGINED_ACCOUNT, loginedAccount);
	}


	public static Account getLoginedAccount(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session == null) {
			return null;
		}
		Account loginedAccount = (Account) session.getAttribute(SessionUtils.LOGINED_ACCOUNT);
		if (loginedAccount == null) {
			putLoginedAccountInfo(request);
			loginedAccount = (Account) session.getAttribute(SessionUtils.LOGINED_ACCOUNT);
		}
		return loginedAccount;
	}

}
