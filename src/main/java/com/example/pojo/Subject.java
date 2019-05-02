package com.example.pojo;

import java.util.Date;

public class Subject {
	private Integer subId;

	private String subName;

	private Date date;

	public Integer getSubId() {
		return subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Subject [subId=" + subId + ", subName=" + subName + ", date=" + date + "]";
	}

}