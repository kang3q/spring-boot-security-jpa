package com.wisebirds.sap.service.ad;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wisebirds.sap.domain.ad.AdCampaignGroup;
import com.wisebirds.sap.domain.ad.AdCampaignItem;
import com.wisebirds.sap.domain.ad.AdCreative;
import com.wisebirds.sap.domain.ad.AdCampaign;
import com.wisebirds.sap.domain.form.CampaignCreateForm;

public interface AdCampaignSetService {
	
	long createCampaignSet(CampaignCreateForm form, HttpServletRequest request);
	
	List<AdCampaignGroup> getByCampaignGroupStatusNot(int status);
	
	List<AdCreative> getAllCreativeRunStatus(int status);
	
	List<AdCreative> getAllCreative(HttpServletRequest request);
		
	String getCampaignSetListPage(HttpServletRequest request);

	AdCampaignGroup createCampaignGroup(CampaignCreateForm form, HttpServletRequest request);
	
	AdCampaignGroup getByCampaignGroupId(Long CampaignGroupId);
	
	List<AdCampaign> createCampaign(CampaignCreateForm form);
	
	
	List<AdCreative> createCreative(CampaignCreateForm form);
	
	AdCampaignItem createCampaignAd(AdCampaign campaign, AdCreative adCreative);
	
	String updateCampaignGroup(HttpServletRequest request);
}
