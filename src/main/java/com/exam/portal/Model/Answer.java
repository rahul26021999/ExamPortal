package com.exam.portal.Model;

import javax.persistence.*;

@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name= "queston_id", nullable=false)
    private Question questions;

    @OneToOne
    @JoinColumn(name= "option_id", nullable=false)
    private Option option;

    public Answer(Question questions, Option option) {
        this.questions = questions;
        this.option = option;
    }

    public Answer() {

    }

    public Question getQuestions() {
        return questions;
    }

    public void setQuestions(Question questions) {
        this.questions = questions;
    }

    public Option getAnswer() {
        return option;
    }

    public void setAnswer(Option answer) {
        this.option = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
