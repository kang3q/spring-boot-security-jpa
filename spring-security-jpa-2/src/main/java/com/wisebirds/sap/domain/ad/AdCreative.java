package com.wisebirds.sap.domain.ad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wisebirds.sap.Application;

@Entity
@Table(name = "ad_creative")
public class AdCreative {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	// actor_id // The Facebook object ID that is the actor for a link ad (not connected to a Page)
	@Column(name = "body", length = 512, nullable = false)
	private String body;
	@Column(name = "call_to_action_type", nullable = false)
	private int callToActionType; // open link, book travel, shop now, install now 등
	// follow_redirect
	// image_crops
	// image_file
	// image_hash
	@Column(name = "image_url", length = 254)
	private String imageUrl;
	@Column(name = "link_url", length = 254, nullable = false)
	private String linkUrl;
	@Column(name = "name", length = 128, nullable = false)
	private String name;
	// object_id
	// object_story_id
	// object_story_spec
	// object_url
	// 오브젝트 분할 전까지 사용 //////////////
	@Column(name = "link_title", length = 128)
	private String linkTitle;
	@Column(name = "link_body", length = 254)
	private String linkBody;
	@Column(name = "link_caption", length = 254)
	private String linkCaption;
	///////////////////////////////////
	@Column(name = "run_status", nullable = false)
	private int runStatus;
	// private String title;
	// private String url_tags;
	@Column(name = "ad_account_id", nullable = false, updatable = false)
	private Long adAccountId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getCallToActionType() {
		return callToActionType;
	}

	public void setCallToActionType(int callToActionType) {
		this.callToActionType = callToActionType;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLinkTitle() {
		return linkTitle;
	}

	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}

	public String getLinkBody() {
		return linkBody;
	}

	public void setLinkBody(String linkBody) {
		this.linkBody = linkBody;
	}

	public String getLinkCaption() {
		return linkCaption;
	}

	public void setLinkCaption(String linkCaption) {
		this.linkCaption = linkCaption;
	}

	public int getRunStatus() {
		return runStatus;
	}

	public void setRunStatus(int runStatus) {
		this.runStatus = runStatus;
	}

	public Long getAdAccountId() {
		return adAccountId;
	}

	public void setAdAccountId(Long adAccountId) {
		this.adAccountId = adAccountId;
	}

	// ///////
	public static int getObjectId() {
		return OBJECT_ID;
	}

	public static final int OBJECT_ID = Application.OBJECT_AD_CREATIVE;

	public static final int RUN_STATUS_ACTIVE = 0;
	public static final int RUN_STATUS_PAUSED = 1;
	public static final int RUN_STATUS_DELETED = 2;
	public static final int RUN_STATUS_PENDING = 10;
	public static final int RUN_STATUS_PENDING_REVIVEW = 11;
	public static final int RUN_STATUS_DISAPPROVED = 12;
	public static final int RUN_STATUS_PREAPPROVED = 13;
	public static final int RUN_STATUS_PENDING_BILLING_INFO = 14;
	public static final int RUN_STATUS_CAMPAIGN_PAUSED = 0;
	public static final int RUN_STATUS_ADGROUP_PAUSED = 0;
	public static final int RUN_STATUS_CAMPAIGN_GROUP_PAUSED = 0;
	public static final int RUN_STATUS_ARCHIVED = 0;


	public static final int CALL_TO_ACTION_TYPE_OPEN_LINK = 0;
	public static final int CALL_TO_ACTION_TYPE_BOOK_TRABEL = 1;
	public static final int CALL_TO_ACTION_TYPE_SHOP_NOW = 2;
	public static final int CALL_TO_ACTION_TYPE_PLAY_GAME = 3;
	public static final int CALL_TO_ACTION_TYPE_LISTEN_MUSIC = 4;
	public static final int CALL_TO_ACTION_TYPE_WATCH_VIDEO = 5;
	public static final int CALL_TO_ACTION_TYPE_USE_APP = 6;
}
