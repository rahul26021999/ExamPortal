package com.exam.portal;

import static org.assertj.core.api.Assertions.assertThat;

import com.exam.portal.Model.Option;
import com.exam.portal.Model.Question;
import com.exam.portal.Repository.OptionRepository;
import com.exam.portal.Repository.OrganiserRepository;
import com.exam.portal.Repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.exam.portal.Model.Exam;
import com.exam.portal.Model.Organiser;
import com.exam.portal.Repository.ExamRepository;

import java.util.Date;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ExamRepositoryTest {

	@Autowired
	private ExamRepository repo;

	@Autowired
	private OrganiserRepository OrgRepo;

	@Autowired
	private OptionRepository optionRepo;

	@Autowired
	private QuestionRepository questionRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testExamCreate()
	{
		Organiser org=new Organiser();
		org.setEmail("rahul@gmail.com");
		org.setName("Rahul");
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		String encodedPassword=encoder.encode("password");
		org.setPassword(encodedPassword);
		OrgRepo.save(org);

		Exam exam= new Exam();
		exam.setTitle("Math");
		exam.setDescription("It is for class 10th.");
		exam.setInstructions("There will be negative marking");
		exam.setMarksOfEachQuestion(4);
		exam.setNegativeMarkOfEachQuestion(1);
		exam.setExamTime(2);
		exam.setStartDate(new Date());
		exam.setOrganisers(org);
		
		Exam savedExam= repo.save(exam);

		Question question= new Question();
		question.setStatement("find the area of rectangle having length= 5 and breadth= 4");
		question.setExams(savedExam);
		Question savedQuestion= questionRepo.save(question);

		//first option
		Option option=new Option();
		option.setOption("hello");
		option.setQuestions(savedQuestion);
		optionRepo.save(option);

		//second option
		option=new Option();
		option.setOption("hello1");
		option.setQuestions(savedQuestion);
		optionRepo.save(option);



		assertThat(true);
	}
	
}

