package com.wisebirds.sap.service.ad;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.wisebirds.sap.domain.ad.AdCreative;
import com.wisebirds.sap.domain.ad.campaign.Campaign;
import com.wisebirds.sap.domain.ad.campaign.CampaignGroup;
import com.wisebirds.sap.domain.form.CampaignCreateForm;

public interface AdCampaignSetService {
	
	long createCampaignSet(CampaignCreateForm form, HttpServletRequest request);
	
	List<CampaignGroup> getByCampaignGroupStatusNot(int status);
		
	String getCampaignSetListPage(HttpServletRequest request);

	CampaignGroup createCampaignGroup(CampaignCreateForm form, HttpServletRequest request);
	
	CampaignGroup getByCampaignGroupId(Long CampaignGroupId);
	
	CampaignGroup updateCampaignGroup(CampaignCreateForm form);
	
	List<Campaign> createCampaign(CampaignCreateForm form);
	
	List<AdCreative> getAllCreativeRunStatus(int status);
	
	List<AdCreative> createCreative(CampaignCreateForm form);
	
}
