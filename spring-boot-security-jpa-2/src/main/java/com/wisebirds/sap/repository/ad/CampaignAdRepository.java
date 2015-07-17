package com.wisebirds.sap.repository.ad;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisebirds.sap.domain.ad.campaign.CampaignAd;



public interface CampaignAdRepository extends JpaRepository<CampaignAd, Long>{
	List<CampaignAd> findByCampaignGroupId(Long campaignGroupId);
}
