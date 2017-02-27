package com.mydelivery.dao;

import com.mydelivery.models.User;

public interface UserDao {

	User validateUser(String email, String password);

	User checkUserByEmail(String email);

	void insertUser(User user);
}
