package com.wisebirds.sap.repository.ad;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisebirds.sap.domain.ad.campaign.CampaignGroup;


public interface CampaignGroupRepository extends JpaRepository<CampaignGroup, Long>{
	Optional<CampaignGroup> findById(Long CampaignGroupId);
	
	List<CampaignGroup> findByCampaignGroupStatusNot(int status);
}
