package com.exam.portal.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_exams")
public class UserExam {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "password")
    private String password;

    @ManyToOne(targetEntity = Exam.class)
    @JoinColumn(name= "exam_id", nullable=false)
    private Exam exams;

    public UserExam(User user, Exam exam,String password) {
        this.exams=exam;
        this.user=user;
        this.password=password;
    }

    public UserExam() {

    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name= "user_id", nullable=false)
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Exam getExams() {
        return exams;
    }

    public void setExams(Exam exams) {
        this.exams = exams;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
