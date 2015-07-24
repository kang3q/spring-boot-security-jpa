package com.wisebirds.sap.controller;

import javax.servlet.http.HttpServletRequest;

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
import com.wisebirds.sap.service.account.AccountAdminService;

@Controller
@RequestMapping("/admin")
public class AccountAdminController {

	// private static final Logger LOGGER = LoggerFactory.getLogger(RestAccountController.class);

	@Autowired
	private AccountAdminService accountAdminService;

	@RequestMapping(value = "/account_create", method = RequestMethod.GET)
	public String createUserPage(HttpServletRequest request) {
		String email = request.getParameter("email");
		if (!StringUtils.isEmpty(email)) {
			request.setAttribute("account", accountAdminService.getByEmail(email).get());
		}
		return "account/create";
	}

	@RequestMapping(value = "/account_create", method = RequestMethod.POST)
	public ModelAndView createUser(@ModelAttribute("form") AccountCreateForm form) {
		if (form.getId() == null) {
			return accountAdminService.create(form);
		} else {
			return accountAdminService.update(form);
		}
	}

	@RequestMapping(value = "/account_delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String accountDelete(HttpServletRequest request, @ModelAttribute("form") AccountDeleteForm form) {
		return accountAdminService.delete(request, form);
	}

	@RequestMapping(value = "/getUserList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getUserList(HttpServletRequest request) {
		return accountAdminService.getUserList(request);
	}

	@RequestMapping(value = "/account_list", method = RequestMethod.GET)
	public ModelAndView accountList(HttpServletRequest request) {
		return accountAdminService.accountList(request);
	}



}
