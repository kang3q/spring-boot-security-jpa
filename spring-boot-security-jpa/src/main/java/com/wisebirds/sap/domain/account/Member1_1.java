package com.wisebirds.sap.domain.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member")
public class Member1_1 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "member_id", unique = true, nullable = false, length = 32)
	private String memberId;

	@Column(name = "name", nullable = false, length = 24)
	private String name;

	private String stock = "으하하";

	// @Column(name = "gender", nullable = false)
	// private Gender gender;

	// @Column(name = "role", nullable = false, length = 24)
	// @Enumerated(EnumType.STRING)
	// private Role role;
	public Member1_1() {}

	public Member1_1(Member m) {
		this.id = m.getId();
		this.memberId = m.getMemberId();
		this.name = m.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	// public Role getRole() {
	// return role;
	// }
	//
	// public void setRole(Role role) {
	// this.role = role;
	// }
}
