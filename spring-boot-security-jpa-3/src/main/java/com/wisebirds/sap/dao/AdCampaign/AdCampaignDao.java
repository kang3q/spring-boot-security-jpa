package com.wisebirds.sap.dao.AdCampaign;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import com.wisebirds.sap.dao.AbstractPostgresQLDAO;
import com.wisebirds.sap.domain.ad.AdCampaignGroup;

@Component
public class AdCampaignDao extends AbstractPostgresQLDAO{
	public AdCampaignGroup selectCampaignGroupById(Long campaignGroupId){
		return pgJdbcTemplate.queryForObject("SELECT * FROM ad_campaign_group WHERE id=?", new BeanPropertyRowMapper<AdCampaignGroup>(AdCampaignGroup.class), new Object[] {campaignGroupId});
	}
	public int insertCampaignGroup(AdCampaignGroup cg){
		 return pgJdbcTemplate.update("INSERT INTO ad_campaign_group(id, name, objective, ad_account_id, create_time) "
		 		+ "VALUES(?, ?, ?, ?, NOW())", new Object[] {cg.getId(), cg.getName(), cg.getObjective(), cg.getAdAccountId()});
	}
	public int updateCampaignGroup(AdCampaignGroup cg){
		return pgJdbcTemplate.update("UPDATE ad_campaign_group SET name=?, update_time=NOW(), campaign_group_status=?, spend_cap=? WHERE id=?", new Object[]{cg.getName(), cg.getCampaignGroupStatus(), cg.getSpendCap(), cg.getId()});
	}

	/**
	 * @param accountId
	 * @param pAccountId
	 * @return accountId에 물려있는 pAccountId가 있다면 1 없으면 0
	 */
	public int checkAccountIdForCampaignList(long accountId, long pAccountId) {
		String q = "select count(a.id) ";
		q += "		from ";
		q += "			(select id from account where agency_id = ? OR id = ?) a "; //로그인계정id
		q += "		where a.id = ? "; //파라미터id
		return pgJdbcTemplate.queryForObject(q, Integer.class, new Object[] {accountId, accountId, pAccountId});
	}
	
}
