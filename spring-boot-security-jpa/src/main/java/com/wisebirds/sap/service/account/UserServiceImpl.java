package com.wisebirds.sap.service.account;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisebirds.sap.domain.account.UserInfo;
import com.wisebirds.sap.repository.account.MemberTestRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private MemberTestRepository memberTestRepository;

	@Override
	public Optional<UserInfo> findOneByEmail(String email) {
		return memberTestRepository.findOneByEmail(email);
	}

}
