package com.mydelivery.serviceImpl;

import org.springframework.stereotype.Service;

import com.mydelivery.dao.UserDao;
import com.mydelivery.models.User;
import com.mydelivery.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserDao userdao;
	 
	@Override
	public User userLogin(String email, String password) {
		// TODO Auto-generated method stub
		return userdao.userLogin(email, password);
	}

}
