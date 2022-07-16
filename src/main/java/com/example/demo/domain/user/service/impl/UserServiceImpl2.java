package com.example.demo.domain.user.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.user.model.MUser;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.repository.UserRepository;

@Service
@Primary
public class UserServiceImpl2 implements UserService{
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	@Lazy
	private PasswordEncoder encoder;
	
	
	/*User signup*/
	@Transactional
	@Override
	public void signup(MUser user) {
		//Existence check
		boolean exists = repository.existsById(user.getUserId());
		if(exists) {
			throw new DataAccessException("User already exists") {};
		}
		
		user.setDepartmentId(1);
		user.setRole("ROLE_GENERAL");
		
		//Password encryption
		String rawPassword = user.getPassword();
		user.setPassword(encoder.encode(rawPassword));
		
		//insert
		repository.save(user);
	}
	
	/*Get users*/
	@Override
	public List<MUser> getUsers(MUser user) {
		//Search conditions
		ExampleMatcher matcher = ExampleMatcher.matching() //Conditions
				.withStringMatcher(StringMatcher.CONTAINING) //Like clause
				.withIgnoreCase(); //case-insensitive
		return repository.findAll(Example.of(user, matcher));
	}
	
	/*Get user(1 record)*/
	@Override
	public MUser getUserOne(String userId) {
		Optional<MUser> option = repository.findById(userId);
		MUser user = option.orElse(null);
		return user;
	}
	
	/*Update user*/
	@Override
	public void updateUserOne(String userId, String password, String userName) {
		
		//Password encryption
		String encryptPassword = encoder.encode(password);
		
		//User update
		repository.updateUser(userId, encryptPassword, userName);
	}
	
	/*Delete user*/
	@Transactional
	@Override
	public void deleteUserOne(String userId) {
		repository.deleteById(userId);
	}
	
	/*Get login user*/
	@Override
	public MUser getLoginUser(String userId) {
		return repository.findLoginUser(userId);
//		Optional<MUser> option = repository.findById(userId);
//		MUser user = option.orElse(null);
//		return user;
	}

}
