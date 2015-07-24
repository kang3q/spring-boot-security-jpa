package com.wisebirds.sap.domain.ad;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.wisebirds.sap.domain.ad.AdCreative;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@Table(name="ad_item")
public class AdCampaignItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "ad_account_id", nullable = false)
	private Long adAccountId;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "ad_status", nullable = false)
	private int adStatus;
	
	@Column(name = "create_time", nullable = false)
	private Date createTime;
	
	@Column(name = "update_time", nullable = true)
	private Date updateTime;
//	
//	@Column(name = "creative", nullable = false)
//	private Long creative;
	
	@Column(name = "campaign_id", nullable = false)
	private Long campaignId;
	
	@Column(name = "campaign_group_id", nullable = false)
	private Long campaignGroupId;

	@Column(name="user_account_id", nullable=false)
	private Long userAccountId;
	
	@ManyToOne(targetEntity=AdCreative.class, fetch=FetchType.LAZY, cascade=CascadeType.DETACH)
	@JoinColumn(name="creative_id")
	private AdCreative adCreative;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAdAccountId() {
		return adAccountId;
	}

	public void setAdAccountId(Long adAccountId) {
		this.adAccountId = adAccountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public int getAdStatus() {
		return adStatus;
	}

	public String getAdStatusText() {
		if(adStatus == CAMPAIGN_AD_STATUS_ACTIVE){
			return "ACTIVED";			
		}else if(adStatus == CAMPAIGN_AD_STATUS_PAUSED){
			return "PAUSED";			
		}else if(adStatus == CAMPAIGN_AD_STATUS_ARCHIVED){
			return "ARCHIVED";			
		}else if(adStatus == CAMPAIGN_AD_STATUS_STATUS_PENDING){
			return "PENDING";			
		}
		return "";
	}

	public void setAdStatus(int adStatus) {
		this.adStatus = adStatus;
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

//	public Long getCreative() {
//		return creative;
//	}
//
//	public void setCreative(Long creative) {
//		this.creative = creative;
//	}

	public Long getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}

	public Long getCampaignGroupId() {
		return campaignGroupId;
	}

	public void setCampaignGroupId(Long campaignGroupId) {
		this.campaignGroupId = campaignGroupId;
	}
	
	public AdCreative getAdCreative() {
		return adCreative;
	}

	public void setAdCreative(AdCreative adCreative) {
		this.adCreative = adCreative;
	}

	public Long getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Long userAccountId) {
		this.userAccountId = userAccountId;
	}



	public static final int CAMPAIGN_AD_STATUS_ACTIVE = 0;
	public static final int CAMPAIGN_AD_STATUS_PAUSED = 1;
	public static final int CAMPAIGN_AD_STATUS_ARCHIVED = 2;
	public static final int CAMPAIGN_AD_STATUS_STATUS_PENDING = 10;
}
	
