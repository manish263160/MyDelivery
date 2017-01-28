package com.mydelivery.dao;

import com.mydelivery.models.User;

public interface UserDao {

	User userLogin(String email, String password);
}
