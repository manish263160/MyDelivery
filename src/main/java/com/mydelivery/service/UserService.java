package com.mydelivery.service;

import com.mydelivery.exception.GenericException;
import com.mydelivery.models.User;

public interface UserService {
	
	User userLogin(String email,String password) throws GenericException;
	
	void userRegistration(User user) throws GenericException;

}
