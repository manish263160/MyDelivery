package com.mydelivery.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mydelivery.logging.IMessage;
import com.mydelivery.logging.MessageLog;
import com.mydelivery.models.User;
import com.mydelivery.service.UserService;

@Controller
@RequestMapping("/user")
@SessionAttributes("userdata")
public class UserController {

	
	private static final MessageLog logger = MessageLog.getLoggerInstance();
	private static final String CLASS_NAME = "UserController.";
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/registration",method=RequestMethod.GET)
	public String register(@ModelAttribute User user,ModelMap map,HttpServletRequest request){
		logger.println(IMessage.DEBUG, new StringBuilder(CLASS_NAME).append("login"));
		return "";
	}
	
}
