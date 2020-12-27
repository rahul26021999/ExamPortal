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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.mapping.Set;

@Entity
@Table(name = "exams")
public class Exam {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false,length=50)
	private String title;
	
	@Column(length=100)
	private String description;
	
	@Column(length=150)
	private String	instructions;
	
	@Column(nullable=false,length=5)
	private int marksOfEachQuestion ;
	
	@Column(nullable=false,length=5)
	private int timeOfEachQuestion;
	
	@Column(length=5)
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
	@OneToMany(mappedBy="exams", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Question> questions;
	


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

	public int getTimeOfEachQuestion() {
		return timeOfEachQuestion;
	}

	public void setTimeOfEachQuestion(int timeOfEachQuestion) {
		this.timeOfEachQuestion = timeOfEachQuestion;
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
	
	

}
