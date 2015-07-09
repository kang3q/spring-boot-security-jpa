package com.wisebirds.sap.domain.form;

import org.hibernate.validator.constraints.NotEmpty;

public class MemberDeleteForm {
	@NotEmpty
	private String memberId;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
}