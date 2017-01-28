package com.mydelivery.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.mydelivery.logging.IMessage;
import com.mydelivery.logging.MessageLog;

/**
 * This class is responsible for handling login failure
 * 
 * @author manishm
 *
 */
@Component("p2pAuthenticationFailureHandler")
public class P2PAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private static final MessageLog LOGGER = MessageLog.getLoggerInstance();
	private static final String CLASS_NAME = "P2PAuthenticationFailureHandler";

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		handle(request, response, exception);
	}

	protected void handle(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
		LOGGER.println(IMessage.INFO, CLASS_NAME+"handle(): Start" + request.getParameter("targetUrl") + "," + request.getHeader("referer"));
		String referer = request.getHeader("referer");
		if (!StringUtils.isBlank(referer)) {
			referer = referer.replaceAll("[&?]error.*?(?=&|\\?|$)", "");
			if (referer.contains("?"))
				redirectStrategy.sendRedirect(request, response, referer + "&error=" + exception.getMessage());
			else 
				redirectStrategy.sendRedirect(request, response, referer + "?error=" + exception.getMessage());
		} else {
			redirectStrategy.sendRedirect(request, response, "/home.htm" + "?error=" + exception.getMessage());
		}
	}
}
