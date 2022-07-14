package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**Set a target out-of-security*/
	@Override
	public void configure(WebSecurity web) throws Exception {
		//Do not apply security
		web.ignoring()
			.antMatchers("/webjars/**")
			.antMatchers("/css/**")
			.antMatchers("/js/**")
			.antMatchers("/h2-console/**");
	}
	
	/**Various security settings*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Set of login unnecessary pages
		http.authorizeRequests()
			.antMatchers("/login").permitAll() //Direct OK
			.antMatchers("/user/signup").permitAll()
			.anyRequest().authenticated(); //Otherwise direct link NG
		
		//Disable CSRF measures (temporary)
		http.csrf().disable();
	}
}
