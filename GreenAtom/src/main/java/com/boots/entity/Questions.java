package com.boots.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class Questions {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionscod;
	private String question_name; 
	
	public long getQuestionscod() {
		return questionscod;
	}
	public String getQuestionName() {
		return question_name;
	}
	public void setQuestionName(String question_name) {
		this.question_name = question_name;
	}
}