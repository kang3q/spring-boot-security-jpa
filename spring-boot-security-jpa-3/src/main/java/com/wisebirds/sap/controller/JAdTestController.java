package com.wisebirds.sap.controller;

import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wisebirds.sap.domain.SimplePagingImpl;
import com.wisebirds.sap.domain.ad.form.TestFileUploadForm;
import com.wisebirds.sap.service.ad.AdCreativeService;
import com.wisebirds.sap.service.ad.form.VerificationResultForm;

@Controller
@RequestMapping("/ad/creative")
public class JAdTestController {
	@Autowired
	private AdCreativeService adCreativeService;
	@Autowired
	private FileSystemResource fileSystemResource;


	@RequestMapping(value = "/verification", method = RequestMethod.GET)
	public ModelAndView adVerification(SimplePagingImpl spi) {
		return adCreativeService.getList(spi);
	}

	@RequestMapping(value = "/verify", method = RequestMethod.POST)
	public ModelAndView reject(@Valid VerificationResultForm vrf) {
		return adCreativeService.verify(vrf);
	}

	@RequestMapping(value = "/fu", method = RequestMethod.GET)
	public ModelAndView fu() {
		return new ModelAndView("fileupload_test");
	}

	@RequestMapping(value = "/fup", method = RequestMethod.POST)
	public ModelAndView fup(@ModelAttribute TestFileUploadForm uploadForm) {
		System.out.println(uploadForm.getFile().getOriginalFilename());
		System.out.println(fileSystemResource.getPath());
		return new ModelAndView("fileupload_test");
	}
}
