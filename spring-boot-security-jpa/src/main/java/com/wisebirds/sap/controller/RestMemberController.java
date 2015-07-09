package com.wisebirds.sap.controller;

import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wisebirds.sap.domain.LongId;
import com.wisebirds.sap.domain.account.Member;
import com.wisebirds.sap.domain.form.MemberCreateForm;
import com.wisebirds.sap.domain.form.MemberDeleteForm;
import com.wisebirds.sap.service.account.MemberService;

@RestController
public class RestMemberController extends AbstractRestV1_0Controller {
	private static final Logger LOGGER = LoggerFactory.getLogger(RestMemberController.class);
	@Autowired
	private MemberService memberService;

	@RequestMapping("/members")
	public Collection<Member> getUsersPage() {
		return memberService.getAllMembers();
	}

	@RequestMapping(value = "/member/{memberId}", method = RequestMethod.GET)
	public Member getMember(@PathVariable String memberId) {
		Optional<Member> optional = memberService.getByMemberId(memberId);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	@RequestMapping(value = "/member", method = RequestMethod.DELETE)
	public boolean removeMember(@ModelAttribute MemberDeleteForm memberDeleteForm) {
		boolean result = memberService.delete(memberDeleteForm);
		return result;
	}

	@RequestMapping(value = "/member", method = RequestMethod.POST)
	public LongId createMember(@ModelAttribute("form") MemberCreateForm form) {
		Member member = memberService.create(form);
		LOGGER.debug(String.format("신규 멤버 생성 {%s}", member.getId()));
		return new LongId(member.getId());
	}

	@RequestMapping(value = "/member", method = RequestMethod.PUT)
	public boolean updateMember(@ModelAttribute("form") MemberCreateForm form) {
		Member member = memberService.update(form);
		LOGGER.debug(String.format("멤버 정보 갱신 {%s}", member.getId()));
		return member != null;
	}
}
