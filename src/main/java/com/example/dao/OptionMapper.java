package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.pojo.Option;

@Mapper
public interface OptionMapper {
	List<Option> getAll(@Param("subjects")String subjects);

	int delByID(@Param("id") String id);

	int insertOP(Option op);

	Option getByID(@Param("id") String id);

	int edit1(Option op);

	List<Option> getBySubID(String subid);

}