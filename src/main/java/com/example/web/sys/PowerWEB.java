package com.example.web.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dao.SubjectMapper;
import com.example.pojo.Subject;
import com.example.service.PowerManerService;

@Controller
@RequestMapping("power")
@RequiresRoles("admin")
public class PowerWEB {
	@Autowired
	private PowerManerService pmSer;
	@Autowired
	private SubjectMapper subMapper;

	@RequestMapping("page")
	public String page() {
		return "sys/power/list";
	}

	@RequestMapping("firstdata")
	@ResponseBody
	public String firstdata(String search) {
		return pmSer.firstdata(search);
	}

	@RequestMapping("dolist")
	@ResponseBody
	public String dolist(String num, String search) {
		return pmSer.dolist(num, search);
	}

	@RequestMapping("dosearch")
	public String dosearch(String search, Model model) {
		model.addAttribute("search", search);
		return "sys/power/list";
	}

	@RequestMapping("page2")
	public String page2(String uid, Model model) {
		model.addAttribute("id", uid);
		pmSer.getPowerList(uid, model);
		return "sys/power/edit";
	}

	@RequestMapping("dopower")
	public String dopower(HttpServletRequest request) {
		pmSer.dopower(request);
		return "sys/power/list";
	}

	@RequestMapping("subPage")
	public String subPage(String uid, Model model) {
		List<Subject> subList = subMapper.getAll(null);
		model.addAttribute("sub", subList);
		model.addAttribute("uid", uid);
		return "sys/power/subPage";
	}

	@RequestMapping("addSub")
	public String addSub(String[] sub, HttpSession session,String uid) {
		return pmSer.addSub(sub, session,uid);
	}
}
