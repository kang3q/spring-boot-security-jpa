package com.wisebirds.sap.service.ad;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wisebirds.sap.dao.AdCampaign.AdCampaignDao;
import com.wisebirds.sap.domain.ad.AdCampaign;
import com.wisebirds.sap.domain.ad.AdCampaignGroup;
import com.wisebirds.sap.domain.ad.AdCampaignItem;
import com.wisebirds.sap.domain.ad.AdCreative;
import com.wisebirds.sap.domain.ad.AdProfile;
import com.wisebirds.sap.domain.form.CampaignCreateForm;
import com.wisebirds.sap.repository.ad.AdCampaignGroupRepository;
import com.wisebirds.sap.repository.ad.AdCreativeRepository;
import com.wisebirds.sap.repository.ad.CampaignAdRepository;
import com.wisebirds.sap.repository.ad.CampaignRepository;
import com.wisebirds.sap.util.AppContext;


@Service
public class AdCampaignSetServiceImpl implements AdCampaignSetService {
	@Autowired
	AdCampaignGroupRepository campaignGroupRepository;
	@Autowired
	CampaignRepository campaignRepository;
	@Autowired
	AdCreativeRepository adCreativeRepository;
	@Autowired
	CampaignAdRepository campaignAdRepository;
	@Autowired
	AdCampaignDao adCampaignDao;

	@Override
	public AdCampaignGroup getByCampaignGroupId(Long CampaignGroupId) {
		return adCampaignDao.selectCampaignGroupById(CampaignGroupId);
	}

	@Override
	public List<AdCampaignGroup> getByCampaignGroupStatusNot(int status) {
		return campaignGroupRepository.findAllByCampaignGroupStatusNotOrderByCreateTimeAsc(status);
	}

	@Override
	public List<AdCreative> getAllCreativeRunStatus(int status) {
		return adCreativeRepository.findAllByRunStatus(status, new Sort(Sort.Direction.ASC, "id"));
	}

	@Override
	public List<AdCreative> getAllCreative(HttpServletRequest request){
		List<AdCreative> creativeList = new ArrayList<AdCreative>();
		if(AppContext.getAccount(request).getRole().equals("CLIENT")){
			creativeList = adCreativeRepository.findAllByRunStatusAndAdAccountIdAndUserAccountIdOrderByCreateTimeAsc(0, AppContext.getAdAccountId(request), AppContext.getAccountId(request));
		}else{
			creativeList = adCreativeRepository.findAllByRunStatusAndAdAccountIdOrderByCreateTimeAsc(0, AppContext.getAdAccountId(request));
		}
		return creativeList;
	}
	
