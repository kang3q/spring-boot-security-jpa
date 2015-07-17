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
@Table(name = "ad_campaign")
public class Campaign {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "ad_account_id", nullable = false)
	private Long adAccountId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "bid_type", nullable = false)
	private String bidType;

	@Column(name = "campaign_Status", nullable = false)
	private int campaignStatus;

	@Column(name = "start_time", nullable = false)
	private Date startTime;

	@Column(name = "end_time", nullable = true)
	private Date endTime;

	@Column(name = "create_time", nullable = false)
	private Date createTime;

	@Column(name = "update_time", nullable = false)
	private Date updateTime;

	@Column(name = "budget", nullable = false)
	private int budget;

	@Column(name = "budget_type", nullable = false)
	private String budgetType;

	@Column(name = "campaign_group_id", nullable = false)
	private Long campaignGroupId;

	@Column(name = "targeting", nullable = true)
	private String targeting;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="campaign_id")
	private Collection<CampaignAd> campaignAds;

	public Campaign() {}

	public Campaign(Long campaignGroupId, Long adAccountId) {
		this.campaignGroupId = campaignGroupId;
		this.adAccountId = adAccountId;
	}

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

	public String getBidType() {
		return bidType;
	}

	public void setBidType(String bidType) {
		this.bidType = bidType;
	}

	public int getCampaignStatus() {
		return campaignStatus;
	}

	public void setCampaignStatus(int campaignStatus) {
		this.campaignStatus = campaignStatus;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public String getBudgetType() {
		return budgetType;
	}

	public void setBudgetType(String budgetType) {
		this.budgetType = budgetType;
	}

	public Long getCampaignGroupId() {
		return campaignGroupId;
	}

	public void setCampaignGroupId(Long campaignGroupId) {
		this.campaignGroupId = campaignGroupId;
	}

	public String getTargeting() {
		return targeting;
	}

	public void setTargeting(String targeting) {
		this.targeting = targeting;
	}
	
	public Collection<CampaignAd> getCampaignAds() {
		return campaignAds;
	}

	public void setCampaignAds(Collection<CampaignAd> campaignAds) {
		this.campaignAds = campaignAds;
	}
	
	public void addCampaingAd(CampaignAd ca){
		if(campaignAds == null){
			campaignAds = new ArrayList<CampaignAd>();
		}
		campaignAds.add(ca);
	}



	public static final int CAMPAIGN_STATUS_ACTIVE = 0;
	public static final int CAMPAIGN_STATUS_PAUSED = 1;
	public static final int CAMPAIGN_STATUS_DELETED = 2;

}
