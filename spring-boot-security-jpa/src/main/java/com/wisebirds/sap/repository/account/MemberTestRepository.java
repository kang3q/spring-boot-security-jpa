package com.wisebirds.sap.repository.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisebirds.sap.domain.account.UserInfo;


public interface MemberTestRepository extends JpaRepository<UserInfo, Long> {
	Optional<UserInfo> findOneByEmail(String email);
}
