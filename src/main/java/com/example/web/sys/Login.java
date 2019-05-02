package com.example.web.sys;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dao.UserMapper;
import com.example.dao.UserRoleMapper;
import com.example.pojo.User;

@Controller
public class Login {

	@Autowired
	private UserMapper umapper;
	@Autowired
	private UserRoleMapper urMapper;

	@RequestMapping("login")
	public String page() {
		return "login";
	}

	@RequestMapping("dologin")
	public String dologin(String username, String password, HttpSession session,Model model) {
		Subject subject = SecurityUtils.getSubject();
		User u=null;
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
			password = new SimpleHash("MD5", password, username, 1024).toString();
			u = umapper.findByNameAndPSW(username, password);
			session.setAttribute("name", u.getName());
			session.setAttribute("user", u);
			model.addAttribute("user", u);
		} catch (Exception e) {
			return "login";
		}
		
		List<String>  roleNames= urMapper.getRolesNamesByUserID(u.getUid());
		//判断为学生跳转至学生首页
		if(roleNames.size()==1&&"stu".equals(roleNames.get(0))) {
			return "stu/index";
		}
		//判断为老师
		if(roleNames.size()==1&&"tec".equals(roleNames.get(0))) {
			model.addAttribute("hidden", "true");
		}else {
			model.addAttribute("hidden", "false");
		}
		return "index";
	}

}
