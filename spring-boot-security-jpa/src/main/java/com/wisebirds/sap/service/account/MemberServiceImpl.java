package com.wisebirds.sap.service.account;

import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.wisebirds.sap.domain.account.Member;
import com.wisebirds.sap.domain.form.MemberCreateForm;
import com.wisebirds.sap.domain.form.MemberDeleteForm;
import com.wisebirds.sap.repository.account.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberServiceImpl.class);
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public Optional<Member> get(Long id) {
		return Optional.ofNullable(memberRepository.findOne(id));
	}

	@Override
	public Optional<Member> getByMemberId(String memberId) {
		return memberRepository.findOneByMemberId(memberId);
	}

	@Override
	public Collection<Member> getAllMembers() {
		return memberRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
	}

	@Override
	public Member create(MemberCreateForm form) {
		Member member = new Member();
		member.setMemberId(form.getMemberId());
		member.setName(form.getName());
		return memberRepository.save(member);
	}

	@Override
	public Member update(MemberCreateForm form) {
		Member member = new Member();
		member.setId(form.getId());
		member.setMemberId(form.getMemberId());
		member.setName(form.getName());
		return memberRepository.save(member);
	}

	@Override
	public boolean delete(MemberDeleteForm memberDeleteForm) {
		try {
			Member member = memberRepository.findOneByMemberId(memberDeleteForm.getMemberId()).get();
			memberRepository.delete(member);
		} catch (Exception e) {
			LOGGER.debug(String.format("존재하지 않는 멤버 아이디입니다. {%s}", memberDeleteForm.getMemberId()));
			return false;
		}
		return true;
	}
}
