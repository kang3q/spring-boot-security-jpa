package com.wisebirds.sap.repository.ad;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wisebirds.sap.domain.ad.AdCreative;

public interface AdCreativeRepository extends JpaRepository<AdCreative, Long> {
	List<AdCreative> findAll(Sort sort);
	// List<AdCreative> findAll(Pageable pageable, Sort sort);

	List<AdCreative> findAllByRunStatus(int runStatus, Sort sort);
	// List<AdCreative> findAllByRunStatus(int runStatus, Pageable pageable, Sort sort);

	List<AdCreative> findAllByRunStatusAndAdAccountIdOrderByCreateTimeAsc(int runStatus, long adAccountid);

	List<AdCreative> findAllByRunStatusAndAdAccountIdAndUserAccountIdOrderByCreateTimeAsc(int runStatus, long adAccountId, long UserAccountId);

	Page<AdCreative> findAllByRunStatusOrderByCreateTimeAsc(int runStatus, Pageable paging); // , PageRequest pageRequest
}
