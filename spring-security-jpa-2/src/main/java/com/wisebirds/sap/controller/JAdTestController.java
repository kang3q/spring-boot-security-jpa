package com.wisebirds.sap.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wisebirds.sap.service.ad.AdCreativeService;
import com.wisebirds.sap.service.ad.form.VerificationResultForm;

@Controller
@RequestMapping("/ad/creative")
public class JAdTestController {
	@Autowired
	private AdCreativeService adCreativeService;

	@RequestMapping(value = "/verification", method = RequestMethod.GET)
	public ModelAndView adVerification() {
		return adCreativeService.getList(1);
	}

	@RequestMapping(value = "/verify", method = RequestMethod.POST)
	public ModelAndView reject(@Valid VerificationResultForm vrf) {
		return adCreativeService.verify(vrf);
	}
}
