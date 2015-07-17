package com.wisebirds.sap.service.ad.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class VerificationResultForm {
	@NotNull
	private Long creativeId;
	private Integer reason = 0;
	@NotEmpty
	private String action;

	public Long getCreativeId() {
		return creativeId;
	}

	public void setCreativeId(Long creativeId) {
		this.creativeId = creativeId;
	}

	public Integer getReason() {
		return reason;
	}

	public void setReason(Integer reason) {
		this.reason = reason;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public static final String ACTION_APPROVE = "approve";
	public static final String ACTION_REJECT = "reject";
}
