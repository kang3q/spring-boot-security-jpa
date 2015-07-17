package com.wisebirds.sap.dao.account;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import com.wisebirds.sap.dao.AbstractPostgresQLDAO;
import com.wisebirds.sap.domain.account.Account;

@Component
public class AccountDao extends AbstractPostgresQLDAO {
	// public int insertCampaignGroup(CampaignGroup cg){
	// return pgJdbcTemplate.update("INSERT INTO ad_campaign_group(id, name, objective, ad_account_id, create_date) "
	// + "VALUES(?, ?, ?, ?, NOW())", new Object[] {cg.getId(), cg.getName(), cg.getObjective(), cg.getAdAccountId()});
	// }

	public List<Account> selectAccountListByIdNot(long id) {
		return pgJdbcTemplate.query("SELECT id, agency_id, name, email, role, create_time FROM account WHERE deleted != true AND id != ? ORDER BY id", //
				new BeanPropertyRowMapper<Account>(Account.class), //
				new Object[] {id});
	}

	public int updateAccount(long id, long agencyId, String password, String name, String role, String accessToken, String phone, String zipcode, String address, String locale) {
		return pgJdbcTemplate.update("UPDATE account SET agencyId = ?, password = ?, name = ?, role = ?, access_token = ?, phone = ?, zipcode = ?, address = ?, locale = ?, update_time = NOW() where id = ?", //
				new Object[] {agencyId, password, name, role, accessToken, phone, zipcode, address, locale, id});
	}

	public int updateAccountByIdForDelete(long id) {
		return pgJdbcTemplate.update("UPDATE account SET deleted = true, update_time = NOW() WHERE id = ?", //
				new Object[] {id});
	}
}
