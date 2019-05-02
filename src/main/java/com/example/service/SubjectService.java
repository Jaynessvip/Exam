package com.example.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.dao.SubjectMapper;
import com.example.pojo.Subject;
import com.example.pojo.Subject2;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;

@Service
public class SubjectService {
	@Autowired
	private SubjectMapper subDao;
	private Gson g = new Gson();

	public String getPage(String index, String search) {
		int num = 1;
		try {
			num = Integer.parseInt(index);
		} catch (Exception e) {
			return "index参数错误";
		}
		PageHelper.startPage(num, 5);
		List<Subject> subList = subDao.getAll(search);
		if (subList == null)
			return "数据为空";
		PageInfo<Subject> info = new PageInfo<Subject>(subList);
		return g.toJson(info);

	}

	public String queryOne(String id) {
		Subject sub = subDao.getByID(id);
		Subject2 sub2 = new Subject2();
		sub2.setSubId(sub.getSubId());
		sub2.setSubName(sub.getSubName());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(sub.getDate());
		sub2.setDate(date);
		return g.toJson(sub2);
	}

	public String doEdit(String id, String name, String date, Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = null;
		try {
			date2 = sdf.parse(date);
		} catch (ParseException e) {
			model.addAttribute("fail", "时间项为空");
			return "fail";
		}
		subDao.updateByID(id, name, date2);
		return "success";
	}

	public void delByID(String string) {
		subDao.delByID(string);
	}

	public String doAdd(String name, String date, Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = null;
		try {
			date2 = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Subject sub = new Subject();
		sub.setSubName(name);
		sub.setDate(date2);
		if (subDao.getByName(name) == null) {
			subDao.insertSelective(sub);
		} else {
			model.addAttribute("fail", "该学科名已存在！");
			return "sys/subject/fail";
		}
		return "sys/subject/list";
	}

}
