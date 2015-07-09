package com.wisebirds.sap.domain.form;

import org.hibernate.validator.constraints.NotEmpty;

public class MemberCreateForm {
	private Long id;
	@NotEmpty
	private String memberId;
	@NotEmpty
	private String name;
	private String[] cb;
	// @NotNull
	// private Role role = Role.USER;

	public String getMemberId() {
		return memberId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setId(String id) {
		this.memberId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String[] getCb() {
		return cb;
	}

	public void setCb(String[] cb) {
		this.cb = cb;
	}

	// public Role getRole() {
	// return role;
	// }
	//
	// public void setRole(Role role) {
	// this.role = role;
	// }
}
