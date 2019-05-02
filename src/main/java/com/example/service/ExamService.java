package com.example.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.dao.GradeMapper;
import com.example.dao.OptionMapper;
import com.example.dao.SubjectMapper;
import com.example.dao.UserSubMapper;
import com.example.pojo.Grade;
import com.example.pojo.Grade2;
import com.example.pojo.Option;
import com.example.pojo.Subject;
import com.example.pojo.User;

@Service
public class ExamService {
	@Autowired
	private UserSubMapper usMapper;
	@Autowired
	private OptionMapper opMapper;
	@Autowired
	private SubjectMapper subMapper;
	@Autowired
	private GradeMapper graMapper;

	public String getPage(HttpSession session, String subid, Model model) {
		model.addAttribute("subid", subid);
		User user = (User) session.getAttribute("user");
		// 1.查询该用户是否参加过该科目考试
		String statu = usMapper.getStatu(subid, user.getUid());
		if ("0".equals(statu)) {
			// 1.1更新考试状态
			usMapper.updateStatu(subid, user.getUid());
			// 1.2得到题目数据
			List<Option> opList = opMapper.getBySubID(subid);
			model.addAttribute("list", opList);
			// 1.3得到考试科目名
			Subject subject = subMapper.getByID(subid);
			model.addAttribute("name", "《" + subject.getSubName() + "》");
			return "stu/exam/exam";
		}
		model.addAttribute("tip", "您已参加过该科目考试！");
		return "stu/exam/tips";

	}

	public String doScore(HttpSession session, String subid, Model model, HttpServletRequest request) {
		User user = (User) session.getAttribute("user");
		List<Option> opList = opMapper.getBySubID(subid);
		int score = 0;
		for (Option option : opList) {
			String key = request.getParameter(String.valueOf(option.getQueid()));
			if(key==null) {
				continue;
			}
			if (key.equals(option.getKey())) {
				score += option.getScore();
			}
		}
		Grade grade = new Grade();
		grade.setScore(score);
		grade.setSubid(Integer.parseInt(subid));
		grade.setUid(user.getUid());
		graMapper.insert(grade);
		model.addAttribute("tip", "提交成功，考试结束！");
		return "stu/exam/tips";
	}

	public String scorePage(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		List<Grade2> gradeList=graMapper.getGradeList(user.getUid());
		System.out.println(gradeList);
		model.addAttribute("list", gradeList);
		return "stu/exam/info";
	}

}
