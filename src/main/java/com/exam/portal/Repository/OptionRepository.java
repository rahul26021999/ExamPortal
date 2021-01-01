package com.exam.portal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.exam.portal.Model.Option;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {

}
