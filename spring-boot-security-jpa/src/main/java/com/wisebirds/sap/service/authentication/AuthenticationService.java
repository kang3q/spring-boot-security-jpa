package com.wisebirds.sap.service.authentication;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wisebirds.sap.domain.account.UserInfo;
import com.wisebirds.sap.repository.account.MemberTestRepository;


@Service
public class AuthenticationService implements UserDetailsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);
	
	@Autowired
	private MemberTestRepository memberTestRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo user = memberTestRepository.findOneByEmail(username).get();
//		UserDetails userDetails = new User(user.getEmail(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ADMIN"), new SimpleGrantedAuthority("AD_MANAGER")));
		UserDetails userDetails = new User(user.getEmail(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority(user.getRole())));
		return userDetails;
	}

}
