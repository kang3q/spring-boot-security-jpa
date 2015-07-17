package com.wisebirds.sap.domain.ad.campaign;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
@Table(name="ad_campaign_group")
public class CampaignGroup {
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
	
	@Column(name = "create_date", nullable = false)
	private Date createDate;
	
	@Column(name = "update_date", nullable = true)
	private Date updateDate;
	
	@Column(name = "campaign_group_status", nullable = false)
	private int campaignGroupStatus;
	
	@Column(name="spend_cap", nullable = false)
	private int spendCap;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="campaign_group_id")
	private Collection<Campaign> campaigns;
	
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

	public void setObjective(int objective) {
		this.objective = objective;
	}

	public Long getAdAccountId() {
		return adAccountId;
	}

	public void setAdAccountId(Long adAccountId) {
		this.adAccountId = adAccountId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getCampaignGroupStatus() {
		return campaignGroupStatus;
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

	public Collection<Campaign> getCampaigns() {
		return campaigns;
	}

	public void setCampaigns(Collection<Campaign> campaigns) {
		this.campaigns = campaigns;
	}
	
	public void addCampaigns(Campaign c){
		if(campaigns == null){
			campaigns = new ArrayList<Campaign>();
		}
		campaigns.add(c);
	}




	public static final int CAMPAIGN_GROUP_STATUS_ACTIVE = 0;
	public static final int CAMPAIGN_GROUP_STATUS_PAUSED = 1;
	public static final int CAMPAIGN_GROUP_STATUS_DELETED = 2;
	public static final int CAMPAIGN_GROUP_TYPE_WEBSITE_CLICKS = 1;
	public static final int CAMPAIGN_GROUP_TYPE_MOBILE_APP_INSTALLS = 2;
	public static final int CAMPAIGN_GROUP_TYPE_MOBILE_APP_ENGAGEMENT = 3;
}
