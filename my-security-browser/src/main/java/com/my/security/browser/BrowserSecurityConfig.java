package com.my.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.my.secuirty.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.my.secuirty.core.properties.SecurityConstants;
import com.my.secuirty.core.properties.SecurityProperties;
import com.my.secuirty.core.validate.code.ValidateCodeFilter;
import com.my.security.browser.jwt.JWTConfigurer;
import com.my.security.browser.jwt.TokenProvider;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private ValidateCodeFilter validateCodeFilter;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http//.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
			.formLogin()
			.loginPage("/authentication/require")
			.loginProcessingUrl("/authentication/form")
			.successHandler(authenticationSuccessHandler)
			.failureHandler(authenticationFailureHandler)
//		.and()
//	         .sessionManagement()
//	         .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	    .and()
			.authorizeRequests()
			.antMatchers( 
					SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                    SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                    securityProperties.getBrowser().getLoginPage(),
                    SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*").permitAll()
			.anyRequest()
			.authenticated()
		.and()
			.csrf().disable()
			.apply(smsCodeAuthenticationSecurityConfig)
		.and()
			.apply(securityConfigurerAdapter());
	}
	
	private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }


}
