package com.wisebirds.sap.repository.ad;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisebirds.sap.domain.ad.campaign.Campaign;



public interface CampaignRepository extends JpaRepository<Campaign, Long>{
	List<Campaign> findByCampaignGroupId(Long campaignGroupId);
}
