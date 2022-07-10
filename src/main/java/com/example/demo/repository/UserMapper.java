package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.user.model.MUser;

@Mapper
public interface UserMapper {
	
	/*User signup*/
	public int insertOne(MUser user);
}
