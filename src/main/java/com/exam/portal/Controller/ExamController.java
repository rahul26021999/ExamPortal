package com.exam.portal.Controller;

import com.exam.portal.Model.Exam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExamController {

    @GetMapping("/organiser/exams")
    public String showExams(Model model){
        // Give me a list of exams created by logged in User
        return "organiser/exam/list";
    }

    @GetMapping("/organiser/exams/create")
    public String showCreateExam(Model model){
        //return empty exam model
        return "organiser/exam/create";
    }

    @PostMapping("/organiser/exams/create")
    public String createExam(Exam exam){
        // create exam functionality here get model and save exam model
        return "redirect:/organiser/exams";
    }

    @GetMapping("/organiser/exams/view")
    public String viewExam(@RequestParam(name = "id",required = true ) String id,Model model){
        // get exam from id and pass to view
        return "organiser/exam/view";
    }
}
