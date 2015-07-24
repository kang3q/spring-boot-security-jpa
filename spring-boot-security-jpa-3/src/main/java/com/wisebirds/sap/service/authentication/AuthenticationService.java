package com.wisebirds.sap.service.authentication;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wisebirds.sap.domain.account.Account;
import com.wisebirds.sap.domain.account.SessionAccount;
import com.wisebirds.sap.repository.account.AccountRepository;


@Service
public class AuthenticationService implements UserDetailsService {
	// private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public /* UserDetails */ SessionAccount loadUserByUsername(String email) throws UsernameNotFoundException {
		Account account = accountRepository.findOneByEmail(email).get();
		// UserDetails userDetails = new User(String.valueOf(account.getId()), account.getPassword(), Arrays.asList(new SimpleGrantedAuthority(account.getRole())));
		SessionAccount sessionAccount = new SessionAccount(account.getEmail(), account.getPassword(), Arrays.asList(new SimpleGrantedAuthority(account.getRole())), account);
		return sessionAccount;
	}

}
