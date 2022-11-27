package com.boots.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vacancy")
public class Vacancy {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vacancyscod;
	private String vacancy_cod;
	private String vacancy_name; 
	
	public String getName() {
		return vacancy_cod;
	}
	public long getVacancyscod() {
		return vacancyscod;
	}
	public void setName(String vacancy_cod) {
		this.vacancy_cod= vacancy_cod;
	}
	public String getVacancyName() {
		return vacancy_name;
	}

	public void setVacancyName(String vacancy_name) {
		this.vacancy_name = vacancy_name;
	}
}