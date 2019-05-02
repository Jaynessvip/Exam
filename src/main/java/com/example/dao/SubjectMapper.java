package com.example.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.pojo.Subject;

@Mapper
public interface SubjectMapper {
	int insert(Subject record);

	int insertSelective(Subject record);

	List<Subject> getAll(@Param("name")String name);

	Subject getByID(@Param("id") String id);

	int updateByID(@Param("id") String id, @Param("name") String name, @Param("date") Date date);

	int delByID(@Param("id") String string);

	Subject getByName(@Param("name") String name);

	List<Subject> getByUID(Integer uid);
}