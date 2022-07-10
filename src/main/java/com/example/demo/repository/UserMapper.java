package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.domain.user.model.MUser;

@Mapper
public interface UserMapper {
	
	/*User signup*/
	public int insertOne(MUser user);
	
	/*Get user*/
	public List<MUser> findMany(MUser user);
	
	/*Get user(single record)*/
	public MUser findOne(String userId);
	
	/*Update user*/
	public void updateOne(@Param("userId") String userId, 
			@Param("password") String password,
			@Param("userName") String userName);
	
	/*Delete user*/
	public int deleteOne(@Param("userId") String userId);
}
