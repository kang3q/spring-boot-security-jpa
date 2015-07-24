package com.wisebirds.sap.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractPostgresQLDAO {
	@Autowired
	protected JdbcTemplate pgJdbcTemplate;

	@Autowired
	protected MessageSource messageSource;
}