	@Override
	public String getCampaignSetListPage(HttpServletRequest request) {
		String searchCampaignGroupStatus = request.getParameter("searchCampaignGroupStatus");
		String userAccountId = request.getParameter(AppContext.PARAM_USER_ACCOUNT);
		List<AdCampaignGroup> campaignList = new ArrayList<AdCampaignGroup>();
		if(searchCampaignGroupStatus == null){
			searchCampaignGroupStatus = "-1";
		}
		if(userAccountId == null){	
			if(AppContext.getAccount(request).getRole().equals("CLIENT")){
				if(searchCampaignGroupStatus.equals("-1")){
					campaignList = campaignGroupRepository.findAllByCampaignGroupStatusNotAndAdAccountIdAndUserAccountIdOrderByCreateTimeAsc(2, AppContext.getAdAccountId(request), AppContext.getAccountId(request));
				}else{
					campaignList = campaignGroupRepository.findAllByCampaignGroupStatusAndAdAccountIdAndUserAccountIdOrderByCreateTimeAsc(Integer.parseInt(searchCampaignGroupStatus), AppContext.getAdAccountId(request), AppContext.getAccountId(request));					
				}
			}else{
				if(searchCampaignGroupStatus.equals("-1")){
					campaignList = campaignGroupRepository.findAllByCampaignGroupStatusNotAndAdAccountIdOrderByCreateTimeAsc(2, AppContext.getAdAccountId(request));					
				}else{
					campaignList = campaignGroupRepository.findAllByCampaignGroupStatusAndAdAccountIdOrderByCreateTimeAsc(Integer.parseInt(searchCampaignGroupStatus), AppContext.getAdAccountId(request));
				}
			}
		}else{
			if(searchCampaignGroupStatus.equals("-1")){
				campaignList = campaignGroupRepository.findAllByCampaignGroupStatusNotAndUserAccountIdOrderByCreateTimeAsc(2, Long.parseLong(userAccountId));
			}else{
				campaignList = campaignGroupRepository.findAllByCampaignGroupStatusAndUserAccountIdOrderByCreateTimeAsc(Integer.parseInt(searchCampaignGroupStatus), Long.parseLong(userAccountId));
			}
			request.setAttribute("userAccountId", userAccountId);
		}
		request.setAttribute("campaignSetStatus", searchCampaignGroupStatus);
		request.setAttribute("campaignSetList", campaignList);
		return "ad/campaign/campaign_list";
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public long createCampaignSet(CampaignCreateForm form, HttpServletRequest request) {
		form.setAdAccountId(AppContext.getAdAccountId(request));
		form.setUserAccountId(AppContext.getAccountId(request));
		AdCampaignGroup cg = createCampaignGroupNoOverride(form);
		form.setCampaignGroupId(cg.getId());
		List<AdCreative> creativeList = createCreativeNoOverride(form);
		List<AdCampaign> campaignList = createCampaignNoOverride(form);
		for (AdCreative creative : creativeList) {
			for (AdCampaign campaign : campaignList) {
				createCampaignAdNoOverride(campaign, creative);
			}
		}
		return cg.getId();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public AdCampaignGroup createCampaignGroup(CampaignCreateForm form, HttpServletRequest request) {
		form.setAdAccountId(AppContext.getAdAccountId(request));
		form.setUserAccountId(AppContext.getAccountId(request));			
		return createCampaignGroupNoOverride(form);
	}

	public AdCampaignGroup createCampaignGroupNoOverride(CampaignCreateForm form) {
		AdCampaignGroup cg = new AdCampaignGroup();
		cg.setName(form.getCampaignGoupName());
		cg.setObjective(form.getObjective());
		cg.setSpendCap(form.getCampaignGroupSpendCap());
		cg.setCreateTime(new Date());
		cg.setAdAccountId(form.getAdAccountId());
		cg.setUserAccountId(form.getUserAccountId());
		return campaignGroupRepository.save(cg);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<AdCampaign> createCampaign(CampaignCreateForm form) {
		return createCampaignNoOverride(form);
	}

	public List<AdCampaign> createCampaignNoOverride(CampaignCreateForm form) {
		List<AdCampaign> resultCampaign = new ArrayList<>();
		for (int i = 0; i < form.getCampaignId().length; i++) {
			AdCampaign campaign = new AdCampaign(form.getCampaignGroupId(), form.getAdAccountId());
			campaign.setUserAccountId(form.getUserAccountId());
			campaign.setName(form.getCampaignName()[i]);
			campaign.setBidType(form.getCampaignBidType()[i]);
			campaign.setStartTime(form.getCampaignStartTime()[i]);
			campaign.setEndTime(form.getCampaignEndTime()[i]);
			campaign.setCreateTime(new Date());
			campaign.setBudget(form.getCampaignBudget()[i]);
			campaign.setBudgetType(form.getCampaignBudgetType()[i]);
			resultCampaign.add(campaignRepository.save(campaign));
		}
		return resultCampaign;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<AdCreative> createCreative(CampaignCreateForm form) {
		return createCreativeNoOverride(form);
	}

	public List<AdCreative> createCreativeNoOverride(CampaignCreateForm form) {
		List<AdCreative> resultCreative = new ArrayList<>();
		for (int i = 0; i < form.getCreativeId().length; i++) {
			if (form.getCreativeId()[i] == 0) {
				AdCreative creative = new AdCreative();
				creative.setAdAccountId(form.getAdAccountId());
				creative.setUserAccountId(form.getUserAccountId());
				creative.setBody(form.getCreativeBody()[i]);
				creative.setCallToActionType(form.getCreativeCallToActionType()[i]);
				creative.setImageUrl(form.getImageUrl()[i]);
				creative.setLinkUrl(form.getLinkUrl()[i]);
				creative.setName(form.getCreativeName()[i]);
				creative.setLinkTitle(form.getLinkTitle()[i]);
				creative.setLinkBody(form.getLinkBody()[i]);
				creative.setLinkCaption(form.getLinkCaption()[i]);
				creative.setAdProfile(new AdProfile(29000000000000003L));
				creative.setRunStatus(10);
				creative.setCreateTime(new Date());
				resultCreative.add(adCreativeRepository.save(creative));
			} else {
				resultCreative.add(adCreativeRepository.findOne(form.getCreativeId()[i]));
			}
		}
		return resultCreative;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public AdCampaignItem createCampaignAd(AdCampaign campaign, AdCreative adCreative) {
		return createCampaignAdNoOverride(campaign, adCreative);
	}

	public AdCampaignItem createCampaignAdNoOverride(AdCampaign campaign, AdCreative adCreative) {
		AdCampaignItem campaignAd = new AdCampaignItem();
		campaignAd.setAdAccountId(campaign.getAdAccountId());
		campaignAd.setName(adCreative.getName() + "_" + campaign.getName());
		campaignAd.setCreateTime(new Date());
		campaignAd.setCampaignId(campaign.getId());
		campaignAd.setCampaignGroupId(campaign.getCampaignGroupId());
		campaignAd.setAdCreative(adCreative);
		campaignAd.setUserAccountId(campaign.getUserAccountId());
		return campaignAdRepository.save(campaignAd);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String updateCampaignGroup(HttpServletRequest request){
		return updateCampaignGroupNoOverride(request);	
	}
	public String updateCampaignGroupNoOverride(HttpServletRequest request){
		AdCampaignGroup cg = new AdCampaignGroup();
		String name = request.getParameter("group_name");
		String status = request.getParameter("group_status");
		String id = request.getParameter("id");
		String spendCap = request.getParameter("spend_cap");
		System.out.println(status);
		cg.setName(name);
		cg.setCampaignGroupStatus(Integer.parseInt(status));
		cg.setSpendCap(Integer.parseInt(spendCap));
		cg.setId(Long.parseLong(id));
		request.setAttribute("data", adCampaignDao.updateCampaignGroup(cg));
		return "common/server_response";	
	}	
}
