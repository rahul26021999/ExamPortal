package com.exam.portal.Repository;

import com.exam.portal.Model.UserExam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExamRepository extends JpaRepository<UserExam, Long> {
}
