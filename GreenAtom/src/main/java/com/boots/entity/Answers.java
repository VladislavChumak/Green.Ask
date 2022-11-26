package com.boots.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "qestion_answer")
public class Answers {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerscod;
	private String answer_name; 
	@Transient
    @ManyToMany(mappedBy = "question")
    private Set<Questions> question;
	
	public String getAnswerName() {
		return answer_name;
	}

	public void setAnswerName(String answer_name) {
		this.answer_name = answer_name;
	}
}