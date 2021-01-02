package com.exam.portal.Controller;

import com.exam.portal.Model.Exam;
import com.exam.portal.Model.Organiser;
import com.exam.portal.OrganiserDetails;
import com.exam.portal.Repository.ExamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.IContext;

@Controller
public class ExamController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExamController.class);

    @Autowired
    ExamRepository repo;

    @GetMapping("/organiser/exams")
    public String showExams(Model model){
        Object user=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user instanceof OrganiserDetails){
            Organiser org = ((OrganiserDetails) user).getOrg();
            model.addAttribute("exams",repo.findByOrganiserId(org.getId()));
            model.addAttribute("examsCount",org.getExams().size());
            return "organiser/exam/list";
        }
        else {
            return "redirect:/organiser/login";
        }
    }

    @GetMapping("/organiser/exams/create")
    public String showCreateExam(Model model){
        model.addAttribute("exam",new Exam());
        return "organiser/exam/create";
    }

    @PostMapping("/organiser/exams/create")
    public String createExam(Exam exam){
        Object user=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user instanceof OrganiserDetails){
            exam.setOrganisers(((OrganiserDetails) user).getOrg());
            repo.save(exam);
            return "redirect:/organiser/exams";            
        }
        else{
            return "redirect:/organiser/login";
        }
    }

    @GetMapping("/organiser/exams/view")
    public String viewExam(@RequestParam(name = "id",required = true ) String id,Model model){
        // get exam from id and pass to view
        return "organiser/exam/view";
    }
}
