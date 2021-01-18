package com.exam.portal.Controller;

import com.exam.portal.Model.Exam;
import com.exam.portal.Model.Organiser;
import com.exam.portal.Model.UserExam;
import com.exam.portal.OrganiserDetails;
import com.exam.portal.Repository.ExamRepository;
import com.exam.portal.Repository.UserAnswerRepository;
import com.exam.portal.Repository.UserExamRepository;
import org.dom4j.rule.Mode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.IContext;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
public class ExamController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExamController.class);

    @Autowired
    ExamRepository repo;

    @Autowired
    UserAnswerRepository userAnswerRepository;

    @Autowired
    UserExamRepository userExamRepository;

    @GetMapping("/organiser/exams")
    public String showExams(Model model){
        Object user=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user instanceof OrganiserDetails){
            Organiser org = ((OrganiserDetails) user).getOrg();
            model.addAttribute("exams",repo.findByOrganiserId(org.getId()));
            return "organiser/exam/list";
        }
        else {
            return OrganiserController.LOGIN_ROUTE;
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
            return OrganiserController.LOGIN_ROUTE;

        }
    }
    @GetMapping("/organiser/exams/edit")
    public String editExam(@RequestParam(name = "id")Long exam_id, Model model){
        Exam oldExam=repo.findById(exam_id).get();
        model.addAttribute("oldExam",oldExam);
        return "organiser/exam/edit";
    }

    @PostMapping("/organiser/exams/edit")
    public String editSaveExam(Exam exam){
        Exam exam1=repo.findById(exam.getId()).get();
        exam.setOrganisers(exam1.getOrganisers());
        repo.save(exam);
        return "redirect:/organiser/exams";
    }

    @GetMapping("/organiser/exams/view")
    public String viewExam(@RequestParam(name = "id",required = true ) Long id,Model model){
        Exam exam=repo.findById(id).get();
        model.addAttribute("exam",exam);
        return "organiser/exam/view";
    }

    @GetMapping("/organiser/exams/result")
    public String viewResult(@RequestParam(name = "id",required = true ) Long id,Model model){
        Exam exam=repo.findById(id).get();
        model.addAttribute("exam",exam);
        List<UserExam> examUsers=exam.getUserExam();

        int presentCount=userExamRepository.findPresentUsersCount(exam.getId());
        int adbsetCount=userExamRepository.findAbsentUsersCount(exam.getId());

        model.addAttribute("presentCount",presentCount);
        model.addAttribute("adbsetCount",adbsetCount);

        HashMap<Long,Integer> correctAnswers=new HashMap<>();
        HashMap<Long,Integer> incorrectAnswers=new HashMap<>();
        HashMap<Long,Integer> score=new HashMap<>();
        for (UserExam userExam:examUsers) {
            int correct=userAnswerRepository.findCorrectAnswersCount(userExam.getId());
            int incorrect=userAnswerRepository.findInCorrectAnswersCount(userExam.getId());
            correctAnswers.put(userExam.getId(),correct);
            incorrectAnswers.put(userExam.getId(),incorrect);
            score.put(userExam.getId(),exam.calculateScore(correct,incorrect));
        }
        model.addAttribute("examUsers",examUsers);
        model.addAttribute("correctAnswers",correctAnswers);
        model.addAttribute("incorrectAnswers",incorrectAnswers);
        model.addAttribute("score",score);

        return "organiser/result/list";
    }
}
