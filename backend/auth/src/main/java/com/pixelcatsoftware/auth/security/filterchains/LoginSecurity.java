package com.pixelcatsoftware.auth.security.filterchains;

import java.util.Arrays;

import com.pixelcatsoftware.auth.security.filters.CustomLoginFilter;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Component
@EnableWebSecurity(debug=true)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LoginSecurity extends WebSecurityConfigurerAdapter{

    UserDetailsService loginService;
    PasswordEncoder appPasswordEncoder;
    CustomLoginFilter loginFilter;

    public LoginSecurity(UserDetailsService loginService ,PasswordEncoder appEncoder) throws Exception{
        this.loginService = loginService;
        this.appPasswordEncoder = appEncoder;
    }

    @Bean 
    public CorsConfigurationSource getCorsConfiguration(){
      CorsConfiguration corsConfig = new CorsConfiguration();
      UrlBasedCorsConfigurationSource corsConfigSource = null;
      corsConfig.addAllowedOrigin("http://localhost:5000");
      corsConfig.addAllowedMethod("POST");
      corsConfig.addAllowedMethod("PUT");
      corsConfig.addAllowedMethod("DELETE");
      corsConfig.addAllowedMethod("PATCH");
      corsConfig.addAllowedMethod("GET");
      corsConfig.addAllowedHeader("content-type");
      corsConfig.setAllowCredentials(true);

      corsConfigSource = new UrlBasedCorsConfigurationSource();
      corsConfigSource.registerCorsConfiguration("/**",corsConfig);

      return corsConfigSource;

    }

    @Autowired
    public void setLoginFilter(CustomLoginFilter filter){
        this.loginFilter = filter;
    }
    @Override 
    public void configure(HttpSecurity security) throws Exception{

        security
        .csrf()
        .disable()
        .cors()
        .configurationSource(this.getCorsConfiguration())
        .and()
            .antMatcher("/auth/**")
            .sessionManagement()
            .maximumSessions(3)
            .maxSessionsPreventsLogin(false)
            .and()
            .and()
            .addFilterAfter(this.loginFilter, SecurityContextPersistenceFilter.class)
                .authorizeRequests()
                 .antMatchers("/auth")
                 .hasAnyAuthority("registered_user", "email_verified_user")
                 .and()
                 .authorizeRequests()
                 .antMatchers("/auth**")
                 .authenticated();
                
        
    }

    public void configure(AuthenticationManagerBuilder builder) throws Exception{
        builder
            .userDetailsService(loginService)
            .passwordEncoder(appPasswordEncoder);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }
    
}
