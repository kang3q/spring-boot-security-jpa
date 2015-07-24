package com.wisebirds.sap.domain.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CampaignCreateForm {

	// CAMPAIGN GROUP 부분 (STEP 1)
	int objective;
	String campaignGoupName;
	int campaignGroupSpendCap;
	Long campaignGroupId;
	int campaignGroupStatus;
	Long adAccountId;
	Long userAccountId;

	// CAMPAIGN 부분 (STEP 2)
	Long[] campaignId;
	String[] campaignName;
	String[] campaignBidType;
	int[] campaignBudget;
	String[] campaignBudgetType;
	String[] campaignTarget;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	// @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	Date[] campaignStartTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	// @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
	Date[] campaignEndTime;

	// CREATIVE 부분 (STEP 3)
	Long[] creativeId;
	String[] creativeBody;
	int[] creativeCallToActionType;
	String[] imageUrl;
	String[] linkUrl;
	String[] creativeName;
	String[] linkTitle;
	String[] linkBody;
	String[] linkCaption;
	


	////////////////////////////////////////// STEP 1
	
	public Long getCampaignGroupId() {
		return campaignGroupId;
	}

	public Long getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Long userAccountId) {
		this.userAccountId = userAccountId;
	}

	public void setCampaignGroupId(Long campaignGroupId) {
		this.campaignGroupId = campaignGroupId;
	}

	public int getObjective() {
		return objective;
	}

	public void setObjective(int objective) {
		this.objective = objective;
	}

	public String getCampaignGoupName() {
		return campaignGoupName;
	}

	public void setCampaignGoupName(String campaignGoupName) {
		this.campaignGoupName = campaignGoupName;
	}

	public int getCampaignGroupStatus() {
		return campaignGroupStatus;
	}

	public void setCampaignGroupStatus(int campaignGroupStatus) {
		this.campaignGroupStatus = campaignGroupStatus;
	}

	public int getCampaignGroupSpendCap() {
		return campaignGroupSpendCap;
	}

	public void setCampaignGroupSpendCap(int campaignGroupSpendCap) {
		this.campaignGroupSpendCap = campaignGroupSpendCap;
	}

	public Long getAdAccountId() {
		return adAccountId;
	}

	public void setAdAccountId(Long adAccountId) {
		this.adAccountId = adAccountId;
	}



	/////////////////////////////////////// STEP 2

	public String[] getCampaignName() {
		return campaignName;
	}

	public Long[] getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Long[] campaignId) {
		this.campaignId = campaignId;
	}

	public void setCampaignName(String[] campaignName) {
		this.campaignName = campaignName;
	}

	public String[] getCampaignBidType() {
		return campaignBidType;
	}

	public void setCampaignBidType(String[] campaignBidType) {
		this.campaignBidType = campaignBidType;
	}

	public int[] getCampaignBudget() {
		return campaignBudget;
	}

	public void setCampaignBudget(int[] campaignBudget) {
		this.campaignBudget = campaignBudget;
	}

	public String[] getCampaignBudgetType() {
		return campaignBudgetType;
	}

	public void setCampaignBudgetType(String[] campaignBudgetType) {
		this.campaignBudgetType = campaignBudgetType;
	}

	public String[] getCampaignTarget() {
		return campaignTarget;
	}

	public void setCampaignTarget(String[] campaignTarget) {
		this.campaignTarget = campaignTarget;
	}

	public Date[] getCampaignStartTime() {
		return campaignStartTime;
	}

	public void setCampaignStartTime(Date[] campaignStartTime) {
		this.campaignStartTime = campaignStartTime;
	}

	public Date[] getCampaignEndTime() {
		return campaignEndTime;
	}

	public void setCampaignEndTime(Date[] campaignEndTime) {
		this.campaignEndTime = campaignEndTime;
	}


	/////////////////////////////////////// STEP 3

	public Long[] getCreativeId() {
		return creativeId;
	}

	public void setCreativeId(Long[] creativeId) {
		this.creativeId = creativeId;
	}

	public String[] getCreativeBody() {
		return creativeBody;
	}

	public void setCreativeBody(String[] creativeBody) {
		this.creativeBody = creativeBody;
	}

	public int[] getCreativeCallToActionType() {
		return creativeCallToActionType;
	}

	public void setCreativeCallToActionType(int[] creativeCallToActionType) {
		this.creativeCallToActionType = creativeCallToActionType;
	}

	public String[] getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String[] imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String[] getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String[] linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String[] getCreativeName() {
		return creativeName;
	}

	public void setCreativeName(String[] creativeName) {
		this.creativeName = creativeName;
	}

	public String[] getLinkTitle() {
		return linkTitle;
	}

	public void setLinkTitle(String[] linkTitle) {
		this.linkTitle = linkTitle;
	}

	public String[] getLinkBody() {
		return linkBody;
	}

	public void setLinkBody(String[] linkBody) {
		this.linkBody = linkBody;
	}

	public String[] getLinkCaption() {
		return linkCaption;
	}

	public void setLinkCaption(String[] linkCaption) {
		this.linkCaption = linkCaption;
	}
	

}
