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
@Table(name = "user_vacancy")
public class Vacancy {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vacancyscod;
	private String vacancy_cod;
	private String vacancy_name; 
	@Transient
    @ManyToMany(mappedBy = "results")
    private Set<User> users;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Questions> questions;
	
	
	public String getName() {
		return vacancy_cod;
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