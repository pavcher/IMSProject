
package com.manan.springcloud.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class Websecurity extends WebSecurityConfigurerAdapter 
{

	private static final Logger logger = LoggerFactory.getLogger(Websecurity.class);
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		logger.info("Inside Websecurity class in configure method");
		
		
		
		auth.inMemoryAuthentication().withUser("user").password("password").roles("user")
		.and().withUser("admin").password("password").roles("USER","ADMIN");
		
		logger.info("Inside Websecurity class in configure method");
	}
	

	@Bean
	public PasswordEncoder getPasswordEncoder( ) {
		return NoOpPasswordEncoder.getInstance();
	}

	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		
		http.httpBasic().and().authorizeRequests()
		    .antMatchers(HttpMethod.GET,"/purchase/**").hasRole("USER")
		     .antMatchers(HttpMethod.POST,"/purchase/**").hasRole("ADMIN")
		     .antMatchers(HttpMethod.DELETE, "/purchase/**").hasRole("ADMIN")
		     .and().csrf().disable().formLogin().disable();
		
		
		
		       
		      
	}
	
	
	
	
	
}

