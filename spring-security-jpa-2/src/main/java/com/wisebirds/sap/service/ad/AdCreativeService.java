package com.wisebirds.sap.service.ad;

import org.springframework.web.servlet.ModelAndView;

import com.wisebirds.sap.service.ad.form.VerificationResultForm;

public interface AdCreativeService {
	ModelAndView verify(VerificationResultForm vrf);

	ModelAndView getList(int i);
}
