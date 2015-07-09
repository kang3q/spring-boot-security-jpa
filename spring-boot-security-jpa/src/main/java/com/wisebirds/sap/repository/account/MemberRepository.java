package com.wisebirds.sap.repository.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wisebirds.sap.domain.account.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	@Query(name = "Member.findByMemberId", nativeQuery = true) //
	Optional<Member> findOneByMemberId(String memberId);
}
