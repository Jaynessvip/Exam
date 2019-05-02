package com.example.web.sys;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.pojo.User;
import com.example.service.UserService;

@RequestMapping("user")
@Controller
@RequiresRoles("admin")
public class UserWEB {

	@Autowired
	private UserService userSer;

	@RequestMapping("page")
	public String page() {
		return "sys/user/add";
	}

	@RequestMapping("add")
	public String add(String password, String username, String[] c1, Model model) {
		return userSer.add(password, username, c1, model);
	}

	@RequestMapping("listPage")
	public String listPage() {
		return "sys/user/list";
	}

	@RequestMapping("dosearch")
	public String dosearch(String search, Model model) {
		model.addAttribute("search", search);
		return "sys/user/list";
	}

	@RequestMapping("delMore")
	public String delMore(String ck[]) {
		userSer.delMore(ck);
		return "sys/user/list";
	}

	@RequestMapping("dataNum")
	@ResponseBody
	public String dataNum() {
		return userSer.dataNum();
	}

	@RequestMapping("edit")
	public String edit(String uid, Model model) {
		userSer.edit(uid, model);
		return "sys/user/edit";
	}

	@RequestMapping("editData")
	public String editData(User u, String date1) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(date1 + " 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		u.setDate(date);
		userSer.editData(u);
		return "sys/user/list";
	}

	@RequestMapping("stop")
	public String edit(String uid) {
		userSer.stop(uid);
		return "sys/user/stop";
	}
	@RequestMapping("pswPage")
	public String editPSW(HttpSession session,Model model) {
		User user=(User) session.getAttribute("user");
		model.addAttribute("user", user);
		return "password";
	}
	@RequestMapping("pswUpdate")
	public String pswUpdate(HttpSession session,Model model,String mpass, String newpass) {
		User user=(User) session.getAttribute("user");
		userSer.dopsw(user, model, mpass, newpass);
		return "tips";
	}
}
