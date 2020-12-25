package com.exam.portal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.exam.portal.Model.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

}
