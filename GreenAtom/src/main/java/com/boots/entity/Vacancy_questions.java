package com.boots.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vacancy_questions")
public class Vacancy_questions {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long questions_vacancyscod;
	private long questionscod;
	private long vacancyscod;
	public long getQuestionscod() {
		return questionscod;
	}
	public void setQuestionscod(long questionscod) {
		this.questionscod = questionscod;
	}
	public long getVacancyscod() {
		return vacancyscod;
	}
	public void setVacancyscod(long vacancyscod) {
		this.vacancyscod = vacancyscod;
	}	
}
