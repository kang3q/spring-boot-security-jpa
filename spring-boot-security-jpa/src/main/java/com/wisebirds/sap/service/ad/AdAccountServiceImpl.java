package com.wisebirds.sap.service.ad;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.wisebirds.sap.domain.account.Member;
import com.wisebirds.sap.domain.ad.form.InsightForm;

@Service
public class AdAccountServiceImpl implements AdAccountService {

	@Override
	public ModelAndView insight(Member me, InsightForm insightFormData) {
		return null;
	}
	// private static final Logger LOGGER = LoggerFactory.getLogger(MemberServiceImpl.class);
}
