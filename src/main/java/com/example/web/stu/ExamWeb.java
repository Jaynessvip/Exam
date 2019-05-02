package com.example.web.stu;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dao.SubjectMapper;
import com.example.pojo.Subject;
import com.example.pojo.User;
import com.example.service.ExamService;

@RequestMapping("exam")
@Controller
public class ExamWeb {
	@Autowired
	private SubjectMapper subMapper;
	@Autowired
	private ExamService examSer;

	@RequestMapping("subPage")
	public String subPage(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<Subject> subList = subMapper.getByUID(user.getUid());
		model.addAttribute("list", subList);
		return "stu/exam/subPage";
	}

	@RequestMapping("examPage")
	public String examPage(HttpSession session, String subid,Model model) {
		return examSer.getPage(session, subid,model);
	}
	
	@RequestMapping("doScore")
	public String doScore(HttpSession session, String subid,Model model,HttpServletRequest request) {
		return examSer.doScore(session, subid,model,request);
	}
	@RequestMapping("scorePage")
	public String scorePage(HttpSession session,Model model) {
		return examSer.scorePage(session,model);
	}
}
