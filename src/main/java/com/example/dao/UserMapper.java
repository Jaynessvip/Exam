package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.pojo.User;

@Mapper
public interface UserMapper {

	User findByNameAndPSW(@Param("u") String username, @Param("p") String password);

	List<User> all();

	int getAllCount(@Param("search") String search);

	List<User> getPage(@Param("num") int num2, @Param("size") int pageSize, @Param("search") String search);

	int insert(User u);

	int delMore(@Param("ck") String ck);

	User findByID(@Param("uid") String uid);

	int updata(User u);

	int stop(@Param("uid") String uid);

	int dopsw(@Param("uid") Integer uid, @Param("pass") String pass);

	int upAvatar(User user);

}