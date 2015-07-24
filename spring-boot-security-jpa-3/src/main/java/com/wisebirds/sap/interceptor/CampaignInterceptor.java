package com.wisebirds.sap.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wisebirds.sap.dao.AdCampaign.AdCampaignDao;
import com.wisebirds.sap.util.AppContext;

public class CampaignInterceptor extends HandlerInterceptorAdapter {

	AdCampaignDao adCampaignDao;

	/**
	 * accountId 파라이터가 있다면 로그인한 계정에 속해있는지 확인
	 * 
	 * @param adCampaignDao
	 */
	public CampaignInterceptor(AdCampaignDao adCampaignDao) {
		this.adCampaignDao = adCampaignDao;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String actId = request.getParameter(AppContext.PARAM_USER_ACCOUNT);
		if (!StringUtils.isEmpty(actId)) {
			String role = AppContext.getAccount(request).getRole();
			if (role.equals("ADMIN")) {
				return true;
			} else if (role.equals("USER")) {
				int cnt = adCampaignDao.checkAccountIdForCampaignList(AppContext.getAccountId(request), Long.parseLong(actId));
				if (cnt == 0) {
					throw new IllegalArgumentException("권한이 없습니다.");
				}
			} else if (role.equals("CLIENT")) {
				throw new IllegalArgumentException("CLIENT는 아직 지원하지 않아요.");
			} else {
				throw new IllegalArgumentException("처리할 수 없는 유형입니다.");
			}
		}
		return true;
	}

}
