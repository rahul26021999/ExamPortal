package com.exam.portal;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.exam.portal.Model.Exam;
import com.exam.portal.Model.Organiser;
import com.exam.portal.Repository.ExamRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ExamRepositoryTest {

	@Autowired
	private ExamRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testExamCreater()
	{
		Exam exam= new Exam();
		
		exam.setId(12345L);
		exam.setTitle("Math");
		exam.setDescription("It is for class 10th.");
		exam.setInstructions("There will be negative marking");
		exam.setMarksOfEachQuestion(4);
		exam.setNegativeMarkOfEachQuestion(1);
		exam.setTimeOfEachQuestion(2);
//		exam.setOrganisers(new Organiser());
		
		Exam savedExam= repo.save(exam);
		Exam existExam= entityManager.find(Exam.class, savedExam.getId());	
		
		assertThat(existExam.getId()).isEqualTo(exam.getId());
	}
	
}

