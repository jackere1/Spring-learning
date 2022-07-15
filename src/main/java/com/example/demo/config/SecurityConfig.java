package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
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
			.antMatchers("/admin").hasAuthority("ROLE_ADMIN") //Authority control
			.anyRequest().authenticated(); //Otherwise direct link NG
		
		//Login process
		http.formLogin()
			.loginProcessingUrl("/login")
			.loginPage("/login")
			.failureUrl("/login?error")
			.usernameParameter("userId")
			.passwordParameter("password")
			.defaultSuccessUrl("/user/list", true);
		
		//Logout process
		http.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout");
		
		//Disable CSRF measures (temporary)
//		http.csrf().disable();
	}
	
	/**Authentication settings*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = passwordEncoder();
		/*
		//In-memory auth
		auth.inMemoryAuthentication()
				.withUser("user") //add user
					.password(encoder.encode("user"))
					.roles("GENERAL")
				.and()
				.withUser("admin") //add admin
					.password(encoder.encode("admin"))
					.roles("ADMIN");
		*/
		
		//User data auth
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(encoder);
	}
}
