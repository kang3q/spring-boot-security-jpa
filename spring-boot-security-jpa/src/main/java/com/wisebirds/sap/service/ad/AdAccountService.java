package com.wisebirds.sap.service.ad;

import org.springframework.web.servlet.ModelAndView;

import com.wisebirds.sap.domain.account.Member;
import com.wisebirds.sap.domain.ad.form.InsightForm;

public interface AdAccountService {
	ModelAndView insight(Member me, InsightForm insightFormData);
}
