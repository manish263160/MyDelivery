package com.mydelivery.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.mydelivery.Enums.STATUS;
import com.mydelivery.logging.IMessage;
import com.mydelivery.logging.MessageLog;
import com.mydelivery.models.User;
import com.mydelivery.service.UserService;

/**
 * @author mukeshks
 * 
 * This Class is responsible for authentication and 
 * access control of users to cube root Admin module over http in extension of AuthenticationProvider interface of Spring web framework .   

 *
 */
@Component("p2pCustomAuthenticationProvider")
public class P2PAuthenticationProvider implements AuthenticationProvider {

	private static MessageLog logger = MessageLog.getLoggerInstance();
	
	@Autowired UserService userService;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
	 */
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			logger.println(IMessage.DEBUG, "P2PAuthenticationProvider.authenticate() authentication.getPrincipal(): " + authentication.getPrincipal());
			logger.println(IMessage.DEBUG, "P2PAuthenticationProvider.authenticate() authentication.getCredentials(): " + authentication.getCredentials());
			
			String userName = authentication.getPrincipal().toString();
			String password = authentication.getCredentials().toString();
			
			User user = userService.userLogin(userName, password);

			if (user == null) {
				throw new UsernameNotFoundException(String.format("Invalid Email/password", authentication.getPrincipal()));
			}
			
			if (STATUS.INACTIVE.ID == user.getUserType()) {
				throw new UsernameNotFoundException(String.format("You are not active", authentication.getPrincipal()));
			}
			
			if (STATUS.BLOCK.ID == user.getStatus()) {
				throw new UsernameNotFoundException(String.format("You are blocked. Please contact admin", authentication.getPrincipal()));
			}
			
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, null, null);
			return token;
		} catch (Exception e) {
			logger.println(IMessage.ERROR, "Error in P2PAuthenticationProvider.authenticate()", e);
			throw new AuthenticationServiceException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#supports(java.lang.Class)
	 */
	public boolean supports(Class<?> clazz) {
		return clazz.equals(UsernamePasswordAuthenticationToken.class);
	}
	
}