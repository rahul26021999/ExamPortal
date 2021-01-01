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
    public String showExams(){
        return "organiser/exam/list";
    }

    @GetMapping("/organiser/exams/create")
    public String showCreateExam(@RequestParam(name = "step",defaultValue ="1") String step,Model model){
        model.addAttribute("step",step);
        return "organiser/exam/create";
    }

    @PostMapping("/organiser/exams/create")
    public String createExam(){
//        create exam functionality here
        return "redirect:/organiser/exams";
    }

    @GetMapping("/organiser/exams/view")
    public String viewExam(@RequestParam(name = "id",required = true ) String id,Model model){
        // get exam from id and pass here
        Exam exam=new Exam();// get exam from id here
        model.addAttribute("exam",exam);
        return "organiser/exam/view";
    }
}
