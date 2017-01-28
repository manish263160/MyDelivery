package com.mydelivery.service;

import com.mydelivery.models.User;

public interface UserService {
	
	User userLogin(String email,String password);

}
