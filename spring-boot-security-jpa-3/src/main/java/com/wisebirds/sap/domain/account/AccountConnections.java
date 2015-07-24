package com.wisebirds.sap.domain.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account_connections")
public class AccountConnections {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "account_id", nullable = false)
	private Long accountId;

	@Column(name = "ad_account_id", nullable = false)
	private Long adAccountId;

	public AccountConnections() {}

	public AccountConnections(Long accountId, Long adAccountId) {
		this.accountId = accountId;
		this.adAccountId = adAccountId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getAdAccountId() {
		return adAccountId;
	}

	public void setAdAccountId(Long adAccountId) {
		this.adAccountId = adAccountId;
	}

}
