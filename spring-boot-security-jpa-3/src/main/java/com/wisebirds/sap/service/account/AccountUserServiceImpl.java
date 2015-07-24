package com.wisebirds.sap.service.account;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.wisebirds.sap.dao.account.AccountDao;
import com.wisebirds.sap.domain.account.Account;
import com.wisebirds.sap.domain.account.AccountConnections;
import com.wisebirds.sap.domain.form.AccountCreateForm;
import com.wisebirds.sap.domain.form.AccountDeleteForm;
import com.wisebirds.sap.repository.account.AccountConnectionsRepository;
import com.wisebirds.sap.repository.account.AccountRepository;
import com.wisebirds.sap.util.AppContext;
import com.wisebirds.sap.util.SecurityUtil;

@Service
public class AccountUserServiceImpl implements AccountUserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountAdminServiceImpl.class);

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private AccountConnectionsRepository actConnectionsRepository;
	@Autowired
	private AccountDao accountDao;

	@Override
	public Optional<Account> getByEmail(String email) {
		return accountRepository.findOneByEmail(email);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ModelAndView create(AccountCreateForm form) {
		Account account = accountRepository.save(new Account(form.getAgency_id(), form.getEmail(), form.getName(), SecurityUtil.encodinPassword(form.getPassword()), form.getRole()));
		accountDao.updateAccessTokenOfAccount(account.getId(), SecurityUtil.createAccessToken(account.getId(), account.getRole()));
		// accountConnections 연결하기
		long adAccountId = actConnectionsRepository.findAllByAccountId(form.getAgency_id()).get(0).getAdAccountId(); // user의 ad_account_id 가져오기 // 나중엔 ad_account가 여러개 있을 수 있음.
		actConnectionsRepository.save(new AccountConnections(account.getId(), adAccountId));

		LOGGER.debug(String.format("신규 account 생성 {%s}", account.getId()));
		
		return new ModelAndView("redirect:/user/account_list");
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ModelAndView update(AccountCreateForm form) {
		String password = SecurityUtil.encodinPassword(form.getPassword());
		String token = SecurityUtil.createAccessToken(form.getId(), form.getRole());
		accountDao.updateAccount(form.getId(), form.getAgency_id(), password, form.getName(), form.getRole(), token, form.getPhone(), form.getZipcode(), form.getAddress(), form.getLocale());
		// Account account = accountRepository.findOne(form.getId());

		LOGGER.debug(String.format("account 정보 갱신 {%s}", form.getId()));
		return new ModelAndView("redirect:/user/account_list");
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String delete(HttpServletRequest request, AccountDeleteForm form) {
		JsonArray arr = new JsonArray();
		for (long id : form.getAccountId()) {
			accountDao.updateAccountByIdForDelete(id);
			arr.add(new JsonPrimitive(id));
		}

		LOGGER.debug(String.format("account 삭제. %s", arr.toString()));

		JsonObject data = new JsonObject();
		data.addProperty("success", true);
		data.add("accountIdList", arr);

		request.setAttribute("data", data.toString());
		return "common/server_response";
	}

	@Override
	public String getUserList(HttpServletRequest request) {
		List<Account> actList = accountRepository.findAllByRole("USER");
		request.setAttribute("data", AppContext.GSON.toJson(actList));
		return "common/server_response";
	}

	@Override
	public ModelAndView accountList(HttpServletRequest request) {
		long accountId = AppContext.getAccountId(request);
		List<Account> a = accountDao.selectClientListOfUser(accountId);
		return new ModelAndView("account/list_user", "clientList", a);
	}
}
