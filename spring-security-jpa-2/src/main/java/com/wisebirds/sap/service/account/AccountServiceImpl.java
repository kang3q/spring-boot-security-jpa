package com.wisebirds.sap.service.account;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
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
import com.wisebirds.sap.domain.ad.AdAccount;
import com.wisebirds.sap.domain.form.AccountCreateForm;
import com.wisebirds.sap.domain.form.AccountDeleteForm;
import com.wisebirds.sap.repository.account.AccountConnectionsRepository;
import com.wisebirds.sap.repository.account.AccountRepository;
import com.wisebirds.sap.repository.ad.AdAccountRepository;
import com.wisebirds.sap.util.AppContext;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private AdAccountRepository adAccountRepository;
	@Autowired
	private AccountConnectionsRepository actConnectionsRepository;
	@Autowired
	private AccountDao accountDao;

	@Override
	public Optional<Account> get(Long id) {
		return Optional.ofNullable(accountRepository.findOne(id));
	}

	@Override
	public Optional<Account> getByEmail(String email) {
		return accountRepository.findOneByEmail(email);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Collection<Account> getAllAccounts() {
		return accountRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ModelAndView create(AccountCreateForm form) {
		String name = form.getName();
		String password = new ShaPasswordEncoder().encodePassword(form.getPassword(), null);
		String token = Jwts.builder().setSubject(form.getEmail()).claim("roles", form.getRole()).signWith(SignatureAlgorithm.HS512, AppContext.SECRET_KEY).compact();
		String role = String.join(",", form.getRole());

		Account account = null;
		if (form.getRole().equals("ADMIN") || form.getRole().equals("USER")) {
			account = accountRepository.save(new Account(form.getEmail(), name, password, role, token));
			// ad_account 만들기
			AdAccount adAccount = adAccountRepository.save(new AdAccount(name, "KRW"));
			// accountConnections 연결하기
			actConnectionsRepository.save(new AccountConnections(account.getId(), adAccount.getId()));
		} else if (form.getRole().equals("CLIENT")) {
			account = accountRepository.save(new Account(form.getAgency_id(), form.getEmail(), name, password, role, token));
			// accountConnections 연결하기
			long adAccountId = actConnectionsRepository.findAllByAccountId(form.getAgency_id()).get(0).getAdAccountId(); // user의 ad_account_id 가져오기 // 나중엔 ad_account가 여러개 있을 수 있음.
			actConnectionsRepository.save(new AccountConnections(account.getId(), adAccountId));
		} else if (form.getRole().equals("REVIEWER")) {
			account = accountRepository.save(new Account(form.getEmail(), name, password, role, token));
		} else {
			LOGGER.debug(String.format("신규 account 생성 실패"));
			ModelAndView mav = new ModelAndView("common/server_response");
			mav.addObject("data", "실패");
			return mav;
		}

		LOGGER.debug(String.format("신규 account 생성 {%s}", account.getId()));
		// ModelAndView mav = new ModelAndView("common/server_response");
		// mav.addObject("data", Application.GSON.toJson(new LongId(account.getId())));
		return new ModelAndView("redirect:/admin/account_list");
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ModelAndView update(AccountCreateForm form) {
		String password = new ShaPasswordEncoder().encodePassword(form.getPassword(), null);
		String token = Jwts.builder().setSubject(form.getEmail()).claim("roles", form.getRole()).signWith(SignatureAlgorithm.HS512, AppContext.SECRET_KEY).compact();
		accountDao.updateAccount(form.getId(), form.getAgency_id(), password, form.getName(), form.getRole(), token, form.getPhone(), form.getZipcode(), form.getAddress(), form.getLocale());
		// Account account = accountRepository.findOne(form.getId());

		LOGGER.debug(String.format("account 정보 갱신 {%s}", form.getId()));
		return new ModelAndView("redirect:/admin/account_list");
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String delete(HttpServletRequest request, AccountDeleteForm form) {
		// try {
		// Account account = accountRepository.findOneByEmail(form.getEmail()).get();
		// // accountRepository.delete(account);
		// } catch (Exception e) {
		// LOGGER.debug(String.format("존재하지 않는 멤버 아이디입니다. {%s}", form.getEmail()));
		// return false;
		// }
		// return true;

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
		return new ModelAndView("account/list", "accountList", accountDao.selectAccountListByIdNot(accountId));
	}
}
