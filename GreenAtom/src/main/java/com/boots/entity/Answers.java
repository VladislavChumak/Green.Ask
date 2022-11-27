package com.boots.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "qestion_answer")
public class Answers {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerscod;
	private String answer_name; 

	public long getAnswerscod() {
		return answerscod;
	}
	
	public String getAnswerName() {
		return answer_name;
	}

	public void setAnswerName(String answer_name) {
		this.answer_name = answer_name;
	}
}