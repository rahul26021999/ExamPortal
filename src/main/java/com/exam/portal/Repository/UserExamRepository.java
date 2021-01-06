package com.exam.portal.Repository;

import com.exam.portal.Model.UserExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserExamRepository extends JpaRepository<UserExam, Long> {

    @Query("select u from UserExam u where u.exams.id = ?1 and u.user.id = ?2")
    UserExam findUserExamByUser(Long exam_id,Long user_id);
}
