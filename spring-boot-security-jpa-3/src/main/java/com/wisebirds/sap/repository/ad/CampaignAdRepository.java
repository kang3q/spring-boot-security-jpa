package com.wisebirds.sap.repository.ad;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisebirds.sap.domain.ad.AdCampaignItem;


public interface CampaignAdRepository extends JpaRepository<AdCampaignItem, Long>{
	List<AdCampaignItem> findAllByCampaignGroupIdOrderByCreateTimeAsc(Long campaignGroupId);
}
