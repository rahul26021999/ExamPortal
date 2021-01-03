package com.exam.portal.Controller;

import com.exam.portal.Model.Exam;
import com.exam.portal.Model.Question;
import com.exam.portal.Repository.ExamRepository;
import com.exam.portal.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class QuestionController {

    @Autowired
    ExamRepository examRepo;

    @Autowired
    QuestionRepository repo;

    @PostMapping("/organiser/question/add")
    public String addQuestion(@RequestParam(name = "question")String question, @RequestParam(name = "option[]") List<String> options, @RequestParam(name="exam_id")Long exam_id){
        Exam exam=examRepo.findById(exam_id).get();
        Question q=new Question();
        q.setStatement(question);
        q.setExams(exam);
        repo.save(q);
        return "redirect:/organsier/exam/view";
    }
}
