package com.wisebirds.sap.service.account;

import java.util.Collection;
import java.util.Optional;

import com.wisebirds.sap.domain.account.Member;
import com.wisebirds.sap.domain.form.MemberCreateForm;
import com.wisebirds.sap.domain.form.MemberDeleteForm;

public interface MemberService {
	Optional<Member> get(Long id);

	Optional<Member> getByMemberId(String memberId);

	Collection<Member> getAllMembers();

	Member create(MemberCreateForm form);

	Member update(MemberCreateForm form);

	boolean delete(MemberDeleteForm memberDeleteForm);
}
