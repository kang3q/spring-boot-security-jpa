package com.wisebirds.sap.controller;

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
	@RequestMapping("/home")
	public ModelAndView getHomePage2() {
		LOGGER.debug("Getting home page");
		return new ModelAndView("test", "data", "home");
	}
}
