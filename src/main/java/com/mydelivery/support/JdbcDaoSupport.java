package com.mydelivery.support;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.mydelivery.logging.MessageLog;

/**
 * 
 * @author mukeshks
 * 
 * Wrapper class for DataSource
 *
 */
public abstract class JdbcDaoSupport {
	
	protected static final MessageLog logger = MessageLog.getLoggerInstance();
	
	@Autowired private DataSource dataSource;

	/**
	 * @return
	 */
	protected JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}

	/**
	 * @return
	 */
	protected NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource);
	}

	
	
}
