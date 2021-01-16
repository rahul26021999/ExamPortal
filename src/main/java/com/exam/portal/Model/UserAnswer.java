package com.exam.portal.Model;

import javax.persistence.*;

@Entity
@Table(name = "user_answer")
public class UserAnswer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name= "queston_id", nullable=false)
    private Question questions;

    @OneToOne
    @JoinColumn(name= "option_id", nullable=false)
    private Option answer;

    @OneToOne
    @JoinColumn(name= "user_id", nullable=false)
    private User user;

    public Question getQuestions() {
        return questions;
    }

    public void setQuestions(Question questions) {
        this.questions = questions;
    }

    public Option getAnswer() {
        return answer;
    }

    public void setAnswer(Option answer) {
        this.answer = answer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
