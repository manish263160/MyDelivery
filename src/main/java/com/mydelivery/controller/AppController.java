package com.mydelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mydelivery.logging.IMessage;
import com.mydelivery.logging.MessageLog;
import com.mydelivery.service.UserService;

/**
 * This is the main controller 
 *  @author manishm
 *
 */

@Controller
@RequestMapping("/")
@SessionAttributes("appcontroller")
public class AppController {
	
	private static final MessageLog logger = MessageLog.getLoggerInstance();
	private static final String CLASS_NAME = "AppController.";
	
	@Autowired
	UserService userService;
	
	/*@ModelAttribute("myRequestObject")
	public void addStuffToRequestScope() {
		System.out.println("Inside of addStuffToRequestScope");
	}*/
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		logger.println(IMessage.DEBUG, new StringBuilder(CLASS_NAME).append("login"));
		return "login";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String registeruser(){
		logger.println(IMessage.DEBUG, new StringBuilder(CLASS_NAME).append("login"));
		return "user/registrationpage";
	}

}
