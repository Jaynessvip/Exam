package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserRoleMapper {

	int deleteByUID(@Param("uid")String uid);

	int insertPowers(@Param("uid")String uid, String[] powers);

	List<String> getRolesNamesByUserID(@Param("uid")Integer uid);

}