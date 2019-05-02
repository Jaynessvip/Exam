package com.example.pojo;

import java.util.Date;

public class Option {
	private Integer queid;

	private Integer score;

	private String subject;

	private String title;

	private String opa;

	private String opb;

	private String opc;

	private String opd;

	private String key;

	private Date date;

	public Integer getQueid() {
		return queid;
	}

	public void setQueid(Integer queid) {
		this.queid = queid;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOpa() {
		return opa;
	}

	public void setOpa(String opa) {
		this.opa = opa;
	}

	public String getOpb() {
		return opb;
	}

	public void setOpb(String opb) {
		this.opb = opb;
	}

	public String getOpc() {
		return opc;
	}

	public void setOpc(String opc) {
		this.opc = opc;
	}

	public String getOpd() {
		return opd;
	}

	public void setOpd(String opd) {
		this.opd = opd;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Option [queid=" + queid + ", score=" + score + ", subject=" + subject + ", title=" + title + ", opa="
				+ opa + ", opb=" + opb + ", opc=" + opc + ", opd=" + opd + ", key=" + key + ", date=" + date + "]";
	}
	
}