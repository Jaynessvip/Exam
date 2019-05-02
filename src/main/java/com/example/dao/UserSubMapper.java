package com.example.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.pojo.UserSub;

@Mapper
public interface UserSubMapper {
	int insert(UserSub record);

	int insertSelective(UserSub record);

	int delByUID(String uid);

	int updateStatu(String subid, Integer uid);

	String getStatu(String subid, Integer uid);

}