package com.wisebirds.sap.domain.ad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ad_verify_history")
public class AdVerifyHistory {
	public AdVerifyHistory() {}

	public AdVerifyHistory(Long adCreativeId, int status, int reason) {
		this.adCreativeId = adCreativeId;
		this.status = status;
		this.reason = reason;
		this.verifyDate = new Date();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "ad_creative_id", nullable = false, updatable = false)
	private Long adCreativeId;

	@Column(name = "status", nullable = false)
	private int status;

	@Column(name = "reason", nullable = false)
	private int reason;

	@Column(name = "verify_date", nullable = false)
	private Date verifyDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAdCreativeId() {
		return adCreativeId;
	}

	public void setAdCreativeId(Long adCreativeId) {
		this.adCreativeId = adCreativeId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getReason() {
		return reason;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}

	public Date getVerifyDate() {
		return verifyDate;
	}

	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}
}
