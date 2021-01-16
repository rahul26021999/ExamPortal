package com.exam.portal.Repository;

import com.exam.portal.Model.Organiser;
import com.exam.portal.Model.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    @Query("SELECT u FROM UserAnswer u WHERE u.user.id = ?1 AND u.questions.id=?2")
    UserAnswer findByUserQuestion(Long user_id,Long question_id);
}
