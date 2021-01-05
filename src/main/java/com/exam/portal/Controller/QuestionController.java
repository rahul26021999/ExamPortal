package com.exam.portal.Controller;

import com.exam.portal.Model.Answer;
import com.exam.portal.Model.Exam;
import com.exam.portal.Model.Option;
import com.exam.portal.Model.Question;
import com.exam.portal.Repository.AnswerRepository;
import com.exam.portal.Repository.ExamRepository;
import com.exam.portal.Repository.OptionRepository;
import com.exam.portal.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
@Controller
public class QuestionController {

    @Autowired
    ExamRepository examRepo;

    @Autowired
    QuestionRepository repo;

    @Autowired
    OptionRepository optionRepo;

    @Autowired
    AnswerRepository answerRepo;

    @PostMapping("/organiser/question/add")
    public String addQuestion(@RequestParam(name = "question")String question, @RequestParam(name = "option[]") List<String> options, @RequestParam(name="exam_id")Long exam_id,@RequestParam(name = "answer")Integer answer){
        Exam exam=examRepo.findById(exam_id).get();
        Question q=new Question();
        q.setStatement(question);
        q.setExams(exam);
        repo.save(q);

        ArrayList<Option> optionList= new ArrayList<>();
        for (String option : options) {
            Option o=new Option(q, option);
            optionList.add(o);
            optionRepo.save(o);
        }

        Answer a=new Answer(q,optionList.get(answer-1));
        answerRepo.save(a);

        return "redirect:/organiser/exams/view?id="+exam_id;
    }

    @GetMapping("/organiser/question/delete")
    public String deleteQuestion(@RequestParam(name = "question_id") Long question_id,@RequestParam(name="exam_id")Long exam_id){
        Question q=repo.findById(question_id).get();
//        answerRepo.deleteById(q.getAnswer().getId());
//        optionRepo.deleteAll(q.getOptions());
        repo.delete(q);
        return "redirect:/organiser/exams/view?id="+exam_id;
    }

}
