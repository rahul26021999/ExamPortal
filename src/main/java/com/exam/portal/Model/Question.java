package com.exam.portal.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class Question {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false,length=300)
	private String statement;
	

	/*
	 * one answer of one question
	 * there will be one column of correct option_id in question table
	 */
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="option_id", nullable=false)
	private Option options;
	
	/*
	 * foreign key
	 * many question will be connected to one exam
	 * 
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name= "exam_id", nullable=false)
	private Exam exams;
	
	
//	@Column(length=50)
//	private String option1;
//	
//	@Column(length=50)
//	private String option2;
//	
//	@Column(length=50)
//	private String option3;
//	
//	@Column(length=50)
//	private String option4;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	
	public Exam getExams() {
		return exams;
	}

	public void setExams(Exam exams) {
		this.exams = exams;
	}
	

	public Option getOptions() {
		return options;
	}

	public void setOptions(Option options) {
		this.options = options;
	}

	

}
