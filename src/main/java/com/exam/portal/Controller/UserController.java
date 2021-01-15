package com.exam.portal.Controller;

import com.exam.portal.Model.*;
import com.exam.portal.Repository.*;
import com.exam.portal.Utils.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller
public class UserController {


    @Autowired
    UserRepository userRepo;

    @Autowired
    ExamRepository examRepository;

    @Autowired
    UserExamRepository userExamRepository;

    @Autowired
    UserAnswerRepository userAnswerRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    OptionRepository optionRepository;

    @PostMapping("/organiser/user/add")
    public String addUser(@RequestParam(name = "exam_id")Long exam_id,@RequestParam(name="name")String name,@RequestParam(name="email")String email){
        Exam exam=examRepository.findById(exam_id).get();
        User user=userRepo.findByEmail(email);
        if(user==null){
            user=new User(email,name);
        }else{
            user.setName(name);
        }
        userRepo.save(user);
        String password= RandomString.getAlphaNumericString(8);
        UserExam userExam=new UserExam(user,exam,password);
        userExamRepository.save(userExam);

        return "redirect:/organiser/exams/view?id="+exam_id;
    }

    @GetMapping("organsier/user/delete")
    public String deleteUser(@RequestParam(name = "id")Long userExamId){
        UserExam userExam=userExamRepository.findById(userExamId).get();
        Long exam_id=userExam.getExams().getId();
        userExamRepository.delete(userExam);
        return "redirect:/organiser/exams/view?id="+exam_id;
    }

    public boolean checkValidExamCode(String examCode){
        Long exam_id= Long.valueOf(examCode.split("-")[1]);
        if(examRepository.findById(exam_id).isPresent()){
            Exam exam=examRepository.findById(exam_id).get();
            return exam.getExamCode().equals(examCode);
        }else{
            return false;
        }
    }

    @PostMapping("{examCode}/login")
    public String loginUser(@PathVariable(name = "examCode")String examCode,@RequestParam(name = "email")String email,@RequestParam(name = "password")String password,Model model,HttpSession session){
        String redirectUrl="redirect:/"+examCode+"/login";
        try{
            if(checkValidExamCode(examCode)){
                User user=userRepo.findByEmail(email);
                if(user==null){
                    redirectUrl+="?error=1";
                    throw new Exception();
                }

                Long exam_id= Long.valueOf(examCode.split("-")[1]);
                UserExam userExam=userExamRepository.findUserExamByUser(exam_id,user.getId());
                if(userExam==null){
                    redirectUrl+="?error=2";
                    throw new Exception();
                }else if(userExam.getPassword().equals(password)){
                    session.setAttribute("user_id",user.getId());
                    session.setAttribute("exam_id",exam_id);
                    return "redirect:/"+examCode+"/exam";
                }else{
                    redirectUrl+="?error=1";
                    throw new Exception();
                }
            }else{
                throw new Exception();
            }
        }catch(Exception e){
            return redirectUrl;
        }
    }

    @GetMapping("{examCode}/login")
    public String showLogin(HttpSession session, @PathVariable(name = "examCode")String examCode, Model model){
        try{
            if(checkValidExamCode(examCode)){
                Long exam_id= Long.valueOf(examCode.split("-")[1]);
                Exam exam=examRepository.findById(exam_id).get();
                model.addAttribute("exam",exam);
                return "user/login";
            }else{
                throw new Exception();
            }
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{examCode}/exam")
    public String showUserDashboard(HttpSession session, @PathVariable(name = "examCode")String examCode, Model model){
        try{
            if(checkValidExamCode(examCode)){
                long exam_id= Long.parseLong(examCode.split("-")[1]);
                // get user_id and exam_id from Session
                Long session_user_id= (Long) session.getAttribute("user_id");
                Long session_exam_id= (Long) session.getAttribute("exam_id");

                if(exam_id != session_exam_id)
                    throw new Exception();

                if(userRepo.findById(session_user_id).isEmpty())
                    throw new Exception();

                UserExam userExam=userExamRepository.findUserExamByUser(session_exam_id,session_user_id);
                if(userExam==null)
                    throw new Exception();

                Exam exam=examRepository.findById(exam_id).get();
                model.addAttribute("exam",exam);
                //More check if exam has started or not and all instruction page and all everything
                return "user/dashboard";
            }else{
                throw new Exception();
            }
        }
        catch (Exception e){
            return "redirect:/"+examCode+"/login";
        }
    }

    public String saveAnswers(){
//        Option answer=optionRepository.findById(option_id).get();
//        Question question=questionRepository.findById(question_id).get();
//        UserAnswer userAnswer=new UserAnswer();
//        userAnswer.setAnswer(answer);
//        userAnswer.setQuestions(question);
//        userExamRepository.save(userAnswer);

        return "";
    }
}
