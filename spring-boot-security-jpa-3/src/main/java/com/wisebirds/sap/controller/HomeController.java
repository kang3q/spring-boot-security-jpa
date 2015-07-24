package com.wisebirds.sap.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping("/")
	public String getHomePage() {
		LOGGER.debug("Getting home page");
		return "test";
	}

	@RequestMapping("/main")
	public ModelAndView getMainPage(HttpServletRequest request) {
		LOGGER.debug("Getting main page");
		return new ModelAndView("main", "data", "main");
	}
}
