package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.pojo.Grade;
import com.example.pojo.Grade2;

@Mapper
public interface GradeMapper {
    int insert(Grade record);

    int insertSelective(Grade record);

	List<Grade2> getGradeList(Integer uid);
}