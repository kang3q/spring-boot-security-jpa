package com.wisebirds.sap.dao.AdCampaign;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import com.wisebirds.sap.dao.AbstractPostgresQLDAO;
import com.wisebirds.sap.domain.ad.campaign.CampaignGroup;

@Component
public class AdCampaignDao extends AbstractPostgresQLDAO{
	public CampaignGroup selectCampaignGroupById(Long campaignGroupId){
		return pgJdbcTemplate.queryForObject("SELECT * FROM ad_campaign_group WHERE id=?", new BeanPropertyRowMapper<CampaignGroup>(CampaignGroup.class), new Object[] {campaignGroupId});
	}
	public int insertCampaignGroup(CampaignGroup cg){
		 return pgJdbcTemplate.update("INSERT INTO ad_campaign_group(id, name, objective, ad_account_id, create_date) "
		 		+ "VALUES(?, ?, ?, ?, NOW())", new Object[] {cg.getId(), cg.getName(), cg.getObjective(), cg.getAdAccountId()});
	}
	public int updateCampaignGroup(CampaignGroup cg){
		return pgJdbcTemplate.update("UPDATE ad_campaign_group SET name=?, update_date=NOW(), campaign_group_status=? WHERE id=?", new Object[]{cg.getName(), cg.getCampaignGroupStatus(), cg.getId()});
	}
}
