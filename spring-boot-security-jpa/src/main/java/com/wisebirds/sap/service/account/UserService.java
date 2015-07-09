package com.wisebirds.sap.service.account;

import java.util.Optional;

import com.wisebirds.sap.domain.account.UserInfo;

public interface UserService {
	Optional<UserInfo> findOneByEmail(String email);
}
