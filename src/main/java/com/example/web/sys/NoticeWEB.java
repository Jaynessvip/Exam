package com.example.web.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.NoticeService;

@RequestMapping("notice")
@Controller
public class NoticeWEB {
	@Autowired
	private NoticeService noticeSer;
	
	@RequestMapping("info")
	@ResponseBody
	public String page() {
		return noticeSer.getInfo();
	}
	@RequestMapping("editPage")
	public String editPage() {
		return "sys/notice/add";
	}
	@RequestMapping("edit")
	@ResponseBody
	public String edit(String notice) {
		String res= noticeSer.updateData(notice);
		return res;
	}

}
