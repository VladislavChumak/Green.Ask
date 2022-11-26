package com.boots.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "vacancy_questions")
public class Questions {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionscod;
	private String question_name; 
	@Transient
    @ManyToMany(mappedBy = "results")
    private Set<Vacancy> vacancy;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Answers> answers;
	
	public String getQuestionName() {
		return question_name;
	}

	public void setQuestionName(String question_name) {
		this.question_name = question_name;
	}
}