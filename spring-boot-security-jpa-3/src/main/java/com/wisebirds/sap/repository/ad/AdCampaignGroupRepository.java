package com.wisebirds.sap.repository.ad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisebirds.sap.domain.ad.AdCampaignGroup;



public interface AdCampaignGroupRepository extends JpaRepository<AdCampaignGroup, Long>{

	List<AdCampaignGroup> findAllByCampaignGroupStatusNotOrderByCreateTimeAsc(int status);
	
	List<AdCampaignGroup> findAllByCampaignGroupStatusNotAndAdAccountIdOrderByCreateTimeAsc(int status, long accountId);
	
	List<AdCampaignGroup> findAllByCampaignGroupStatusNotAndAdAccountIdAndUserAccountIdOrderByCreateTimeAsc(int status, long accountId, long UserAccountId);
	
	List<AdCampaignGroup> findAllByCampaignGroupStatusNotAndUserAccountIdOrderByCreateTimeAsc(int status, long accountId);
	
List<AdCampaignGroup> findAllByCampaignGroupStatusOrderByCreateTimeAsc(int status);
	
	List<AdCampaignGroup> findAllByCampaignGroupStatusAndAdAccountIdOrderByCreateTimeAsc(int status, long accountId);
	
	List<AdCampaignGroup> findAllByCampaignGroupStatusAndAdAccountIdAndUserAccountIdOrderByCreateTimeAsc(int status, long accountId, long UserAccountId);
	
	List<AdCampaignGroup> findAllByCampaignGroupStatusAndUserAccountIdOrderByCreateTimeAsc(int status, long accountId);
}
