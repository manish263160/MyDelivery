package com.mydelivery.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mydelivery.Enums.STATUS;
import com.mydelivery.dao.UserDao;
import com.mydelivery.logging.IMessage;
import com.mydelivery.logging.MessageLog;
import com.mydelivery.models.User;

@Repository
public class UserDaoImpl extends com.mydelivery.support.JdbcDaoSupport implements UserDao {
	
	private static final MessageLog logger = MessageLog.getLoggerInstance();
	private static final String CLASS_NAME = "UserDaoImpl.";
	
	private static final String GET_USER = "select u.*,uad.* from user u left outer join user_address uad on u.user_id=uad.user_id "
											+ " where u.email=? and u.password=?";
	
	public User validateUser(final String emailId, final String password) {
		logger.println(IMessage.DEBUG, new StringBuilder().append(CLASS_NAME).append("validateUser() email: ").append(emailId));
		User user = null;
		try {
			user = getJdbcTemplate().queryForObject(GET_USER, new ValidatedUserRowMapper(), emailId, password);
		} catch (EmptyResultDataAccessException e) {
			logger.println(IMessage.ERROR, new StringBuilder().append(CLASS_NAME).append(" validateUser() EmptyResultDataAccessException"));
		} catch (DataAccessException e) {
			logger.println(IMessage.ERROR, new StringBuilder().append(CLASS_NAME).append(" validateUser() DataAccessException"));
		}
		return user;
	}

	
	private class ValidatedUserRowMapper implements RowMapper<User>{
		public User mapRow(ResultSet rs, int rowNum)throws SQLException{
			User user = new User();
			user.setUserId(rs.getLong("user_id"));
			user.setUserType(rs.getLong("user_type"));
			user.setEmail(rs.getString("email"));
			user.setStatus(rs.getInt("status"));
			user.setUserImage(rs.getString("user_image"));
			user.setName(rs.getString("name"));
			user.setGender(rs.getString("gender"));
			user.setMobileNo(rs.getString("mobile_no"));
			return user ;
		}
		
	}


	public User checkUserByEmail(String email) {
		logger.println(IMessage.INFO, new StringBuilder(CLASS_NAME).append("::checkUserByEmail()"));
		User user = null;
		final String query="select * from user where email=?";
		try {
			user = getJdbcTemplate().queryForObject(query, new BeanPropertyRowMapper<User>(User.class), email);
		} catch (EmptyResultDataAccessException e) {
			logger.println(IMessage.ERROR, new StringBuilder().append(CLASS_NAME).append(" checkUserByEmail() EmptyResultDataAccessException"));
		} catch (DataAccessException e) {
			logger.println(IMessage.ERROR, new StringBuilder().append(CLASS_NAME).append(" checkUserByEmail() DataAccessException"));
		}
		return user;
	}


	public void insertUser(User user) {

		logger.println(IMessage.INFO, new StringBuilder(CLASS_NAME).append("::insertUser()"));
		final String query="INSERT INTO mydelivery.user(user_type,email,mobile_no,password,name,gender,status,created_on)VALUES(?,?,?,?,?,?,?,now());";
		KeyHolder keyHolder=new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con)throws SQLException {
				PreparedStatement ps=con.prepareStatement(query,new String[]{"user_id"});
				int i = 1;
				ps.setLong(i++, user.getUserType());
				ps.setString(i++, user.getEmail());
				ps.setString(i++, user.getMobileNo());
				
				ps.setString(i++, user.getPassword());
				ps.setString(i++,user.getName());
				ps.setString(i++,user.getGender());
				ps.setInt(i++, STATUS.INACTIVE.ID);
				
				return ps;
			}
		},keyHolder);
		user.setUserId(keyHolder.getKey().longValue());
	
		
	}
	
	
	
	
}
