package com.example.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.pojo.subOp;

@Mapper
public interface subOpMapper {
    int insert(subOp record);

    int insertSelective(subOp record);

	int delBysubID(String subjects);
}