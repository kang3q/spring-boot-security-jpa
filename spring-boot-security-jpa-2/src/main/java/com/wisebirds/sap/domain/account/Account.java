package com.wisebirds.sap.domain.account;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wisebirds.sap.Application;

/**
 * @author Administrator
 *
 */
@Entity
@Table(name = "account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "agency_id", nullable = false)
	private Long agencyId = 0L; // 0 = admin,user,viewer // 0 != client

	@Column(name = "email", unique = true, nullable = false, length = 128)
	private String email;

	@Column(name = "password", nullable = false, length = 128)
	private String password;

	@Column(name = "name", nullable = false, length = 128)
	private String name;

	@Column(name = "role", nullable = false, length = 10)
	private String role;

	@Column(name = "access_token", length = 300)
	private String accessToken;

	@Column(name = "deleted")
	private Boolean deleted = false;

	@Column(name = "create_time")
	private Date createTime = new Date();

	@Column(name = "update_time")
	private Date updateTime;

	@Column(name = "phone", length = 15)
	private String phone;

	@Column(name = "zipcode", length = 6)
	private String zipcode;

	@Column(name = "address", length = 200)
	private String address;

	@Column(name = "locale", length = 5)
	private String locale;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id")
	private List<AccountConnections> adAccountList;

	public Account() {}

	public Account(String email, String name, String password, String role, String accessToken) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.role = role;
		this.accessToken = accessToken;
	}

	public Account(Long agencyId, String email, String name, String password, String role, String accessToken) {
		this.agencyId = agencyId;
		this.email = email;
		this.password = password;
		this.name = name;
		this.role = role;
		this.accessToken = accessToken;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(Long agencyId) {
		this.agencyId = agencyId;
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

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	public List<AccountConnections> getAdAccountList() {
		return adAccountList;
	}

	public void setAdAccountList(List<AccountConnections> adAccountList) {
		this.adAccountList = adAccountList;
	}

	////////////////////
	public static int getObjectId() {
		return OBJECT_ID;
	}

	public static final int OBJECT_ID = Application.OBJECT_SAP_ACCOUNT;
}
