package com.boots.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "question_answers")
public class Question_answers {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long questions_vacancyscod;
	private long questionscod;
	private long answerscod;
	private int answer_count;
	
	public long getQuestionscod() {
		return questionscod;
	}
	public void setQuestionscod(long questionscod) {
		this.questionscod = questionscod;
	}
	public long getAnswerscod() {
		return answerscod;
	}
	public void setAnswerscod(long answerscod) {
		this.answerscod = answerscod;
	}
	public int getAnswer_count() {
		return answer_count;
	}
	public void setAnswer_count(int answer_count) {
		this.answer_count = answer_count;
	}
}
