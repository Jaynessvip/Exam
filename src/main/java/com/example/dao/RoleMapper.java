package com.example.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.pojo.Role;

@Mapper
public interface RoleMapper {
	int insert(Role record);

	int insertSelective(Role record);

	List<Role> getListByUID(@Param("uid") String uid);

	Set<String> getRole(String username);
}