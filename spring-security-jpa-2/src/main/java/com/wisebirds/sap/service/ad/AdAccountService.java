package com.wisebirds.sap.service.ad;

import org.springframework.web.servlet.ModelAndView;

import com.wisebirds.sap.domain.account.Account;
import com.wisebirds.sap.domain.ad.form.InsightForm;

public interface AdAccountService {
	ModelAndView insight(Account me, InsightForm insightFormData);
}
