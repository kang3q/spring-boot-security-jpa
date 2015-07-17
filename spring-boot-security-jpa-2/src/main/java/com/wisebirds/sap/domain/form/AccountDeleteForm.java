package com.wisebirds.sap.domain.form;

import org.hibernate.validator.constraints.NotEmpty;

public class AccountDeleteForm {
	@NotEmpty
	private long[] accountId;

	public long[] getAccountId() {
		return accountId;
	}

	public void setAccountId(long[] accountId) {
		this.accountId = accountId;
	}


}
