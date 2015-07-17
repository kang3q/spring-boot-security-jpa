package com.wisebirds.sap.domain.stats;

import java.util.List;
import java.util.Map;

/**
 * 광고 통계정보 모델 클래스
 * 
 * @author Jongmin
 */
public class AdInsight {
	// 애드 오브젝트 필드
	private Long campaignGroupId;
	private String campaignGroupName;
	private Long campaignId;
	private String campaignName;
	private Long adId;
	private String adName;
	////////////////////////
	// 통계 날짜 필드 ///////////
	private String dateStart; // "2015-06-06",
	private String dateEnd; // "2015-07-03",
	private String dateStop; // "2015-07-03",
	////////////////////////
	private long impressions;
	private long clicks;
	private long spend;

	private Long uniqueClicks;
	private Long uniqueSocialClicks;
	private Long reach;
	private Long socialImpressions;
	private Long socialClicks;
	private Long socialReach;

	private Double frequency;

	private Double cpm;
	private Double cpc;
	private Double cpp;
	private Double ctr;
	private Double uniqueCtr;

	private List<Map<String, Long>> relevanceScore;

	private Long totalActions;
	private Long totalUniqueActions;
	private List<ActionValue> actions;

	private List<Map<String, Object>> videoAvgPctWatchedActions;
	private List<Map<String, Object>> videoAvgSecWatchedActions;
	private List<Map<String, Object>> videoCompleteWatchedActions;

	private List<Map<String, Object>> videoP25WatchedActions;
	private List<Map<String, Object>> videoP50WatchedActions;
	private List<Map<String, Object>> videoP75WatchedActions;
	private List<Map<String, Object>> videoP95WatchedActions;
	private List<Map<String, Object>> videoP100WatchedActions;
	private List<Map<String, Object>> videoStartActions;

	private Double totalActionValue;
	private List<Map<String, Object>> actionValues;

	private Long costPerUniqueClick;
	private Double costPerTotalAction;
	private List<CostPerActionValue> costPerActionType;

	public Long getCampaignGroupId() {
		return campaignGroupId;
	}

	public void setCampaignGroupId(Long campaignGroupId) {
		this.campaignGroupId = campaignGroupId;
	}

	public String getCampaignGroupName() {
		return campaignGroupName;
	}

	public void setCampaignGroupName(String campaignGroupName) {
		this.campaignGroupName = campaignGroupName;
	}

	public Long getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getDateStop() {
		return dateStop;
	}

	public void setDateStop(String dateStop) {
		this.dateStop = dateStop;
	}

	public long getImpressions() {
		return impressions;
	}

	public void setImpressions(long impressions) {
		this.impressions = impressions;
	}

	public long getClicks() {
		return clicks;
	}

	public void setClicks(long clicks) {
		this.clicks = clicks;
	}

	public long getSpend() {
		return spend;
	}

	public void setSpend(long spend) {
		this.spend = spend;
	}

	public Long getUniqueClicks() {
		return uniqueClicks;
	}

	public void setUniqueClicks(Long uniqueClicks) {
		this.uniqueClicks = uniqueClicks;
	}

	public Long getUniqueSocialClicks() {
		return uniqueSocialClicks;
	}

	public void setUniqueSocialClicks(Long uniqueSocialClicks) {
		this.uniqueSocialClicks = uniqueSocialClicks;
	}

	public Long getReach() {
		return reach;
	}

	public void setReach(Long reach) {
		this.reach = reach;
	}

	public Long getSocialImpressions() {
		return socialImpressions;
	}

	public void setSocialImpressions(Long socialImpressions) {
		this.socialImpressions = socialImpressions;
	}

	public Long getSocialClicks() {
		return socialClicks;
	}

	public void setSocialClicks(Long socialClicks) {
		this.socialClicks = socialClicks;
	}

	public Long getSocialReach() {
		return socialReach;
	}

	public void setSocialReach(Long socialReach) {
		this.socialReach = socialReach;
	}

	public Double getFrequency() {
		return frequency;
	}

	public void setFrequency(Double frequency) {
		this.frequency = frequency;
	}

	public Double getCpm() {
		return cpm;
	}

	public void setCpm(Double cpm) {
		this.cpm = cpm;
	}

	public Double getCpc() {
		return cpc;
	}

	public void setCpc(Double cpc) {
		this.cpc = cpc;
	}

	public Double getCpp() {
		return cpp;
	}

	public void setCpp(Double cpp) {
		this.cpp = cpp;
	}

	public Double getCtr() {
		return ctr;
	}

	public void setCtr(Double ctr) {
		this.ctr = ctr;
	}

