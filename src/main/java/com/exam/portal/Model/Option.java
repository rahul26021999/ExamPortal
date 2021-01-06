package com.exam.portal.Model;

import javax.persistence.*;

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

	public Option(Question questions, String option) {
		this.questions = questions;
		this.option = option;
	}

	public Option() {

	}


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
