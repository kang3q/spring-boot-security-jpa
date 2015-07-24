package com.wisebirds.sap.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;

import com.wisebirds.sap.domain.account.Account;
import com.wisebirds.sap.domain.account.SessionAccount;

public class SessionUtils {

	public static final String LOGINED_ACCOUNT = "loginedAccount";
	public static final String SESSION_ACCOUNT_ID = "sAccountId";
	public static final String SESSION_AD_ACCOUNT_ID = "sAdAccountId";
	public static final String SESSION_ROLE = "sRole";
	public static final String SESSION_ROLE_LOWER_CASE = "sRole_LC";

	private static void setSessionInfo(HttpServletRequest request, Account loginedAccount) {
		HttpSession session = request.getSession();
		session.setAttribute(SessionUtils.LOGINED_ACCOUNT, loginedAccount);

		session.setAttribute(SESSION_ROLE, loginedAccount.getRole());
		session.setAttribute(SESSION_ROLE_LOWER_CASE, loginedAccount.getRole().toLowerCase());
		session.setAttribute(SESSION_ACCOUNT_ID, loginedAccount.getId());
		if (!loginedAccount.getAdAccountList().isEmpty()) {
			session.setAttribute(SESSION_AD_ACCOUNT_ID, loginedAccount.getAdAccountList().get(0).getAdAccountId());
		}
	}

	private static void putLoginedAccountInfo(HttpServletRequest request, Authentication authentication) {
		SessionAccount userDetails = (SessionAccount) authentication.getPrincipal();
		Account loginedAccount = userDetails.getWittAccount();
		setSessionInfo(request, loginedAccount);
	}

	public static Account getLoginedAccount(HttpServletRequest request, Authentication authentication) {
		HttpSession session = request.getSession();
		if (session == null) {
			return null;
		}
		Account loginedAccount = (Account) session.getAttribute(SessionUtils.LOGINED_ACCOUNT);
		if (loginedAccount == null) {
			putLoginedAccountInfo(request, authentication);
			loginedAccount = (Account) session.getAttribute(SessionUtils.LOGINED_ACCOUNT);
		}
		return loginedAccount;
	}

}
