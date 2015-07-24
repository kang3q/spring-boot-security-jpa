package com.wisebirds.sap.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wisebirds.sap.domain.account.Account;
import com.wisebirds.sap.service.account.AccountAdminService;

@RestController
public class RestAccountController extends AbstractRestV1_0Controller {
	
	//private static final Logger LOGGER = LoggerFactory.getLogger(RestAccountController.class);
	
	@Autowired
	private AccountAdminService accountService;

	@RequestMapping("/accounts")
	public Collection<Account> getUsersPage() {
		return accountService.getAllAccounts();
	}

	@RequestMapping(value = "/account/{email}", method = RequestMethod.GET)
	public Account getAccount(@PathVariable String email) {
		Optional<Account> optional = accountService.getByEmail(email);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

//	@RequestMapping(value = "/account", method = RequestMethod.DELETE)
//	public boolean removeAccount(@ModelAttribute AccountDeleteForm acountDeleteForm) {
//		boolean result = accountService.delete(acountDeleteForm);
//		return result;
//	}

//	@RequestMapping(value = "/member", method = RequestMethod.POST)
//	public LongId createMember(@ModelAttribute("form") MemberCreateForm form) {
//		Member member = memberService.create(form);
//		LOGGER.debug(String.format("신규 멤버 생성 {%s}", member.getId()));
//		return new LongId(member.getId());
//	}

//	@RequestMapping(value = "/account", method = RequestMethod.PUT)
//	public boolean updateAccount(@ModelAttribute("form") AccountCreateForm form) {
//		Account account = accountService.update(form);
//		LOGGER.debug(String.format("멤버 정보 갱신 {%s}", account.getId()));
//		return account != null;
//	}
}
