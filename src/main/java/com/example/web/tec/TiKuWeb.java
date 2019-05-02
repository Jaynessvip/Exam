package com.example.web.tec;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dao.SubjectMapper;
import com.example.dao.subOpMapper;
import com.example.pojo.Option;
import com.example.pojo.Subject;
import com.example.pojo.subOp;
import com.example.service.TikuService;

@Controller
@RequestMapping("tiku")
@RequiresRoles("tec")
public class TiKuWeb {
	@Autowired
	private TikuService tkSer;
	@Autowired
	private SubjectMapper subMapper;
	@Autowired
	private subOpMapper subopMapper;

	@RequestMapping("page")
	public String page() {
		return "tec/tiku/list";
	}

	@RequestMapping("getList")
	@ResponseBody
	public String getList(String index, String subjects) {
		if (subjects.equals("")) {
			subjects = null;
		}
		return tkSer.getList(index, subjects);
	}

	@RequestMapping("doDel")
	public String doDel(String[] ids) {
		if (ids == null) {
			return "tec/tiku/list";
		}
		return tkSer.doDel(ids);
	}

	@RequestMapping("addPage")
	public String addPage() {
		return "tec/tiku/add";
	}

	@RequestMapping("doAdd")
	public String doAdd(Option op) {
		return tkSer.doAdd(op);
	}

	@RequestMapping("getAllSub")
	@ResponseBody
	public String getAllSub() {
		return tkSer.getAllSub();
	}

	@RequestMapping("editPage")
	public String editPage(String id, Model model) {
		return tkSer.editPage(id, model);
	}

	@RequestMapping("edit1")
	public String edit1(Option op) {
		return tkSer.edit1(op);
	}

	@RequestMapping("page2")
	public String page2(Model model) {
		List<Subject> subList = subMapper.getAll(null);
		model.addAttribute("list", subList);
		return "tec/paper/subPage";
	}

	@RequestMapping("subList")
	public String subList(String subjects, Model model) {
		model.addAttribute("subjects", subjects);
		return "tec/paper/list2";
	}

	@RequestMapping("paper")
	public String subTi(String subjects, String[] ids, Model model) {
		if (ids == null) {
			model.addAttribute("tip", "未选择数据");
			return "tec/paper/tips";
		}
		// 1.删除原有数据
		subopMapper.delBysubID(subjects);
		// 2.添加数据
		for (String id : ids) {
			subOp subop = new subOp();
			subop.setSubid(Integer.parseInt(subjects));
			subop.setOpid(Integer.parseInt(id));
			subopMapper.insertSelective(subop);
		}
		model.addAttribute("tip", "试卷生成成功");
		return "tec/paper/tips";
	}

	@RequestMapping("getAll")
	@ResponseBody
	public String getAll(String subjects) {
		System.out.println(subjects);
		return tkSer.getAll(subjects);
	}
}
