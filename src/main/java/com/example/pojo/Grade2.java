package com.example.pojo;

public class Grade2 {
	String sub_name;
	Integer score;

	public String getSub_name() {
		return sub_name;
	}

	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Grade2 [sub_name=" + sub_name + ", score=" + score + "]";
	}
	
}