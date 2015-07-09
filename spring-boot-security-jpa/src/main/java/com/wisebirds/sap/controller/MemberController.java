package com.wisebirds.sap.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wisebirds.sap.service.account.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/v/member_create", method = RequestMethod.GET)
	public String getUsersPage(HttpServletRequest request) {
		String memberId = request.getParameter("memberId");
		if (!StringUtils.isEmpty(memberId)) {
			request.setAttribute("member", memberService.getByMemberId(memberId).get());
		}
		return "account/create";
	}

	@RequestMapping(value = "/v/member_delete", method = RequestMethod.GET)
	public String memberDeleteForm() {
		return "account/delete";
	}
}
