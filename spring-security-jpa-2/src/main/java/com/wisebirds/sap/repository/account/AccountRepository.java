package com.wisebirds.sap.repository.account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisebirds.sap.domain.account.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	// @Query(name = "Member.findByMemberId", nativeQuery = true) //
	Optional<Account> findOneByEmail(String email);

	List<Account> findAllByRole(String role);

	List<Account> findAllByIdNot(Long id);
}
