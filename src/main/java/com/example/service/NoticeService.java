package com.example.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.noticeMapper;
import com.example.pojo.notice;
import com.google.gson.Gson;

@Service
public class NoticeService {
	@Autowired
	private noticeMapper nMapper;
	private Gson g = new Gson();

	public String getInfo() {
		notice nt = nMapper.getByID(1);
		Map<String, String> map = new HashMap<String, String>();
		map.put("notice", nt.getNotice());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(nt.getDate());
		map.put("date", date);
		return g.toJson(map);
	}

	public String updateData(String notice) {
		notice noti=new notice();
		noti.setNoticeId(1);
		noti.setDate(new Date());
		noti.setNotice(notice);
		int i=nMapper.update(noti);
		if(i>0) {
			return "发布成功！";
		}
		return "发布失败";
		
	}

}
