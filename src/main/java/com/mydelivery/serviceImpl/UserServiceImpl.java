package com.mydelivery.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mydelivery.Enums.STATUS;
import com.mydelivery.dao.UserDao;
import com.mydelivery.exception.GenericException;
import com.mydelivery.logging.IMessage;
import com.mydelivery.logging.MessageLog;
import com.mydelivery.models.User;
import com.mydelivery.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final MessageLog logger = MessageLog.getLoggerInstance();
	private static final String CLASS_NAME = "UserServiceImpl.";

	private UserDao userDao;
	 
	@Override
	public User userLogin(String email, String password) throws GenericException{
		return userDao.validateUser(email, password);
	}

	
	@Transactional(rollbackFor = Throwable.class)
	public void userRegistration(User user) throws GenericException {
		logger.println(IMessage.INFO, new StringBuilder(CLASS_NAME).append("::userRegistration()"));
		User checkUser = userDao.checkUserByEmail(user.getEmail());
		if (checkUser != null) {
			GenericException exception = new GenericException();
			exception.setMessage("User already registered!!");
			throw exception;
		}
		userDao.insertUser(user);
		
	}
}
