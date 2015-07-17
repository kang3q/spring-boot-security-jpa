package com.wisebirds.sap.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wisebirds.sap.Application;
import com.wisebirds.sap.domain.LongId;
import com.wisebirds.sap.domain.ad.AdCreative;
import com.wisebirds.sap.domain.ad.campaign.CampaignGroup;
import com.wisebirds.sap.domain.form.CampaignCreateForm;
import com.wisebirds.sap.service.ad.AdCampaignSetService;

@Controller
public class CampaignController {
	@Autowired
	AdCampaignSetService adCampaignSetService;
	
	@RequestMapping(value = "/campaign_create", method = RequestMethod.GET)
	public String getCreateCampaignPage(HttpServletRequest request) {
		List<AdCreative> adCreativeList = adCampaignSetService.getAllCreativeRunStatus(0);
		request.setAttribute("adCreative", adCreativeList);
		return "ad/campaign/campaign_create";
		//return adCampaignSetService.viewCampaignCreatePage(request);
	}
	
	@RequestMapping(value = "/campaign_create", method = RequestMethod.POST)
	public ModelAndView CreateCapmaignSet(CampaignCreateForm form, HttpServletRequest request) {
		long campaignId = adCampaignSetService.createCampaignSet(form, request);
		System.out.println(String.format("CampaginController 38 : 신규 광고, 캠페인, ad 생성 {%s}", campaignId));
		ModelAndView mav = new ModelAndView("common/server_response");
		mav.addObject("data", Application.GSON.toJson(new LongId(campaignId)));
		return mav;
	}
	
	@RequestMapping(value = "/campaign_list", method = RequestMethod.GET)
	public String getListCampaigGroupPage(HttpServletRequest request){
		return adCampaignSetService.getCampaignSetListPage(request);
	}

	@RequestMapping(value = "/campaign_update", method = RequestMethod.POST)
	public boolean updateCapmaignSet(@ModelAttribute("form") CampaignCreateForm form) {
		CampaignGroup cg = adCampaignSetService.updateCampaignGroup(form);
		System.out.println(String.format("CampaginController 47 : 광고 수정 {%s}", cg.getId()));
		return cg != null;
	}
}
