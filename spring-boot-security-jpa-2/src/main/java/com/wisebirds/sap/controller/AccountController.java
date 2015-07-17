package com.wisebirds.sap.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wisebirds.sap.domain.form.AccountCreateForm;
import com.wisebirds.sap.domain.form.AccountDeleteForm;
import com.wisebirds.sap.service.account.AccountService;

@Controller
@RequestMapping("/admin")
public class AccountController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestAccountController.class);

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/account_create", method = RequestMethod.GET)
	public String createUserPage(HttpServletRequest request) {
		String email = request.getParameter("email");
		if (!StringUtils.isEmpty(email)) {
			request.setAttribute("account", accountService.getByEmail(email).get());
		}
		return "account/create";
	}

	@RequestMapping(value = "/account_create", method = RequestMethod.POST)
	public ModelAndView createUser(@ModelAttribute("form") AccountCreateForm form) {
		if (form.getId() == null) {
			return accountService.create(form);
		} else {
			return accountService.update(form);
		}
	}

	@RequestMapping(value = "/account_delete", method = RequestMethod.POST)
	public String accountDelete(HttpServletRequest request, @ModelAttribute("form") AccountDeleteForm form) {
		return accountService.delete(request, form);
	}

	@RequestMapping(value = "/getUserList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getUserList(HttpServletRequest request) {
		return accountService.getUserList(request);
	}

	@RequestMapping(value = "/account_list", method = RequestMethod.GET)
	public ModelAndView accountList(HttpServletRequest request) {
		return accountService.accountList(request);
	}



}
