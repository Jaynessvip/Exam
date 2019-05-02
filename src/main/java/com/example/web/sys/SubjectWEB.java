package com.example.web.sys;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.SubjectService;

@Controller
@RequestMapping("subject")
@RequiresRoles("admin")
public class SubjectWEB {
	@Autowired
	private SubjectService subSer;

	@RequestMapping("listPage")
	public String listPage() {
		return "sys/subject/list";
	}

	@RequestMapping("getPage")
	@ResponseBody
	public String getPage(String index, String search) {
		if (search != null) {
			search = "%" + search + "%";
		}
		return subSer.getPage(index, search);
	}

	@RequestMapping("edit")
	public String edit(String id, Model model) {
		model.addAttribute("id", id);
		return "sys/subject/edit";
	}

	@RequestMapping("queryOne")
	@ResponseBody
	public String queryOne(String id) {
		return subSer.queryOne(id);
	}

	@RequestMapping("doEdit")
	public String doEdit(String id, String name, String date, Model model) {
		String res = subSer.doEdit(id, name, date, model);
		if (res == "fail") {
			return "sys/subject/fail";
		}
		return "sys/subject/list";
	}

	@RequestMapping("doDel")
	public String doDel(String ck[]) {
		for (String string : ck) {
			subSer.delByID(string);
		}
		return "sys/subject/list";
	}

	@RequestMapping("addPage")
	public String doDel() {
		return "sys/subject/add";
	}

	@RequestMapping("doAdd")
	public String doAdd(String name, String date,Model model) {
		return subSer.doAdd(name, date,model);
	}

	@RequestMapping("doSearch")
	public String doSearch(String search, Model model) {
		model.addAttribute("search", search);
		return "sys/subject/list";
	}

}
