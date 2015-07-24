package com.wisebirds.sap.dao.account;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import com.wisebirds.sap.dao.AbstractPostgresQLDAO;
import com.wisebirds.sap.domain.account.Account;

@Component
public class AccountDao extends AbstractPostgresQLDAO {

	public int updateAccount(long id, long agencyId, String password, String name, String role, String accessToken, String phone, String zipcode, String address, String locale) {
		return pgJdbcTemplate.update("UPDATE account SET agency_id = ?, password = ?, name = ?, role = ?, access_token = ?, phone = ?, zipcode = ?, address = ?, locale = ?, update_time = NOW() where id = ?", //
				new Object[] {agencyId, password, name, role, accessToken, phone, zipcode, address, locale, id});
	}

	public int updateAccountByIdForDelete(long id) {
		return pgJdbcTemplate.update("UPDATE account SET deleted = true, update_time = NOW() WHERE id = ?", //
				new Object[] {id});
	}

	public void updateAccessTokenOfAccount(Long id, String accessToken) {
		pgJdbcTemplate.update("UPDATE account SET access_token = ? WHERE id = ?", //
				new Object[] {accessToken, id});
	}

	public List<Account> selectAccountListByIdNot(long id) {
		return pgJdbcTemplate.query("SELECT id, agency_id, name, email, role, create_time FROM account WHERE deleted != true AND id != ? ORDER BY id", //
				new BeanPropertyRowMapper<Account>(Account.class), //
				new Object[] {id});
	}

	public List<Account> selectClientListOfUser(long accountId) {
		return pgJdbcTemplate.query("SELECT id, agency_id, name, email, role, create_time FROM account WHERE deleted != true AND agency_id = ? ORDER BY id", //
				new BeanPropertyRowMapper<Account>(Account.class), //
				new Object[] {accountId});
	}
}
