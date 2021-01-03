package com.exam.portal.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "options")
public class Option {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name= "queston_id", nullable=false)
	private Question questions;

	@Column(length=50,name = "statement")
	private String option;

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Question getQuestions() {
		return questions;
	}


	public void setQuestions(Question questions) {
		this.questions = questions;
	}


	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}


	
}
