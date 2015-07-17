package com.wisebirds.sap.repository.ad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisebirds.sap.domain.ad.AdCreative;

public interface AdCreativeRepository extends JpaRepository<AdCreative, Long> {
	List<AdCreative> findAllByRunStatus(int runStatus);
	
	AdCreative findOneById(Long id);
}
