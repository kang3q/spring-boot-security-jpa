package com.wisebirds.sap.repository.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisebirds.sap.domain.account.AccountConnections;

public interface AccountConnectionsRepository extends JpaRepository<AccountConnections, Long> {

	List<AccountConnections> findAllByAccountId(long accountId);

	List<AccountConnections> findAllByAdAccountId(long adAccountId);
}
