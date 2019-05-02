package com.example.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.dao.OptionMapper;
import com.example.dao.SubjectMapper;
import com.example.pojo.Option;
import com.example.pojo.Subject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;

@Service
public class TikuService {
	@Autowired
	private OptionMapper opMapper;
	@Autowired
	private SubjectMapper subjectMapper;
	private Gson g = new Gson();

	public String getList(String index, String subjects) {
		int num = 1;
		try {
			num = Integer.parseInt(index);
		} catch (Exception e) {
			return "index参数错误";
		}
		PageHelper.startPage(num, 5);
		List<Option> opList = opMapper.getAll(subjects);
		PageInfo<Option> info = new PageInfo<Option>(opList);
		return g.toJson(info);
	}

	public String doDel(String[] ids) {
		for (String id : ids) {
			opMapper.delByID(id);
		}
		return "tec/tiku/list";
	}

	public String doAdd(Option op) {
		op.setDate(new Date());
		opMapper.insertOP(op);
		return "tec/tiku/list";
	}

	public String getAllSub() {
		List<Subject> subList = subjectMapper.getAll(null);
		return g.toJson(subList);
	}

	public String editPage(String id, Model model) {
		Option op = opMapper.getByID(id);
		model.addAttribute("op", op);
		return "tec/tiku/edit1";

	}

	public String edit1(Option op) {
		op.setDate(new Date());
		opMapper.edit1(op);
		return "tec/tiku/list";
	}

	public String getAll(String subjects) {
		List<Option> opList = opMapper.getAll(subjects);
		return g.toJson(opList);
	}

}
