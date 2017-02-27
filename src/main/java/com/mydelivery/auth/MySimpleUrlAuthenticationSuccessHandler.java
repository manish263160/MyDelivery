package com.mydelivery.auth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.mydelivery.logging.IMessage;
import com.mydelivery.logging.MessageLog;
import com.mydelivery.models.User;
import com.mydelivery.service.UserService;
import com.mydelivery.utils.GenUtilitis;

@Component("myAuthenticationSuccessHandler")
public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	private static final MessageLog LOGGER = MessageLog.getLoggerInstance();
	private static final String CLASS_NAME= "MySimpleUrlAuthenticationSuccessHandler.";
	 
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//    @Autowired SocialLoginService socialloginservice;
    @Autowired UserService userservice;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }
 
    protected void handle(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(request,authentication);
 
        if (response.isCommitted()) {
        	LOGGER.println(IMessage.INFO, CLASS_NAME + "handle():: Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
        Map<String, List<String>> params = getQueryParams(targetUrl);
        LOGGER.println(IMessage.INFO, "param::::::::::::::::::"+params);
        
        response.sendRedirect(targetUrl);
        redirectStrategy.sendRedirect(request, response, targetUrl);
        
        
    }
 
    /** Builds the target URL according to the logic defined in the main class Javadoc. */
    protected String determineTargetUrl(HttpServletRequest request, Authentication authentication) {
       boolean isUser = false;
        boolean isAdmin = false;
        LOGGER.println(IMessage.INFO, "inside the target URL1"+request.getParameter("targetUrl")+","+ request.getHeader("referer"));
        String targetUrl = request.getParameter("targetUrl");
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                isUser = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
                break;
            }
        
        
        if(targetUrl!=null && targetUrl.equalsIgnoreCase("default")){
        	return request.getHeader("referer");
        }
        else if(targetUrl!=null && targetUrl.length()>0 ){
        	return targetUrl;
        }
        else {
        	User user=GenUtilitis.getLoggedInUser();
//      
        }
        }
        return "/dashboard.htm";
        
    }
 
    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
 
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    public static Map<String, List<String>> getQueryParams(String url) {
        try {
            Map<String, List<String>> params = new HashMap<String, List<String>>();
            String[] urlParts = url.split("\\?");
            if (urlParts.length > 1) {
                String query = urlParts[1];
                for (String param : query.split("&")) {
                    String[] pair = param.split("=");
                    String key = URLDecoder.decode(pair[0], "UTF-8");
                    String value = "";
                    if (pair.length > 1) {
                        value = URLDecoder.decode(pair[1], "UTF-8");
                    }

                    List<String> values = params.get(key);
                    if (values == null) {
                        values = new ArrayList<String>();
                        params.put(key, values);
                    }
                    values.add(value);
                }
            }

            return params;
        } catch (UnsupportedEncodingException ex) {
            throw new AssertionError(ex);
        }
    }


}
