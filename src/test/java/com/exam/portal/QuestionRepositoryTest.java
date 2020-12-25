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
import com.exam.portal.Model.Question;
import com.exam.portal.Repository.QuestionRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class QuestionRepositoryTest {

	
	@Autowired
	private QuestionRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateQuestion()
	{
		Question question= new Question();
		
		question.setStatement("find the area of rectangle having length= 5 and breadth= 4");
		question.setOption1("20");
		question.setOption2("40");
		question.setOption3("5");
		question.setOption4("10");
		question.setCorrectOption(1);
		
		Question savedQuestion= repo.save(question);
		Question existQuestion= entityManager.find(Question.class, savedQuestion.getId());
		
		assertThat(existQuestion.getId()).isEqualTo(question.getId());
		
	}
}
