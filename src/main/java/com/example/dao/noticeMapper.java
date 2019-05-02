package com.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.pojo.notice;

@Mapper
public interface noticeMapper {
	int insert(notice record);

	int insertSelective(notice record);

	notice getByID(@Param("id") int id);

	int update(notice noti);
}