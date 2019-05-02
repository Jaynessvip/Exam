package com.example.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.dao.UserMapper;
import com.example.dao.UserRoleMapper;
import com.example.pojo.User;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper urMapper;

	public String add(String password, String username, String[] c1, Model model) {
		if (c1 == null) {
			model.addAttribute("fail", "检测到角色为空，请重试！");
			return "sys/user/fail";

		}
		// 不允许登录重名
		if (userMapper.findByNameAndPSW(username, null) == null) {
			// 插入基本信息
			password = new SimpleHash("MD5", new String(password), username, 1024).toString();
			User u = new User();
			u.setUsername(username);
			u.setPassword(password);
			u.setDate(new Date());
			u.setStatu(0);
			userMapper.insert(u);
			int uid = u.getUid();
			// 插入权限
			urMapper.insertPowers(String.valueOf(uid), c1);

			return "sys/user/success";
		}
		model.addAttribute("fail", "检测到登录名重复，请重试！");
		return "sys/user/fail";

	}

	public void delMore(String[] ck) {
		for (String string : ck) {
			userMapper.delMore(string);
		}

	}

	public String dataNum() {
		int totalRecords = userMapper.getAllCount(null);
		return String.valueOf(totalRecords);
	}

	public void edit(String uid, Model model) {
		User user = userMapper.findByID(uid);
		if (user.getDate() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(user.getDate());
			model.addAttribute("date", date);
		}
		model.addAttribute("uid", uid);
		model.addAttribute("user", user);
	}

	public void editData(User u) {
		userMapper.updata(u);
	}

	public void stop(String uid) {
		userMapper.stop(uid);
	}

	public void dopsw(User user, Model model, String mpass, String newpass) {
		mpass = new SimpleHash("MD5", mpass, user.getUsername(), 1024).toString();
		newpass = new SimpleHash("MD5", newpass, user.getUsername(), 1024).toString();
		if (mpass.equals(user.getPassword())) {
			userMapper.dopsw(user.getUid(),newpass);
			model.addAttribute("tip", "修改成功");
		}else {
			model.addAttribute("tip", "原密码错误");
		}
	}

	public void upAvatar(User user) {
		userMapper.upAvatar(user);
		
	}

}
