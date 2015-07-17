package com.wisebirds.sap.service.ad;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wisebirds.sap.dao.AdCampaign.AdCampaignDao;
import com.wisebirds.sap.domain.ad.AdCreative;
import com.wisebirds.sap.domain.ad.campaign.Campaign;
import com.wisebirds.sap.domain.ad.campaign.CampaignAd;
import com.wisebirds.sap.domain.ad.campaign.CampaignGroup;
import com.wisebirds.sap.domain.form.CampaignCreateForm;
import com.wisebirds.sap.repository.ad.AdCreativeRepository;
import com.wisebirds.sap.repository.ad.CampaignAdRepository;
import com.wisebirds.sap.repository.ad.CampaignGroupRepository;
import com.wisebirds.sap.repository.ad.CampaignRepository;
import com.wisebirds.sap.util.AppContext;


@Service
public class AdCampaignSetServiceImpl implements AdCampaignSetService {
	@Autowired
	CampaignGroupRepository campaignGroupRepository;
	@Autowired
	CampaignRepository campaignRepository;
	@Autowired
	AdCreativeRepository adCreativeRepository;
	@Autowired
	CampaignAdRepository campaignAdRepository;
	@Autowired
	AdCampaignDao adCampaignDao;

	@Override
	public CampaignGroup getByCampaignGroupId(Long CampaignGroupId) {
		return adCampaignDao.selectCampaignGroupById(CampaignGroupId);
	}
	
	@Override
	public List<CampaignGroup> getByCampaignGroupStatusNot(int status){
		return campaignGroupRepository.findByCampaignGroupStatusNot(status);
	}

	@Override
	public List<AdCreative> getAllCreativeRunStatus(int status) {
		return adCreativeRepository.findAllByRunStatus(status);
	}
	

	@Override
	public String getCampaignSetListPage(HttpServletRequest request) {
		request.setAttribute("campaignSetList", campaignGroupRepository.findByCampaignGroupStatusNot(2));
		return "ad/campaign/campaign_list";
	}
	
	@Override
	public long createCampaignSet(CampaignCreateForm form, HttpServletRequest request) {
		CampaignGroup cg = createCampaignGroup(form, request);
		form.setCampaignGroupId(cg.getId());
		form.setAdAccountId(cg.getAdAccountId());
		List<AdCreative> creativeList = createCreative(form);
		List<Campaign> campaignList = createCampaign(form);
		for (AdCreative creative : creativeList) {
			for (Campaign campaign : campaignList) {
				createCampaignAd(campaign, creative);
			}
		}
		return cg.getId();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public CampaignGroup createCampaignGroup(CampaignCreateForm form, HttpServletRequest request) {
		CampaignGroup cg = new CampaignGroup();
		cg.setName(form.getCampaignGoupName());
		cg.setObjective(form.getObjective());
		cg.setSpendCap(form.getCampaignGroupSpendCap());
		cg.setCreateDate(new Date());
		cg.setAdAccountId(AppContext.getAdAccountId(request));
		return campaignGroupRepository.save(cg);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Campaign> createCampaign(CampaignCreateForm form) {
		List<Campaign> resultCampaign = new ArrayList<>();
		for (int i = 0; i < form.getCampaignId().length; i++) {
			Campaign campaign = new Campaign(form.getCampaignGroupId(), form.getAdAccountId());
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
		List<AdCreative> resultCreative = new ArrayList<>();
		for (int i = 0; i < form.getCreativeId().length; i++) {
			if (form.getCreativeId()[i] == 0) {
				AdCreative creative = new AdCreative();
				creative.setAdAccountId(form.getAdAccountId());
				creative.setBody(form.getCreativeBody()[i]);
				creative.setCallToActionType(form.getCreativeCallToActionType()[i]);
				creative.setImageUrl(form.getImageUrl()[i]);
				creative.setLinkUrl(form.getLinkUrl()[i]);
				creative.setName(form.getCreativeName()[i]);
				resultCreative.add(adCreativeRepository.save(creative));
			} else {
				resultCreative.add(adCreativeRepository.findOneById(form.getCreativeId()[i]));
			}
		}
		return resultCreative;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public CampaignAd createCampaignAd(Campaign campaign, AdCreative adCreative) {
		CampaignAd campaignAd = new CampaignAd();
		campaignAd.setAdAccountId(campaign.getAdAccountId());
		campaignAd.setName(adCreative.getName() + "_" + campaign.getName());
		campaignAd.setCreateTime(new Date());
		campaignAd.setCampaignId(campaign.getId());
		campaignAd.setCampaignGroupId(campaign.getCampaignGroupId());
		campaignAd.setAdCreative(adCreative);
		return campaignAdRepository.save(campaignAd);
	}

	@Override
	public CampaignGroup updateCampaignGroup(CampaignCreateForm form) {
		CampaignGroup cg = new CampaignGroup();
		cg.setName(form.getCampaignGoupName());
		cg.setCampaignGroupStatus(form.getCampaignGroupStatus());
		cg.setId(form.getCampaignGroupId());
		cg.setSpendCap(form.getCampaignGroupSpendCap());
		int updateCount = adCampaignDao.updateCampaignGroup(cg);
		System.out.println(updateCount);
		return adCampaignDao.selectCampaignGroupById(cg.getId());
	}


	public String imgFileUpload(HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String saveFileName = "";
		try {
			String dirFullPath = "c:/";
			String fileName = "";
			List<MultipartFile> files = multipartRequest.getFiles("imageUrl");
			if (files != null) {
				for (MultipartFile file : files) {
					System.out.println(file.getOriginalFilename());
					saveFileName = fileName + "_" + file.getOriginalFilename();

					String filePath = dirFullPath + "/" + saveFileName;

					File f = new File(filePath);
					file.transferTo(f);
				}
			}
		} catch (Exception ex) {
		}
		return saveFileName;
	}
}