	public Double getUniqueCtr() {
		return uniqueCtr;
	}

	public void setUniqueCtr(Double uniqueCtr) {
		this.uniqueCtr = uniqueCtr;
	}

	public List<Map<String, Long>> getRelevanceScore() {
		return relevanceScore;
	}

	public void setRelevanceScore(List<Map<String, Long>> relevanceScore) {
		this.relevanceScore = relevanceScore;
	}

	public Long getTotalActions() {
		return totalActions;
	}

	public void setTotalActions(Long totalActions) {
		this.totalActions = totalActions;
	}

	public Long getTotalUniqueActions() {
		return totalUniqueActions;
	}

	public void setTotalUniqueActions(Long totalUniqueActions) {
		this.totalUniqueActions = totalUniqueActions;
	}

	public List<ActionValue> getActions() {
		return actions;
	}

	public void setActions(List<ActionValue> actions) {
		this.actions = actions;
	}

	public List<Map<String, Object>> getVideoAvgPctWatchedActions() {
		return videoAvgPctWatchedActions;
	}

	public void setVideoAvgPctWatchedActions(List<Map<String, Object>> videoAvgPctWatchedActions) {
		this.videoAvgPctWatchedActions = videoAvgPctWatchedActions;
	}

	public List<Map<String, Object>> getVideoAvgSecWatchedActions() {
		return videoAvgSecWatchedActions;
	}

	public void setVideoAvgSecWatchedActions(List<Map<String, Object>> videoAvgSecWatchedActions) {
		this.videoAvgSecWatchedActions = videoAvgSecWatchedActions;
	}

	public List<Map<String, Object>> getVideoCompleteWatchedActions() {
		return videoCompleteWatchedActions;
	}

	public void setVideoCompleteWatchedActions(List<Map<String, Object>> videoCompleteWatchedActions) {
		this.videoCompleteWatchedActions = videoCompleteWatchedActions;
	}

	public List<Map<String, Object>> getVideoP25WatchedActions() {
		return videoP25WatchedActions;
	}

	public void setVideoP25WatchedActions(List<Map<String, Object>> videoP25WatchedActions) {
		this.videoP25WatchedActions = videoP25WatchedActions;
	}

	public List<Map<String, Object>> getVideoP50WatchedActions() {
		return videoP50WatchedActions;
	}

	public void setVideoP50WatchedActions(List<Map<String, Object>> videoP50WatchedActions) {
		this.videoP50WatchedActions = videoP50WatchedActions;
	}

	public List<Map<String, Object>> getVideoP75WatchedActions() {
		return videoP75WatchedActions;
	}

	public void setVideoP75WatchedActions(List<Map<String, Object>> videoP75WatchedActions) {
		this.videoP75WatchedActions = videoP75WatchedActions;
	}

	public List<Map<String, Object>> getVideoP95WatchedActions() {
		return videoP95WatchedActions;
	}

	public void setVideoP95WatchedActions(List<Map<String, Object>> videoP95WatchedActions) {
		this.videoP95WatchedActions = videoP95WatchedActions;
	}

	public List<Map<String, Object>> getVideoP100WatchedActions() {
		return videoP100WatchedActions;
	}

	public void setVideoP100WatchedActions(List<Map<String, Object>> videoP100WatchedActions) {
		this.videoP100WatchedActions = videoP100WatchedActions;
	}

	public List<Map<String, Object>> getVideoStartActions() {
		return videoStartActions;
	}

	public void setVideoStartActions(List<Map<String, Object>> videoStartActions) {
		this.videoStartActions = videoStartActions;
	}

	public Double getTotalActionValue() {
		return totalActionValue;
	}

	public void setTotalActionValue(Double totalActionValue) {
		this.totalActionValue = totalActionValue;
	}

	public List<Map<String, Object>> getActionValues() {
		return actionValues;
	}

	public void setActionValues(List<Map<String, Object>> actionValues) {
		this.actionValues = actionValues;
	}

	public Long getCostPerUniqueClick() {
		return costPerUniqueClick;
	}

	public void setCostPerUniqueClick(Long costPerUniqueClick) {
		this.costPerUniqueClick = costPerUniqueClick;
	}

	public Double getCostPerTotalAction() {
		return costPerTotalAction;
	}

	public void setCostPerTotalAction(double costPerTotalAction) {
		this.costPerTotalAction = costPerTotalAction;
	}

	public List<CostPerActionValue> getCostPerActionType() {
		return costPerActionType;
	}

	public void setCostPerActionType(List<CostPerActionValue> costPerActionType) {
		this.costPerActionType = costPerActionType;
	}
}
