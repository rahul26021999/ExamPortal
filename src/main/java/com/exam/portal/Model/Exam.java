package com.exam.portal.Model;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "exams")
public class Exam {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false,length=50,name = "title")
	private String title;
	
	@Column(length=100 ,name = "description")
	private String description;
	
	@Column(length=150,name = "instructions")
	private String	instructions;

	@DateTimeFormat(pattern = "dd/MM/yyyy h:mm a")
	@Column(nullable = false,name = "start_date")
	private Date startDate;
	
	@Column(nullable=false,length=5,name = "marks")
	private int marksOfEachQuestion ;
	
	@Column(nullable=false,length=5,name = "time")
	private int examTime;
	
	@Column(length=5,name = "negative_marks",nullable = true)
	private int negativeMarkOfEachQuestion;
	
	
	/*
	 * foreign key
	 * many Exam paper will be given by one organiser
	 */
	@ManyToOne
	@JoinColumn(name= "organiser_id", nullable= false)
	private Organiser organisers;
	
	
	/*
	 * exam id is the foreign key in the question table
	 * one exam will contain many questions.
	 */
	@OneToMany(mappedBy="exams", cascade = CascadeType.ALL)
	private List<Question> questions= new ArrayList<Question>();



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public int getMarksOfEachQuestion() {
		return marksOfEachQuestion;
	}

	public void setMarksOfEachQuestion(int marksOfEachQuestion) {
		this.marksOfEachQuestion = marksOfEachQuestion;
	}

	public int getExamTime() {
		return examTime;
	}

	public void setExamTime(int timeOfEachQuestion) {
		this.examTime = timeOfEachQuestion;
	}

	public int getNegativeMarkOfEachQuestion() {
		return negativeMarkOfEachQuestion;
	}

	public void setNegativeMarkOfEachQuestion(int negativeMarkOfEachQuestion) {
		this.negativeMarkOfEachQuestion = negativeMarkOfEachQuestion;
	}

	public Organiser getOrganisers() {
		return organisers;
	}

	public void setOrganisers(Organiser organisers) {
		this.organisers = organisers;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@OneToMany(mappedBy="exams", cascade = CascadeType.ALL, fetch = FetchType.EAGER,targetEntity = Question.class)
	public List<Question> getQuestions() {
		return questions;
	}
}
