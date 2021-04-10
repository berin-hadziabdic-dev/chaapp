package com.pixelcatsoftware.auth.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pixelcatsoftware.auth.api_lookup.AuthApi;
import com.pixelcatsoftware.auth.details.UsersDetails;
import com.pixelcatsoftware.auth.dto.UsernamePasswordDto;

import org.apache.catalina.filters.CorsFilter;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.GenericFilterBean;

@Component
@DependsOn("auth-config")
public class CustomLoginFilter extends GenericFilterBean {
    DelegatingFilterProxy dpf;
    FilterChainProxy fcp;
    ObjectMapper jacksonObjectMapper;

    private AuthenticationManager loginManager;
    public CustomLoginFilter(ObjectMapper injectedMapper ){
        this.jacksonObjectMapper = injectedMapper;
    }

    @Autowired
    public void setLoginManager(AuthenticationManager manager){
        this.loginManager = manager;
        FilterSecurityInterceptor filter;
    }

    SessionManagementFilter smf;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
    String method = httpServletRequest.getMethod();
    
     if(AuthApi.LOGIN.equals(httpServletRequest.getRequestURI()) && httpServletRequest.getMethod().equals("POST") && (currentUser == null || !currentUser.isAuthenticated()))
     {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        UsernamePasswordDto bodyCredentials = null;
        UsernamePasswordAuthenticationToken usersCredentials = null;
        Authentication authManagerReturnedAuthentication;
        String body = null;

     
            body = IOUtils.toString(request.getReader());
            bodyCredentials = getDtoFromBody(body);
            if(!bodyCredentials.isInvalid())
            {
                usersCredentials = new UsernamePasswordAuthenticationToken(bodyCredentials.getUsername(),bodyCredentials.getPassword());        
                authManagerReturnedAuthentication = this.loginManager.authenticate(usersCredentials);
                if(authManagerReturnedAuthentication.isAuthenticated())
                {
                    SecurityContextHolder.getContext().setAuthentication(authManagerReturnedAuthentication);
                    httpServletResponse.setStatus(200);
                }
                else
                {
                    throw new AuthenticationCredentialsNotFoundException("Username and password combination not found");
                }
            }
        }
        
        chain.doFilter(request, response);
    }

    private UsernamePasswordDto getDtoFromBody(String body) throws AuthenticationException{

        UsernamePasswordDto returnDto = null;
        try{
            returnDto = (UsernamePasswordDto) this.jacksonObjectMapper.readValue(body,UsernamePasswordDto.class);
        }
        catch(Exception e)
        {
            throw new AuthenticationCredentialsNotFoundException("Unable to authenticate due to empty body.");
        }
        return returnDto;
    }
}
