package com.spring.JWTSpringSecurity.Configurer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.spring.JWTSpringSecurity.Service.myUserDetailssService;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer {	
	@Autowired
	private myUserDetailssService service;
	
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		security.csrf().disable()
				.authorizeHttpRequests(request -> request.requestMatchers("/authenticate")
						.permitAll().anyRequest().authenticated());
		return security.build();
	}

	   @Bean
	 public AuthenticationManager authenticateManager(HttpSecurity httpSecurity) throws Exception
	 {
		   AuthenticationManagerBuilder managerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
			managerBuilder.userDetailsService(service).passwordEncoder(passwordEncoder());
			return managerBuilder.build();
	 }
	   @Bean 
	   public PasswordEncoder passwordEncoder() {
		   return NoOpPasswordEncoder.getInstance();
	   }
}
