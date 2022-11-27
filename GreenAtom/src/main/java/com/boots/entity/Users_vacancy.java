package com.boots.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "users_vacancy")
public class Users_vacancy {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long users_vacancyscod;
	private long userscod;
	private long vacancyscod;
	private int result; 
	@ManyToOne
    @PrimaryKeyJoinColumn
    private Vacancy vacancy;
	
	@ManyToOne
    @PrimaryKeyJoinColumn
    private User users;
    
	public Users_vacancy() {
		result = 0;
	}
	public Users_vacancy(long userscod, long vacancyscod) {
		this.userscod = userscod;
		this.users_vacancyscod = vacancyscod;
		result = 0;
	}
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
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}	
}
