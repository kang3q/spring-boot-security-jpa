package com.wisebirds.sap.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wisebirds.sap.domain.account.Member;
import com.wisebirds.sap.domain.account.Member1_1;
import com.wisebirds.sap.service.account.MemberService;

@RestController
public class RestMemberControllerV1_1 extends AbstractRestV1_1Controller {
	// private static final Logger LOGGER = LoggerFactory.getLogger(RestMemberController.class);
	@Autowired
	private MemberService memberService;

	@RequestMapping("/members")
	public Collection<Member1_1> getUsersPage() {
		Collection<Member> memberList = memberService.getAllMembers();
		List<Member1_1> dataList = new ArrayList<>(memberList.size());
		memberList.forEach(m -> dataList.add(new Member1_1(m)));
		return dataList;
	}
}
