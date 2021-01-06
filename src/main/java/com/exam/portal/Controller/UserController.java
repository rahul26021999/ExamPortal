package com.exam.portal.Controller;

import com.exam.portal.Model.Exam;
import com.exam.portal.Model.User;
import com.exam.portal.Model.UserExam;
import com.exam.portal.Repository.ExamRepository;
import com.exam.portal.Repository.UserExamRepository;
import com.exam.portal.Repository.UserRepository;
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

import java.net.http.HttpResponse;

@Controller
public class UserController {


    @Autowired
    UserRepository userRepo;

    @Autowired
    ExamRepository examRepository;

    @Autowired
    UserExamRepository userExamRepository;

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
    @PostMapping("{examCode}/login")
    public String loginUser(@PathVariable(name = "examCode")String examCode,@RequestParam(name = "email")String email,Model model){
        String redirectUrl="redirect:/"+examCode+"/login";
        Long exam_id= Long.valueOf(examCode.split("-")[1]);
        if(examRepository.findById(exam_id).isPresent()){
            Exam exam=examRepository.findById(exam_id).get();
            if(exam.getExamCode().equals(examCode)){
                User user=userRepo.findByEmail(email);
                if(user==null){
                    model.addAttribute("error","Invalid User.User not found");
                }else {
                    UserExam userExam=userExamRepository.findUserExamByUser(exam_id,user.getId());
                    if(userExam==null){
                        model.addAttribute("error","User Not Authorised to give this exam");
                    }else{
                        //got to Exam page
                        //set redirect url
                        //create session and all things for user
                    }
                }
            }else{
                model.addAttribute("error","Invalid Exam Code.Please check Carefully");
            }
        }else{
            model.addAttribute("error","Invalid Exam Code.Please check Carefully");
        }
        return redirectUrl;
    }
    @GetMapping("{examCode}/login")
    public String showLogin(@PathVariable(name = "examCode")String examCode,Model model){
        try{
            Long exam_id= Long.valueOf(examCode.split("-")[1]);
            if(examRepository.findById(exam_id).isPresent()){
                Exam exam=examRepository.findById(exam_id).get();
                if(exam.getExamCode().equals(examCode)){
                    model.addAttribute("exam",exam);
                    return "user/login";
                }else{
                    throw new Exception();
                }
            }else{
                throw new Exception();
            }
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
