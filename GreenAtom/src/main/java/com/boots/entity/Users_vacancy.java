package com.boots.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users_vacancy")
public class Users_vacancy {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long users_vacancyscod;
	private long userscod;
	private long vacancyscod;
	public long getUserscod() {
		return userscod;
	}
	public void setUserscod(long userscod) {
		this.userscod = userscod;
	}
	public long getVacancyscod() {
		return vacancyscod;
	}
	public void setVacancyscod(long vacancyscod) {
		this.vacancyscod = vacancyscod;
	}	
}
