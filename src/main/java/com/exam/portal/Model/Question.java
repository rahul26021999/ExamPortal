package com.exam.portal.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "questions")
public class Question {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false,length=300)
	private String statement;

	
	/*
	 * foreign key
	 * many question will be connected to one exam
	 * 
	 */
	@ManyToOne
	@JoinColumn(name= "exam_id", nullable=false)
	private Exam exams;


	@OneToMany(mappedBy="questions", cascade = CascadeType.REMOVE,orphanRemoval = true)
	private List<Option> options= new ArrayList<Option>();

	@OneToOne(mappedBy="questions", cascade = CascadeType.REMOVE,orphanRemoval = true)
	private Answer answer;


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

	public List<Option> getOptions() {
		return options;
	}


	public Answer getAnswer() {
		return answer;
	}
}
