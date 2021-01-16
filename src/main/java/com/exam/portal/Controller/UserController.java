package com.exam.portal.Controller;

import com.exam.portal.Model.*;
import com.exam.portal.Repository.*;
import com.exam.portal.Utils.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import java.util.*;

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
                    return "redirect:/"+examCode+"/instruction";
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

    @GetMapping("user/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("{examcode}/instruction")
    public String showInstructionPage(Model model,@PathVariable(name = "examcode")String examCode,HttpSession session){
        try{
            if(checkValidExamCode(examCode)){
                if(isLoggedInForExam(session,examCode)){
                    long exam_id= Long.parseLong(examCode.split("-")[1]);
                    Exam exam=examRepository.findById(exam_id).get();
                    model.addAttribute("exam",exam);
                    return "user/instruction";
                }else{
                    throw new Exception();
                }
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            return "redirect:/"+examCode+"/login";
        }
    }

    private boolean isLoggedInForExam(HttpSession session, String examCode) {

        long exam_id= Long.parseLong(examCode.split("-")[1]);
        Long session_user_id= (Long) session.getAttribute("user_id");
        Long session_exam_id= (Long) session.getAttribute("exam_id");

        //Check User is logged in For current Exam Code
        if(exam_id != session_exam_id)
            return false;

        //Check User exists or not
        if(userRepo.findById(session_user_id).isEmpty())
            return false;

        //Check User Authorization for Current Exam
        UserExam userExam=userExamRepository.findUserExamByUser(session_exam_id,session_user_id);
        if(userExam==null)
            return false;

        return true;
    }

    @GetMapping(value = {"/{examCode}/exam", "/{examCode}/exam/{questionID}"})
    public String showUserDashboard(HttpSession session, @PathVariable(name = "examCode")String examCode, Model model,@PathVariable(name = "questionID",required = false)String question_id){
        try{
            if(checkValidExamCode(examCode)){
                if(isLoggedInForExam(session,examCode)){
                    Long user_id= (Long) session.getAttribute("user_id");

                    long exam_id= Long.parseLong(examCode.split("-")[1]);
                    Exam exam=examRepository.findById(exam_id).get();

                    if(question_id==null)
                        question_id=exam.getQuestions().get(0).getId().toString();

                    Long q_id=Long.parseLong(question_id);
                    Question currentQuestion=questionRepository.findById(q_id).get();

                    //Getting Answers
                    List<Question> all_questions = exam.getQuestions();
                    HashMap<Long, Long> answers=new HashMap<>();
                    for (Question question:all_questions) {
                        UserAnswer userAnswer=userAnswerRepository.findByUserQuestion(user_id,question.getId());
                        if(userAnswer!=null){
                            answers.put(question.getId(),userAnswer.getAnswer().getId());
                        }
                    }
                    model.addAttribute("answers",answers);
                    model.addAttribute("question",currentQuestion);
                    model.addAttribute("exam",exam);
                    return "user/dashboard";
                }else{
                    throw new Exception();
                }
            }else{
                throw new Exception();
            }
        }
        catch (Exception e){
            return "redirect:/"+examCode+"/login?"+e.getMessage();
        }
    }

    @PostMapping("{examcode}/submit")
    public String saveAnswers(HttpSession session,@PathVariable(name = "examcode")String examcode,@RequestParam(name = "answer_id",required = false)Long answer_id,@RequestParam(name = "question_id")Long question_id){
        try{
            if(checkValidExamCode(examcode)){
                if(isLoggedInForExam(session,examcode)){

                    Long user_id= (Long) session.getAttribute("user_id");
                    Long exam_id= Long.parseLong(examcode.split("-")[1]);
                    Exam exam = examRepository.findById(exam_id).get();

                    UserAnswer userAnswer = userAnswerRepository.findByUserQuestion(user_id, question_id);
                    if(answer_id==null){
                        if(userAnswer!=null)
                            userAnswerRepository.delete(userAnswer);
                    }else{
                        if(userAnswer==null){
                            userAnswer = new UserAnswer();
                        }
                        User user = userRepo.findById(user_id).get();
                        Question question=questionRepository.findById(question_id).get();
                        Option answer = optionRepository.findById(answer_id).get();
                        userAnswer.setAnswer(answer);
                        userAnswer.setUser(user);
                        userAnswer.setQuestions(question);
                        userAnswerRepository.save(userAnswer);
                    }
                    return "redirect:/"+examcode+"/exam/"+exam.getNextQuestionID(question_id);
                }else{
                    throw new Exception();
                }
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            return "redirect:/"+examcode+"/exam?"+e.getMessage();
        }
    }
}
