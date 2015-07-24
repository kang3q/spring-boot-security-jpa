package com.wisebirds.sap.domain.ad;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wisebirds.sap.Application;
import com.wisebirds.sap.domain.ad.AdCampaign;


@Entity
@Table(name="ad_campaign_group")
public class AdCampaignGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "objective", nullable = false)
	private int objective;
	
	@Column(name = "ad_account_id", nullable = false)
	private Long adAccountId;
	
	@Column(name = "create_time", nullable = false)
	private Date createTime;
	
	@Column(name = "update_time", nullable = true)
	private Date updateTime;
	
	@Column(name = "campaign_group_status", nullable = false)
	private int campaignGroupStatus;
	
	@Column(name="spend_cap", nullable = false)
	private int spendCap;
	
	@Column(name="user_account_id", nullable=false)
	private Long userAccountId;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="campaign_group_id")
	private Collection<AdCampaign> campaigns;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
	

	public int getObjective() {
		return objective;
	}

	public String getObjectiveText() {
		if(objective == CAMPAIGN_GROUP_TYPE_WEBSITE_CLICKS){
			return "WEBSITE CLICKS";			
		}else if(objective == CAMPAIGN_GROUP_TYPE_WEBSITE_LINK_CLICKS){
			return "WEBSITE LINK CLICKS";			
		}else if(objective == CAMPAIGN_GROUP_TYPE_MOBILE_APP_INSTALLS){
			return "MOBILE APP INSTALLS";			
		}else if(objective == CAMPAIGN_GROUP_TYPE_MOBILE_APP_ENGAGEMENT){
			return "MOBILE APP ENGAGEMENT";			
		}
		return "";
	}

	public void setObjective(int objective) {
		this.objective = objective;
	}

	public Long getAdAccountId() {
		return adAccountId;
	}

	public void setAdAccountId(Long adAccountId) {
		this.adAccountId = adAccountId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	
	public int getCampaignGroupStatus() {
		return campaignGroupStatus;
	}

	public String getCampaignGroupStatusText() {
		if(campaignGroupStatus == CAMPAIGN_GROUP_STATUS_ACTIVE){
			return "ACTIVED";			
		}else if(campaignGroupStatus == CAMPAIGN_GROUP_STATUS_PAUSED){
			return "PAUSED";			
		}else if(campaignGroupStatus == CAMPAIGN_GROUP_STATUS_ARCHIVED){
			return "ARCHIVED";			
		}
		return "";
	}

	public void setCampaignGroupStatus(int campaignGroupStatus) {
		this.campaignGroupStatus = campaignGroupStatus;
	}
	
	public int getSpendCap() {
		return spendCap;
	}

	public void setSpendCap(int spendCap) {
		this.spendCap = spendCap;
	}

	public Collection<AdCampaign> getCampaigns() {
		return campaigns;
	}

	public void setCampaigns(Collection<AdCampaign> campaigns) {
		this.campaigns = campaigns;
	}
	
	public void addCampaigns(AdCampaign c){
		if(campaigns == null){
			campaigns = new ArrayList<AdCampaign>();
		}
		campaigns.add(c);
	}
	
	public Long getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Long userAccountId) {
		this.userAccountId = userAccountId;
	}




	public static final int CAMPAIGN_GROUP_STATUS_ACTIVE = 0;
	public static final int CAMPAIGN_GROUP_STATUS_PAUSED = 1;
	public static final int CAMPAIGN_GROUP_STATUS_ARCHIVED = 2;
	public static final int CAMPAIGN_GROUP_TYPE_WEBSITE_CLICKS = 1;
	public static final int CAMPAIGN_GROUP_TYPE_WEBSITE_LINK_CLICKS = 2;
	public static final int CAMPAIGN_GROUP_TYPE_MOBILE_APP_INSTALLS = 10;
	public static final int CAMPAIGN_GROUP_TYPE_MOBILE_APP_ENGAGEMENT = 11;

	public static int getObjectId() {
		return OBJECT_ID;
	}

	public static final int OBJECT_ID = Application.OBJECT_AD_CAMPAIGN_GROUP;
}
