package com.wisebirds.sap.domain.form;

import org.hibernate.validator.constraints.NotEmpty;

public class AccountCreateForm {
	private Long id;
	@NotEmpty
	private String email;
	@NotEmpty
	private String password;
	@NotEmpty
	private String name;
	@NotEmpty
	private String role;
	private String access_token;
	private String phone;
	private String zipcode;
	private String address;
	private String locale;
	private Long agency_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Long getAgency_id() {
		return agency_id;
	}

	public void setAgency_id(Long agency_id) {
		this.agency_id = agency_id;
	}

	///////////////////////////////////////// 아래부턴 update 전용//////////////////////////////////////////



}
