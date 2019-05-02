package com.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.dao.RoleMapper;
import com.example.dao.UserMapper;
import com.example.dao.UserRoleMapper;
import com.example.dao.UserSubMapper;
import com.example.pojo.Role;
import com.example.pojo.User;
import com.example.pojo.UserSub;
import com.example.utils.Utils;

@Service
public class PowerManerService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private UserSubMapper usMapper;

	// 单页面显示数据数
	private static int pageSize = 5;

	public String firstdata(String search) {
		if (search == null) {
			search = "";
		}
		search = "%" + search + "%";
		// 数据总数
		int totalRecords = userMapper.getAllCount(search);
		// 总页数
		int total = totalRecords / pageSize;
		// 有余页数加一
		if (totalRecords % pageSize > 0 && totalRecords > pageSize)
			++total;
		Map<String, Integer> dataMap = new HashMap<String, Integer>();
		dataMap.put("totalRecords", totalRecords);
		dataMap.put("total", total);
		return Utils.gson.toJson(dataMap);
	}

	public String dolist(String num, String search) {
		if (search == null) {
			search = "";
		}
		search = "%" + search + "%";
		// 当前页数
		int num2 = Integer.parseInt(num);
		num2 = num2 - 1;
		if (num2 > 0) {
			num2 = num2 * pageSize;
		}
		List<User> userList = userMapper.getPage(num2, pageSize, search);
		return Utils.gson.toJson(userList);
	}

	public void getPowerList(String uid, Model model) {
		List<Role> roleList = roleMapper.getListByUID(uid);
		for (Role role : roleList) {
			System.out.println(role.getRname());
		}
		for (Role role : roleList) {
			if ("admin".equals(role.getRname())) {
				model.addAttribute("admin", "true");
			}
			if ("tec".equals(role.getRname())) {
				model.addAttribute("tec", "true");
			}
			if ("stu".equals(role.getRname())) {
				model.addAttribute("stu", "true");
			}
		}

	}

	public void dopower(HttpServletRequest request) {
		String powers[] = request.getParameterValues("power");
		String uid = request.getParameter("id");
		// 清除原有权限
		userRoleMapper.deleteByUID(uid);
		// 添加新权限
		userRoleMapper.insertPowers(uid, powers);

	}

	public String addSub(String[] sub, HttpSession session, String uid) {
		usMapper.delByUID(uid);
		for (String subid : sub) {
			UserSub us=new UserSub();
			us.setUid(Integer.parseInt(uid));
			us.setSubid(Integer.parseInt(subid));
			us.setStatu(0);
			usMapper.insertSelective(us);
		}
		return "sys/power/list";
	}

}
