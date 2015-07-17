package com.wisebirds.sap.domain.ad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wisebirds.sap.Application;

/**
 * 광고계정을 표현한다.
 * 
 * @author jongmin
 */
@Entity
@Table(name = "ad_account")
public class AdAccount {
	// account_groups
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	@Column(name = "account_status", nullable = false)
	private Integer accountStatus = 0;
	// private Float age; // Amount of time the ad account has been open, in days
	// agencyClientDeclaration
	// amountSpent
	////////////////////
	// ownerAccountId
	// balance
	// business
	// businessCity
	// businessCountryCode
	// businessName
	// businessState
	// businessStreet
	// businessStreet2
	// businessZip
	////////////////////
	// capabilities
	@Column(name = "created_time", nullable = false)
	private Date createdTime = new Date();
	@Column(name = "currency", nullable = false, length = 3)
	private String currency;
	// endAdvertiser
	// fundingSource
	// fundingSource_details
	// isPersonal
	// mediaAgency
	@Column(name = "name", nullable = false, length = 128)
	private String name;

	// offsitePixelsTosAccepted
	// partner
	// rfSpec
	// spendCap
	// taxIdStatus
	// timezoneId
	// timezoneName
	// timezoneOffsetHoursUtc
	// tosAccepted
	// users

	public AdAccount() {}

	public AdAccount(String name, String currency) {
		this.name = name;
		this.currency = currency;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public static int getObjectId() {
		return OBJECT_ID;
	}

	public static final int OBJECT_ID = Application.OBJECT_AD_ACCOUNT;
}
